import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Client.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfClientNamesAretheSame() {
    Client firstClient = new Client("Judith", 1);
    Client secondClient = new Client("Judith", 1);
    assertTrue(firstClient.equals(secondClient));
  }


  @Test
  public void save_returnsTrueIfClientNamesAretheSame() {
    Client myClient = new Client("Judith", 1);
    myClient.save();
    assertTrue(Client.all().get(0).equals(myClient));
  }

  @Test
  public void save_assignsIdToObject() {
    Client myClient = new Client("Judith", 1);
    myClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(myClient.getId(), savedClient.getId());
  }

  @Test
  public void find_findsClientInDatabase_true() {
    Client myClient = new Client("Judith", 1);
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    assertTrue(myClient.equals(savedClient));
  }

  @Test
  public void save_savesStylistIdIntoDatabase_true() {
    Stylist myStylist = new Stylist("Kelly");
    myStylist.save();
    Client myClient = new Client("Judith", myStylist.getId());
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    assertEquals(savedClient.getStylistId(), myStylist.getId());
  }

  @Test
  public void update_updatesClientinDatabase_true() {
    Client myClient = new Client("Judith", 1);
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    savedClient.update("Feona");
    Client updatedClient = Client.find(savedClient.getId());
    assertEquals(updatedClient.getClientName(), "Feona");
  }

  @Test
  public void delete_deletesClientFromDatabase_true() {
    Client firstClient = new Client("Judith", 1);
    firstClient.save();
    Client secondClient = new Client("Feona", 1);
    secondClient.save();
    firstClient.delete();
    assertEquals(Client.all().size(), 1);
  }

}
