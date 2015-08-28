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



}
