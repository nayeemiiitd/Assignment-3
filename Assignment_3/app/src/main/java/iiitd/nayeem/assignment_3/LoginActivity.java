package iiitd.nayeem.assignment_3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {

    private EditText username,password;
    DatabaseHelper myDb;
    private final String file = "dates.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login,signup,del_acc;
        TextView f_pass;

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login_button);
        signup = (Button) findViewById(R.id.sign_up);
        del_acc = (Button) findViewById(R.id.del_acc);
        f_pass = (TextView) findViewById(R.id.f_password);

        myDb = new DatabaseHelper(this);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        if(pref.getString("name_key",null)!=null)
        {
            Toast.makeText(LoginActivity.this, "New Fragment will open", Toast.LENGTH_SHORT).show();
            Intent a1 = new Intent(getApplicationContext(),notesActivity.class);
            startActivity(a1);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(!username.getText().toString().equals("") && !password.getText().toString().equals(""))
                {
                    Cursor res = myDb.getpassword(username.getText().toString());
                    StringBuffer buffer = new StringBuffer();
                    res.moveToNext();
                    if(res.getCount()==0)
                        Toast.makeText(LoginActivity.this, "No username Exist", Toast.LENGTH_SHORT).show();
                    else
                    {
                        buffer.append(res.getString(0));
                        if(buffer.toString().equals(password.getText().toString()))
                        {
                            //for internal storage getting date value
                            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                            FileOutputStream fOut;
                            try
                            {
                                fOut = openFileOutput(file,MODE_PRIVATE);
                                fOut.write(date.getBytes());
                                fOut.close();
                                Toast.makeText(LoginActivity.this, "Timestamp saved in Internal Storage", Toast.LENGTH_SHORT).show();
                            }
                            catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }

                            //for reading internal storage
                            FileInputStream fis;
                            try
                            {
                                fis = openFileInput(file);
                                InputStreamReader isr = new InputStreamReader(fis);
                                BufferedReader bufferedReader = new BufferedReader(isr);
                                String line;
                                while ((line = bufferedReader.readLine()) != null)
                                {
                                    if((bufferedReader.readLine())==null){
                                        Toast toast= Toast.makeText(LoginActivity.this,line, Toast.LENGTH_SHORT);
                                        toast.setGravity(Gravity.TOP, 0, 0);
                                        toast.show();
                                    }
                                }
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            //making sharedpreference state
                            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("name_key",username.getText().toString());
                            editor.putString("pass_key",password.getText().toString());
                            editor.apply();
                            Toast.makeText(LoginActivity.this, "Credentials saved in sharedPreferenceState", Toast.LENGTH_SHORT).show();

                            //Opening notesActivity activity
                            Intent a1 = new Intent(view.getContext(),notesActivity.class);
                            startActivity(a1);

                            }
                        else
                            Toast.makeText(LoginActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Please Fill all the details.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(view.getContext(),SignupActivity.class);
                startActivity(a);
            }
        });

        del_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!username.getText().toString().equals(""))
                {
                    Integer deletedRows = myDb.deleteData(username.getText().toString());
                    if(deletedRows>0)
                    {
                        //Ending the sharedPreference of the user
                        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.remove("name_key");
                        editor.remove("pass_key");
                        editor.apply();

                        //dialog box
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
                        alertDialogBuilder.setMessage("Account Deleted for "+username.getText().toString());

                        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
//                            Toast.makeText(LoginActivity.this,"dialog box is going to close",Toast.LENGTH_LONG).show();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                    }
                    else
                        Toast.makeText(LoginActivity.this, "Account Does not Exist", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(LoginActivity.this, "Please Enter the Username", Toast.LENGTH_SHORT).show();
            }
        });

        f_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!username.getText().toString().equals(""))
                {
                    Cursor res = myDb.getpassword(username.getText().toString());
                    StringBuffer buffer = new StringBuffer();
                    res.moveToNext();
                    if (res.getCount() == 0)
                        Toast.makeText(LoginActivity.this, "No username exists", Toast.LENGTH_SHORT).show();
                    else
                    {
                        buffer.append(res.getString(0));
                        //dialog box
                        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
                        alertDialogBuilder.setMessage("Your password is: "+buffer.toString());

                        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(LoginActivity.this, "dialog box is going to close", Toast.LENGTH_LONG).show();

                            }
                        });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                }
                else
                    Toast.makeText(LoginActivity.this, "Please Enter the Username", Toast.LENGTH_SHORT).show();
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
