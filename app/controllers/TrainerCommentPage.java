package controllers;

import java.util.ArrayList;
import java.util.List;
import models.Assessment;
import models.Trainer;
import play.Logger;
import play.db.jpa.Model;
import play.mvc.Controller;
import models.Member;

public class TrainerCommentPage extends Controller {

    public static void index(Long id) {
        Logger.info("Rendering TrainerCommentPage");
        Trainer trainer = Accounts.getLoggedInTrainer();
        Member member = Member.findById(id);
        List<Assessment> assessments = member.getAssessments();

        render ("trainercommentpage.html", trainer, member, assessments);
    }

    public static void addComment(Long memberid, Long assessmentid, String comment)
    {
        Assessment assessment = Assessment.findById(assessmentid);
        assessment.setComment(comment);
        assessment.save();
        Logger.info("Adding comment: " + comment);
        redirect("/trainercommentpage/" + memberid);

    }

}