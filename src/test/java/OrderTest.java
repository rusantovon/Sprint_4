import org.example.factory.DriverFactory;
import org.example.pageobject.OrderPage;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class OrderTest {

    @Rule
    public DriverFactory factory = new DriverFactory();

    private final String name;

    private final String surname;

    private final String address;

    private final String metro;

    private final String phoneNumber;

    private final String date;

    private final String timePeriod;

    private final String color;

    private final String comment;

    public OrderTest(String name, String surname, String address, String metro, String phoneNumber, String date, String timePeriod, String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.timePeriod = timePeriod;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "Заказ клиента: {0} {1}")
    public static Object[][] getOrderData(){
        return new Object[][] {
                {"Антон", "Русанов", "Кукуево 13", "Черкизовская", "+79184992533", "01.06.2027", "двое суток", "чёрный жемчуг", "лалала"},
                {"Анна", "Кевлярова", "Луговая 23", "Красные Ворота", "+79182551610", "23.05.2027", "пятеро суток", "серая безысходность", "хахаха"}
        };
    }

    @Test
    public void makeAnOrderThroughFirstButtonTest() throws InterruptedException {
        OrderPage orderPage = new OrderPage(factory.getDriver());

        orderPage.openSite();
        orderPage.clickFirstOrderButton();

        orderPage.fillPersonalData(name, surname, address, metro, phoneNumber);
        orderPage.fillRentData(date, timePeriod, color, comment);

        orderPage.clickYesButton();

        Assert.assertTrue("Не удалось создать заказ", orderPage.isOrderConfirmationVisible());
    }

    @Test
    public void makeAnOrderThroughSecondButtonTest() {
        OrderPage orderPage = new OrderPage(factory.getDriver());

        orderPage.openSite();
        orderPage.scrollToOrderButton();
        orderPage.clickSecondOrderButton();

        orderPage.fillPersonalData(name, surname, address, metro, phoneNumber);
        orderPage.fillRentData(date, timePeriod, color, comment);

        orderPage.clickYesButton();

        Assert.assertTrue("Не удалось создать заказ", orderPage.isOrderConfirmationVisible());
    }
}
