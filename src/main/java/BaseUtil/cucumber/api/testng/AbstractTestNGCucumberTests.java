package BaseUtil.cucumber.api.testng;


import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AbstractTestNGCucumberTests {
	 private TestNGCucumberRunner testNGCucumberRunner;

	    @BeforeClass(alwaysRun = true)
	    public void setUpClass() throws Exception {
	        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	    }

	    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
	    public void runScenario(PickleEventWrapper pickleWrapper, CucumberFeatureWrapper featureWrapper) throws Throwable {
	        // the 'featureWrapper' parameter solely exists to display the feature file in a test report
	        testNGCucumberRunner.runScenario(pickleWrapper.getPickleEvent());
	    }

	    /**
	     * Returns two dimensional array of PickleEventWrapper scenarios with their associated CucumberFeatureWrapper feature.
	     *
	     * @return a two dimensional array of scenarios features.
	     * @throws IOException 
	     */
	    @DataProvider
	    public Object[][] scenarios() throws IOException {
	        if (testNGCucumberRunner == null) {
	            return new Object[0][0];
	        }
	        return testNGCucumberRunner.provideScenarios();
	    }

	    @AfterClass(alwaysRun = true)
	    public void tearDownClass() throws Exception {
	        if (testNGCucumberRunner == null) {
	            return;
	        }
	        testNGCucumberRunner.finish();
	    }
}
