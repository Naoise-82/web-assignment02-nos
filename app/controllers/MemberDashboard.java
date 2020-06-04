package controllers;

import java.util.ArrayList;
import java.util.List;
import models.Assessment;
import play.Logger;
import play.mvc.Controller;
import models.Member;

public class MemberDashboard extends Controller
{
  public static void index() {
    Logger.info("Rendering Member Dashboard");
    Member member = Accounts.getLoggedInMember();

    //single parameter for BMI to pass to the userpanel
    String startingBMI = GymUtility.calculateCurrentBMI(member);

    List<Assessment> assessmentList = member.assessments;

    // Generate an ArrayList of strings and add member statistics:
    // 0: Current BMI
    // 1: BMI Category
    // 2: Ideal Body Weight
    // 3: Current Body Weight
    // 4: Syntax for weight trend arrow

    List<String> memberStats = new ArrayList<>();

    memberStats.add(GymUtility.calculateCurrentBMI(member));
    memberStats.add(GymUtility.determineBMICategory(member));
    memberStats.add(GymUtility.calculateIdealBodyWeight(member));

    if(member.getAssessments().isEmpty()) {
      memberStats.add(Float.toString(member.getStartingWeight()));
    } else memberStats.add(Float.toString(member.getAssessments().get(0).weight));

    List<Float> assessmentWeight = new ArrayList<>();

    for(Assessment assessment : assessmentList)
    {
      assessmentWeight.add(assessment.weight);
    }

    render ("/memberdashboard.html", assessmentList, assessmentWeight, member, memberStats, startingBMI);
  }

  public static void addAssessment(float weight, float chest, float thigh, float upperArm, float waist, float hips)
  {
    Assessment assessment = new Assessment(weight, chest, thigh, upperArm, waist, hips);
    Member member = Accounts.getLoggedInMember();
    Logger.info("Adding Assessment: " + member.firstname + member.lastname);
    member.assessments.add(0, assessment);
    assessment.save();

    redirect("/memberdashboard");
  }


  public static void deleteAssessment(Long assessmentid, Long memberid)
  {
    Member member = Member.findById(memberid);
    Assessment assessment = Assessment.findById(assessmentid);
    member.assessments.remove(assessment);
    member.save();
    assessment.delete();
    redirect("/memberdashboard");
  }
}
