package br.com.invest7.ProjetoInvest7.mapper;


import br.com.invest7.ProjetoInvest7.controller.request.AtualizarUsuarioRequest;
import br.com.invest7.ProjetoInvest7.controller.request.CadastrarUsuarioRequest;
import br.com.invest7.ProjetoInvest7.controller.response.CadastrarUsuarioResponse;
import br.com.invest7.ProjetoInvest7.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public static Usuario map(CadastrarUsuarioRequest usuarioRequest){
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequest.getNome());
        usuario.setEndereco(usuarioRequest.getEndereco());
        usuario.setCpf(usuarioRequest.getCpf());
        usuario.setCep(usuarioRequest.getCep());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setSenha(usuarioRequest.getSenha());
        usuario.setDt_nasc(usuarioRequest.getDt_nasc());
        usuario.setGenero(usuarioRequest.getGenero());
        usuario.setIdPerfil(usuarioRequest.getIdPerfil());
        return usuario;
    }
/*
    public static CadastrarUsuarioResponse map(Usuario usuario){
        CadastrarUsuarioResponse usuarioResponse = new CadastrarUsuarioResponse();
        usuarioResponse.setId(usuario.getId_user());
        return usuarioResponse;
    }

 */

    public static Usuario map(AtualizarUsuarioRequest atualizarUsuarioRequest){
        Usuario usuario = new Usuario();
        usuario.setNome(atualizarUsuarioRequest.getNome());
        usuario.setEndereco(atualizarUsuarioRequest.getEndereco());
        usuario.setCep(atualizarUsuarioRequest.getCep());
        usuario.setGenero(atualizarUsuarioRequest.getGenero());
        usuario.setDt_nasc(atualizarUsuarioRequest.getDt_nasc());
        return usuario;
    }
}
