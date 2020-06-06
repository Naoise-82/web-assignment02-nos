package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;

/**
 * Controller class for the Update Details page
 */
public class UpdateDetails extends Controller {
    /**
     * Renders the Update Details page.
     */
    public static void index() {
        Logger.info("Rendering Update Details");
        Member member = Accounts.getLoggedInMember();

        render("/updatedetails.html", member);
    }
}
