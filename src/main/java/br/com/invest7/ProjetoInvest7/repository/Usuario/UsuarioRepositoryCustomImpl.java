package br.com.invest7.ProjetoInvest7.repository.Usuario;

import br.com.invest7.ProjetoInvest7.entity.Usuario;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Query;


public class UsuarioRepositoryCustomImpl implements UsuarioRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    public UsuarioRepositoryCustomImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;

    }

    @Override
    public Usuario uptadeUsuario(String id, Usuario usuario){
        Update update = new Update();

        if (usuario.getNome() != null){
            update.set("nome", usuario.getNome());
        }

        if (usuario.getEndereco()!=null){
            update.set("endereco", usuario.getEndereco());

        }
        if (usuario.getCep() != null) {
            update.set("cep", usuario.getCep());
        }

        if (usuario.getGenero() != null) {
            update.set("genero", usuario.getGenero());
        }

        if (usuario.getDt_nasc() != null) {
            update.set("dt_nasc", usuario.getDt_nasc());
        }

        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findAndModify(query, update, Usuario.class);

    }

}
