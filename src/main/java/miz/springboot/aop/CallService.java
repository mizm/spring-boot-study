package miz.springboot.aop;

import org.springframework.stereotype.Service;

@Service
public class CallService {

    private final AopService aopService;

    public CallService(AopService aopService) {
        this.aopService = aopService;
    }

    void proxyTest() {
        System.out.println("aopService.getClass() = " + aopService.getClass());
        aopService.test();
        aopService.test();
        System.out.println("aopService = " + aopService.getClass());
    }
}
