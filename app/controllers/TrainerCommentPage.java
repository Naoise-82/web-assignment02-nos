package controllers;

import models.Assessment;
import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;
import java.util.List;

/**
 * Controller for the trainer's comment page
 */
public class TrainerCommentPage extends Controller {

    /**
     * Renders the trainer comment page
     * @param id the ID of the logged in trainer
     */
    public static void index(Long id) {
        Logger.info("Rendering TrainerCommentPage");
        Trainer trainer = Accounts.getLoggedInTrainer();
        Member member = Member.findById(id);
        List<Assessment> assessments = member.getAssessments();

        render ("trainercommentpage.html", trainer, member, assessments);
    }

    /**
     * Allows a trainer to ass a comment to any member's assessment
     * @param memberid the ID of the member
     * @param assessmentid the ID of the assessment
     * @param comment The trainer's comment
     */
    public static void addComment(Long memberid, Long assessmentid, String comment)
    {
        Assessment assessment = Assessment.findById(assessmentid);
        assessment.setComment(comment);
        assessment.save();
        Logger.info("Adding comment: " + comment);
        redirect("/trainercommentpage/" + memberid);

    }

}