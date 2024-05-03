package kg.bektur.ui.pages;

import kg.bektur.ui.models.CheckingAccount;
import kg.bektur.ui.utils.ConfigReader;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;

import static kg.bektur.ui.utils.Driver.getDriver;

public class CreateCheckingPage extends BaseMenuPage {
    public CreateCheckingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "Standard Checking")
    private WebElement standardCheckingRadioButton;

    @FindBy(id = "Interest Checking")
    private WebElement interestCheckingRadioButton;

    @FindBy(id = "Individual")
    private WebElement individualOwnershipRadioButton;

    @FindBy(id = "Joint")
    private WebElement jointOwnershipRadioButton;

    @FindBy(id = "name")
    private WebElement accountNameTextBox;

    @FindBy(id = "openingBalance")
    private WebElement openingBalanceTextBox;

    @FindBy(id = "newCheckingSubmit")
    private WebElement newCheckingSubmitButton;


    public void createNewChecking(List<CheckingAccount> checkingAccountList) {
        CheckingAccount checkingAccount = checkingAccountList.get(0);
        checkingMenu.click();
        newCheckingMenuItem.click();

        Assertions.assertEquals(ConfigReader.getPropertiesValue("digitalbank.createnewcheckingurl"),
                getDriver().getCurrentUrl(), "New Checking button did not take to the " + ConfigReader.getPropertiesValue("digitalbank.createnewcheckingurl"));

        if (checkingAccount.getAccountType().equalsIgnoreCase("Standard Checking")) {
            standardCheckingRadioButton.click();
        } else if (checkingAccount.getAccountType().equalsIgnoreCase("Interest Checking")) {
            interestCheckingRadioButton.click();
        } else {
            throw new NoSuchElementException("Invalid checking account type.");
        }

        if (checkingAccount.getAccountOwnership().equalsIgnoreCase("Individual")) {
            individualOwnershipRadioButton.click();
        } else if (checkingAccount.getAccountOwnership().equalsIgnoreCase("Joint")) {
            jointOwnershipRadioButton.click();
        } else {
            throw new NoSuchElementException("Invalid checking account ownership.");
        }

        accountNameTextBox.sendKeys(checkingAccount.getAccountName());
        openingBalanceTextBox.sendKeys(String.valueOf(checkingAccount.getInitialDeposit()));
        newCheckingSubmitButton.click();
    }
}
