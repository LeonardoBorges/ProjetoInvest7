package br.com.invest7.ProjetoInvest7.service;

import br.com.invest7.ProjetoInvest7.entity.Usuario;
import br.com.invest7.ProjetoInvest7.repository.Usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Usuario cadastrarUsuario(Usuario usuario){
        String senhaHash = this.bCryptPasswordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaHash);
        return this.usuarioRepository.save(usuario);
    }
}
