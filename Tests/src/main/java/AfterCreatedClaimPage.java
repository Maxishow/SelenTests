import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AfterCreatedClaimPage {
    private final WebDriver driver;
    public AfterCreatedClaimPage(WebDriver driver) {
        this.driver = driver;
    }

    private  final By successfulCreatedClaimField = By.xpath("//*[@id=\"main\"]/main/div/div[2]/div/div/h1");

    public String getTextAboutCreatedClaim () {
        return driver.findElement(successfulCreatedClaimField).getText();
    }
}
