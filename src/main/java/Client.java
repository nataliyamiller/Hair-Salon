import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Client {
  private int id;
  private int stylist_id;
  private String client_name;

  public int getId() {
    return id;
  }

  public int getStylist_id() {
    return stylist_id;
  }

  public String getClientName() {
    return client_name;
  }

  public Client(String client_name, int stylist_id) {
    this.client_name = client_name;
    this.stylist_id = stylist_id;
  }


}
