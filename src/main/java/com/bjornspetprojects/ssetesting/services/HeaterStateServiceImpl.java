package com.bjornspetprojects.ssetesting.services;

import com.bjornspetprojects.ssetesting.HeaterState;
import org.springframework.stereotype.Service;

@Service
public class HeaterStateServiceImpl implements HeaterStateService {

    private final EventEmitterStateService eventEmitterStateService;

    private HeaterState lastState = new HeaterState();


    public HeaterStateServiceImpl(EventEmitterStateService eventEmitterStateService) {
        this.eventEmitterStateService = eventEmitterStateService;
    }


    @Override
    public void setRequestedTemp(int requestedTemp) {
        lastState.setRequestedTemp(requestedTemp);
        eventEmitterStateService.onPostState(lastState);
    }

    @Override
    public void setCurrentTemp(int currentTemp) {
        lastState.setCurrentTemp(currentTemp);
        eventEmitterStateService.onPostState(lastState);
    }

    @Override
    public void setAuto(boolean auto) {
        lastState.setAutoTemp(auto);
        eventEmitterStateService.onPostState(lastState);
    }
}
