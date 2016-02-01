package tgccaddy.android.owendoyle.com.tgccaddy;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;


public class TGC extends ActionBarActivity {

    public static final String YARDS = "yards";
    public static final String ELEVATION = "elevation";
    public static final String WIND_SPEED = "wind_speed";
    public static final String WIND_DIRECTION = "wind_direction";
    public static final String LIE = "lie";

    public static final int STRAIGHT_INTO_CODE = 1;
    public static final int STRAIGHT_WITH_CODE = 2;
    public static final int STRAIGHT_ACROSS_RIGHT_CODE = 3;
    public static final int STRAIGHT_ACROSS_LEFT_CODE = 4;
    public static final int DIAGONAL_INTO_LEFT_CODE = 5;
    public static final int DIAGONAL_INTO_RIGHT_CODE = 6;
    public static final int DIAGONAL_WITH_LEFT_CODE = 7;
    public static final int DIAGONAL_WITH_RIGHT_CODE = 8;

    public static final String STRAIGHT_INTO = "Straight into";
    public static final String STRAIGHT_AGAINST = "Straight with";
    public static final String STRAIGHT_ACROSS_RIGHT = "Straight Across from the right";
    public static final String STRAIGHT_ACROSS_LEFT = "Straight Across from the left";
    public static final String DIAGONAL_INTO_LEFT = "Diagonal into from the left";
    public static final String DIAGONAL_INTO_RIGHT = "Diagonal into from the right";
    public static final String DIAGONAL_WITH_LEFT = "Diagonal with from the left";
    public static final String DIAGONAL_WITH_RIGHT = "Diagonal with from the right";

    private static final String TAG = "TGCactivity";

    private Spinner windDirection;
    private EditText yards, elevation, windSpeed, lie;
    private Button getClubButton;
    private HashMap<String, Integer> enteredValues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tgc);
        getValues();
        getClubButton = (Button)findViewById(R.id.get_club_button);
        getClubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, enteredValues.toString());
                ArrayList<Shot> bestShots = ClubSelector.getClub(enteredValues);
            }
        });
    }

    private void getValues(){

        enteredValues = new HashMap<String, Integer>();

        windDirection = (Spinner)findViewById(R.id.wind_direction_selection);
        windDirection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            int code;
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = parent.getItemAtPosition(position).toString();
                switch (selection){
                    case STRAIGHT_INTO:
                        code = STRAIGHT_INTO_CODE;
                        break;
                    case STRAIGHT_AGAINST:
                        code = STRAIGHT_INTO_CODE;
                        break;
                    case STRAIGHT_ACROSS_LEFT:
                        code = STRAIGHT_ACROSS_LEFT_CODE;
                        break;
                    case STRAIGHT_ACROSS_RIGHT:
                        code = STRAIGHT_ACROSS_RIGHT_CODE;
                        break;
                    case DIAGONAL_INTO_LEFT:
                        code = DIAGONAL_INTO_LEFT_CODE;
                        break;
                    case DIAGONAL_INTO_RIGHT:
                        code = DIAGONAL_INTO_RIGHT_CODE;
                        break;
                    case DIAGONAL_WITH_LEFT:
                        code = DIAGONAL_WITH_LEFT_CODE;
                        break;
                    case DIAGONAL_WITH_RIGHT:
                        code = DIAGONAL_WITH_RIGHT_CODE;
                        break;
                    default:
                        code = -1;
                        break;
                }
                Log.d(TAG, selection);
                enteredValues.put(WIND_DIRECTION, code);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        yards = (EditText)findViewById(R.id.entered_yardage);
        yards.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String entered = s.toString();
                int yards;
                try {
                    yards = Integer.parseInt(entered);
                }catch (NumberFormatException e){
                    yards = 0;
                }
                enteredValues.put(YARDS, yards);
                Log.d(TAG, ""+yards);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        elevation = (EditText)findViewById(R.id.entered_elevation);
        elevation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String entered = s.toString();
                int elevation;
                try {
                    elevation = Integer.parseInt(entered);
                }catch (NumberFormatException e){
                    elevation = 0;
                }
                enteredValues.put(ELEVATION, elevation);
                Log.d(TAG, ""+elevation);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        windSpeed = (EditText)findViewById(R.id.wind_speed_entered);
        windSpeed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String entered = s.toString();
                int wind_speed;
                try {
                    wind_speed = Integer.parseInt(entered);
                }catch (NumberFormatException e){
                    wind_speed = 0;
                }
                enteredValues.put(WIND_SPEED, wind_speed);
                Log.d(TAG, ""+wind_speed);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lie = (EditText)findViewById(R.id.entered_lie_percentage);
        lie.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String entered = s.toString();
                int lie;
                try {
                    lie = Integer.parseInt(entered);
                }catch (NumberFormatException e){
                    lie = 0;
                }
                enteredValues.put(LIE, lie);
                Log.d(TAG, ""+lie);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tgc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
