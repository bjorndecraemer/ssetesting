package com.bjornspetprojects.ssetesting.controllers;

import com.bjornspetprojects.ssetesting.HeaterState;
import com.bjornspetprojects.ssetesting.services.EventEmitterStateService;
import com.bjornspetprojects.ssetesting.services.HeaterStateService;
import io.netty.util.internal.ThreadLocalRandom;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.time.Duration;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MyController {


    private final EventEmitterStateService eventEmitterStateService;
    private final HeaterStateService heaterStateService;

    public MyController(EventEmitterStateService eventEmitterStateService, HeaterStateService heaterStateService) {
        this.eventEmitterStateService = eventEmitterStateService;
        this.heaterStateService = heaterStateService;
    }

    @GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<HeaterState>> getEmitter(){
        return eventEmitterStateService.subscribe();
    }

    @PostMapping("/currenttemp")
    public ResponseEntity<Void> currentTemp(@RequestParam Integer value){
        heaterStateService.setCurrentTemp(value);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/requestedtemp")
    public ResponseEntity<Void> requestedTemp(@RequestParam Integer value){
        heaterStateService.setRequestedTemp(value);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/auto")
    public ResponseEntity<Void> auto(@RequestParam Boolean value){
        heaterStateService.setAuto(value);
        return ResponseEntity.ok().build();
    }
}
