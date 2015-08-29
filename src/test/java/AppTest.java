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
    assertThat(pageSource()).contains("Add a new client");
  }

  @Test
  public void clientIsAddedAndDisplayed() {
    Stylist myStylist = new Stylist("Drake");
    myStylist.save();
    Client firstClient = new Client("Melissa", "503-053-9985", myStylist.getId());
    firstClient.save();
    Client secondClient = new Client("Tom", "976-973-9999", myStylist.getId());
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
    Client myClient = new Client("Melissa", "971-205-1535", myStylist.getId());
    myClient.save();
    String clientPath = String.format("http://localhost:4567/stylists/%d/clients/%d/update", myStylist.getId(), myClient.getId());
    goTo(clientPath);
    assertThat(pageSource()).contains("Update Melissa's info");
  }

  @Test
  public void updateName_UpdatesAndDisplaysUpdatedClientName() {
    Stylist myStylist = new Stylist("Derek");
    myStylist.save();
    Client myClient = new Client("Melissa", "971-253-5558", myStylist.getId());
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    savedClient.updateName("Feona");
    String updatedClientPath = String.format("http://localhost:4567/stylists/%d/clients", myStylist.getId());
    goTo(updatedClientPath);
    assertThat(pageSource()).contains("Feona");
  }

  @Test
  public void updatePhone_UpdatesAndDisplaysUpdatedClientPhone() {
    Stylist myStylist = new Stylist("Derek");
    myStylist.save();
    Client myClient = new Client("Melissa", "971-253-5558", myStylist.getId());
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    savedClient.updatePhone("503-553-5555");
    String updatedClientPath = String.format("http://localhost:4567/stylists/%d/clients", myStylist.getId());
    goTo(updatedClientPath);
    assertThat(pageSource()).contains("503-553-5555");
  }

  // @Test
  // public void update_UpdatesAndDisplaysUpdatedStylist() {
  //   Stylist myStylist = new Stylist("Derek");
  //   myStylist.save();
  //   Stylist savedStylist = Stylist.find(myStylist.getId());
  //   savedStylist.update("Feona");
  //   String updatedStylistPath = String.format("http://localhost:4567");
  //   goTo(updatedStylistPath);
  //   assertThat(pageSource()).contains("Feona");
  // }
  //
  // @Test
  // public void delete_DeletedStylistNoLongerDisplayed() {
  //   Stylist myStylist = new Stylist("Derek");
  //   myStylist.save();
  //   Stylist savedStylist = Stylist.find(myStylist.getId());
  //   savedStylist.delete();
  //   String deletedStylistPath = String.format("http://localhost:4567");
  //   goTo(deletedStylistPath);
  //   assertThat(!(pageSource()).contains("Derek"));
  // }
  //
  // @Test
  // public void delete_DeletedClientNoLongerDisplayed() {
  //   Stylist myStylist = new Stylist("Derek");
  //   myStylist.save();
  //   Client myClient = new Client("Feona", myStylist.getId());
  //   myClient.save();
  //   Client savedClient = Client.find(myClient.getId());
  //   savedClient.delete();
  //   String deletedClientPath = String.format("http://localhost:4567/stylists/%d", myStylist.getId());
  //   goTo(deletedClientPath);
  //   assertThat(!(pageSource()).contains("Feona"));
  // }

}
