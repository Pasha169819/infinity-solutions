import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class MainTestClass {

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp(){
        System.setProperty("webdriver.edge.driver", "C:\\Users\\pasha\\IdeaProjects\\Infinnity Solutions\\Drivers\\msedgedriver.exe");  //устанавливаем путь к драйверу
        driver = new EdgeDriver();  //Инициализируем драйвер
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);  //Задаем неявное ожидание
        driver.manage().window().maximize();  //Открываем окно на полный экран
        driver.get("https://eda.yandex.ru/chelyabinsk?shippingType=delivery");  //Открываем страницу
        mainPage = new MainPage(driver, wait);  //Создаем объект mainPage
        String location = "октябрьская 7";  //Создаем переменную и указываем адрес
        mainPage.location(location);   //Метод ввода локации (получает значение адрес("октябрьская 7"), открывает поле ввода, вводит адрес, нажимает кнопку "Ок"
    }

    @Test
    public void Search(){
        String searchRequest = "суши";   //Создаем переменную и указываем текст запроса("суши")
        mainPage.Search(searchRequest);   //Метод поиска (получает текст запроса, вставляет его в поисковую строку, нажимает кнопку найти
        String searchResult = mainPage.SearchResult();  //Получает текущее значение запроса
        Assert.assertEquals(searchRequest, searchResult);  //Сравнивает ожидаемый запрос и фактический
    }

    @Test
    public void Sort(){
        mainPage.selectOption("Быстрые");   //Метод выбора параметра сортировки(получает значение параметра сортировки("Быстрые"), открывает параметры сортировки, выбирает параметр, нажимает кнопку "Показать"
    }

    @Test
    public void ShippingOptions(){
        mainPage.selectDelivery("Завтра", "14:00"); //Метод выбора параметров доставки(получает значения параметров доставки("Завтра", "14:00", открывает параметры доставки, выбирает день и время доставки, нажимает кнопку "Показать"
    }  //

    @Test
    public void CategorySetting() {
        mainPage.dishChoice("Суши");    //Метод выбора подборки ресторанов по блюду или кухне(получает значение блюда или кухни("Суши") и нажимает соответствующую кнопку)
    }  //

    @Test
    public void RestaurantSelection() {
        String restaurantName = "Subway";  //Создаем переменную и указываем название ресторана "Subway"
        mainPage.restaurantChoice(restaurantName);  //Метод выбора ресторана(получает название ресторана и выбирает его)
        String restName = mainPage.getRestaurantName();  //Метод получает название ресторана в карточке ресторана
        Assert.assertEquals(restaurantName, restName);  //Сравниваем ожидаемое название рестора и текущее
    }

    @Test
    public void SmokeTestRestaurant() {
        mainPage.selectDelivery("Завтра", "02:00");  //Метод выбора параметров доставки(получает значения параметров доставки("Завтра", "01:00", открывает параметры доставки, выбирает день и время доставки, нажимает кнопку "Показать"
        String restaurantName = "Coffee Like";  //Создаем переменную и указываем название ресторана "Coffee Like"
        mainPage.restaurantChoice(restaurantName);  //Метод выбора ресторана(получает название ресторана и выбирает его)
        mainPage.chooseProduct("Популярные блюда", "Капучино");  //Метод выбора продукта из категории(получает название категории("Популярные блюда"), продукта("Капучино") и выбирает его
        mainPage.optionsProductRadioButton("Объем", "200 мл");  //Метод выбора параметров продукта с помощью радиокнопок(получает название категории("Объем") и опции("200 мл"), выбирает соответствующую радиокнопку
        mainPage.optionsProductCheckbox("Добавки", "Сахар");  //Метод выбора параметров продукта с помощью чекбоксов(получает название категории("Добавки") и опции("Сахар"), выбирает соответствующий чекбокс
        mainPage.addProducts();  //Метод для добавления товара в корзину
        mainPage.checkout();  //Метод для нажатия кнопки "Оформить заказ"
    }

    @Test
    public void SmokeTestSupermarket(){
        String searchRequest = "Ашан";   //Создаем переменную и указываем текст запроса("суши")
        mainPage.Search(searchRequest);   //Метод поиска (получает текст запроса, вставляет его в поисковую строку, нажимает кнопку найти
        String searchResult = mainPage.SearchResult();  //получает текущее значение запроса
        Assert.assertEquals(searchRequest, searchResult);  //сравнивает ожидаемый запрос и фактический
        mainPage.ChoiceOfResult("Ашан Гипермаркет");  //Метод выбора ресторана или супермаркета в разделе поиска(получает название супермаркета("Ашан Гипермаркет") и открывает его
        mainPage.StoreCategorySelection("Овощи и зелень");  //Метод выбора категории в каталоге супермаркета(получает название категории("Овощи и зелень"), открывает её и ждет, пока она откроется)
        mainPage.ProductSelection("Томаты черри");  //Метод выбора продукта(получает название продукта("Томаты черри") и добавляет его в корзину)
        mainPage.StoreCategorySelection("Мясо и птица");    //Метод выбора категории в каталоге супермаркета
        mainPage.ProductSelection("Крылышки куриные Глазовская Птица охлажденные 0,5-0,7 кг, 1 упаковка ~");    //Метод выбора продукта
        mainPage.StoreCategorySelection("Молоко и яйца");   //Метод выбора категории в каталоге супермаркета
        mainPage.ProductSelection("Молоко Первый вкус  2.5%");   //Метод выбора продукта
        mainPage.StoreCategorySelection("Сладости");    //Метод выбора категории в каталоге супермаркета
        mainPage.ProductSelection("Шоколад Milka Bubbles белый пористый с фундуком");   //Метод выбора продукта
        mainPage.Order();   //Метод оформления заказа
    }

//    @After
//    public void tearDown(){
//        driver.quit();  //закрываем браузер
//    }

}
