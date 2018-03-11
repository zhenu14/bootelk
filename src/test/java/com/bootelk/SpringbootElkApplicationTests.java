package com.bootelk;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringbootApplication.class)
public class SpringbootElkApplicationTests {

    @Test
    public void contextLoads() {
    }

    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void test() throws Exception {

        for(int i=0;i<10;i++) {
            logger.info("输出info  ");
            logger.warn("输出warn");
            logger.error("输出error  嗡嗡嗡我");
        }
    }


}