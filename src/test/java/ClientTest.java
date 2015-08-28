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

}
