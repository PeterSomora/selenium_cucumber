package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pages.BananaPage;

public class BananaSteps {
    private BananaPage bananaPage;

    @Given("^I am on banana shop page$")
    public void iAmOnBananaShopPage() {
        getBananaPage().openPage();
    }

    @When("^price for one banana is 1.5$")
    public void priceForOneBananaIs() {
    }

    @When("^I have chosen 1 banana$")
    public void iHaveChosenBanana() {
        getBananaPage().enterBananaAmount("1");
        getBananaPage().submitBananaAmount();
    }

    private BananaPage getBananaPage() {
        if (bananaPage == null) {
            bananaPage = new BananaPage();
        }
        return bananaPage;
    }
}
