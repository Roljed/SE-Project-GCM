package product;

import java.util.List;
import product.content.Content;
import user.Role;

/**
 * Third product - Route
 *
 * @version 1
 * @author Yaad Nahshon
 */
public class Tour
{
    private int tourID;
    private String tourName;
    private String tourDescription;
    private List<Content> tourSequence;
    private double tourTotalDuration;

    public Tour(int tourID, String tourName, String tourDescription, List<Content> tourSequence) {
        this.tourID = tourID;
        this.tourName = tourName;
        this.tourDescription = tourDescription;
        this.tourSequence = tourSequence;
        this.tourTotalDuration = calculateTourDuration(tourSequence);
    }

    private double calculateTourDuration(List<Content> tourSequence)
    {
        double duration = 0;
        for (Content content : tourSequence)
        {
            duration += content.getContentDuration();
        }
        return duration;
    }

    public int getTourID() {
        return tourID;
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

    public List<Content> getTourSequence() {
        return tourSequence;
    }

    public void setTourSequence(List<Content> tourSequence) {
        this.tourSequence = tourSequence;
    }

    public double getTourTotalDuration() {
        return tourTotalDuration;
    }

    public void setTourTotalDuration(double tourTotalDuration) {
        this.tourTotalDuration = tourTotalDuration;
    }

    public void out(Role role)
    {
        if (role != null && (role != Role.USER && role != Role.MEMBER))
        {
            System.out.println("\tID: " + tourID);
        }
        System.out.println("\tTour Name: " + tourName);
        System.out.println("\tDescription: " + tourDescription);
        System.out.println("\tDuration: " + tourTotalDuration);
        for (Content content : tourSequence)
        {
            content.out(role);
        }
    }
}
