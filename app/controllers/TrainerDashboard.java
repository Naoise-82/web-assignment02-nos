package controllers;

import java.util.ArrayList;
import java.util.List;
import models.Assessment;
import models.Trainer;
import play.Logger;
import play.db.jpa.Model;
import play.mvc.Controller;
import models.Member;

public class TrainerDashboard extends Controller {



        public static void index() {
            Logger.info("Rendering TrainerDashboard");
            Trainer trainer = Accounts.getLoggedInTrainer();

            List<Member> members;
            members = Member.findAll();


            render ("trainerdashboard.html", trainer, members);
        }

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
