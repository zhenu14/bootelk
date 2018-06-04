import com.logalert.SpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringbootApplication.class)
public class LogTest {
    private Logger logger = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void test() throws Exception {

        for(int i=0;i<50;i++) {
            logger.info("输出info  ");
            logger.warn("输出warn");
            logger.error("输出error");
        }
    }
}
