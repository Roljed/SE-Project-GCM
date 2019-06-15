package product;

import java.io.Serializable;
import java.util.List;
import product.content.Content;
import product.content.Site;
import user.Permission;

/**
 * Third product - Route
 *
 * @version 1
 * @author Yaad Nahshon
 */
public class Tour implements Serializable
{
    private int tourID;
    private String tourName;
    private String tourDescription;
    private List<Site> tourSequence;
    private double tourTotalDuration;

    public Tour(int tourID, String tourName, String tourDescription, List<Site> tourSequence) {
        this.tourID = tourID;
        this.tourName = tourName;
        this.tourDescription = tourDescription;
        this.tourSequence = tourSequence;
        this.tourTotalDuration = calculateTourDuration(tourSequence);
    }

    public Tour(int tourID, String tourName, String tourDescription, List<Site> tourSequence, double tourTotalDuration) {
        this.tourID = tourID;
        this.tourName = tourName;
        this.tourDescription = tourDescription;
        this.tourSequence = tourSequence;
        this.tourTotalDuration = tourTotalDuration;
    }

    private double calculateTourDuration(List<Site> tourSequence)
    {
        double duration = 0;
        for (Site site : tourSequence)
        {
            duration += site.getContentDuration();
        }
        return duration;
    }

    public int getTourID() {
        return tourID;
    }
    public String getTourIDToString()
    {
        return String.valueOf(tourID);
    }

    public void setTourID(int tourID) {
        this.tourID = tourID;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getTourDescription() {
        return tourDescription;
    }

    public void setTourDescription(String tourDescription) {
        this.tourDescription = tourDescription;
    }

    public List<Site> getTourSequence() {
        return tourSequence;
    }

    public void setTourSequence(List<Site> tourSequence) {
        this.tourSequence = tourSequence;
    }

    public double getTourTotalDuration() {
        return tourTotalDuration;
    }

    public String getTourTotalDurationToString() {
        return String.valueOf(tourTotalDuration);
    }

    public void setTourTotalDuration(double tourTotalDuration) {
        this.tourTotalDuration = tourTotalDuration;
    }

//    public void out(Permission permission)
//    {
//        if (permission != null && (permission != Permission.USER && permission != Permission.MEMBER))
//        {
//            System.out.println("\tID: " + tourID);
//        }
//        System.out.println("\tTour Name: " + tourName);
//        System.out.println("\tDescription: " + tourDescription);
//        System.out.println("\tDuration: " + tourTotalDuration);
//        for (Content content : tourSequence)
//        {
//            content.out(permission);
//        }
//    }
}
