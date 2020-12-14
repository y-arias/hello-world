package cl.claro.fvc.repository.dao;

import cl.claro.fvc.repository.document.DataTypeAccredDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by ChristianCarmonaQuin on 23-01-2020.
 */
public interface DataTypeAccredSimpleRepository extends MongoRepository<DataTypeAccredDocument, String> {
}
