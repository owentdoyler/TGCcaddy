package tgccaddy.android.owendoyle.com.tgccaddy;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Owen on 08/10/2015.
 */
public class ClubSelector {

    private static final int RANGE = 10;

    private static final int[] NLW = {75, 72, 69, 66, 64, 61, 58, 56, 54, 46, 39};
    private static final int[] NSW = {95, 80, 67, 55, 47};
    private static final int[] NPW = {120, 100, 82, 65, 54};
    private static final int[] NI9 = {132, 112, 93, 76, 64};
    private static final int[] NI8 = {145, 125, 106, 87, 75};
    private static final int[] NI7 = {158, 139, 120, 100, 87};
    private static final int[] NI6 = {170, 153, 134, 114, 101};
    private static final int[] NI5 = {181, 167, 149, 130, 115};
    private static final int[] NI4 = {195, 183, 166, 146, 131};
    private static final int[] NI3 = {207, 198, 182, 162, 147};
    private static final int[] NW5 = {225, 225, 225, 225, 225};
    private static final int[] NW3 = {245, 247, 247, 247, 247};
    private static final int[] ND = {265, 268, 268, 270, 270};

    private static final HashMap<String, int[]> normalShots;
    static {
        normalShots = new HashMap<>();
        normalShots.put("Lob Wedge", NLW);
        normalShots.put("Sand Wedge", NSW);
        normalShots.put("Pitching Wedge", NPW);
        normalShots.put("9 Iron", NI9);
        normalShots.put("8 Iron", NI8);
        normalShots.put("7 Iron", NI7);
        normalShots.put("6 Iron", NI6);
        normalShots.put("5 Iron", NI5);
        normalShots.put("4 Iron", NI4);
        normalShots.put("3 Iron", NI3);
        normalShots.put("5 Wood", NW3);
        normalShots.put("3 Wood", NW5);
        normalShots.put("Driver", ND);
    }
    private static final int[] FLW = {30, 24, 20};
    private static final int[] FSW = {35, 30, 25};
    private static final int[] FPW = {40, 38, 30};

    private static final HashMap<String, int[]> flopShots;
    static {
        flopShots = new HashMap<>();
        flopShots.put("Lob Wedge", FLW);
        flopShots.put("Sand Wedge", FSW);
        flopShots.put("Pitching Wedge", FPW);
    }

    private static final int[] PLW = {45, 38, 32, 26, 21};
    private static final int[] PSW = {55, 46, 37, 29, 24};
    private static final int[] PPW = {75, 63, 51, 39, 32};
    private static final int[] PI9 = {80, 68, 57, 45, 37};
    private static final int[] PI8 = {90, 79, 67, 55, 46};
    private static final int[] PI7 = {95, 86, 75, 62, 54};
    private static final int[] PI6 = {100, 93, 83,72, 63};

    private static final HashMap<String, int[]> pitchShots;
    static {
        pitchShots = new HashMap<>();
        pitchShots.put("Lob Wedge", PLW);
        pitchShots.put("Sand Wedge", PSW);
        pitchShots.put("Pitching Wedge", PPW);
        pitchShots.put("9 Iron", PI9);
        pitchShots.put("8 Iron", PI8);
        pitchShots.put("7 Iron",PI7);
        pitchShots.put("6 Iron",PI6);
    }

    private static int givenYards;
    private static int givenElevation;
    private static int givenWindSpeed;
    private static int givenLie;
    private static int givenWindDirection;


    public static ArrayList<Shot> getClub(HashMap<String, Integer> enteredValues){

        ArrayList<Shot> bestShots = new ArrayList<>();

        givenYards = enteredValues.get(TGC.YARDS);
        givenElevation = enteredValues.get(TGC.ELEVATION);
        givenWindSpeed = enteredValues.get(TGC.WIND_SPEED);
        givenWindDirection = enteredValues.get(TGC.WIND_DIRECTION);
        givenLie = enteredValues.get(TGC.LIE);

        bestShots.addAll(getClosest(normalShots, "Normal"));
        bestShots.addAll(getClosest(flopShots, "Flop"));
        bestShots.addAll(getClosest(pitchShots, "Pitch"));
        return bestShots;
    }

    private static int calculateYards(){
        int elevationAdjusrment =(int)Math.floor((givenElevation/3));
        return 0;

    }

    private static int calculateWindAdjustment(){
        switch (givenWindDirection){
            case TGC.STRAIGHT_INTO_CODE:
                if(givenWindSpeed  > 15){
                    return givenWindSpeed*2;
                }
                else if (givenWindSpeed < 15 & givenWindSpeed > 8){
                    return (int) Math.floor((givenWindSpeed*1.65));
                }
                else {
                    return (int) Math.floor((givenWindSpeed * 1.5));
                }
            case TGC.STRAIGHT_WITH_CODE:
                if (givenWindSpeed > 10){
                    return (int)Math.floor(givenWindSpeed *0.75);
                }
                else {
                    return (int)Math.floor(givenWindSpeed*0.6);
                }
        }
        return 0;
    }

    private static ArrayList<Shot> getClosest(HashMap<String, int[]> shotType, String type){
        ArrayList<Shot> closest = new ArrayList<>();
        for(String club : shotType.keySet()){
            int[] yardages = shotType.get(club);
            for (int i = 0; i < yardages.length; i++){
                if (inRange(yardages[i])){
                    closest.add(new Shot(type, club, i));
                }
            }
        }
        return closest;
    }

    private static boolean inRange(int n){
        return ((n <= (givenYards + RANGE)) && (n >= (givenYards - RANGE)));
    }
}
