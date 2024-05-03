package kg.bektur.ui.steps;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kg.bektur.ui.models.ExternalAccount;
import kg.bektur.ui.pages.LinkExternalAccountPage;
import kg.bektur.ui.pages.LoginPage;
import kg.bektur.ui.utils.Driver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ExternalAccountLinkSteps {
    WebDriver driver = Driver.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    LinkExternalAccountPage linkExternalAccountPage = new LinkExternalAccountPage(driver);

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/bekturyusupov/" +
                "IdeaProjects/Drivers/chrome-mac-123/chromedriver");
    }

    @Given("the user logged in as {string} with a password {string} to create External Account")
    public void the_user_logged_in_as_with_a_password(String username
            , String password) {loginPage.login(username, password);
    }

    @When("User links a new external banking account with the following data")
    public void user_links_a_new_external_banking_account_with_the_following_data(List<ExternalAccount> externalAccountList) {
        linkExternalAccountPage.fillLinkExternalAccountForm(externalAccountList);
    }
    @Then("User should see the red {string} message")
    public void user_should_see_the_red_message(String redMessage) {
        Assertions.assertEquals(redMessage, linkExternalAccountPage.getNoBankingProviderMessage(),
                "Create New Checking Confirmation Green Message Mismatch");
    }
    @Then("User should see on the Open Banking Providers field the following required field error message {string}")
    public void user_should_see_on_the_open_banking_providers_field_the_following_required_field_error_message(String noProviderMessage) {
        Assertions.assertEquals(noProviderMessage, linkExternalAccountPage.getRequiredFieldErrorMessage(),
                "No banking provider message mismatch.");
    }
}
