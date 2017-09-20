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

   private void initializeViews(){
       mLengthEditText = (EditText) findViewById(R.id.lengthEditText);
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
           // to-do all
       }
   }


   private void saveSharedPreferences() {
       SharedPreferences.Editor editor = mPrefs.edit();
       editor.clear();
       editor.putFloat("length", mRoom.getLength());
       editor.putFloat("width",  mRoom.getWidth());
       editor.putFloat("height", mRoom.getHeight());
       // to-do all

       // Save the changes to the SharedPreferences file
       editor.commit();
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the View
        initializeViews();

        loadSharedPreferences();

    }

    protected void computerGallons(View v){

        // Update model first, then calculate
        mRoom.setLength(Float.parseFloat(mLengthEditText.getText().toString()));
        mRoom.setWidth(Float.parseFloat(mWidthEditText.getText().toString()));
        //TODO: FINISH THE REST

        mGallonsTextView.setText(getString(R.string.interior_survace_area_text) + mRoom.totalSurfaceArea() + "\n" + getString(R.string.gallons_needed_text) + mRoom.gallonsOfPaintRequired());
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
