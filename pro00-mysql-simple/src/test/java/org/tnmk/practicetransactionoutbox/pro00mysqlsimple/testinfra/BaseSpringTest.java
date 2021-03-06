package org.tnmk.practicetransactionoutbox.pro00mysqlsimple.testinfra;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.tnmk.practicetransactionoutbox.pro00mysqlsimple.Pro00MysqlSimpleApplication;
import org.tnmk.practicetransactionoutbox.pro00mysqlsimple.testinfra.dbtestcontainer.DBContainerContextInitializer;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Pro00MysqlSimpleApplication.class})
@Ignore
public abstract class BaseSpringTest {
}
