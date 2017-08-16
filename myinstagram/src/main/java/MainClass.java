import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileWriter;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 15.08.2017.
 */
public class MainClass {
    private static ChromeDriver driver;
    private static String alphabet="abcdefghijklmnopqrstuvwxyz0123456789";
    private static char[] a=alphabet.toCharArray();
    private static int randomIndex;
    public static void main(String[] args) {

        int emailLength=10,nameLength=10,userLength=10,passLength=10;
        WebElement registrateButton,email,name,user,pass;
        String emailString,nameString,userString,passString,savePath,chromeDriverPath;
        //setting save path
        savePath="D:/Insta1.txt";
        //setting chromedriver path
        chromeDriverPath="C://Program Files (x86)/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath );
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        driver.get("https://www.instagram.com");
        driver.manage().window().maximize();
        email=driver.findElement(By.name("emailOrPhone"));
        name=driver.findElement(By.name("fullName"));
        user=driver.findElement(By.name("username"));
        pass=driver.findElement(By.name("password"));
        registrateButton=driver.findElements(By.className("_qv64e")).get(1);
        emailString=generateRandomStringOfLength(emailLength)+"@taric.com";
        nameString=generateRandomStringOfLength(nameLength);
        userString=generateRandomStringOfLength(userLength);
        passString=generateRandomStringOfLength(passLength);
        try {
            email.sendKeys(emailString);
            name.sendKeys(nameString);
            user.sendKeys(userString);
            pass.sendKeys(passString);
            registrateButton.click();
            System.out.println("email:");

            //File writing part
            FileWriter writer = new FileWriter(savePath, false);
            writer.write("email:"+emailString);
            writer.append('\n');
            writer.flush();
            writer.write("user:"+userString);
            writer.append('\n');
            writer.flush();
            writer.write("password:"+passString);
            writer.append('\n');
            writer.flush();
            writer.close();

        }
        catch(Exception e){
            System.out.println("Error occured");
        }

    }
    public static char generateRandomChar(){
        randomIndex=(int)(Math.random()* alphabet.length());
        return a[randomIndex];
    }
    public static String generateRandomStringOfLength(int length){
        StringBuilder s=new StringBuilder();
        for (int i=0;i<length;i++){
            s.append(generateRandomChar());
        }
        return s.toString();
    }
}
