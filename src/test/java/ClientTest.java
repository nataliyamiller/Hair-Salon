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
  public void equals_returnsTrueIfClientNamePhoneAretheSame() {
    Client firstClient = new Client("Judith", "503-305-3355", 1);
    Client secondClient = new Client("Judith", "503-305-3355", 1);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_returnsTrueIfClientNamesAretheSame() {
    Client myClient = new Client("Judith", "971-205-5555", 1);
    myClient.save();
    assertTrue(Client.all().get(0).equals(myClient));
  }

  @Test
  public void save_assignsIdToObject() {
    Client myClient = new Client("Judith", "971-205-5555", 1);
    myClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(myClient.getId(), savedClient.getId());
  }

  @Test
  public void find_findsClientInDatabase_true() {
    Client myClient = new Client("Judith", "971-205-5555", 1);
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    assertTrue(myClient.equals(savedClient));
  }

  @Test
  public void save_savesStylistIdIntoDatabase_true() {
    Stylist myStylist = new Stylist("Kelly");
    myStylist.save();
    Client myClient = new Client("Judith", "971-255-5555", myStylist.getId());
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    assertEquals(savedClient.getStylistId(), myStylist.getId());
  }

  @Test
  public void updateName_updatesClientNameinDatabase_true() {
    Client myClient = new Client("Judith", "971-255-5555", 1);
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    savedClient.updateName("Feona");
    Client updatedClient = Client.find(savedClient.getId());
    assertEquals(updatedClient.getClientName(), "Feona");
  }

  @Test
  public void updatePhone_updatesClientPhoneInDatabase_true() {
    Client myClient = new Client("Judith", "971-255-5555", 1);
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    savedClient.updatePhone("503-305-3335");
    Client updatedClient = Client.find(savedClient.getId());
    assertEquals(updatedClient.getPhone(), "503-305-3335");
  }

  @Test
  public void delete_deletesClientFromDatabase_true() {
    Client firstClient = new Client("Judith", "971-205-5553", 1);
    firstClient.save();
    Client secondClient = new Client("Feona", "503-305-3005", 1);
    secondClient.save();
    firstClient.delete();
    assertEquals(Client.all().size(), 1);
  }

}
