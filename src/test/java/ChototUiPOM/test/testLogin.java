package ChototUiPOM.test;

import ChototUiPOM.resource.baseTest;
import ChototUiPOM.resource.loginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class testLogin extends baseTest {
    loginPage login;

    public testLogin(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /* ************************** Login Valid ************************** */
    @Test(priority = 0)
    public void loginValidPhone() {
        login = new loginPage(driver);
        login.accessToLoginPage();
        login.inputPhoneAndPassword("0367599015", "123456");
        login.clickBtnDangNhap();
    }

    /* ************************** Logout  ************************** */
    @Test(priority = 1)
    public void  logoutAccount() {
        login.clickBtnThem();
        login.clickBtnLogout();
        login.clickBtnDangXuat();
        login.verifyBtnLoginAfterLogout();
    }

    /* ************************** Login Invalid ************************** */
    @Test(priority = 2)
    public void loginInvalid() {
        login.accessToLoginPage();
        //Input invalid phone
        login.inputPhoneAndPassword("8273912873", "123123");
        login.clickBtnDangNhap();
        System.out.print("Test case invalid phone: ");
        login.showWarningInvalidPhone();
        //Clear textbox more code than refresh
        driver.navigate().refresh();
        //Input phone include special charater
        login.inputPhoneAndPassword("123@423@", "123123");
        login.clickBtnDangNhap();
        System.out.print("Test case phone with special character: ");
        login.showWarningInvalidPhone();
        //Refresh for next test case
        driver.navigate().refresh();
    }

    @Test(priority = 3)
    //Input wrong password then account get blocked
    public void inputWrongPwdToGetBlocked(String phoneNbr, String pwd) {
        login.clickBtnDangNhap();
        login.inputPhoneAndPassword("0367599013", "123123");
    }

    //Account get blocked when wrong password 5 times
    @Test(priority = 4)
    public void getBlockWhenWrongPwdFiveTimes() {
        login.accessToLoginPage();
        login.inputPhoneAndPassword("0367599040", "123123");
        login.verifyPopupBlockAccount();

    }
}
