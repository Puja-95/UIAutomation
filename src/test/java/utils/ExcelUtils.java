package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    public static Object[] provideDataUsername() throws IOException {
        FileInputStream file = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet("Sheet1");

        // Assuming you want to take data from row 1 (index 1) and column 1 (username)
        int rowIndex = 1; // Change this index for different rows as needed
        int colIndex = 1; // Column index for username

        String cellData = sheet.getRow(rowIndex).getCell(colIndex).toString();

        workbook.close();

        // Return the cellData in a 2D array format
        return new Object[] {cellData}; // Wrap the single value in an Object array
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
        return cellData; // Wrap the single value in an Object array
    }





    @DataProvider(name = "enterMailId")
    public static Object[] emailId() throws IOException {
        FileInputStream file = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet("Sheet1");

        // Define specific rows and columns for data extraction
        int rowIndex = 1; // Change this index for different rows as needed
        int colIndex = 4; // Column index for username

        String cellData = sheet.getRow(rowIndex).getCell(colIndex).toString();

        workbook.close();

        // Return the cellData in a 2D array format
        return new Object[] {cellData};
    }

    @DataProvider(name = "enterFirstName")
    public static Object[] firstName() throws IOException {
        FileInputStream file = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet("Sheet1");
        int rowIndex = 1; // Change this index for different rows as needed
        int colIndex = 5; // Column index for username

        String cellData = sheet.getRow(rowIndex).getCell(colIndex).toString();

        workbook.close();
        System.out.println(cellData);
        // Return the cellData in a 2D array format
        return new Object[] {cellData};
    }

    @DataProvider(name = "enterInvalidUsername")
    public static Object[] invalidUser() throws IOException {
        FileInputStream file = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet("Sheet1");
        int rowIndex = 2; // Change this index for different rows as needed
        int colIndex = 1; // Column index for username

        String cellData = sheet.getRow(rowIndex).getCell(colIndex).toString();
//String cellData1=sheet.getRow(rowIndex1).getCell(colIndex1).toString();
        workbook.close();
        System.out.println(cellData);
        // Return the cellData in a 2D array format
        return new Object[] {cellData};
    }

    @DataProvider(name = "invalidPassword")
    public static Object[] invalidPassword() throws IOException {
        FileInputStream file = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet("Sheet1");
        int rowIndex = 2; // Change this index for different rows as needed
        int colIndex = 2; // Column index for username

        String cellData = sheet.getRow(rowIndex).getCell(colIndex).toString();
//String cellData1=sheet.getRow(rowIndex1).getCell(colIndex1).toString();
        workbook.close();
        System.out.println(cellData);
        // Return the cellData in a 2D array format
        return new Object[] {cellData};
    }

    @DataProvider(name = "enterSecondName")
    public static Object[][] lastName() throws IOException {
        FileInputStream file = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet("Sheet1");

        // Define specific rows and columns for data extraction
        int rowIndex = 1; // Change this index for different rows as needed
        int colIndex = 6; // Column index for username

        String cellData = sheet.getRow(rowIndex).getCell(colIndex).toString();

        workbook.close();

        // Return the cellData in a 2D array format
        return new Object[][] {{cellData}};
    }


    @DataProvider(name = "role")
    public static Object[] addingRole() throws IOException {
        FileInputStream file = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet("Sheet1");
        int rowIndex = 1; // Change this index for different rows as needed
        int colIndex = 7; // Column index for username
        //  ArrayList<String> al=new ArrayList<>();
        Object[] cellData = new String[]{(sheet.getRow(rowIndex).getCell(colIndex)).getStringCellValue()};
        Row row = sheet.getRow(rowIndex);
        // Get the cell at the specified index
        Cell cell = row.getCell(colIndex); // Retrieve the cell's value based on its type
        String cellValue = cell.getStringCellValue();
        System.out.println("Cell Value: " + cellValue);
        workbook.close();
        return cellData; // Wrap the single value in an Object array
    }

}
