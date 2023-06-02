package com.model.contract.page;

import java.io.Serializable;

/**
 * Created by kang on 2019-1-25.
 */
public class Words implements Serializable {

    private String character = "";

    private double confidence = 0.0;

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
}
