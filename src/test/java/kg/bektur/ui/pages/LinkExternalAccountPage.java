package kg.bektur.ui.pages;

import kg.bektur.ui.models.CheckingAccount;
import kg.bektur.ui.models.ExternalAccount;
import kg.bektur.ui.utils.ConfigReader;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

import static kg.bektur.ui.utils.Driver.getDriver;

public class LinkExternalAccountPage extends BaseMenuPage{
    public LinkExternalAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='sufee-alert alert with-close alert-danger alert-dismissible fade show']")
    private WebElement noBankingProviderMessage;


    @FindBy(id = "bankId")
    private WebElement bankListDropDown;

    @FindBy(id = "username")
    private WebElement usernameTxtBox;

    @FindBy(id = "password")
    private WebElement passwordTxtBox;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-sm']")
    private WebElement submitButton;

    public void fillLinkExternalAccountForm(List<ExternalAccount> externalAccountList){
        ExternalAccount externalAccount = externalAccountList.get(0);

        externalAccountsMenuItem.click();
        linkExternalMenuItem.click();

        Assertions.assertEquals(ConfigReader.getPropertiesValue("digitalbank.linkexternalaccounturl"),
                getDriver().getCurrentUrl(), "Link External Account button did not take to the " + ConfigReader.getPropertiesValue("digitalbank.createnewcheckingurl"));

        Select bankList = new Select(bankListDropDown);
        List<WebElement> bankListOptions = bankList.getOptions();
        if(bankListOptions.size()==0){
            System.out.println("No banking providers!");
            bankListOptions.get(0).click();
        }
        else if(bankListOptions.size()!=0){
            for(WebElement bank: bankListOptions){
                if(bank.getText().equalsIgnoreCase(externalAccount.getBankingProvider())) {
                    bank.click();
                    break;
                }
            }
        }
        else{
            System.out.println("Something not right!");
        }

        usernameTxtBox.sendKeys(externalAccount.getUsername());
        passwordTxtBox.sendKeys(externalAccount.getUsername());
        submitButton.click();
    }

    public String getNoBankingProviderMessage(){
        String noProviderText = noBankingProviderMessage.getText().substring(6,noBankingProviderMessage.getText().indexOf("\n"));
        return noProviderText;
    }

    public String getRequiredFieldErrorMessage() {
        return bankListDropDown.getAttribute("validationMessage");
    }
}
