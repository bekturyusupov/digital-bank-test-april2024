package kg.bektur.ui.steps;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kg.bektur.ui.pages.RegistrationPage;
import kg.bektur.ui.utils.ConfigReader;
import kg.bektur.ui.utils.DBUtils;
import kg.bektur.ui.utils.Driver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationSteps {
    WebDriver driver = Driver.getDriver();
    RegistrationPage registrationPage = new RegistrationPage(driver);
    List<Map<String, Object>> nextValList = new ArrayList<>();

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/bekturyusupov/" +
                "IdeaProjects/Drivers/chrome-mac-123/chromedriver");
    }

    @Given("User navigates to Digital Bank signup page")
    public void user_navigates_to_digital_bank_signup_page() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(ConfigReader.getPropertiesValue("digitalbank.registrationpageurl"));
        assertEquals("Digital Bank", driver.getTitle(),
                "Registration Page Title mismatch.");
    }

    @When("User creates account with following fields")
    public void user_creates_account_with_following_fields(List<Map<String, String>> registrationTestDataMap) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        registrationPage.fillRegistrationPageForm(registrationTestDataMap);
    }

    @Then("User should be displayed with the message {string}")
    public void user_should_be_displayed_with_the_message(String expectedSuccessMessage) {
        String actualSuccessMessage = registrationPage.getMessage().substring(0, registrationPage.getMessage().lastIndexOf('.') + 1);
        assertEquals(expectedSuccessMessage, actualSuccessMessage, "Registration Page Success Message Mismatch");
    }

    @Then("User should see the {string} following required field error message {string}")
    public void userShouldSeeTheFollowingRequiredFieldErrorMessage(String fieldName, String expectedErrorMessage) {
        String actualErrorMessage = registrationPage.getRequiredFieldErrorMessage(fieldName);
        assertEquals(expectedErrorMessage, actualErrorMessage,
                "Error Message of required " + fieldName + " mismatch.");
    }

    @Then("The following user info should be saved in the DataBase")
    public void theFollowingUserInfoShouldBeSavedInTheDataBase(List<Map<String, String>> expectedUserProfileInfoDB) {
        Map<String, String> expectedUserInfo = expectedUserProfileInfoDB.get(0);
        String queryUsersTable = String.format("SELECT * from users WHERE username='%s';", expectedUserInfo.get("email"));
        String queryUserProfileTable = String.format("SELECT * from user_profile WHERE email_address='%s';", expectedUserInfo.get("email"));



        List<Map<String, Object>> actualUsersInfoList = DBUtils.runSQLSelectQuery(queryUsersTable);
        List<Map<String, Object>> actualUserProfileInfoList = DBUtils.runSQLSelectQuery(queryUserProfileTable);


        //Assertions
        assertEquals(1, actualUsersInfoList.size(), "Registration generated unexpected number of users.");
        assertEquals(1, actualUserProfileInfoList.size(), "Registration generated unexpected number of user profiles.");

        Map<String, Object> actualUsersInfoMap = actualUsersInfoList.get(0);
        Map<String, Object> actualUserProfileInfoMap = actualUserProfileInfoList.get(0);

        //Validate user profile
        assertEquals(expectedUserInfo.get("title"),actualUserProfileInfoMap.get("title"), "Title mismatch");
        assertEquals(expectedUserInfo.get("firstName"),actualUserProfileInfoMap.get("first_name"), "First name mismatch");
        assertEquals(expectedUserInfo.get("lastName"),actualUserProfileInfoMap.get("last_name"), "Last name mismatch");
        assertEquals(expectedUserInfo.get("gender"),actualUserProfileInfoMap.get("gender"), "Gender mismatch");
        //assertEquals(expectedUserInfo.get("dob"),actualUserProfileInfoMap.get("dob"), "dob mismatch");
        assertEquals(expectedUserInfo.get("ssn"),actualUserProfileInfoMap.get("ssn"), "ssn mismatch");
        assertEquals(expectedUserInfo.get("email"),actualUserProfileInfoMap.get("email_address"), "email mismatch");
        assertEquals(expectedUserInfo.get("address"),actualUserProfileInfoMap.get("address"), "address mismatch");
        assertEquals(expectedUserInfo.get("locality"),actualUserProfileInfoMap.get("locality"), "locality mismatch");
        assertEquals(expectedUserInfo.get("region"),actualUserProfileInfoMap.get("region"), "region mismatch");
        assertEquals(expectedUserInfo.get("postalCode"),actualUserProfileInfoMap.get("postal_code"), "postalCode mismatch");
        assertEquals(expectedUserInfo.get("country"),actualUserProfileInfoMap.get("country"), "country mismatch");
        assertEquals(expectedUserInfo.get("homePhone"),actualUserProfileInfoMap.get("home_phone"), "homePhone mismatch");
        assertEquals(expectedUserInfo.get("mobilePhone"),actualUserProfileInfoMap.get("mobile_phone"), "mobilePhone mismatch");
        assertEquals(expectedUserInfo.get("workPhone"),actualUserProfileInfoMap.get("work_phone"), "workPhone mismatch");

        //Validate users
        assertEquals(expectedUserInfo.get("email"),actualUsersInfoMap.get("username"), "username mismatch");
        assertEquals(nextValList.get(0).get("next_val"),actualUsersInfoMap.get("id"), "users id mismatch");
        long expectedUserProdileId = Long.parseLong(String.valueOf(nextValList.get(0).get("next_val")));
        assertEquals(++expectedUserProdileId,actualUserProfileInfoMap.get("id"), "user profile id mismatch");
    }

    @Given("User with {string} is not in DB")
    public void userWithIsNotInDB(String email) {
        String queryForUserProfile = String.format("DELETE FROM user_profile WHERE email_address='%s';", email);
        String queryForUsers = String.format("DELETE FROM users WHERE username='%s';", email);
        String queryToGetNextValHibernateSequence = String.format("SELECT next_val FROM hibernate_sequence LIMIT 1;");

        nextValList = DBUtils.runSQLSelectQuery(queryToGetNextValHibernateSequence);
        DBUtils.runSQLUpdateQuery(queryForUserProfile);
        DBUtils.runSQLUpdateQuery(queryForUsers);


    }
}
