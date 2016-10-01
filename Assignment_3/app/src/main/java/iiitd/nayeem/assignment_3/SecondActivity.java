package iiitd.nayeem.assignment_3;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView head,note;

        head = (TextView) findViewById(R.id.head);
        note = (TextView) findViewById(R.id.note);
        // get the intent from which this activity is called.
        Intent intent = getIntent();
        // fetch value from key-value pair and make it visible on TextView.
        String item = intent.getStringExtra("selected-item");
        head.setText(item);

        String aDataRow;
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state))
        {
            try {
                File myFile = new File("/sdcard/" + "add_notes.txt");
                FileInputStream fIn = new FileInputStream(myFile);
                BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                while ((aDataRow = myReader.readLine()) != null) {

                    String[] temp = aDataRow.split(",");
                    if (item.equals(temp[0])) {
                        note.setText(temp[1]);
                        break;
                    }

                }
                myReader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onPause()
    {
        super.onPause();
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Override
    public void onStop()
    {
        super.onStop();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }
}
