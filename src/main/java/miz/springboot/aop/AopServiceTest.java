package miz.springboot.aop;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AopServiceTest implements AopService {
    @Override
    @Transactional
    public void test() {
        System.out.println("true = " + true);
        System.out.println("true = " + this);
    }
}
