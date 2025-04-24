package br.com.invest7.ProjetoInvest7.repository.Fiis;

import br.com.invest7.ProjetoInvest7.entity.Fiis;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiisRepository extends MongoRepository<Fiis, String> {
}
