package automationBoa;
import java.time.Duration;
import java.util.List;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestBoa {
    WebDriver driver;
    List<WebElement> listResult;
    List<WebElement> listOptions;
    List<WebElement> listOptTra;
    List<WebElement> listCalendar;
    List<WebElement> listPassenger;
    List<WebElement> Send;
    List<WebElement> countTickets;
    List<WebElement> cardList;
    WebElement card;

    @BeforeTest
    public void before() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.boa.bo/");
        
        acceptCookies();
        card = driver.findElement(By.id("flight-search"));
        listResult = driver.findElements(By.cssSelector("div .flex.w-full.items-center.justify-start.gap-3 button"));
        listOptions = driver.findElements(By.cssSelector(".cursor-pointer"));
        
       
        
    }

    @Test (testName = "Inicio")
    public void test() throws InterruptedException {
        scroll(card);
        listResult.get(3).click();
        
        listOptions.get(38).click();
        
        // Verificar 
        listOptTra = driver.findElements(By.cssSelector("div.max-h-v ul li"));
        Thread.sleep(3000);
        listOptTra.get(0).click();
        //destino sc
        listOptions.get(39).click();
        listOptTra = driver.findElements(By.cssSelector("div.max-h-v ul li"));
        Thread.sleep(3000);
        listOptTra.get(1).click();
        //salida
        listOptions.get(40).click();
        Thread.sleep(3000);
        listCalendar=driver.findElements(By.cssSelector("div.rdrMonth div.rdrDays button"));
        listCalendar.get(35).click();
        
        //pasajeros
        listOptions.get(46).click();
        Thread.sleep(3000);
        listPassenger=driver.findElements(By.cssSelector("div.top-full button"));
        listPassenger.get(3).click();
        listPassenger.get(5).click();
        listOptions.get(46).click();
        //send
        Send=driver.findElements(By.xpath("//button[(text()='Buscar vuelo')]"));
        Send.get(1).click();
        
      Thread.sleep(100000);
        
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(140));
       wait.until(ExpectedConditions.urlToBe("https://www.boa.bo/Reserva/Inicio"));
       
       driver.switchTo().frame(driver.findElement(By.cssSelector("iframe")));

       
       	card = driver.findElement(By.cssSelector("main div.simplebar-content h6.MuiTypography-root"));
       	scroll(card);
       	countTickets=driver.findElements(By.cssSelector("div.MuiBox-root.css-1hoinuc"));
        //countTickets=driver.findElements(By.xpath("//div[span[text()='lun.. 30 sep.. 2024']]"));
       System.out.println("Existen "+countTickets.size()+" vuelos disponibles para la fecha: lun.. 30 sep.. 2024");
       
    }
    

    @AfterTest
    public void after() throws InterruptedException {
    	Thread.sleep(5000);
    	  driver.close();
    }
    
    public void acceptCookies() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Aceptar')]"))); 
        acceptButton.click();
    }

    public void scroll(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
