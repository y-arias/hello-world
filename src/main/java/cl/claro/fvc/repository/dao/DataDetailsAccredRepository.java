package cl.claro.fvc.repository.dao;

import cl.claro.fvc.repository.document.DataDetailsAccredDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataDetailsAccredRepository extends ReactiveMongoRepository<DataDetailsAccredDocument, String> {
}
