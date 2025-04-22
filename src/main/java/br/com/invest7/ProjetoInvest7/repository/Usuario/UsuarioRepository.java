package br.com.invest7.ProjetoInvest7.repository.Usuario;


import br.com.invest7.ProjetoInvest7.entity.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
}
