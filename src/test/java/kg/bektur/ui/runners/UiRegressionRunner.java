package kg.bektur.ui.runners;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;


@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("ui/features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "kg.bektur.ui.steps")
//@IncludeTags("Test")

public class UiRegressionRunner {

}
