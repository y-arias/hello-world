package cl.claro.fvc.repository.event;

import cl.claro.fvc.util.Constantes;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Log4j2
@Component
public class KafkaMsgProducer {

  private KafkaTemplate<String, String> kafkaTemplateRequest;
  private Environment env;

  @Value(value = "${message.topic.name:profesorp}")
  private String topicName;


  @Autowired
  public KafkaMsgProducer(KafkaTemplate kafkaTemplateRequest) {
    this.kafkaTemplateRequest = kafkaTemplateRequest;
  }

  public void sendMessageRequest(String topic, String message) {
    if (topic == null || ("").equals(topic.trim())) {
      topic = topicName;
    }
    ListenableFuture<SendResult<String, String>> future = kafkaTemplateRequest.send(topic, message);
    future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
      @Override
      public void onSuccess(SendResult<String, String> result) {

        log.info(env.getProperty(Constantes.LOG_KAFKA_PRODUCCER_SUCCESS), message,
              result.getRecordMetadata().offset());
      }

      @Override
      public void onFailure(Throwable ex) {
        log.info(env.getProperty(Constantes.LOG_KAFKA_PRODUCCER_FAILURE),
              message, ex.getMessage());
      }
    });
  }
}
