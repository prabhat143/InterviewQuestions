import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AmazonSearch {
    WebDriver driver;
    @Test
    public void startTest() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.amazon.in/");

        WebElement searchInpBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchInpBox.sendKeys("lg soundbar");
        searchInpBox.sendKeys(Keys.ENTER);

        List<WebElement> brandList = driver.findElements(By.xpath("//div[@id='brandsRefinements']//li"));

        for(WebElement element : brandList){
            if(element.getAttribute("aria-label").equalsIgnoreCase("lg")){
                element.findElement(By.xpath(".//i[@class='a-icon a-icon-checkbox']")).click();
                break;
            }
        }

        //span[@class='a-size-medium a-color-base a-text-normal']
        List<WebElement> searchResults = driver.findElements(By.xpath("//div[contains(@class,'s-main-slot s-result-list ')]//div[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]"));

        Map<String,Integer> productPriceNameMap = new HashMap<>();

        searchResults.stream().iterator().forEachRemaining(e->{
            String elementPrice, elementName = null;
            elementName = e.findElement(By.xpath(".//span[@class='a-size-medium a-color-base a-text-normal']")).getText();

            try {
                elementPrice = e.findElement(By.xpath(".//span[@class='a-price-whole']")).getText();
            }catch (NoSuchElementException exception){
                elementPrice = "0";
            }
            
            productPriceNameMap.put(elementName, Integer.parseInt(elementPrice.replaceAll("[^0-9]","")));
        });

        System.out.println("Before sorting");
        productPriceNameMap.entrySet().iterator().forEachRemaining(e->{
            System.out.println(e.getKey()+ " "+e.getValue());
        });

        System.out.println("After sorting");
        sortedHashMapByValues(productPriceNameMap).entrySet().iterator().forEachRemaining(e->{
            System.out.println(e.getKey()+ " "+e.getValue());
        });
    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }

    public static Map<String,Integer> sortedHashMapByValues(Map<String,Integer> hashmap) {
        // Insert all key-value pairs into TreeMap using a custom comparator.
        TreeMap treeMap = new TreeMap<String,Integer>((o1, o2) -> {
            if ( hashmap.get(o1) != hashmap.get(o2) )
                return Integer.compare( hashmap.get(o2) , hashmap.get(o1) );

            return o2.compareTo(o1);
        });

        treeMap.putAll(hashmap);
        return treeMap;
    }
}
