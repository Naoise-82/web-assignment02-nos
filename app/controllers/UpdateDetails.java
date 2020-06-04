package controllers;

import java.util.ArrayList;
import java.util.List;
import models.Assessment;
import play.Logger;
import play.mvc.Controller;
import models.Member;

public class UpdateDetails extends Controller {

    public static void index() {
        Logger.info("Rendering Update Details");
        Member member = Accounts.getLoggedInMember();

        render("/updatedetails.html", member);
    }
}
