package com.example.mostafa.apptask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContuctUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contuct_us);


        ((Button) findViewById(R.id.Send)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"mostafaahmedsalam@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, ((EditText) findViewById(R.id.editText)).getText().toString());
                i.putExtra(Intent.EXTRA_TEXT, ((EditText) findViewById(R.id.editText3)).getText().toString());
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(ContuctUs.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
            });

    }
}
