package ChototUiPOM.resource;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class loginPage extends baseTest{
    /* ************************** xPath Element ************************** */
    //Locate textbox phone number login
    @FindBy(xpath = "//body/div[@id='__next']/div[1]/main[1]/div[1]/form[1]/div[2]/div[1]/input[1]")
    WebElement phone;
    //Locate textbox password login
    @FindBy(xpath = "//body/div[@id='__next']/div[1]/main[1]/div[1]/form[1]/div[3]/div[1]/input[1]")
    WebElement password;
    //Locate button click login
    @FindBy(xpath = "//button[contains(text(),'Đăng nhập')]")
    WebElement loginBtnClick;
    //Locate button access login page
    @FindBy(xpath = "//b[contains(text(),'Đăng nhập')]")
    WebElement loginBtnAccess;
    //Locate warning 'Không tìm thấy thông tin người dùng.'
    @FindBy(xpath = "//p[contains(text(),'Không tìm thấy thông tin người dùng.')]")
    WebElement warningInvalidPhone;
    //Locate warning icon when input wrong phone number
    @FindBy(xpath = "//body/div[@id='__next']/div[1]/div[1]/main[1]/div[1]/form[1]/div[2]/div[1]/button[1]/span[1]")
    WebElement warningIconInvalidLogin;
    //Locate icon (X) to delete phone was input to textbox
    @FindBy(xpath = "//body/div[@id='__next']/div[1]/div[1]/main[1]/div[1]/form[1]/div[2]/div[1]/button[1]/span[1]")
    WebElement clearPhoneIcon;
    //Locate warning wrong phone number of password
    @FindBy(xpath = "//div[contains(@class,'error')]")
    WebElement warningWrongPassword;
    //Locate button 'Hiện' on password's textbox
    @FindBy(xpath = "//button[contains(text(),'Hiện')]")
    WebElement showPasswordIcon;
    //Locate button 'Quản lý tin'
    @FindBy(xpath = "//span[contains(text(),'Quản lý tin')]")
    WebElement quanLyTinBtn;
    //Popup inform account get blocked
    @FindBy(xpath = "//body/div[@id='__next']/div[@id='modal-root']/div[1]/div[1]/div[2]")
    WebElement blockAccountPopup;
    //Locate text 'Tài khoản của bạn đã bị khoá'
    @FindBy(xpath = "//h3[contains(text(),'Tài khoản của bạn đã bị khoá')]")
    WebElement textBlockAccount;
    //Button 'Đã Hiểu" on popup block user
    @FindBy(xpath = "//button[contains(text(),'Đã hiểu')]")
    WebElement daHieuBtn;
    //Button 'Tôi bán' on Login page
    @FindBy(xpath = "//span[contains(text(),'Tôi bán')]")
    WebElement toiBanBtn;
    //Locate button 'Thêm'
    @FindBy(xpath = "//span[contains(text(),'Thêm')]")
    WebElement themBtn;
    //Move action to menu page (button "Thêm")
    @FindBy(xpath = "//header/div[1]/div[2]/div[1]/div[1]/div[1]")
    WebElement moveActionPopupMenuThem;
    //Button 'Đăng xuất'
    @FindBy(xpath = "//div[contains(text(),'Đăng xuất')]")
    WebElement logoutBtn;
    /* ************************** xPath Element ************************** */

    /* ************************** Testing ************************** */

    //Constructor
    public loginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /* ************************** Login Valid ************************** */
    //Click link text "Đăng nhập" access to login page
    public void accessToLoginPage() {
        loginBtnAccess.click();
    }

    //Input phone and password to textbox on login page
    public void inputPhoneAndPassword(String phoneNbr, String pwd) {
        phone.click();
        phone.sendKeys(phoneNbr);
        password.click();
        password.sendKeys(pwd);
    }
    public void inputPhone (String phoneNbr) {
        phone.click();
        phone.sendKeys(phoneNbr);
    }
    public void inputPassword (String pwd) {
        password.click();
        password.sendKeys(pwd);
    }

    public void verifyPopupBlockAccount() {
        WebDriverWait wait =new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.valueOf(textBlockAccount))));
        {
            //Move action to popup
            WebElement element = blockAccountPopup;
            Actions builder = new Actions(driver);
            builder.moveToElement(element).release().perform();
        }
        System.out.println("After 5 times wrong: " + textBlockAccount.getText());
    }


    public void verifyWarningWrongPassword (){
        loginBtnClick.click();
        inputPhoneAndPassword("0367599013","123123");

        for (int i = 1; i < 5; i++) {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            loginBtnClick.click();
            System.out.println(" -> " + warningWrongPassword.getText());
            wait.until(ExpectedConditions.visibilityOfElementLocated((By) warningWrongPassword));
        }
    }

    //Click button 'Đăng nhập' to login
    public void clickBtnDangNhap() {
        loginBtnClick.click();
    }
    //Verify warning when input invalid phone
    public void showWarningInvalidPhone() {
        String warningDisplay = warningInvalidPhone.getText();
        if (warningDisplay.equals("Không tìm thấy thông tin người dùng.")) {
            System.out.println(warningDisplay);
        } else
            System.out.println("There is no warning when input wrong username");
    }
    /* ************************** Logout ************************** */

    public void clickBtnThem() {
        themBtn.click();
    }

    public void clickBtnLogout() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        {
            //Move to menu page
            WebElement element = driver.findElement((By) moveActionPopupMenuThem);
            Actions builder = new Actions(driver);
            builder.moveToElement(element).release().perform();
            //Scroll down to button "Đăng xuất"
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        }
    }
    public void clickBtnDangXuat (){
        logoutBtn.click();
    }

    //Verify logout success, button "Đăng nhập" appear
    public void verifyBtnLoginAfterLogout() {
        String value = driver.findElement(By.cssSelector("b")).getText();
        System.out.println("Logout success, back to homepage, button: " + value + " appear!");
    }
}
