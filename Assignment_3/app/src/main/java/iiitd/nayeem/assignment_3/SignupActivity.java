package iiitd.nayeem.assignment_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    private EditText user,pass,mobile,email;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button submit;

        myDb = new DatabaseHelper(this);

        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        mobile = (EditText) findViewById(R.id.mobile);
        email = (EditText) findViewById(R.id.email);
        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!user.getText().toString().equals("") && !email.getText().toString().equals("") && !pass.getText().toString().equals("")&& !mobile.getText().toString().equals(""))
                {
                    Boolean isInserted = myDb.insertData(user.getText().toString(),email.getText().toString(),pass.getText().toString(),mobile.getText().toString());
                    if(isInserted)
                    {
                        Toast.makeText(SignupActivity.this, "Calling Login Activity", Toast.LENGTH_SHORT).show();
                        Intent b = new Intent(view.getContext(),LoginActivity.class);
                        startActivity(b);
                    }
                    else
                        Toast.makeText(SignupActivity.this, "Some error", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(SignupActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
