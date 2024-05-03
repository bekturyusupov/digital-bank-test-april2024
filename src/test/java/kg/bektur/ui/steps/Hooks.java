package kg.bektur.ui.steps;

import io.cucumber.java.*;
import kg.bektur.ui.utils.ConfigReader;
import kg.bektur.ui.utils.DBUtils;
import kg.bektur.ui.utils.Driver;

public class Hooks {
    @Before("@Registration")
    public void establishConnectionToDB(){
        DBUtils.establishConnection();
    }

    @Before("not @Registration")
    public void the_user_is_on_dbank_homepage(){
        Driver.getDriver().get(ConfigReader.getPropertiesValue("digitalbank.loginpageurl"));
        Driver.getDriver().manage().window().maximize();
    }

    @After("not @NegativeRegistrationCases")
    public void afterScenario(Scenario scenario){
        Driver.takeScreenShot(scenario);
        Driver.closeDriver();
    }

    @AfterAll()
    public static void closeConnectionToDB(){
        DBUtils.closeConnection();
    }
}
