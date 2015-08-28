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

}
