package kg.bektur.ui.steps;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kg.bektur.ui.models.AccountCard;
import kg.bektur.ui.models.CheckingAccount;
import kg.bektur.ui.models.TransactionsList;
import kg.bektur.ui.pages.CreateCheckingPage;
import kg.bektur.ui.pages.LoginPage;
import kg.bektur.ui.pages.ViewCheckingAccountPage;
import kg.bektur.ui.utils.Driver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;

import java.util.List;
import java.util.Map;

public class NewCheckingSteps {
    WebDriver driver = Driver.getDriver();
    private CreateCheckingPage createCheckingPage = new CreateCheckingPage(driver);
    private LoginPage loginPage = new LoginPage(driver);
    private ViewCheckingAccountPage viewCheckingAccountPage = new ViewCheckingAccountPage(driver);

    @BeforeAll
    public static void setUp() {
        /*System.setProperty("webdriver.chrome.driver", "/Users/bekturyusupov/" +
                "IdeaProjects/Drivers/chrome-mac-123/chromedriver");*/
    }

    @Given("the user logged in as {string} with a password {string}")
    public void the_user_logged_in_as_with_a_password(String username
            , String password) {
        loginPage.login(username, password);
    }

    @When("the user creates a new checking account with the following data")
    public void the_user_creates_a_new_checking_account_with_the_following_data(
            List<CheckingAccount> checkingAccountList) {
        createCheckingPage.createNewChecking(checkingAccountList);
    }

    @Then("the user should see the green {string} message")
    public void the_user_should_see_the_green_message(String greenMessage) {
        Assertions.assertEquals(greenMessage, viewCheckingAccountPage.getActualCreateCheckingAccountConfirmationMessage(),
                "Create New Checking Confirmation Green Message Mismatch");
    }

    @Then("the user should see newly added account card")
    public void the_user_should_see_newly_added_account_card(List<AccountCard> accountCardList) {
        Map<String, String> actualResultMap = viewCheckingAccountPage.createdCheckingAccountCard();

        AccountCard expectedResult = accountCardList.get(0);

        Assertions.assertEquals(expectedResult.getAccountName(), actualResultMap.get("actualAccountName"));
        Assertions.assertEquals("Account: " + expectedResult.getAccountType(), actualResultMap.get("actualAccountType"));
        Assertions.assertEquals("Ownership: " + expectedResult.getOwnership(), actualResultMap.get("actualOwnership"));
        Assertions.assertEquals("Interest Rate: " + expectedResult.getInterestRate(), actualResultMap.get("actualInterestRate"));
        Assertions.assertEquals("Balance: $" + String.format("%.2f", expectedResult.getBalance()), actualResultMap.get("actualBalance"));
    }

    @Then("the user should see the following transactions")
    public void the_user_should_see_the_following_transactions(List<TransactionsList> transactionsList) {
        Map<String, String> actualResultMap = viewCheckingAccountPage.createdCheckingAccountTransactionsMap();

        TransactionsList expectedTransaction = transactionsList.get(0);

        Assertions.assertEquals(expectedTransaction.getCategory(), actualResultMap.get("actualCategory"), "Category mismatch");
        //Assertions.assertEquals(expectedTransaction.getDescription(), actualResultMap.get("actualDescription"), "Description mismatch");
        Assertions.assertEquals(expectedTransaction.getAmount(), Double.parseDouble(actualResultMap.get("actualAmount")), "Amount mismatch");
        Assertions.assertEquals(expectedTransaction.getBalance(), Double.parseDouble(actualResultMap.get("actualBalance")), "Balance mismatch");
    }
}
