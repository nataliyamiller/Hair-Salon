import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;
import java.util.List;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";


    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:id", (request, response) -> {
      response.redirect("/stylists/" + request.params(":id") + "/clients");
      return null;
    });

    get("stylists/:id/update", (request, response) -> {
     HashMap<String, Object> model = new HashMap<String, Object>();
     Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
     model.put("stylist", stylist);
     model.put("template", "templates/stylist-update-form.vtl");
     return new ModelAndView(model, layout);
   }, new VelocityTemplateEngine());

    get("/stylists/:id/clients", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("stylist", stylist);
      model.put("clients", Client.clientsByStylist(Integer.parseInt(request.params(":id"))));
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:stylist_id/clients/:id/update", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params(":id")));
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylist_id")));
      model.put("client", client);
      model.put("stylist", stylist);
      model.put("template", "templates/client-update-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      Stylist newStylist = new Stylist(name);
      newStylist.save();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("stylists/:id/update", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      String name = request.queryParams("name");
      stylist.update(name);
      response.redirect("/stylists/" + stylist.getId() + "/clients");
      return null;
    });

    post("/stylists/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      stylist.delete();
      response.redirect("/");
      return null;
    });

    post("/stylists/:id/clients", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      String client_name = request.queryParams("client_name");
      String client_phone = request.queryParams("client_phone");
      Client newClient = new Client(client_name, client_phone, stylist.getId());
      newClient.save();
      model.put("stylist", stylist);
      model.put("clients", Client.clientsByStylist(Integer.parseInt(request.params(":id"))));
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists/:stylist_id/clients/:id/update/name", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params(":id")));
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylist_id")));
      String client_name = request.queryParams("client_name");
      client.updateName(client_name);
      response.redirect("/stylists/" + stylist.getId() + "/clients");
      return null;
    });

    post("/stylists/:stylist_id/clients/:id/update/phone", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params(":id")));
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylist_id")));
      String client_phone = request.queryParams("client_phone");
      client.updatePhone(client_phone);
      response.redirect("/stylists/" + stylist.getId() + "/clients");
      return null;
    });

    post("/stylists/:stylist_id/clients/:id/delete", (request, response) -> {
      Client client = Client.find(Integer.parseInt(request.params(":id")));
      client.delete();
      response.redirect("/stylists/" + request.params(":stylist_id") + "/clients");
      return null;
    });

  }
}
