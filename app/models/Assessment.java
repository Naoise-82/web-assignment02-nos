package models;

import play.db.jpa.Model;
import javax.persistence.Entity;

// Date/Time implementation sourced from https://www.w3schools.com/java/java_date.asp
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

/**
 * Model for the assessments recorded for members of the gym
 */
@Entity
public class Assessment extends Model
{
    public float weight;
    public float chest;
    public float thigh;
    public float upperArm;
    public float waist;
    public float hips;
    public String date;

    public String comment;

    /**
     * Adds an assessment to the member
     * @param weight The recorded weight
     * @param chest The recorded chest measurement
     * @param thigh The recorded thigh measurement
     * @param upperArm The recorded upper arm measurement
     * @param waist The recorded waist measurement
     * @param hips the recorded hip measurement
     */
    public Assessment(float weight, float chest, float thigh, float upperArm, float waist, float hips)
    {
        // get the current date and time and re-format it to look nice and readable
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter reformatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        this.date = currentDateTime.format(reformatDateTime);
        this.weight = weight;
        this.chest = chest;
        this.thigh = thigh;
        this.upperArm = upperArm;
        this.waist = waist;
        this.hips = hips;
    }

    public String getComment() {
        return comment;
    }

    public float getWeight() {
        return weight;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
