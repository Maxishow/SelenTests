import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class Tests {
    public WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void test1() throws InterruptedException {
        AuthorizationPage authorizationPage = new AuthorizationPage(driver);
        driver.get(authorizationPage.getAuthWebUrl());
        ChocolatePage chocolatePageWindow = authorizationPage.authorizationInSystem(authorizationPage.getLogin(), authorizationPage.getPassword()); //первый аогумент - логин, второй - пароль

        Assert.assertEquals("Ошибка авторизации в системе", "ПЕРЕХОД В ПОДСИСТЕМЫ",
                chocolatePageWindow.getStringEnterInSubSystemsText());
        System.out.println("Успешная авторизация");
        driver.quit();

    }

    @Test
    public void test2() {
        AuthorizationPage authorizationPage = new AuthorizationPage(driver);
        driver.get(authorizationPage.getAuthWebUrl());
        ChocolatePage chocolatePageWindow = authorizationPage.authorizationInSystem(authorizationPage.getLogin(), authorizationPage.getPassword()); //первый аогумент - логин, второй - пароль
        Assert.assertTrue("Ошибка при создании обращения",chocolatePageWindow.
                goToClaimPageWindow().
                toNewBrowserWindow(1).
                pressClaimButton().
                selectCreateNewClaim().
                clickOnSubdivisionListButton().
                selectValueOfSubdivisionList().
                clickOnTypeOfSendingList().
                selectValueOfSendingList().
                fillSurnameField().
                fillNameField().
                fillFathernameField().
                scrollToNewElement(CreateFormClaimPage.goToOrganizationBlock()).
                clickOnStreetListButton().
                selectStreetFromList().saveCreatedClaim().
                getTextAboutCreatedClaim().
                equals("Создано входящее обращение"));
        System.out.println("Успешное создание обращения");

        driver.quit();
    }
}
