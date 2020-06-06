package controllers;

import models.Member;
import models.Trainer;

import play.Logger;
import play.mvc.Controller;
import java.util.List;

/**
 * Controller class for the Trainer's dashboard
 */
public class TrainerDashboard extends Controller {

    /**
     * Renders the trainer's dashboard page
     */
    public static void index() {
        Logger.info("Rendering TrainerDashboard");
        Trainer trainer = Accounts.getLoggedInTrainer();

        List<Member> members;
        members = Member.findAll();

        render ("trainerdashboard.html", trainer, members);
    }

    /**
     * Allows a trainer to delete a member
     * @param id The member's ID
     */
    public static void deleteMember(Long id) {

        List<Member> members;
        members = Member.findAll();
        Member member = Member.findById(id);
        members.remove(member);
        member.delete();

        Logger.info("Deleting Member: " + member.firstname);

        redirect("/trainerdashboard");
    }

}
