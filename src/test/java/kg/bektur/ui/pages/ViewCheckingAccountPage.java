package kg.bektur.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kg.bektur.ui.utils.Driver.getDriver;

public class ViewCheckingAccountPage extends BaseMenuPage{
    public ViewCheckingAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "new-account-msg")
    private WebElement newAccountConfirmationMessage;

    @FindBy(xpath = "//div[@id='firstRow']/div")
    private List<WebElement> allFirstRowDivs;

    @FindBy(xpath = "//table[@id='transactionTable']/tbody//td")
    private List<WebElement> firstRowTransactions;

    public Map<String, String> createdCheckingAccountTransactionsMap(){
        List<WebElement> transactionRow = getDriver().findElements(By.xpath(
                "//table[@id='transactionTable']/tbody//td"));
        Map<String, String> actualResultMap = new HashMap<>();
        actualResultMap.put("actualCategory", transactionRow.get(1).getText());
        actualResultMap.put("actualDescription", transactionRow.get(2).getText().substring(transactionRow.get(2).getText().indexOf(' ')));
        actualResultMap.put("actualAmount", transactionRow.get(3).getText().substring(1));
        actualResultMap.put("actualBalance", transactionRow.get(4).getText().substring(1));
        return actualResultMap;
    }

    public Map<String, String> createdCheckingAccountCard() {
        WebElement lastAccountCard = allFirstRowDivs.get(allFirstRowDivs.size() - 1);
        String actualResult = lastAccountCard.getText();

        Map<String, String> actualResultMap = new HashMap<>();
        actualResultMap.put("actualAccountName", actualResult.substring(0, actualResult.indexOf("\n")).trim());
        actualResultMap.put("actualAccountType", actualResult.substring(actualResult.indexOf("Account"), actualResult.indexOf("Ownership")).trim());
        actualResultMap.put("actualOwnership", actualResult.substring(actualResult.indexOf("Ownership:"), actualResult.indexOf("Account Number:")).trim());
        actualResultMap.put("actualAccountNumber", actualResult.substring(actualResult.indexOf("Account Number:"), actualResult.indexOf("Interest Rate")).trim());
        actualResultMap.put("actualInterestRate", actualResult.substring(actualResult.indexOf("Interest Rate:"), actualResult.indexOf("Balance")).trim());
        actualResultMap.put("actualBalance", actualResult.substring(actualResult.indexOf("Balance:")).trim());

        return actualResultMap;
    }

    public String getActualCreateCheckingAccountConfirmationMessage() {
        return newAccountConfirmationMessage.getText();
    }
}
