package kg.bektur.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id = "username")
    private WebElement usernameTxtBox;

    @FindBy(id = "password")
    private WebElement passwordTxtBox;

    @FindBy(id = "remember-me")
    private WebElement rememberMeCheckBox;

    @FindBy(id = "submit")
    private WebElement submitButton;

    @FindBy(xpath = "//a[contains(text(),'Sign Up Here')]")
    private WebElement signUpButton;

    public void login (String username, String password){
        /*usernameTxtBox.sendKeys(username);
        passwordTxtBox.sendKeys(password);
        submitButton.click();*/
        //dbank login page has a glitch so you need to login twice
        usernameTxtBox.sendKeys(username);
        passwordTxtBox.sendKeys(password);
        submitButton.click();
    }
}
