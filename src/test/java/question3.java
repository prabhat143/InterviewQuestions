import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class question3 {
    //Incorrect username/password.

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "#username")
    private WebElement userName;
    @FindBy(css = "#password")
    private WebElement password;
    @FindBy(xpath = "//select[@class = 'form-control']")
    private WebElement formControl;
    @FindBy(css = "#terms")
    private WebElement termsConditions;
    @FindBy(css = "#signInBtn")
    private WebElement signBtn;
    @FindBy(css = ".alert.alert-danger")
    private WebElement errMsgBox;


    @BeforeMethod
    public void beforeMethod(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver,10);
    }

    @Test(description = "verify error message")
    public void verifyErrorMsg(){
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
        wait.until(ExpectedConditions.visibilityOf(userName)).sendKeys("sdfas");
        wait.until(ExpectedConditions.visibilityOf(password)).sendKeys("sadfas");

        Select select = new Select(formControl);
        select.selectByValue("stud");

        termsConditions.click();
        signBtn.click();

        String errMsgTxt = wait.until(ExpectedConditions.visibilityOf(errMsgBox)).getText();
        System.out.println(errMsgTxt);
        assert errMsgTxt.contains("Incorrect username/password");
    }

    @AfterMethod
    public void afteMethod(){
        driver.quit();
    }
}
