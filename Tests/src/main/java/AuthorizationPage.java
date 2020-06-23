import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPage {
    private final String login = "avte";
    private final String password = "1234";
    private  final RemoteWebDriver driver;
    private  final String authWebUrl = "http://172.20.255.251:8080/authWeb/";
    private final By loginField = By.cssSelector("#app > div.layout > div > div > div > div > " +
            "div > div > div > form > div:nth-child(1) > div > div > div > input");
    private final By passworField = By.cssSelector("#app > div.layout > div > div > div > div > div > div > div >" +
            " form > div:nth-child(2) > div > div > div > input");
    private final By submitButton = By.cssSelector("#app > div.layout > div > div > div > " +
            "div > div > div > div > form > div.py12.align-center > button > span");

    public AuthorizationPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public  String getAuthWebUrl() {
        return authWebUrl;
    }

    public void fillLoginField(String login) {
        driver.findElement(loginField).sendKeys(login);
    }
    public void fillPasswordField(String password) {
        driver.findElement(passworField).sendKeys(password);
    }

    public ChocolatePage authorizationInSystem(String login, String password) {
        driver.findElement(loginField).sendKeys(login);
        driver.findElement(passworField).sendKeys(password);
        driver.findElement(submitButton).click();

        return new ChocolatePage(driver);
    }

    public ChocolatePage pressSubmitButton() {
        driver.findElement(submitButton).click();
        return new ChocolatePage(driver);
    }
}
