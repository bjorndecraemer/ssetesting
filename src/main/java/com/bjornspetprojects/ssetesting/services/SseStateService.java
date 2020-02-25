package com.bjornspetprojects.ssetesting.services;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SseStateService {
    private final SseEmitter sseEmitter = new SseEmitter(0L);
    private final List<String> _state = new ArrayList();

    public SseEmitter getSseEmitter() {
        return sseEmitter;
    }

    public void pushString(String value){
        _state.add(value);
        try {
            sseEmitter.send(_state);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destroy() {
        sseEmitter.complete();
        System.out.println(
                "Callback triggered - @PreDestroy.");
    }
}
