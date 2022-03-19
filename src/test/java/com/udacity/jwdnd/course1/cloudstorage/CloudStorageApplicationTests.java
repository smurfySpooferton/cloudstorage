package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.File;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.firefoxdriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		this.driver = new FirefoxDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
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
	private void doMockSignUp(String firstName, String lastName, String userName, String password){
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
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signupButton")));
		WebElement buttonSignUp = driver.findElement(By.id("signupButton"));
		buttonSignUp.click();

		/* Check that the sign up was successful. 
		// You may have to modify the element "success-msg" and the sign-up 
		// success message below depening on the rest of your code.
		*/
		//Assertions.assertTrue(driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));

		//User will be redirected after login, no success dialogue provided
	}

	
	
	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doLogIn(String userName, String password)
	{
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
	 * 
	 * If this test is failing, please ensure that you are handling redirecting users 
	 * back to the login page after a succesful sign up.
	 * Read more about the requirement in the rubric: 
	 * https://review.udacity.com/#!/rubrics/2724/view 
	 */
	@Test
	public void testRedirection() {
		// Create a test account
		doMockSignUp("Redirection","Test","RT","123");
		
		// Check if we have been redirected to the log in page.
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling bad URLs 
	 * gracefully, for example with a custom error page.
	 * 
	 * Read more about custom error pages at: 
	 * https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page
	 */
	@Test
	public void testBadUrl() {
		// Create a test account
		doMockSignUp("URL","Test","UT","123");
		doLogIn("UT", "123");
		// Try to access a random made-up URL.
		driver.get("http://localhost:" + this.port + "/some-random-page");
		Assertions.assertFalse(driver.getPageSource().contains("Whitelabel Error Page"));
	}


	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling uploading large files (>1MB),
	 * gracefully in your code. 
	 * 
	 * Read more about file size limits here: 
	 * https://spring.io/guides/gs/uploading-files/ under the "Tuning File Upload Limits" section.
	 */
	@Test
	public void testLargeUpload() {
		// Create a test account
		doMockSignUp("Large File","Test","LFT","123");
		doLogIn("LFT", "123");

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

	@Test
	public void testAddNote() {
		doMockSignUp("Test", "Test", "AddNote", "123");
		doLogIn("AddNote", "123");
		addNote("Hello Note", "This is a test!");
	}

	@Test
	public void testEditNote() {
		doMockSignUp("Test", "Test", "EditNote", "123");
		doLogIn("EditNote", "123");
		addNote("Hello Note", "This is a test!");
		WebElement item = driver.findElement(By.id("note-table"));
		List<WebElement> buttons = item.findElements(By.className("btn-success"));
		buttons.get(buttons.size() - 1).click();
		driver.findElement(By.id("note-title")).sendKeys("s");
		driver.findElement(By.id("note-description")).sendKeys(" Editing seems to work.");
		driver.findElement(By.id("noteSubmit")).submit();
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success-msg")));
		assertLastNoteEntry("Hello Notes", "This is a test! Editing seems to work.");
	}

	@Test
	public void testDeleteNote() {
		doMockSignUp("Test", "Test", "DeleteNote", "123");
		doLogIn("DeleteNote", "123");

		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		addNote("DELETE", "DELETE THIS ITEM");
		assertLastNoteEntry("DELETE", "DELETE THIS ITEM");

		WebElement item = driver.findElement(By.id("note-table"));
		List<WebElement> buttons = item.findElements(By.className("btn-danger"));
		buttons.get(buttons.size() - 1).click();
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success-msg")));

		List<WebElement> entries = driver.findElement(By.id("note-table")).findElements(By.className("note-entry"));
		for (WebElement entry : entries) {
			assertNoteEntry(entry, "DELETE", "DELETE THIS ITEM", false);
		}
	}

	private void assertLastNoteEntry(String expectedTitle, String expectedDescription) {
		List<WebElement> entries = driver.findElement(By.id("note-table")).findElements(By.className("note-entry"));
		for (WebElement entry : entries) {
			assertNoteEntry(entry, expectedTitle, expectedDescription, true);
		}
	}

	private void assertNoteEntry(WebElement item, String expectedTitle, String expectedDescription, boolean expected) {
		String title = item.findElement(By.className("note-title")).getAttribute("innerHTML");
		String description = item.findElement(By.className("note-description")).getAttribute("innerHTML");
		if (expected) {
			Assertions.assertTrue(title.equals(expectedTitle));
			Assertions.assertTrue(description.equals(expectedDescription));
		} else {
			Assertions.assertFalse(title.equals(expectedTitle));
			Assertions.assertFalse(description.equals(expectedDescription));
		}
	}

	private void addNote(String title, String description) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		driver.findElement(By.id("nav-notes-tab")).click();
		WebElement item = driver.findElement(By.id("nav-notes"));
		item.findElement(By.className("btn-info")).click();
		driver.findElement(By.id("note-title")).sendKeys(title);
		driver.findElement(By.id("note-description")).sendKeys(description);
		driver.findElement(By.id("noteSubmit")).submit();
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success-msg")));
	}

	@Test
	public void testAddCredentials() {
		doMockSignUp("Test", "Test", "AddCredentials", "123");
		doLogIn("AddCredentials", "123");
		addCredentials("localhost:8080", "username", "password");
	}

	@Test
	public void testDeleteCredentials() {
		doMockSignUp("Test", "Test", "DeleteCredentials", "123");
		doLogIn("DeleteCredentials", "123");

		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		addCredentials("DELETE", "username", "password");
		assertLastCredentials("DELETE", "username", "password");

		WebElement item = driver.findElement(By.id("credentials-table"));
		List<WebElement> buttons = item.findElements(By.className("btn-danger"));
		buttons.get(buttons.size() - 1).click();
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success-msg")));

		List<WebElement> entries = driver.findElement(By.id("credentials-table")).findElements(By.className("credentials-entry"));
		for (WebElement entry : entries) {
			assertNoteEntry(entry, "DELETE", "DELETE THIS ITEM", false);
		}
	}

	@Test
	public void testEditCredentials() {
		doMockSignUp("Test", "Test", "EditCredentials", "123");
		doLogIn("EditCredentials", "123");
		addCredentials("localhost", "DH", "12");
		WebElement item = driver.findElement(By.id("credentials-table"));
		List<WebElement> buttons = item.findElements(By.className("btn-success"));
		buttons.get(buttons.size() - 1).click();
		driver.findElement(By.id("credentials-url")).sendKeys(":8080");
		driver.findElement(By.id("credentials-username")).sendKeys("E");
		driver.findElement(By.id("credentials-password")).sendKeys("3");
		driver.findElement(By.id("credentialSubmit")).submit();

		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success-msg")));
		assertLastCredentials("localhost:8080", "DHE", "123");
	}

	private void addCredentials(String url, String username, String password) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		driver.findElement(By.id("nav-credentials-tab")).click();
		WebElement item = driver.findElement(By.id("nav-credentials"));
		item.findElement(By.className("btn-info")).click();
		driver.findElement(By.id("credentials-url")).sendKeys(url);
		driver.findElement(By.id("credentials-username")).sendKeys(username);
		driver.findElement(By.id("credentials-password")).sendKeys(password);
		driver.findElement(By.id("credentialSubmit")).submit();
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success-msg")));
	}

	private void assertLastCredentials(String expectedUrl, String expectedUsername, String expectedPassword) {
		WebElement newItem = driver.findElement(By.id("credentials-table"));
		List<WebElement> entries = newItem.findElements(By.className("credentials-entry"));
		assertCredentials(entries.get(entries.size() - 1), expectedUrl, expectedUsername, expectedPassword, true);
	}

	private void assertCredentials(WebElement item, String expectedUrl, String expectedUsername, String expectedPassword, boolean expected) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success-msg")));
		String url = item.findElement(By.className("credentials-url")).getAttribute("innerHTML");
		String username = item.findElement(By.className("credentials-username")).getAttribute("innerHTML");
		String password = item.findElement(By.className("credentials-password")).getAttribute("innerHTML");
		if (expected) {
			Assertions.assertTrue(url.equals(expectedUrl));
			Assertions.assertTrue(username.equals(expectedUsername));
			Assertions.assertTrue(password.equals(expectedPassword));
		} else {
			Assertions.assertFalse(url.equals(expectedUrl));
			Assertions.assertFalse(username.equals(expectedUsername));
			Assertions.assertFalse(password.equals(expectedPassword));
		}
	}
}
