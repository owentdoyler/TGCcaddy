package tgccaddy.android.owendoyle.com.tgccaddy;

import java.util.ArrayList;

/**
 * Created by Owen on 09/10/2015.
 */
public class Shot {

    private String shotType;
    private String club;
    private double loft;

    public Shot(String givenshotType, String givenClub, double givenLoft) {
        shotType = givenshotType;
        club = givenClub;
        loft = givenLoft;
    }

    public String getShotType() {
        return shotType;
    }

    public String getClub() {
        return club;
    }

    public double getLoft() {
        return loft;
    }
}
