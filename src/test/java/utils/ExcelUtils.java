package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
    static String userDirectory = System.getProperty("user.dir");
    private static final String FILE_PATH = userDirectory+"/src/TestDataExcel.xlsx";

    @DataProvider(name = "excelData")
    public static Object[][] provideData() throws IOException {
        FileInputStream file = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet("Sheet1");

        // Assuming you want to take data from row 1 (index 1) and column 1 (username)
        int rowIndex = 1; // Change this index for different rows as needed
        int colIndex = 0; // Column index for username

        String cellData = sheet.getRow(rowIndex).getCell(colIndex).toString();

        workbook.close();

        // Return the cellData in a 2D array format
        return new Object[][] {{cellData}}; // Wrap the single value in an Object array
    }

    @DataProvider(name = "excelDataUsername")
    public static Object[][] provideDataUsername() throws IOException {
        FileInputStream file = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet("Sheet1");

        // Assuming you want to take data from row 1 (index 1) and column 1 (username)
        int rowIndex = 1; // Change this index for different rows as needed
        int colIndex = 1; // Column index for username

        String cellData = sheet.getRow(rowIndex).getCell(colIndex).toString();

        workbook.close();

        // Return the cellData in a 2D array format
        return new Object[][] {{cellData}}; // Wrap the single value in an Object array
    }

    @DataProvider(name = "excelDataPassword")
    public static Object[][] provideDataPassword() throws IOException {
        FileInputStream file = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet("Sheet1");

        // Assuming you want to take data from row 1 (index 1) and column 1 (username)
        int rowIndex = 1; // Change this index for different rows as needed
        int colIndex = 2; // Column index for username

        String cellData = sheet.getRow(rowIndex).getCell(colIndex).toString();

        workbook.close();

        // Return the cellData in a 2D array format
        return new Object[][] {{cellData}}; // Wrap the single value in an Object array
    }


    @DataProvider(name = "countryData")
    public static Object[] provideDataCountryName() throws IOException {
        FileInputStream file = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet("Sheet1");
        int rowIndex = 1; // Change this index for different rows as needed
        int colIndex = 3; // Column index for username
      //  ArrayList<String> al=new ArrayList<>();
        Object[] cellData = new String[]{(sheet.getRow(rowIndex).getCell(colIndex)).getStringCellValue()};
        Row row = sheet.getRow(rowIndex);
        // Get the cell at the specified index
        Cell cell = row.getCell(colIndex); // Retrieve the cell's value based on its type
        String cellValue = cell.getStringCellValue();
        System.out.println("Cell Value: " + cellValue);
        workbook.close();
       // ArrayList<String> al=new ArrayList<>();
        // Return the cellData in a 2D array format
        return cellData; // Wrap the single value in an Object array
    }


/*    @DataProvider(name = "excelDataCountry")
    public static Object[][] provideDataCountryName() throws IOException {
        String countryName = getCellData(1, 3);  // Fetches a single cell value from Excel
        return new Object[][] { { countryName } };  // Wrapping the single value in a 2D array
    }

    public static String getCellData(int rowIndex, int colIndex) throws IOException {
        // Your existing code for fetching cell data from Excel
        FileInputStream file = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet("Sheet1");
        String cellData = sheet.getRow(rowIndex).getCell(colIndex).getStringCellValue();
      //  workbook.close();
        return cellData;
    }*/
}
