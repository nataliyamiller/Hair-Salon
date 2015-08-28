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

  public int getStylistId() {
    return stylist_id;
  }

  public String getClientName() {
    return client_name;
  }

  public Client(String client_name, int stylist_id) {
    this.client_name = client_name;
    this.stylist_id = stylist_id;
  }

  public static List<Client> all() {
    String sql = "SELECT id, client_name, stylist_id FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  @Override
  public boolean equals (Object otherClient) {
    if(!(otherClient instanceof Client)) {
      return false;
    } else {
      Client myClient = (Client) otherClient;
      return this.getClientName().equals(myClient.getClientName()) &&
             this.getId() == myClient.getId() &&
             this.getStylistId() == myClient.getStylistId();
    }
  }


}
