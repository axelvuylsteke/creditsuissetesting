package ch.creditsuisse.testing.utils;

import cucumber.api.Scenario;

public class CurrentScenario {
    public final static CurrentScenario INSTANCE = new CurrentScenario();

    private Scenario scenario;
    private boolean warmUp;

    private CurrentScenario() {
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public Scenario getScenario() {
        return this.scenario;
    }
}
