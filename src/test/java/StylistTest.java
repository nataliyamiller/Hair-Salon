import java.util.Arrays;

import org.junit.*;
import static org.junit.Assert.*;


public class StylistTest {
//
  @Rule
  public DatabaseRule database = new DatabaseRule();


  @Test
  public void all_emptyAtFirst() {
    assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfStylistNamesAretheSame() {
    Stylist firstStylist = new Stylist("Kelly");
    Stylist secondStylist = new Stylist("Kelly");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_savesStylistIntoDatabase_true() {
    Stylist myStylist = new Stylist("Kelly");
    myStylist.save();
    assertTrue(Stylist.all().get(0).equals(myStylist));
  }

  @Test
  public void find_findsStylistInDatabase_true() {
    Stylist myStylist = new Stylist("Kelly");
    myStylist.save();
    Stylist savedStylist = Stylist.find(myStylist.getId());
    assertTrue(myStylist.equals(savedStylist));
  }

  @Test
  public void getClients_retrievesAllClientsFromDatabase_clientsList() {
    Stylist myStylist = new Stylist("Kelly");
    myStylist.save();
    Client firstClient = new Client("Judith", "503-305-3355", myStylist.getId());
    firstClient.save();
    Client secondClient = new Client("Feona", "971-205-5555", myStylist.getId());
    secondClient.save();
    Client[] clients = new Client[] {firstClient, secondClient};
    assertTrue(myStylist.getClients().containsAll(Arrays.asList(clients)));
  }

  @Test
  public void update_updatesStylistInDatabase_true() {
    Stylist myStylist = new Stylist("Kelly");
    myStylist.save();
    Stylist savedStylist = Stylist.find(myStylist.getId());
    savedStylist.update("Derek");
    Stylist updatedStylist = Stylist.find(savedStylist.getId());
    assertEquals(updatedStylist.getName(), "Derek");
  }

  @Test
  public void delete_deletesStylistFromDatabase_true() {
    Stylist firstStylist = new Stylist("Kelly");
    firstStylist.save();
    Stylist secondStylist = new Stylist("Derek");
    secondStylist.save();
    firstStylist.delete();
    assertEquals(Stylist.all().size(), 1);
  }

  @Test
  public void clientsPopulatedByStylist_true() {
    Stylist firstStylist = new Stylist("Kelly");
    firstStylist.save();
    Stylist secondStylist = new Stylist("Derek");
    secondStylist.save();
    Client firstClient = new Client("Judith", "503-205-3355", firstStylist.getId());
    firstClient.save();
    Client secondClient = new Client("Feona", "971-225-8777", secondStylist.getId());
    secondClient.save();
    assertEquals(Client.clientsByStylist(secondStylist.getId()), secondStylist.getClients());

  }
}
