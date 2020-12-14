/*
 * Copyright (c) 2019.
 * Claro Chile
 * Creado por Sistemas ltda
 */

package cl.claro.fvc.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * @author Carlos Tapia
 */
@EnableReactiveMongoRepositories
public class MongoReactiveApplication extends AbstractReactiveMongoConfiguration {

  /**
   * Defniicion db name mongodb.
   *
   * @return
   */
  @Override
  protected String getDatabaseName() {
    return "reactive";
  }

  /**
   * Bean de creación de mongodb.
   *
   * @return
   */
  @Bean
  @Override
  public MongoClient reactiveMongoClient() {
    return MongoClients.create();
  }
}
