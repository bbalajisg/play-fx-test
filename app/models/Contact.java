package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by dev-env on 12/1/15.
 */

@Entity
public class Contact extends Model{

    @Id
    @GeneratedValue
    public Long id;

    public String name;

    public String phone;

    public String email;

    public static Finder<Long, Contact>  find = new Finder<Long, Contact>(Long.class, Contact.class);

}
