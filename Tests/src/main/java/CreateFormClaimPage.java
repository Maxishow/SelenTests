import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class CreateFormClaimPage implements SwitchBetweenBrowsersWindows, ScrollToElement {
    private final WebDriver driver;
    private final By subdivisionListButton = By.xpath("//*[@id=\"executor\"]" +
            "/div/div/div[1]/div/div[2]/div/span/button/span[1]"); // Кнопка выбора подразделения из списка
    private final By valueOfSubdivisionList = By.xpath("//*[@id=\"pr_id_14_list\"]/li[2]"); // Значение из списка подразделение
    private final By typeOfSendingList = By.xpath("//*[@id=\"basic\"]/div/div[1]/div[1]/div/div[2]/div/span/button");
    private final By valueOfSendingList = By.cssSelector("#pr_id_16_list > li:nth-child(1)");
    private final By surnameField = By.cssSelector("#claimant > div > div > " +
            "div:nth-child(1) > div:nth-child(3) > div.form-item__input > div > input");
    private final By nameField = By.cssSelector("#claimant > div > div > div:nth-child(1)" +
            " > div:nth-child(4) > div.form-item__input > div > input");
    private final By fathernameField = By.cssSelector("#claimant > div > div > " +
            "div:nth-child(1) > div:nth-child(5) > div.form-item__input > div > input");
    private static final By organizationBlock = By.cssSelector("#organizations > button > div > h3");
    private final By streetListButton = By.cssSelector("#address > div > div:nth-child(2) > " +
            "div:nth-child(1) > div:nth-child(4) > div.form-item__input > div > span > button");
    private final By valueOfStreetFromList = By.xpath("//*[@id=\"pr_id_25_list\"]/li[2]");
    private final By saveButton = By.xpath("//*[@id=\"main\"]/main/div/div[2]/div[2]/div[2]/div/button[2]");


    public CreateFormClaimPage(WebDriver driver) {
        this.driver = driver;
    }

    public static By goToOrganizationBlock() {
        return organizationBlock;
    }

    public CreateFormClaimPage clickOnSubdivisionListButton() {
        driver.findElement(subdivisionListButton).click();
        return new CreateFormClaimPage(driver);
    }

    public CreateFormClaimPage selectValueOfSubdivisionList() {
        driver.findElement(valueOfSubdivisionList).click();
        return new CreateFormClaimPage(driver);
    }

    public CreateFormClaimPage clickOnTypeOfSendingList() {
        driver.findElement(typeOfSendingList).click();
        return new CreateFormClaimPage(driver);
    }
    public CreateFormClaimPage selectValueOfSendingList() {
        driver.findElement(valueOfSendingList).click();
        return  new CreateFormClaimPage(driver);
    }
    public CreateFormClaimPage fillSurnameField() {
        driver.findElement(surnameField).sendKeys("Тестовый");
        return new CreateFormClaimPage(driver);
    }
    public CreateFormClaimPage fillNameField() {
        driver.findElement(nameField).sendKeys("Тест");
        return new CreateFormClaimPage(driver);
    }

    public CreateFormClaimPage fillFathernameField() {
        driver.findElement(fathernameField).sendKeys("Тестович");
        return new CreateFormClaimPage(driver);
    }

    public CreateFormClaimPage clickOnStreetListButton() {
        driver.findElement(streetListButton).click();
        return new CreateFormClaimPage(driver);
    }

    public CreateFormClaimPage selectStreetFromList() {
        driver.findElement(valueOfStreetFromList).click();
        return new CreateFormClaimPage(driver);
    }

    public AfterCreatedClaimPage saveCreatedClaim() {
        driver.findElement(saveButton).click();
        return new AfterCreatedClaimPage(driver);
    }

    public CreateFormClaimPage scrollToNewElement (By element) {
        WebElement scrollLocation = driver.findElement(element);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true);", scrollLocation);
        return new CreateFormClaimPage(driver);
    }

    public CreateFormClaimPage toNewBrowserWindow(int step) {
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
        return new CreateFormClaimPage(driver);
    }
}
