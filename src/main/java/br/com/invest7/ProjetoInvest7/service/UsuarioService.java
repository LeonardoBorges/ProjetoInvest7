package br.com.invest7.ProjetoInvest7.service;

import br.com.invest7.ProjetoInvest7.client.ViaCepClient;
import br.com.invest7.ProjetoInvest7.client.response.ViaCepEnderecoResponse;
import br.com.invest7.ProjetoInvest7.entity.Usuario;
import br.com.invest7.ProjetoInvest7.exception.EntradaErrorExeception;
import br.com.invest7.ProjetoInvest7.exception.UsuarioNaoEncontratoException;
import br.com.invest7.ProjetoInvest7.repository.Usuario.UsuarioRepository;
import br.com.invest7.ProjetoInvest7.util.CpfValidate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ViaCepClient viaCepClient;

    //controller
    public UsuarioService(UsuarioRepository usuarioRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder,
                            ViaCepClient viaCepClient) {
        this.usuarioRepository = usuarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.viaCepClient = viaCepClient;
    }



    public Usuario cadastrarUsuario(Usuario usuario){
        String senhaHash = this.bCryptPasswordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaHash);
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new EntradaErrorExeception("E-mail já cadastrado.");
        }

        if (usuarioRepository.existsByCpf(usuario.getCpf())) {
            throw new EntradaErrorExeception("CPF já cadastrado.");
        }

        String cpfValido = CpfValidate.validaCpf(usuario.getCpf());
        if(cpfValido == null){
            throw new EntradaErrorExeception("CPF invalido");
        }

        ViaCepEnderecoResponse buscaCep = viaCepClient.buscarEnderecoPorCep(usuario.getCep());
        // Preenche os dados do usuário com as informações do endereço
        usuario.setCep(buscaCep.getLogradouro() + ", " +
                buscaCep.getBairro() + ", " +
                buscaCep.getLocalidade() + " - " +
                buscaCep.getUf());

        return this.usuarioRepository.save(usuario);
    }

    public Usuario buscarIndividual(String id){

            return usuarioRepository.findById(id).orElseThrow();
    }

    public void updateUsuario(String id, Usuario usuario){
        Usuario updateUsuario = this.usuarioRepository.uptadeUsuario(id, usuario);
        if(null == updateUsuario) throw new UsuarioNaoEncontratoException();
    }

}
