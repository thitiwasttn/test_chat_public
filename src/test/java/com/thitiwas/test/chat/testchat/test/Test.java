package com.thitiwas.test.chat.testchat.test;

import com.thitiwas.test.chat.testchat.TestchatApplication;
import com.thitiwas.test.chat.testchat.service.ChannelService;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = TestchatApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class Test {
    @Autowired
    ChannelService channelService;

    @Before
    public void setUp() throws Exception {
        // gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @After
    public void tearDown() throws Exception {

    }

    @BeforeClass
    public static void beforeClass() throws Exception {

    }

    @AfterClass
    public static void afterClass() throws Exception {

    }

    @org.junit.Test
    public void test() {
        var test = channelService.findAll();
        log.debug("test : {} " , test);
    }
}
