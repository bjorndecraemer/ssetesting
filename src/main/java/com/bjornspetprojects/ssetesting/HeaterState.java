package com.bjornspetprojects.ssetesting;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HeaterState {
    private int id;
    private int requestedTemp;
    private int currentTemp;
    private boolean autoTemp;
}
