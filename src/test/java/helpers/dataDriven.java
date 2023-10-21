package helpers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class dataDriven {

    public ArrayList<String> getData(String country) throws IOException {
        DataFormatter formatter = new DataFormatter();
        String val;
        ArrayList<String> a=new ArrayList<String>( );

        FileInputStream fis = new FileInputStream("/home/blink22/Desktop/Untitled 1.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        int sheets = workbook.getNumberOfSheets();
        for(int i =0; i < sheets;i++)
        {
            if(workbook.getSheetName(i).equalsIgnoreCase("Countries"))
            {
                XSSFSheet sheet = workbook.getSheetAt(i);
                Iterator<Row> rows = sheet.iterator();
                Row firstRow = rows.next();
                Iterator<Cell> ce=firstRow.cellIterator();
                int column = 0;

                //Identify country
                while (rows.hasNext()){
                    Row r = rows.next();
                    if(r.getCell(column).getStringCellValue().equalsIgnoreCase(country))
                    {
                        Iterator<Cell> cv= r.cellIterator();
                        while(cv.hasNext()){
                            val = formatter.formatCellValue(cv.next());
                            a.add(val);
                        }
                    }
                }
            }
        }
        return a;
    }
}
