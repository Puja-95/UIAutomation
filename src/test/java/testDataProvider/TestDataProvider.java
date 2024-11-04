package testDataProvider;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "loginData")
    public Object[][] getData() {
        return new Object[][]{
                //ycWC2KipkF
            {"admin-lyca@yopmail.com"}
        };
    }

    @DataProvider(name = "loginDataPassword")
    public Object[][] getDataPassword() {
        return new Object[][]{
                //ycWC2KipkF
                {"d50NhBwJUB"}
        };
    }
}
