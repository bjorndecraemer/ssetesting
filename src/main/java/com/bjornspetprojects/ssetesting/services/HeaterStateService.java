package com.bjornspetprojects.ssetesting.services;

public interface HeaterStateService {
    void setRequestedTemp(int requestedTemp);
    void setCurrentTemp(int currentTemp);
    void setAuto (boolean auto);
}
