package com.demo.robofightfederation.models;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class Tournament {

    @Exclude
    private String id;

    private String winnerId;

    private String hatPrize;

    private List<Round> rounds;

    public String getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(String winnerId) {
        this.winnerId = winnerId;
    }

    public String getHatPrize() {
        return hatPrize;
    }

    public void setHatPrize(String hatPrize) {
        this.hatPrize = hatPrize;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
