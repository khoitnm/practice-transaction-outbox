package org.tnmk.practicetransactionoutbox.pro00mysqlsimple.testinfra.dbtestcontainer;

import org.testcontainers.containers.MySQLContainer;

import java.time.Duration;

public class DBContainerStarter {
  public static final MySQLContainer DB_CONTAINER = startContainer();

  private static MySQLContainer startContainer() {
    MySQLContainer container = new MySQLContainer()
        .withUsername("user")
        .withPassword("user")
//        .withDatabaseName("test")
        ;
    container
        .withEnv("MYSQL_ROOT_PASSWORD", "root")
        .withStartupTimeout(Duration.ofSeconds(90))
        .start();
    return container;
  }

}
