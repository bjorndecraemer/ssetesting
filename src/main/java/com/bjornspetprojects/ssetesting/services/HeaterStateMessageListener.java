package com.bjornspetprojects.ssetesting.services;

import com.bjornspetprojects.ssetesting.HeaterState;

public interface HeaterStateMessageListener {
    void onPostState(HeaterState heaterState);
}
