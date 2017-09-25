package edu.orangecoastcollege.cs273.ttran1272.paintestimator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Member variables for the Views
    private EditText mLengthEditText;
    private EditText mWidthEditText;
    private EditText mHeightEditText;

    private EditText mWindowsEditText;
    private EditText mDoorsEditText;

    private TextView mGallonsTextView;

    // Member variable for the Model
    private InteriorRoom mRoom = new InteriorRoom();

    // Member variable for SharedPreferences
    private SharedPreferences mPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the View
        initializeViews();

        loadSharedPreferences();

    }

    private void initializeViews(){
       mLengthEditText = (EditText) findViewById(R.id.lengthEditText);
       mWidthEditText = (EditText) findViewById(R.id.widthEditText);
       mHeightEditText = (EditText) findViewById(R.id.heightEditText);
       mWindowsEditText = (EditText) findViewById(R.id.windowsEditText);
       mDoorsEditText = (EditText) findViewById(R.id.doorsEditText);
       mGallonsTextView = (TextView) findViewById(R.id.gallons);
       //TO-DO ALL
   }

   private void loadSharedPreferences(){
       mPrefs = getSharedPreferences("edu.orangecoastcollege.cs273.ttran1272.PaintEstimator", MODE_PRIVATE);
       if (mPrefs != null){
           // Load all the room information
           mRoom.setDoors(mPrefs.getInt("doors", 0));
           mDoorsEditText.setText(String.valueOf(mRoom.getDoors()));

           mRoom.setHeight(mPrefs.getFloat("height", 0.0f));
           mHeightEditText.setText(String.valueOf(mRoom.getHeight()));

           mRoom.setLength(mPrefs.getFloat("length", 0.0f));
           mLengthEditText.setText(String.valueOf(mRoom.getLength()));

           mRoom.setWidth(mPrefs.getFloat("width", 0.0f));
           mWidthEditText.setText(String.valueOf(mRoom.getWidth()));

           mRoom.setWindows(mPrefs.getInt("windows", 0));
           mWindowsEditText.setText(String.valueOf(mRoom.getWindows()));

           // to-do all
       }
   }


   private void saveSharedPreferences() {
       SharedPreferences.Editor editor = mPrefs.edit();
       editor.clear();
       editor.putFloat("length", mRoom.getLength());
       editor.putFloat("width",  mRoom.getWidth());
       editor.putFloat("height", mRoom.getHeight());
       editor.putInt("doors", mRoom.getDoors());
       editor.putInt("windows", mRoom.getWindows());
       // to-do all

       // Save the changes to the SharedPreferences file
       editor.commit();
   }


    protected void computerGallons(View v){

        // Update model first, then calculate
        mRoom.setLength(Float.parseFloat(mLengthEditText.getText().toString()));
        mRoom.setWidth(Float.parseFloat(mWidthEditText.getText().toString()));
        mRoom.setHeight(Float.parseFloat(mHeightEditText.getText().toString()));
        mRoom.setDoors(Integer.parseInt(mDoorsEditText.getText().toString()));
        mRoom.setWindows(Integer.parseInt(mWindowsEditText.getText().toString()));

        //TODO: FINISH THE REST
        float total = mRoom.totalSurfaceArea();
        float gallons = mRoom.gallonsOfPaintRequired();

        mGallonsTextView.setText(R.string.interior_survace_area_text + total + "\n" + R.string.gallons_needed_text + gallons);
        // mGallonsTextView.setText(String.valueOf(mRoom.gallonsOfPaintRequired()));
        saveSharedPreferences();
    }

    protected void goToHelp(View v){
        // construct an EXPLICIT Intent to go to HelpActivity
        // Intent: specify where to start (context) and where we're going (next Activity)
        Intent helpIntent = new Intent(this, HelpActivity.class);
        helpIntent.putExtra("gallons", mRoom.gallonsOfPaintRequired());
        startActivity(helpIntent);
    }
}
