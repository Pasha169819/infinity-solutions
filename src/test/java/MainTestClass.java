import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class MainTestClass {

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp(){
        System.setProperty("webdriver.edge.driver", "C:\\Users\\pasha\\IdeaProjects\\Infinnity Solutions\\Drivers\\msedgedriver.exe");
        driver = new EdgeDriver();
        Actions actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://eda.yandex.ru/chelyabinsk?shippingType=delivery");
        mainPage = new MainPage(driver);
        String location = "октябрьская 7";
        mainPage.location(location);
        String locationResult = mainPage.locationResult();
        Assert.assertEquals(locationResult, locationResult);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void Search(){
        String searchRequest = "суши";
        mainPage.Search(searchRequest);
        String searchResult = mainPage.SearchResult();
        Assert.assertEquals(searchRequest, searchResult);
    }

    @Test
    public void Sort(){
        mainPage.selectOption("Быстрые");
    }

    @Test
    public void ShippingOptions(){
        mainPage.selectDelivery("Завтра", "14:00");
    }

    @Test
    public void CategorySetting() {
        mainPage.dishChoice("Суши");
    }

    @Test
    public void RestaurantSelection() {
        String restaurantName = "Subway";
        mainPage.restaurantChoice(restaurantName);
        String restName = mainPage.getRestaurantName();
        Assert.assertEquals(restaurantName, restName);
    }

    @Test
    public void SmokeTest() {
        String restaurantName = "Coffee Like";
        mainPage.restaurantChoice(restaurantName);
        mainPage.chooseProduct("Популярные блюда", "Капучино");
        mainPage.optionsProductRadioButton("Объем", "200 мл");
        mainPage.optionsProductCheckbox("Добавки", "Сахар");
        mainPage.addProducts();
        mainPage.checkout();
    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
