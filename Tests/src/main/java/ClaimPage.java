import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class ClaimPage implements SwitchBetweenBrowsersWindows, ScrollToElement {
    WebDriver driver;

    private  final By claimButton = By.xpath("//*[@id=\"main\"]/header/div[2]/ul/li[1]/a/span[1]");

    private final By createNewClaimButton = By.xpath("//*[@id=\"main\"]/header/div[2]/ul/li[1]/div/div/div[1]/ul/li[3]/a/span");

    public ClaimPage(WebDriver driver) {
        this.driver = driver;
    }

    public ClaimPage pressClaimButton() {
        driver.findElement(claimButton).click();
        return new ClaimPage(driver);
    }

    public CreateFormClaimPage selectCreateNewClaim() {
        driver.findElement(createNewClaimButton).click();
        return new CreateFormClaimPage(driver);
    }

    public ClaimPage toNewBrowserWindow(int step) {
        String currentWindow = driver.getWindowHandle();
        ArrayList<String> list = new ArrayList<>(driver.getWindowHandles());
        int beforeListSize = list.size();
        int afterListSize = list.size();
        while (beforeListSize == afterListSize) {
            list = new ArrayList<>(driver.getWindowHandles());
            afterListSize = list.size();
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(currentWindow)) {
                driver.switchTo().window(list.get(i + step));
                break;
            }
        }
        return new ClaimPage(driver);
    }

    public Object scrollToNewElement(By element) {
        WebElement scrollLocation = driver.findElement(element);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true);", scrollLocation);
        return new ClaimPage(driver);
    }
}
