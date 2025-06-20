# Selenium E-commerce Automation

This project demonstrates how to automate basic user interactions on a popular e-commerce website—such as Amazon India or Flipkart—using Selenium WebDriver with Java. The script follows a realistic user journey: opening the site, logging in, selecting a product, optionally adding it to the cart, and finally logging out or closing the session.

---

## What This Project Does

- **Opens an e-commerce website** (Amazon India or Flipkart)
- **Logs in** using your test or dummy account credentials
- **Selects a product** (either from the homepage or after a search)
- **(Optional) Adds the selected product to the shopping cart**
- **Logs out** or closes the browser session

This automation is perfect for learning Selenium basics, testing e-commerce workflows, or as part of a QA automation assignment.

---

## How to Run the Selenium Script

### Prerequisites

Before you start, make sure you have the following installed and set up:

- **Java 11 or higher** (for running the project)
- **Maven** (for managing dependencies and building the project)
- **Google Chrome browser** (since the script uses ChromeDriver)
- **ChromeDriver** (download the correct version for your Chrome browser from [ChromeDriver Downloads](https://chromedriver.chromium.org/downloads) and ensure it is available in your system PATH or project directory)

---

### Step-by-Step Setup

1. **Clone the Repository**

   Open your terminal and run:

git clone https://github.com/Mayank-kanojiya/SeleniumE-commerce.git
cd SeleniumE-commerce   

This will download the project to your computer and move you into the project directory.

2. **Configure Your Test Credentials**

- Open the main Java file (for example, `AmazonAutomation.java` or `FlipkartAutomation.java`) in your favorite code editor.
- Look for the variables that store the email and password (usually named `email` and `password`).
- Replace the placeholder values with your test or dummy account credentials.
- **Tip:** If you’re using Amazon, you may need to handle the "Continue" button after entering the email.

3. **Install Dependencies**

- Maven will automatically download all the required libraries (like Selenium WebDriver) for you.
- Just make sure you’re in the project directory when running Maven commands.

4. **Run the Script**

- **Using IntelliJ IDEA:**
  - Open the project in IntelliJ.
  - Right-click the main Java file and select **Run**.
- **Using the Terminal:**
  - Make sure you’re in the project directory.
  - Run:
    ```
    mvn compile exec:java -Dexec.mainClass="AmazonAutomation"
    ```
    (Replace `AmazonAutomation` with the name of your main class if it’s different.)

---

## Project Structure

Here’s what your project folder should look like:

├── pom.xml # Maven project configuration file
├── README.md # This file, with setup and usage instructions
└── src
└── main
└── java
└── AmazonAutomation.java # Main Selenium automation script


---

## Notes & Tips

- **Amazon Login:** Amazon’s login page sometimes asks you to click "Continue" after entering your email. Make sure your script handles this step if you’re automating Amazon.
- **Product Selection:** The script can be set to select a product from the homepage or after performing a search. You can customize this part to target specific products or categories.
- **Add to Cart (Optional):** If you want to see how adding to cart works, uncomment or add the relevant code section in your script.
- **Logout/Close Session:** The script logs out or closes the browser at the end, ensuring a clean finish to each test run.
- **Troubleshooting:** If you see errors about ChromeDriver, double-check that it’s installed and in your PATH. If elements aren’t found, the website structure may have changed—update your locators (XPath, CSS selectors) as needed.

---

## Support & Contributions

If you have questions or want to improve this project, feel free to open an issue or submit a pull request on GitHub. Your feedback is welcome!

---

## License

This project is open-source and available under the MIT License.

