package ch.creditsuisse.testing.utils;

import cucumber.api.Scenario;
import cucumber.api.java.Before;

public class Hooks {
    @Before
    public void setScenario(Scenario scenario){
        CurrentScenario.INSTANCE.setScenario(scenario);
    }
}
