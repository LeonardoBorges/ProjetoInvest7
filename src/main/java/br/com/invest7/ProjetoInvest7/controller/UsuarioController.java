package br.com.invest7.ProjetoInvest7.controller;

import br.com.invest7.ProjetoInvest7.controller.request.AtualizarUsuarioRequest;
import br.com.invest7.ProjetoInvest7.controller.request.CadastrarUsuarioRequest;
import br.com.invest7.ProjetoInvest7.controller.request.LoginRequest;
import br.com.invest7.ProjetoInvest7.controller.response.CadastrarUsuarioResponse;
import br.com.invest7.ProjetoInvest7.entity.Usuario;
import br.com.invest7.ProjetoInvest7.exception.EntradaErrorExeception;
import br.com.invest7.ProjetoInvest7.mapper.UsuarioMapper;
import br.com.invest7.ProjetoInvest7.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RequestMapping("/user")
@RestController
public class UsuarioController {
    private final UsuarioService usuarioService;

   public UsuarioController(UsuarioService usuarioService){
       this.usuarioService =usuarioService;
   }

    @PostMapping()
    public CadastrarUsuarioResponse cadastrar(@RequestBody @Valid CadastrarUsuarioRequest usuarioRequest){
        Usuario usuario = UsuarioMapper.map(usuarioRequest);
        usuario = usuarioService.cadastrarUsuario(usuario);

        return UsuarioMapper.map(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarIndividual(@PathVariable String id){
        try {
            return new ResponseEntity<Usuario>(usuarioService.buscarIndividual(id), HttpStatus.OK);
        } catch (EntradaErrorExeception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable String id, @RequestBody AtualizarUsuarioRequest atualizarUsuarioRequest) {
        try {
            Usuario usuario = UsuarioMapper.map(atualizarUsuarioRequest);
            usuarioService.updateUsuario(id, usuario);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (EntradaErrorExeception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
