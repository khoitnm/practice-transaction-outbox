package org.tnmk.outboxpattern.pro00mysqlsimple.config.transactionaloutbox;

import com.gruelbox.transactionoutbox.DefaultInvocationSerializer;
import com.gruelbox.transactionoutbox.DefaultPersistor;
import com.gruelbox.transactionoutbox.Dialect;
import com.gruelbox.transactionoutbox.Persistor;
import com.gruelbox.transactionoutbox.SpringInstantiator;
import com.gruelbox.transactionoutbox.SpringTransactionManager;
import com.gruelbox.transactionoutbox.SpringTransactionOutboxConfiguration;
import com.gruelbox.transactionoutbox.TransactionOutbox;
import com.gruelbox.transactionoutbox.TransactionOutboxEntry;
import com.gruelbox.transactionoutbox.TransactionOutboxListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.tnmk.outboxpattern.pro00mysqlsimple.entity.SampleEntity;

import java.time.Duration;
import java.util.Set;
import java.util.UUID;

@Import({ SpringTransactionOutboxConfiguration.class }) // This is required for SpringTransactionManager & SpringInstantiator beans
@Configuration
public class TransactionalOutboxConfig {
  @Bean
  //  @Lazy
  public TransactionOutbox transactionOutbox(
      SpringTransactionManager springTransactionManager, SpringInstantiator springInstantiator
      , Persistor persistor
      , TransactionOutboxListener transactionOutboxListener) {

    return TransactionOutbox.builder()
        .instantiator(springInstantiator)
        .transactionManager(springTransactionManager)
        .retentionThreshold(Duration.ofSeconds(5))

        // Flush once every 4 seconds
        .attemptFrequency(Duration.ofSeconds(4))
        .persistor(persistor)

        // Include Slf4j's Mapped Diagnostic Context in tasks. This means that anything in the MDC when schedule()
        // is called will be recreated in the task when it runs. Very useful for tracking things like user ids and
        // request ids across invocations.
        .serializeMdc(true)

        .listener(transactionOutboxListener)
        .build();
  }

  @Bean
  public Persistor transactionOutboxPersistor() {
    return DefaultPersistor.builder()
        // This config must match with Dialect config in application.yml: `hibernate.dialect: org.hibernate.dialect.MySQL5InnoDBDialect`
        .dialect(Dialect.MY_SQL_5)

        // Disable automatic creation and migration of the outbox table, forcing the application to manage migrations itself.
        .migrate(false)
        .serializer(DefaultInvocationSerializer.builder()
            .serializableTypes(Set.of(
                UUID.class,
                // Must define this class here to avoid error "Cannot serialize: Class Not Found SampleEntity.class"
                SampleEntity.class
            ))
            .build())
        .writeLockTimeoutSeconds(1)
        .build();
  }
}