package com.runescape.runemetrics.api.model;

import lombok.Data;

@Data
public class Quest {

    private String title;
    private String status;
    private int difficulty;
    private boolean members;
    private int questPoints;
    private boolean userEligible;

}
