package br.com.invest7.ProjetoInvest7.controller;

import br.com.invest7.ProjetoInvest7.controller.request.LoginRequest;
import br.com.invest7.ProjetoInvest7.exception.EntradaErrorExeception;
import br.com.invest7.ProjetoInvest7.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/login")
@RestController
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = usuarioService.autenticar(loginRequest);
            return ResponseEntity.ok().body(Map.of("token", token));
        } catch (EntradaErrorExeception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
