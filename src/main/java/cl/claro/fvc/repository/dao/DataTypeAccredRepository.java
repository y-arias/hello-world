package cl.claro.fvc.repository.dao;

import cl.claro.fvc.repository.document.DataTypeAccredDocument;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface DataTypeAccredRepository extends ReactiveMongoRepository<DataTypeAccredDocument, String> {

   @Query("{$and : [{$or : [{ 'indcActBiometria' : 'A' }, {'indcActBiometria' : ?0}]}, {'codiTipoCliente' : ?1}]}")
   Flux<DataTypeAccredDocument> findConfiguredAccreditationByBiometricIndAndCustomerType(String indcActBiometria,
                                                                                         String customerType);
}
