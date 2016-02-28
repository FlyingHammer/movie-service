package com.wakaleo.myflix.movies.features.steps;

import com.jayway.restassured.RestAssured;
import com.wakaleo.myflix.movies.MovieServiceApplication;
import com.wakaleo.myflix.movies.features.serenitysteps.MovieCatalog;
import com.wakaleo.myflix.movies.model.Movie;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static net.serenitybdd.rest.SerenityRest.rest;
import static org.hamcrest.Matchers.equalTo;

@ContextConfiguration(loader = SpringApplicationContextLoader.class,
                      classes = MovieServiceApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ArtistSteps {

    @Steps
    MovieCatalog theMovieCatalog;

    @Value("${local.server.port}")
    int port;


    @Before
    public void configurePorts() {
        RestAssured.port = port;
    }

    @When("^I consult the filmography of (.*)$")
    public void i_consult_the_filmography_of(String actor) throws Throwable {
      rest().when().get("/artists/" + actor).then().body("name",equalTo(actor));

        /*

         {
            "name" : "Ben Stiller",
            "acted-in" : (...)
            "directed-in : (...)
          }

         */

    }

    @Then("^I should see the following films that he has acted in:$")
    public void i_should_see_the_following_films_that_he_has_acted_in(List<String> moviesActedIn) throws Throwable {


    }

    @Then("^I should see the following films that he has directed:$")
    public void i_should_see_the_following_films_that_he_has_directed(List<String> moviesDirected) throws Throwable {

        System.out.printf("Directed " + moviesDirected);

    }

}