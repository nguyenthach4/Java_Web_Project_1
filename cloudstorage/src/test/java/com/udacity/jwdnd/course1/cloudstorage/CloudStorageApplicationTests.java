package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.utils.TestHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import java.io.File;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    @BeforeAll
    static void beforeAll() {

        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        System.setProperty("webdriver.chrome.driver", TestHelper.getDriverUrl());
        this.driver = new ChromeDriver();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.close();
            driver.quit();
        }
    }

    @Test
    public void getLoginPage() {
        driver.get("http://localhost:" + this.port + "/login");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    /**
     * PLEASE DO NOT DELETE THIS method.
     * Helper method for Udacity-supplied sanity checks.
     **/
    private void doMockSignUp(String firstName, String lastName, String userName, String password) {
        // Create a dummy account for logging in later.

        // Visit the sign-up page.
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
        driver.get("http://localhost:" + this.port + "/signup");
        webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));

        // Fill out credentials
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
        WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
        inputFirstName.click();
        inputFirstName.sendKeys(firstName);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
        WebElement inputLastName = driver.findElement(By.id("inputLastName"));
        inputLastName.click();
        inputLastName.sendKeys(lastName);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
        WebElement inputUsername = driver.findElement(By.id("inputUsername"));
        inputUsername.click();
        inputUsername.sendKeys(userName);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
        WebElement inputPassword = driver.findElement(By.id("inputPassword"));
        inputPassword.click();
        inputPassword.sendKeys(password);

        // Attempt to sign up.
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
        WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
        buttonSignUp.click();

		/* Check that the sign up was successful. 
		// You may have to modify the element "success-msg" and the sign-up 
		// success message below depening on the rest of your code.
		*/
        Assertions.assertTrue(driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));
    }


    /**
     * PLEASE DO NOT DELETE THIS method.
     * Helper method for Udacity-supplied sanity checks.
     **/
    private void doLogIn(String userName, String password) {
        // Log in to our dummy account.
        driver.get("http://localhost:" + this.port + "/login");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
        WebElement loginUserName = driver.findElement(By.id("inputUsername"));
        loginUserName.click();
        loginUserName.sendKeys(userName);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
        WebElement loginPassword = driver.findElement(By.id("inputPassword"));
        loginPassword.click();
        loginPassword.sendKeys(password);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        webDriverWait.until(ExpectedConditions.titleContains("Home"));

    }

    /**
     * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
     * rest of your code.
     * This test is provided by Udacity to perform some basic sanity testing of
     * your code to ensure that it meets certain rubric criteria.
     * <p>
     * If this test is failing, please ensure that you are handling redirecting users
     * back to the login page after a succesful sign up.
     * Read more about the requirement in the rubric:
     * https://review.udacity.com/#!/rubrics/2724/view
     */
    @Test
    @DisplayName("testRedirection")
    public void testRedirection() {
        // Create a test account
        doMockSignUp("Redirection", "Test", "testRedirection", "TestRedirection@123");

        // Check if we have been redirected to the log in page.
        Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
    }

    /**
     * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
     * rest of your code.
     * This test is provided by Udacity to perform some basic sanity testing of
     * your code to ensure that it meets certain rubric criteria.
     * <p>
     * If this test is failing, please ensure that you are handling bad URLs
     * gracefully, for example with a custom error page.
     * <p>
     * Read more about custom error pages at:
     * https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page
     */
    @Test
    @DisplayName("testBadUrl")
    public void testBadUrl() {
        // Create a test account
        doMockSignUp("URL", "Test", "testBadUrl", "TestBadUrl@123");
        doLogIn("testBadUrl", "TestBadUrl@123");

        // Try to access a random made-up URL.
        driver.get("http://localhost:" + this.port + "/some-random-page");
        Assertions.assertFalse(driver.getPageSource().contains("Whitelabel Error Page"));
    }


    /**
     * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
     * rest of your code.
     * This test is provided by Udacity to perform some basic sanity testing of
     * your code to ensure that it meets certain rubric criteria.
     * <p>
     * If this test is failing, please ensure that you are handling uploading large files (>1MB),
     * gracefully in your code.
     * <p>
     * Read more about file size limits here:
     * https://spring.io/guides/gs/uploading-files/ under the "Tuning File Upload Limits" section.
     */
    @Test
    @DisplayName("testLargeUpload")
    public void testLargeUpload() {
        // Create a test account
        doMockSignUp("Nguyen", "Thach", "nguyent4", "Nguyen@123");
        doLogIn("nguyent4", "Nguyen@123");

        // Try to upload an arbitrary large file
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
        String fileName = "upload5m.zip";

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
        WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
        fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());

        WebElement uploadButton = driver.findElement(By.id("uploadButton"));
        uploadButton.click();
        try {
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Large File upload failed");
        }
        Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));

    }

    /**
     * addNewNote.
     *
     * @throws Exception
     */
    public void addNewNote() throws Exception {
        try {
            WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

            WebElement noteTab = driver.findElement(By.id("nav-notes-tab"));
            noteTab.click();

            Thread.sleep(500);

            WebElement showNotes = driver.findElement(By.id("showNotes"));
            showNotes.click();

            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
            WebElement noteTitle = driver.findElement(By.id("note-title"));
            noteTitle.click();
            noteTitle.sendKeys("New Task 1");

            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-description")));
            WebElement noteDescription = driver.findElement(By.id("note-description"));
            noteDescription.click();
            noteDescription.sendKeys("New Description 1");

            WebElement noteButton = driver.findElement(By.id("saveNotes"));
            noteButton.click();

            // COMPARE
            Assertions.assertTrue(driver.findElement(By.id("show-note-title")).getText().contains("New Task 1"));
            Assertions.assertTrue(driver.findElement(By.id("show-note-description")).getText().contains("New Description 1"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * editNote.
     *
     * @throws Exception
     */
    @Test
    @DisplayName("editNote")
    public void editNote() throws Exception {
        try {
            doMockSignUp("Nguyen", "Thach", "nguyent3", "Nguyen@123");

            doLogIn("nguyent3", "Nguyen@123");

            addNewNote();

            WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

            WebElement noteTab = driver.findElement(By.id("nav-notes-tab"));
            noteTab.click();

            Thread.sleep(500);

            WebElement editNotes = driver.findElement(By.id("editNotes"));
            editNotes.click();

            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
            WebElement noteTitle = driver.findElement(By.id("note-title"));
            noteTitle.clear();
            noteTitle.sendKeys("New Task 2");

            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-description")));
            WebElement noteDescription = driver.findElement(By.id("note-description"));
            noteDescription.clear();
            noteDescription.sendKeys("New Description 2");

            WebElement saveNotes = driver.findElement(By.id("saveNotes"));
            saveNotes.click();


            // COMPARE
            Assertions.assertTrue(driver.findElement(By.id("show-note-title")).getText().contains("New Task 2"));
            Assertions.assertTrue(driver.findElement(By.id("show-note-description")).getText().contains("New Description 2"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Delete note
     */
    @Test
    public void deleteNote() throws Exception {
        try {
            doMockSignUp("Nguyen", "Thach", "nguyent5", "Nguyen@123");

            doLogIn("nguyent5", "Nguyen@123");

            addNewNote();

            WebElement noteTab = driver.findElement(By.id("nav-notes-tab"));
            noteTab.click();

            Thread.sleep(500);

            WebElement deleteNotes = driver.findElement(By.id("deleteNotes"));
            deleteNotes.click();

            driver.get("http://localhost:" + this.port + "/home");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Add credential
     */
    public void addCredential() throws Exception {
        try {

            WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

            WebElement credentialsTab = driver.findElement(By.id("nav-credentials-tab"));
            credentialsTab.click();

            Thread.sleep(500);

            WebElement showCredentials = driver.findElement(By.id("showCredentials"));
            showCredentials.click();

            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-url")));
            WebElement credentialUrl = driver.findElement(By.id("credential-url"));
            credentialUrl.click();
            credentialUrl.sendKeys("https://learn.udacity.com/nanodegrees/");

            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-username")));
            WebElement credentialUsername = driver.findElement(By.id("credential-username"));
            credentialUsername.click();
            credentialUsername.sendKeys("nguyent4");

            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-password")));
            WebElement credentialPassword = driver.findElement(By.id("credential-password"));
            credentialPassword.click();
            credentialPassword.sendKeys("Nguyen@123");

            WebElement credentialButton = driver.findElement(By.id("saveCredentials"));
            credentialButton.click();

            Thread.sleep(500);
            Assertions.assertTrue(driver.findElement(By.id("tblCredentialUrl")).getText().contains("https://learn.udacity.com/nanodegrees/"));
            Assertions.assertTrue(driver.findElement(By.id("tblCredentialUsername")).getText().contains("nguyent4"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * editCredential.
     *
     * @throws Exception
     */
    @Test
    @DisplayName("editCredential")
    public void editCredential() throws Exception {
        try {
            doMockSignUp("Nguyen", "Thach", "nguyent1", "Nguyen@123");

            doLogIn("nguyent1", "Nguyen@123");

            addCredential();

            WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

            WebElement noteTab = driver.findElement(By.id("nav-credentials-tab"));
            noteTab.click();

            Thread.sleep(500);

            WebElement editNotes = driver.findElement(By.id("btnEditCredential"));
            editNotes.click();

            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-url")));
            WebElement credentialUrl = driver.findElement(By.id("credential-url"));
            credentialUrl.clear();
            credentialUrl.sendKeys("https://www.youtube.com/");

            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-username")));
            WebElement credentialUsername = driver.findElement(By.id("credential-username"));
            credentialUsername.clear();
            credentialUsername.sendKeys("nguyen123");

            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-password")));
            WebElement credentialPassword = driver.findElement(By.id("credential-password"));
            credentialPassword.clear();
            credentialPassword.sendKeys("123@Nguyen");

            WebElement saveNotes = driver.findElement(By.id("saveCredentials"));
            saveNotes.click();


            Assertions.assertTrue(driver.findElement(By.id("tblCredentialUrl")).getText().contains("https://www.youtube.com/"));
            Assertions.assertTrue(driver.findElement(By.id("tblCredentialUsername")).getText().contains("nguyen123"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * deleteCredential.
     *
     * @throws Exception
     */
    @Test
    @DisplayName("deleteCredential")
    public void deleteCredential() throws Exception {
        try {
            doMockSignUp("Nguyen", "Thach", "nguyent2", "Nguyen@123");

            doLogIn("nguyent2", "Nguyen@123");
            addCredential();

            WebElement noteTab = driver.findElement(By.id("nav-credentials-tab"));
            noteTab.click();

            Thread.sleep(500);

            WebElement deleteNotes = driver.findElement(By.id("aDeleteCredential"));
            deleteNotes.click();

            driver.get("http://localhost:" + this.port + "/home");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
