package CalendarTests;

import base.BaseTests;
import helpers.dataDriven;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.devtools.v85.emulation.Emulation;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class CalendarTests extends BaseTests {

    @Test
    public void testGetCurrentLocation(){
        homePage.clearLocationText();
        homePage.clickOnGetLocationButton();
        String currentLocation = homePage.getCurrentLocation();
        assertEquals(currentLocation,"Alexandria Governorate, Egypt","wrong location");
    }
    @Test
    public void gettingUserCurrentLocation() throws InterruptedException {
        setLocation(30,30,1);
        driver.findElement(By.xpath("//button[contains(.,'Get')]")).click();
    }

    @Test
    public void applyDaylightSaving() throws InterruptedException {
        Thread.sleep(5000);
        homePage.clickOnDayLightSavingsButton();
    }

    @Test
    public void TestLocation() throws IOException {
        dataDriven d = new dataDriven();
        ArrayList<String> data =d.getData("Egypt");
        setLocation(Integer.parseInt(data.get(1)),Integer.parseInt(data.get(2)),Integer.parseInt(data.get(3)));
        driver.findElement(By.xpath("//button[contains(.,'Get')]")).click();
    }



    public void setLocation(int latitude,int longitude,int accuracy){
        Map<String, Object> coordinates = new HashMap<String, Object>();
        coordinates.put("latitude",latitude);
        coordinates.put("longitude",longitude);
        coordinates.put("accuracy",accuracy);
        driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
    }







}
