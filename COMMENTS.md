# COMMENTS.md

## Overview

This project implements an Appium + Java + TestNG mobile automation framework using the Page Object Model (POM) design pattern. The framework is designed to be modular, reusable, and easy to maintain.

---

## Framework Design

- Implemented using Java, Appium, TestNG, and Maven.
- Followed the Page Object Model (POM) design pattern.
- Driver creation is centralized using DriverFactory.
- Common actions are implemented in BasePage.
- Test data is externalized using properties files.
- Logging is implemented using Log4j2.
- HTML reporting is implemented using Extent Reports.
- Screenshot attachment for failed tests

---

## Locator Strategy

- Android locators were primarily implemented using `AppiumBy.androidUIAutomator()` with `resource-id` wherever available, as it provides better readability and reliability than XPath.
- XPath was used only in cases where a unique resource-id or accessibility identifier was not available.
- The current implementation is optimized for Android. To support iOS, platform-specific locators (XCUITest/iOS predicates or accessibility identifiers) would need to be added while reusing the existing framework structure.

## Cross-Platform Support

- The framework has been structured to support both Android and iOS through a common `DriverFactory`.
- The current assignment was implemented for Android, so page objects primarily use Android UIAutomator locators.
- iOS support can be added by introducing platform-specific locators while keeping the same test structure and business logic.
- 
## Test Coverage

The following scenarios have been automated:

### High Priority

- Login with Standard User
- Login with Locked User
- Login with Performance User
- Login with Problem User
- Product List Display
- Product Details Validation
- Product Details Page Navigation
- Add Product to Cart
- Remove Product from Cart
- Cart Validation
- Checkout
- End-to-End Purchase Flow (Product Details → Checkout → Logout)
- End-to-End Shopping Journey (Multiple Products → Remove Product → Continue Shopping → Checkout → Logout)

### Medium Priority

- Continue Shopping
- Logout
- Multiple Products

---

## Assumptions

- The application under test is already installed on the Android emulator.
- Test users provided by the application are valid.
- Checkout information is read from the test data properties file.
- Tests are executed independently with a fresh application session.

---

## Known Limitations

- The `problem_user` account intentionally has inconsistent UI behaviour. The automated test validates successful login and product page availability but does not rely on image rendering.
- Product selection uses randomization for better coverage. The same product may occasionally be selected in different test executions.

---

## Future Improvements

If more time were available, I would extend the framework with:


- Retry mechanism for flaky tests
- Parallel execution
- Data Providers for multiple test users
- CI/CD integration with GitHub Actions or Jenkins
- Appium execution using Docker


---

## Notes

The focus of this assignment was to build a clean, maintainable automation framework rather than simply automate individual test cases. The framework has been structured so that additional scenarios can be added with minimal code duplication.

## Deliverables

The `Video` folder contains:

- Test execution recording.
- Generated Extent Report.
- Screenshots captured for failed test cases.