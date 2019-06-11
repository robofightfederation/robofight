package com.demo.robofightfederation.models;

import java.util.List;

public class Round {
    private List<RoundContestant> contestants;

    public List<RoundContestant> getContestants() {
        return contestants;
    }

    public void setContestants(List<RoundContestant> contestants) {
        this.contestants = contestants;
    }
}
