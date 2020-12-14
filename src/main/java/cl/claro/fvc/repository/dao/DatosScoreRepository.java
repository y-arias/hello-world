package cl.claro.fvc.repository.dao;

import cl.claro.fvc.repository.document.DatosCreditScoreDocument;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface DatosScoreRepository extends ReactiveMongoRepository<DatosCreditScoreDocument, String> {

    @Query("{ 'orderId' : ?0 }")
    Mono<DatosCreditScoreDocument> findByOrderId(String idEvaluation);
}
