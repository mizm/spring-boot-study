package miz.springboot.aop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class callServiceTest {

    @Autowired
    CallService callService;
    @Test
    @DisplayName("proxy 는 누가 만들까?")
    void aopTest() {
        callService.proxyTest();
    }
}