package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Contact;


import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;

import play.libs.ws.*;
import play.libs.F.Function;
import play.libs.F.Promise;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dev-env on 12/1/15.
 */
public class ContactManagerImpl extends Controller {


    public static Result listAll(){

        List<Contact> contacts = Contact.find.all();
        return ok();

    }


    public static Result show(Long id){
        Contact contact = Contact.find.byId(id);

        if(contact != null ){
            return ok();
        }else{
            return notFound();
        }

    }

    public static Result newContact(){

        Form<Contact> contactForm = Form.form(Contact.class);
        return ok();

    }

    public static Result createContact(){
        Form<Contact>   contactForm = Form.form(Contact.class).bindFromRequest();
        Contact contact = contactForm.get();
        contact.save();

        return redirect(controllers.routes.ContactManagerImpl.listAll());
    }


    public static Promise<Result> getAllCurrencies(){


        String curl =  "http://finance.yahoo.com/webservice/v1/symbols/allcurrencies/quote?format=json";

        Promise<Result> holder = WS.url(curl).get().map(new Function<WSResponse, Result>() {
            @Override
            public Result apply(WSResponse wsResponse) throws Throwable {

                //return ok(wsResponse.asJson());

                 JsonNode json = wsResponse.asJson();
                if(json == null) {
                    return badRequest("Expecting Json data");
                } else {
                    String name = "";

                    JsonNode filed = json.get("fields");

                    if(name == null) {
                        return badRequest("Missing parameter [name]");
                    } else {
                        return ok(name);
                    }
                }
            }
        });

        return holder;
    }

}