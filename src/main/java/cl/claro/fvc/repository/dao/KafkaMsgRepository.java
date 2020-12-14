package cl.claro.fvc.repository.dao;

import cl.claro.fvc.repository.document.RspDataScoreDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KafkaMsgRepository extends ReactiveMongoRepository <RspDataScoreDocument,String> {
}
