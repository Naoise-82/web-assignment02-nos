package controllers;

import models.Member;
import play.mvc.Controller;

/**
 * A Utility class used for calculating various biometric data, with code partly shared with
 * Programming Assignment 02
 */
public class GymUtility extends Controller {

    /**
     * Returns the member's BMI in String form
     * @param member The members whose BMI is being calculated
     * @return The BMI of the member as a String
     */
    public static String calculateCurrentBMI(Member member) {
        String currentBMI;
        float latestWeight;

        // check if there are any assessments, and use the starting weight if there are none
        if (member.getAssessments().isEmpty()) {
            latestWeight = member.getStartingWeight();
        } else latestWeight = member.getAssessments().get(0).weight;

        float currentBMIFloat = Math.round((latestWeight / (member.getHeight() / 100 * member.getHeight() / 100)) * 100.0) / 100.0f;

        currentBMI = Float.toString(currentBMIFloat);
        return currentBMI;
    }

    /**
     * Determines the BMI category of the member based on their latest weight
     * @param member The member being analysed
     * @return A String stating the BMI category
     */
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

    /**
     * Calculates a members ideal body weight based on the Devine Formula
     * @param member The member being Analysed
     * @return The ideal body weight as a String
     */
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