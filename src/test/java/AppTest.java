// import java.util.ArrayList;

import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.junit.rules.ExternalResource;
import org.sql2o.*;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;


public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("List of Stylists");
  }

  @Test
  public void stylistIsAddedTest() {
    goTo("http://localhost:4567/");
    fill("#name").with("Derek");
    submit(".btn");
    assertThat(pageSource()).contains("Derek");
  }

  @Test
  public void stylistIsDisplayedOnItsPageTest() {
    Stylist myStylist = new Stylist("Monica");
    myStylist.save();
    String stylistPath = String.format("http://localhost:4567/stylists/%d", myStylist.getId());
    goTo(stylistPath);
    assertThat(pageSource()).contains("Monica");
  }

  @Test
  public void stylistClientsFormIsDisplayed() {
    Stylist myStylist = new Stylist("Drake");
    myStylist.save();
    String stylistPath = String.format("http://localhost:4567/stylists/%d", myStylist.getId());
    goTo(stylistPath);
    assertThat(pageSource()).contains("Add a client");
  }

  @Test
  public void clientIsAddedAndDisplayed() {
    Stylist myStylist = new Stylist("Drake");
    myStylist.save();
    Client firstClient = new Client("Melissa", myStylist.getId());
    firstClient.save();
    Client secondClient = new Client("Tom", myStylist.getId());
    secondClient.save();
    String stylistPath = String.format("http://localhost:4567/stylists/%d/clients", myStylist.getId());
    goTo(stylistPath);
    assertThat(pageSource()).contains("Melissa");
    assertThat(pageSource()).contains("Tom");
  }

  @Test
  public void formToUpdateClientIsDisplayed() {
    Stylist myStylist = new Stylist("Drake");
    myStylist.save();
    Client myClient = new Client("Melissa", myStylist.getId());
    myClient.save();
    String clientPath = String.format("http://localhost:4567/stylists/%d/clients/%d/update", myStylist.getId(), myClient.getId());
    goTo(clientPath);
    assertThat(pageSource()).contains("Update Melissa's name");
  }


}
