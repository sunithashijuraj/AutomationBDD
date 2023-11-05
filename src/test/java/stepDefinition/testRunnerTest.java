package stepDefinition;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;
import java.io.File;
import java.io.IOException;

@RunWith(Cucumber.class)
@CucumberOptions(
         features={"src/test/resources/Features"}
        ,glue={"stepDefinition"}
        ,plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
        ,tags="@searchItem"
)

public class testRunnerTest {
    @AfterClass
    public static void writeExtentReport() {
     
    }

}
