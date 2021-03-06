import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Client {
  private int id;
  private int stylist_id;
  private String client_name;
  private String client_phone;

  public int getId() {
    return id;
  }

  public int getStylistId() {
    return stylist_id;
  }

  public String getClientName() {
    return client_name;
  }

  public String getPhone() {
    return client_phone;
  }

  public Client(String client_name, String client_phone, int stylist_id) {
    this.client_name = client_name;
    this.client_phone = client_phone;
    this.stylist_id = stylist_id;
  }

  public static List<Client> all() {
    String sql = "SELECT id, client_name, client_phone, stylist_id FROM clients";
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
             this.getPhone().equals(myClient.getPhone()) &&
             this.getId() == myClient.getId() &&
             this.getStylistId() == myClient.getStylistId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (client_name, client_phone, stylist_id) VALUES (:client_name, :client_phone, :stylist_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("client_name", this.client_name)
        .addParameter("client_phone", this.client_phone)
        .addParameter("stylist_id", this.stylist_id)
        .executeUpdate()
        .getKey();
    }
  }

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE id = :id";
      Client client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
        return client;
    }
  }

  public void updateName(String client_name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET client_name = :client_name WHERE id = :id";
      con.createQuery(sql)
        .addParameter("client_name", client_name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void updatePhone(String client_phone) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET client_phone = :client_phone WHERE id = :id";
      con.createQuery(sql)
        .addParameter("client_phone", client_phone)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public static List<Client> clientsByStylist(int stylist_id) {
    String sql = "SELECT id, client_name, client_phone, stylist_id FROM clients WHERE stylist_id = :stylist_id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("stylist_id", stylist_id)
        .executeAndFetch(Client.class);
    }
  }

}
