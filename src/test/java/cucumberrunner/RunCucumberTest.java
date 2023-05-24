package cucumberrunner;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/main/resources/cucumberfiles",
        glue = "StepDefinitions"
)
public class RunCucumberTest {

}
