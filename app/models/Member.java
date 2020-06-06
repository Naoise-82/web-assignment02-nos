package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Model for the Member objects in the Web App
 */
@Entity
public class Member extends Model {

    public String firstname;
    public String lastname;
    public String email;
    public String password;
    public String gender;
    public float height;
    public float startingWeight;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Assessment> assessments = new ArrayList<Assessment>();

    public Member(String firstname, String lastname, String email, String password, String gender, float height, float startingWeight)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.height = height;
        this.startingWeight = startingWeight;

    }

    public static Member findByEmail(String email)
    {
        return find("email", email).first();
    }

    public boolean checkPassword(String password)
    {
        return this.password.equals(password);
    }

    public float getHeight() {
        return height;
    }

    public float getStartingWeight() {
        return startingWeight;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setStartingWeight(float startingWeight) {
        this.startingWeight = startingWeight;
    }
}