package com.bjornspetprojects.ssetesting.controllers;

import com.bjornspetprojects.ssetesting.services.SseStateService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class MyController {


    private final SseStateService sseStateService;

    public MyController(SseStateService sseStateService) {
        this.sseStateService = sseStateService;
    }

    @GetMapping("/sse")
    public SseEmitter getEmitter(){
        return sseStateService.getSseEmitter();
    }

    @PostMapping("/message")
    public ResponseEntity<Void> addMessage(@RequestParam String value){
        sseStateService.pushString(value);
        return ResponseEntity.ok().build();
    }
}
