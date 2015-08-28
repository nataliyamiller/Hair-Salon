import java.util.List;
import org.sql2o.*;

public class Stylist {
  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Stylist (String name) {
    this.name = name;
  }

}
