package br.com.invest7.ProjetoInvest7.service;

import br.com.invest7.ProjetoInvest7.client.ViaCepClient;
import br.com.invest7.ProjetoInvest7.client.response.ViaCepEnderecoResponse;
import br.com.invest7.ProjetoInvest7.controller.request.LoginRequest;
import br.com.invest7.ProjetoInvest7.controller.response.CadastrarUsuarioResponse;
import br.com.invest7.ProjetoInvest7.controller.response.LoginResponse;
import br.com.invest7.ProjetoInvest7.entity.Usuario;
import br.com.invest7.ProjetoInvest7.exception.EntradaErrorExeception;
import br.com.invest7.ProjetoInvest7.exception.UsuarioNaoEncontratoException;
import br.com.invest7.ProjetoInvest7.repository.Usuario.UsuarioRepository;
import br.com.invest7.ProjetoInvest7.util.CpfValidate;
import br.com.invest7.ProjetoInvest7.util.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ViaCepClient viaCepClient;
    private final JwtUtil jwtUtil;


    //controller
    public UsuarioService(UsuarioRepository usuarioRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder,
                          ViaCepClient viaCepClient, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.viaCepClient = viaCepClient;
        this.jwtUtil = jwtUtil;
    }



    public CadastrarUsuarioResponse cadastrarUsuario(Usuario usuario){
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
        this.usuarioRepository.save(usuario);
        String token = jwtUtil.generateToken(usuario);
        return new CadastrarUsuarioResponse(token, usuario.getId(), usuario.getNome());

    }

    public Usuario buscarIndividual(String id){

            return usuarioRepository.findById(id).orElseThrow();
    }

    public void updateUsuario(String id, Usuario usuario){
        Usuario updateUsuario = this.usuarioRepository.uptadeUsuario(id, usuario);
        if(null == updateUsuario) throw new UsuarioNaoEncontratoException();
    }

    public LoginResponse autenticar(LoginRequest loginRequest) {
        Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new EntradaErrorExeception("Email não encontrado"));

        if (!bCryptPasswordEncoder.matches(loginRequest.getSenha(), usuario.getSenha())) {
            throw new EntradaErrorExeception("Senha inválida");
        }

        String token = jwtUtil.generateToken(usuario);
        return new LoginResponse(token, usuario.getId(), usuario.getNome());
    }

}
