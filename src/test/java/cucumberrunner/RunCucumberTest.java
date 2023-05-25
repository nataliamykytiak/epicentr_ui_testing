package cucumberrunner;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/main/resources/cucumberfiles/authorization.feature",
        glue = "stepdefinitions"
)
public class RunCucumberTest {

}
