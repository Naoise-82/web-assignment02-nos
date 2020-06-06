package controllers;

import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

/**
 * This class is the controller for all of the account related fuinctions on the site
 */
public class Accounts extends Controller {
    /**
     * Renders the Sign Up page of the site
     */
    public static void signup()
    {
        render("signup.html");
    }

    /**
     * Renders the login page of the site
     */
    public static void login()
    {
        render("login.html");
    }

    /**
     * Registers a new member and adds their details to the database
     * @param firstname The member's first name
     * @param lastname  The member's last name
     * @param email     The member's email address
     * @param password  The members password for the site
     * @param gender    The member's gender
     * @param height    The member's height
     * @param startingWeight    The member's initial weight recorded
     */
    public static void register(String firstname, String lastname, String email, String password, String gender, float height, float startingWeight)
    {
        Logger.info("Registering new user " + email);
        Member member = new Member(firstname, lastname, email, password, gender, height, startingWeight);
        member.save();
        redirect("/");
    }

    /**
     * Allows the member to update all of their details
     *
     * @param firstname The member's first name
     * @param lastname  The member's last name
     * @param email     The member's email address
     * @param password  The members password for the site
     * @param gender    The member's gender
     * @param height    The member's height
     * @param startingWeight    The member's initial weight recorded
     */
    public static void updateMember(String firstname, String lastname, String email, String password, String gender, float height, float startingWeight)
    {
        Logger.info("Updating Member Details: " + firstname + " " + lastname);
        Member member = Accounts.getLoggedInMember();
        member.setFirstname(firstname);
        member.setLastname(lastname);
        member.setEmail(email);
        member.setPassword(password);
        member.setGender(gender);
        member.setHeight(height);
        member.setStartingWeight(startingWeight);
        member.save();
        redirect("/login");

    }

    /**
     * Aithentication for access to the personal pages of the site
     * @param email the email address of the user
     * @param password the users' password
     */
    public static void authenticate(String email, String password)
    {
        Logger.info("Attempting to authenticate with " + email + ":" + password);

        // find the member in the database by their email address
        Member member = Member.findByEmail(email);
        if ((member != null) && (member.checkPassword(password) == true)) { // check for a match
            Logger.info("Authentication successful");
            session.put("logged_in_Memberid", member.id);
            redirect("/memberdashboard");
        } else {
            Trainer trainer = Trainer.findByEmail(email);
            if((trainer != null) && (trainer.checkPassword(password) == true))
            {
                Logger.info("Authentication successful");
                session.put("logged_in_Trainerid", trainer.id);
                TrainerDashboard.index();
            } else {
                Logger.info("Authentication failed");
                login();
            }
        }
    }

    /**
     * Log out of the site
     */
    public static void logout()
    {
        session.clear();
        redirect ("/");
    }

    /**
     * Retrieve the currently logged in member
     * @return The member who is currently logged in
     */
    public static Member getLoggedInMember()
    {
        Member member = null;
        if (session.contains("logged_in_Memberid")) {
            String memberId = session.get("logged_in_Memberid");
            member = Member.findById(Long.parseLong(memberId));
        } else {
            login();
        }
        return member;
    }

    /**
     * Retrieve the currently logged in trainer
     * @return The trainer who is currently logged in
     */
    public static Trainer getLoggedInTrainer()
    {
        Trainer trainer = null;
        if (session.contains("logged_in_Trainerid")) {
            String trainerId = session.get("logged_in_Trainerid");
            trainer = Trainer.findById(Long.parseLong(trainerId));
        } else {
            login();
        }
        return trainer;
    }
}