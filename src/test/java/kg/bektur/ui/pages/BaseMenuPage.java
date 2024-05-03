package kg.bektur.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BaseMenuPage extends BasePage{
    public BaseMenuPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id = "menuToggle")
    protected WebElement menuToggleButton;

    @FindBy(xpath = "//a[@class='navbar-brand']")
    protected WebElement digitalBankLogoLink;

    @FindBy(id = "home-menu-item")
    protected WebElement homeButton;

    @FindBy(id = "checking-menu")
    protected WebElement checkingMenu;

    @FindBy(id = "new-checking-menu-item")
    protected WebElement newCheckingMenuItem;

    @FindBy(id = "view-checking-menu-item")
    protected WebElement viewCheckingMenuItem;

    @FindBy(id = "savings-menu")
    protected WebElement savingsMenu;

    @FindBy(id = "view-savings-menu-item")
    protected WebElement viewSavingsMenuItem;

    @FindBy(id = "new-savings-menu-item")
    protected WebElement newSavingsMenuItem;

    @FindBy(id = "external-accounts-menu")
    protected WebElement externalAccountsMenuItem;

    @FindBy(id = "link-external-menu-item")
    protected WebElement linkExternalMenuItem;

    @FindBy(id = "view-external-menu-item")
    protected WebElement viewExternalMenuItem;

    @FindBy(id = "deposit-menu-item")
    protected WebElement depositMenuItem;

    @FindBy(id = "withdraw-menu-item")
    protected WebElement withdrawMenuItem;

    @FindBy(id = "transfer-menu-item")
    protected WebElement transferMenuItem;

    @FindBy(id = "visa-transfer-menu-item")
    protected WebElement visaTransferMenuItem;

    @FindBy(id = "searchLocations")
    protected WebElement searchButton;

    @FindBy(id = "notification")
    protected WebElement notificationButton;

    @FindBy(id = "message")
    protected WebElement messageButton;

    @FindBy(id = "aboutLink")
    protected WebElement aboutLinkButton;

    @FindBy(id = "language")
    protected WebElement languageButton;

    @FindBy(id = "//div[@class='user-area dropdown']//img")
    protected WebElement avatarButton;

    public void goToHomePage(){
        homeButton.click();
    }

    public void linkExternalAccountPage(){
        externalAccountsMenuItem.click();
        linkExternalMenuItem.click();
    }
}




