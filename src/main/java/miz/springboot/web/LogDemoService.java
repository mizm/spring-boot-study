package miz.springboot.web;

import lombok.RequiredArgsConstructor;
import miz.springboot.common.MyLogger;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {
    private final MyLogger myLogger;

    public void logic(String testId) {
        myLogger.log("service Id " + testId);
    }
}
