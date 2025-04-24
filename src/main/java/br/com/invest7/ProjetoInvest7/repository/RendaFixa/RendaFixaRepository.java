package br.com.invest7.ProjetoInvest7.repository.RendaFixa;

import br.com.invest7.ProjetoInvest7.entity.RendaFixa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RendaFixaRepository extends MongoRepository<RendaFixa, String> {
}
