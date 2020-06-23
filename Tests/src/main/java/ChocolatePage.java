import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class ChocolatePage implements SwitchBetweenBrowsersWindows, ScrollToElement {
    private final WebDriver driver;
    private final By stringEnterInSubSystemsText = By.xpath("//*[@id=\"container\"]/main/h2");
    private final By iconOfClaimModule = By.xpath("//*[@id=\"container\"]/main/div[1]/div[2]/div/h3");

    public ChocolatePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getStringEnterInSubSystemsText() {
        return driver.findElement(stringEnterInSubSystemsText).getText();
    }

    public ClaimPage goToClaimPageWindow() {    //  Переход из Шоколадки в обращения
        driver.findElement(iconOfClaimModule).click();
        return new ClaimPage(driver);
    }

    public ChocolatePage toNewBrowserWindow(int step) {
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
        return new ChocolatePage(driver);
    }

    public Object scrollToNewElement(By element) {
        WebElement scrollLocation = driver.findElement(element);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true);", scrollLocation);
        return new ChocolatePage(driver);
    }
}
