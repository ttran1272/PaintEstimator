package edu.orangecoastcollege.cs273.ttran1272.paintestimator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Bundle extras = getIntent().getExtras();
        String gallons = extras.getString("gallons");

        //Toast.makeText(getApplicationContext(),"Estimated Paint Required: " + gallons, Toast.LENGTH_LONG).show();
        Toast.makeText(this,"Estimated Paint Required: " + gallons, Toast.LENGTH_LONG).show();

        Button returnButton = (Button) findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}
