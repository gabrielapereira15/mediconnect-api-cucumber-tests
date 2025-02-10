package mediconnect.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setup() {
        System.out.println("Starting API tests...");
    }

    @After
    public void teardown() {
        System.out.println("API tests completed.");
    }
}