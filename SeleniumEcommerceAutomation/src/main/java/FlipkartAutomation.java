import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import java.util.List;
import java.util.Arrays;

public class FlipkartAutomation {
    private WebDriver driver;
    private WebDriverWait wait;

    public FlipkartAutomation() {
        // Set up Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");

        // Initialize driver
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void automateEcommerceSite() {
        try {
            // Step 1: Open e-commerce site (using Amazon as example)
            System.out.println("Step 1: Opening e-commerce website...");
            driver.get("https://www.amazon.in"); // FIXED: Use Amazon URL
            Thread.sleep(2000);

            // Step 2: Navigate to sign-in page
            System.out.println("Step 2: Navigating to login page...");
            WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("nav-link-accountList")));
            signInButton.click();

            // Step 3: Login with test credentials
            System.out.println("Step 3: Attempting to login...");
            loginToSite("enter-test-mail", "enter-your-password");//enter your dummy id and password here

            // Step 4: Search and select a product
            System.out.println("Step 4: Searching for a product...");
            searchAndSelectProduct("laptop");

            // Step 5: Add to cart (optional)
            System.out.println("Step 5: Adding product to cart...");
            addToCart();

            // Step 6: Logout
            System.out.println("Step 6: Logging out...");
            logout();

            System.out.println("Automation completed successfully!");

        } catch (Exception e) {
            System.err.println("Error during automation: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the browser
            if (driver != null) {
                driver.quit();
            }
        }
    }

    private void loginToSite(String email, String password) {
        try {
            // Enter email
            WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.id("ap_email")));
            emailField.clear();
            emailField.sendKeys(email);

            // Click continue button
            WebElement continueButton = driver.findElement(By.id("continue"));
            continueButton.click();

            // Enter password
            WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.id("ap_password")));
            passwordField.clear();
            passwordField.sendKeys(password);

            // Click sign-in button
            WebElement signInSubmit = driver.findElement(By.id("signInSubmit"));
            signInSubmit.click();

            Thread.sleep(3000); // Wait for login to complete

        } catch (Exception e) {
            System.out.println("Login failed or may require additional verification: " + e.getMessage());
            // Continue with the rest of the automation even if login fails
        }
    }

    private void searchAndSelectProduct(String searchTerm) {
        try {
            // Find search box and enter search term
            WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.id("twotabsearchtextbox")));
            searchBox.clear();
            searchBox.sendKeys(searchTerm);

            // Click search button
            WebElement searchButton = driver.findElement(By.id("nav-search-submit-button"));
            searchButton.click();

            // Wait for search results to load
            Thread.sleep(3000);

            // Select the first product from search results
            List<WebElement> products = driver.findElements(
                    By.cssSelector("[data-component-type='s-search-result'] h2 a"));

            if (!products.isEmpty()) {
                System.out.println("Found " + products.size() + " products. Selecting the first one...");
                products.get(0).click();
                Thread.sleep(2000);
            } else {
                System.out.println("No products found for search term: " + searchTerm);
            }

        } catch (Exception e) {
            System.err.println("Error during product search: " + e.getMessage());
        }
    }

    private void addToCart() {
        try {
            // Look for "Add to Cart" button with multiple possible selectors
            WebElement addToCartButton = null;
            String[] cartButtonSelectors = {
                    "#add-to-cart-button",
                    "[name='submit.add-to-cart']",
                    ".a-button-primary[title*='Add to Cart']"
            };
            for (String selector : cartButtonSelectors) {
                try {
                    addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(
                            By.cssSelector(selector)));
                    break;
                } catch (Exception e) {
                    // Try next selector
                }
            }

            if (addToCartButton != null) {
                // Scroll to the button to ensure it's visible
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                        addToCartButton);
                Thread.sleep(1000);

                addToCartButton.click();
                System.out.println("Product added to cart successfully!");
                Thread.sleep(2000);

                // Handle any popup dialogs that might appear
                try {
                    WebElement closeButton = driver.findElement(By.cssSelector("[aria-label='Close']"));
                    closeButton.click();
                } catch (Exception e) {
                    // No popup to close
                }

            } else {
                System.out.println("Add to Cart button not found. Product page may have different layout.");
            }

        } catch (Exception e) {
            System.err.println("Error while adding to cart: " + e.getMessage());
        }
    }

    private void logout() {
        try {
            // Navigate to account menu
            WebElement accountMenu = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("nav-link-accountList")));
            accountMenu.click();

            // Click sign out
            WebElement signOutButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.linkText("Sign Out")));
            signOutButton.click();

            System.out.println("Logged out successfully!");
            Thread.sleep(2000);

        } catch (Exception e) {
            System.err.println("Error during logout: " + e.getMessage());
            System.out.println("Continuing to close browser...");
        }
    }

    // Alternative method for Flipkart automation
    public void automateFlipkart() {
        try {
            System.out.println("Starting Flipkart automation...");
            driver.get("https://www.flipkart.com");
            Thread.sleep(2000);

            // Close login popup if it appears
            try {
                WebElement closePopup = driver.findElement(By.cssSelector("button._2KpZ6l._2doB4z"));
                closePopup.click();
            } catch (Exception e) {
                // No popup to close
            }

            // Search for a product
            WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.name("q")));
            searchBox.sendKeys("smartphone");

            WebElement searchButton = driver.findElement(By.cssSelector("button[type='submit']"));
            searchButton.click();

            Thread.sleep(3000);

            // Select first product
            List<WebElement> products = driver.findElements(By.cssSelector("._4rR01T"));
            if (!products.isEmpty()) {
                products.get(0).click();
                Thread.sleep(2000);

                // Try to add to cart
                try {
                    WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(
                            By.cssSelector("._2KpZ6l._2U9uOA._3v1-ww")));
                    addToCartBtn.click();
                    System.out.println("Product added to cart on Flipkart!");
                } catch (Exception e) {
                    System.out.println("Could not add to cart on Flipkart: " + e.getMessage());
                }
            }

        } catch (Exception e) {
            System.err.println("Error during Flipkart automation: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Make sure to set the ChromeDriver path before running
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");

        FlipkartAutomation automation = new FlipkartAutomation();

        // Choose which site to automate
        String site = args.length > 0 ? args[0] : "amazon";

        if ("flipkart".equalsIgnoreCase(site)) {
            automation.automateFlipkart();
        } else {
            automation.automateEcommerceSite(); // Default to Amazon
        }
    }
}
