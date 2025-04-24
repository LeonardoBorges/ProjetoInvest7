package br.com.invest7.ProjetoInvest7.repository.Acoes;

import br.com.invest7.ProjetoInvest7.entity.Acoes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcoesRepository extends MongoRepository<Acoes, String> {
}
