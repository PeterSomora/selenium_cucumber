package steps;

import cucumber.api.java.en.*;
import pages.RegistrationPage;

public class RegistrationSteps {
    private static final String INVALID_PASSWORD = "1234";
    private static final String VALID_PASSWORD = "12345";
    private static final String VALID_EMAIL = "dionyz@fekete.hu";
    private static final String VALID_NAME = "dionyz";
    private static final String VALID_SURNAME = "fekete";
    private RegistrationPage registrationPage;

    @Given("^I am on the registration page$")
    public void iAmOnTheRegistrationPage() {
        getRegistrationPage().openPage();
    }

    @And("^I enter email (.*)$")
    public void iEnterEmailMatkoMatkoSk(String email) {
        getRegistrationPage().enterEmail(email);
    }

    @And("^I enter name (.*)$")
    public void iEnterNameMatko(String name) {
        getRegistrationPage().enterName(name);
    }

    @And("^I enter surname (.*)$")
    public void iEnterSurname(String surname) {
        getRegistrationPage().enterSurname(surname);
    }

    @But("^I enter password (.*)$")
    public void iEnterPassword(String password) {
        registrationPage.enterPassword(password);

    }

    @But("^I repeat password (.*)$")
    public void iRepeatPassword(String password) {
        registrationPage.enterPasswordRepeat(password);
    }

    @And("^I check robot checkbox$")
    public void iCheckRobotCheckbox() {
        getRegistrationPage().checkRobotCheckbox();
    }

    @When("^I click on registration button$")
    public void iClickOnRegistrationButton() {
        getRegistrationPage().submitRegistration();
    }

    private RegistrationPage getRegistrationPage() {
        if (registrationPage == null) {
            registrationPage = new RegistrationPage();
        }
        return registrationPage;
    }

    @Then("^the registration fails$")
    public void theRegistrationFails() {
        getRegistrationPage().checkRegistrationFailed();
    }

    @When("^I enter and repeat invalid password$")
    public void iEnterInvalidPassword() {
        getRegistrationPage().enterPassword(INVALID_PASSWORD);
        getRegistrationPage().enterPasswordRepeat(INVALID_PASSWORD);
    }

    @And("^I repeat invalid password$")
    public void iRepeatInvalidPassword() {
    }

    @When("^I enter and repeat valid password$")
    public void iEnterAndRepeatValidPassword() {
        getRegistrationPage().enterPassword(VALID_PASSWORD);
        getRegistrationPage().enterPasswordRepeat(VALID_PASSWORD);
    }

    @Then("^the registration succeeds$")
    public void theRegistrationSucceeds() {
        getRegistrationPage().checkRegistrationSucceeded();
    }

    @And("^I enter valid email, name, surname$")
    public void iEnterValidEmailNameSurname() {
      getRegistrationPage().enterEmail(VALID_EMAIL);
      getRegistrationPage().enterName(VALID_NAME);
      getRegistrationPage().enterSurname(VALID_SURNAME);
    }
}