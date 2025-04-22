package br.com.invest7.ProjetoInvest7.controller;

import br.com.invest7.ProjetoInvest7.controller.request.CadastrarUsuarioRequest;
import br.com.invest7.ProjetoInvest7.controller.response.CadastrarUsuarioResponse;
import br.com.invest7.ProjetoInvest7.entity.Usuario;
import br.com.invest7.ProjetoInvest7.mapper.UsuarioMapper;
import br.com.invest7.ProjetoInvest7.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/user")
@RestController
public class UsuarioController {
    private final UsuarioService usuarioService;

   public UsuarioController(UsuarioService usuarioService){
       this.usuarioService =usuarioService;
   }

    @PostMapping("/cadastrar")
    public CadastrarUsuarioResponse cadastrar(@RequestBody @Valid CadastrarUsuarioRequest usuarioRequest){
        Usuario usuario = UsuarioMapper.map(usuarioRequest);
        usuario = usuarioService.cadastrarUsuario(usuario);

        return UsuarioMapper.map(usuario);
    }
}
