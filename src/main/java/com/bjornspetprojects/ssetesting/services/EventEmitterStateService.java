package com.bjornspetprojects.ssetesting.services;

import com.bjornspetprojects.ssetesting.HeaterState;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ReplayProcessor;

@Service
public class EventEmitterStateService implements HeaterStateMessageListener{

    private ReplayProcessor<ServerSentEvent<HeaterState>> replayProcessor = ReplayProcessor.cacheLast();

    @Override
    public void onPostState(HeaterState heaterState){
        ServerSentEvent<HeaterState> state = ServerSentEvent.builder(heaterState).event("message").id(Integer.toString(heaterState.getId())).build();
        replayProcessor.onNext(state);
    }

    public Flux<ServerSentEvent<HeaterState>> getFluxFromHeaterStateReplayProcessor(){
        return replayProcessor.cache();
    }
}
