import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }


    private By searchInput = By.xpath("//input[@placeholder=\"Найти блюдо, ресторан или магазин\"]");   //Локатор поля ввода для поиска
    private By searchButton = By.xpath("//span[text()=\"Найти\"]/parent::button");  //Локатор кнопки "Найти" в поле поиска
    private By addressButton = By.xpath("//span[@class=\"DesktopAddressButton_address\"]"); //Локатор кнопки изменения адреса
    private By addressInput = By.xpath("//input[@placeholder=\"Введите улицу и дом\"]");    //Локатор поля ввода адреса
    private By okButton = By.xpath("//button[text()=\"ОК\"]");  //Локатор кнопки подтверждения адреса
    private By sortButton = By.xpath("//span[text()=\"Сортировка\"]");  //Локатор кнопки "Сортировка"
    private By showButton = By.xpath("//span[text()=\"Показать\"]/parent::*");  //Локатор кнопки "Показать" в параметрах сортировки
    private By deliveryButton = By.xpath("//span[text()=\"Доставка:\"]");   //Локатор кнопки "Доставка"
    private By restaurantHeaderName = By.xpath("//h1[@class=\"RestaurantPageHeader_name\"]");   //Локатор названия ресторана
    private By addButton = By.xpath("//button//span[text()=\"Добавить\"]"); //Локатор кнопки "Добавить"
    private By checkoutButton = By.xpath("//button//span[text()=\"Оформить заказ\"]");  //Локатор кнопки "Оформить заказ"

    public void Search(String searchRequest){
        driver.findElement(searchInput).sendKeys(searchRequest);
        driver.findElement(searchButton).click();
    }
    //Метод поиска (получает текст запроса, вставляет его в поисковую строку, нажимает кнопку найти

    public String SearchResult(){
        return driver.findElement(searchInput).getAttribute("value");
    }
    //Метод возвращает результат поискового запроса

    public void location(String address){
        driver.findElement(addressButton).click();
        driver.findElement(addressInput).sendKeys(address, Keys.ENTER);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(okButton).click();
    }
    //Метод ввода локации (получает значение адрес, открывает поле ввода, вводит адрес, нажимает кнопку "Ок"

    public String locationResult(){
        return driver.findElement(addressButton).getText();
    }
    //Метод возвращает текущий адрес

    public void selectOption(String options){
        String sortOptions = String.format("//ul[@class=\"DesktopCatalogBduPageSort_options\"]//span[text()='%s']",options);
        driver.findElement(sortButton).click();
        driver.findElement(By.xpath(sortOptions)).click();
        driver.findElement(showButton).click();
    }
    //Метод выбора параметра сортировки(получает значение параметра сортировки, открывает параметры сортировки, выбирает параметр, нажимает кнопку "Показать"

    public void selectDelivery(String day, String time){
        String dayOptions = String.format("//ul[@class=\"DesktopDeliveryTimePane_buttons\"]/li[text()='%s']", day);
        String timeOptions = String.format("//ul[@class=\"DesktopDeliveryTimePane_timeList\"]/li[text()='%s']", time);
        driver.findElement(deliveryButton).click();
        driver.findElement(By.xpath(dayOptions)).click();
        driver.findElement(By.xpath(timeOptions)).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //Метод выбора параметров доставки(получает значения параметров доставки, открывает параметры доставки, выбирает день и время доставки, нажимает кнопку "Показать"

    public void dishChoice(String dishName){
        String dishChoice = String.format("//nav[@class=\"DesktopCatalogPageFiltersBdu_root\"]//a[text()='%s']", dishName);
        driver.findElement(By.xpath(dishChoice)).click();
    }
    //Метод выбора подборки ресторанов по блюду или кухне(получает значение блюда или кухни и нажимает соответствующую кнопку

    public void restaurantChoice(String restaurantName) {
        String restaurant = String.format("//h2[text()=\"Все рестораны\"]/parent::*/following-sibling::*//h3[text()='%s']", restaurantName);
        driver.findElement(By.xpath(restaurant)).click();
    }
    //Метод выбора ресторана(получает название ресторана и выбирает его

    public String getRestaurantName(){
        return driver.findElement(restaurantHeaderName).getText();
    }
    //Мето получает название ресторана в карточке ресторана

    public void chooseProduct(String categoryName, String productName){
        String product = String.format("//h2[text()='%s']/parent::*/parent::*/parent::*/following-sibling::*//h3[text()='%s']", categoryName, productName);
        driver.findElement(By.xpath(product)).click();
    }
    //Метод выбора продукта из категории(получает название категории, продукта и выбирает его

    public void optionsProductRadioButton(String categoryName, String optionsName){
        String options = String.format("//div[@class=\"ModalOptionsGroup_root\"]//div[text()='%s']/parent::*/parent::*/following-sibling::*//span[text()='%s']/parent::*/preceding-sibling::*//input", categoryName, optionsName);
        driver.findElement(By.xpath(options)).click();
    }
    //Метод выбора параметров продукта с помощью радиокнопок(получает название категории и опции, выбирает соответствующую радиокнопку

    public void optionsProductCheckbox(String categoryName, String optionsName){
        String options = String.format("//div[@class=\"ModalOptionsGroup_root\"]//div[text()='%s']/parent::*/parent::*/following-sibling::*//span[text()='%s']/parent::*/preceding-sibling::*//input", categoryName, optionsName);
        if(!driver.findElement(By.xpath(options)).isSelected())
            driver.findElement(By.xpath(options)).click();
    }
    //Метод выбора параметров продукта с помощью чекбоксов(получает название категории и опции, выбирает соответствующий чекбокс

    public void addProducts(){
        driver.findElement(addButton).click();
    }
    //Метод для добавления товара в корзину

    public void checkout(){
        driver.findElement(checkoutButton).click();
    }
    //Метод для нажатия кнопки "Оформить заказ"

}