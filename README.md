# Zilch Mobile Website Test Automation
This project provides an automated test solution for the Zilch mobile website, designed to validate functionality on both 
iOS and Android mobile simulators.

## Technology Stack & Setup
Technologies: `Java, Appium, Selenium, TestNG, Log4j, Maven`

Setup: Install `Java (JDK 8+)` and `Appium` (e.g., npm install -g appium).
Ensure Appium Server is running (start via terminal with appium or use Appium Desktop). 

Dependencies are managed by `Maven`; reload the project if needed.

## Configuration
This project includes configurations for both iOS and Android simulators. You can customize settings such as device name, platform version, and app URL within the properties files located at:

`./src/main/resources/properties/`
