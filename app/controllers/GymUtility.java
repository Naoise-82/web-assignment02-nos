package controllers;

import play.Logger;
import play.mvc.Controller;
import models.Member;
import models.Assessment;
import java.util.ArrayList;
import java.util.List;

public class GymUtility extends Controller {

    public static String calculateCurrentBMI(Member member) {
        String currentBMI;
        float latestWeight;

        if (member.getAssessments().isEmpty()) {
            latestWeight = member.getStartingWeight();
        } else latestWeight = member.getAssessments().get(0).weight;

        float currentBMIFloat = Math.round((latestWeight / (member.getHeight() / 100 * member.getHeight() / 100)) * 100.0) / 100.0f;

        currentBMI = Float.toString(currentBMIFloat);
        return currentBMI;
    }

    public static String determineBMICategory(Member member) {
        String BMICategory = "";
        float latestWeight;

        if (member.getAssessments().isEmpty()) {
            latestWeight = member.getStartingWeight();
        } else latestWeight = member.getAssessments().get(0).weight;

        float currentBMIFloat = Math.round((latestWeight / (member.getHeight() / 100 * member.getHeight() / 100)) * 100.0) / 100.0f;
        if (currentBMIFloat < 16) {
            BMICategory = BMICategory + "Severely Underweight";
        } else if (currentBMIFloat >= 16.0 && currentBMIFloat < 18.5) {
            BMICategory = BMICategory + "Underweight";
        } else if (currentBMIFloat >= 18.5 && currentBMIFloat < 25.0) {
            BMICategory = BMICategory + "Normal";
        } else if (currentBMIFloat >= 25.0 && currentBMIFloat < 30.0) {
            BMICategory = BMICategory + "Overweight";
        } else if (currentBMIFloat >= 30.0 && currentBMIFloat < 35.0) {
            BMICategory = BMICategory + "Moderately Obese";
        } else if (currentBMIFloat >= 35.0) {
            BMICategory = BMICategory + "Severely Obese";
        }
        return BMICategory;
    }

    public static String calculateIdealBodyWeight(Member member) {
        String idealBodyWeight = "";
        float idealBodyWeightFloat = 1;

        if (member.gender.toLowerCase().equals("male")) {
            idealBodyWeightFloat = 50f + 0.9f * (member.height - 152f);
        } else if (member.gender.toLowerCase().equals("female")) {
            idealBodyWeightFloat = 45.5f + 0.9f * (member.height - 152f);
        }

        idealBodyWeight = idealBodyWeight + idealBodyWeightFloat;
        return idealBodyWeight;
    }
}