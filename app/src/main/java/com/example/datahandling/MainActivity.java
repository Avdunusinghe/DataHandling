package com.example.datahandling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.datahandling.Database.DbHelper;

import java.util.List;



public class MainActivity extends AppCompatActivity {

    EditText et_username,et_password;
    Button saveBtn;
    private DbHelper dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_username = findViewById(R.id.password_et);
        et_password = findViewById(R.id.user_nameet);
        saveBtn = findViewById(R.id.SaveaBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbHandler = new DbHelper(MainActivity.this);

                List userNames = dbHandler.readAllInfo("username");
                List passwords = dbHandler.readAllInfo("password");

                String username = et_username.getText().toString().trim();
                String password = et_password.getText().toString().trim();

                if(userNames.indexOf(username)>=0){

                    if(passwords.get(userNames.indexOf(username)).equals(password)){
                        Toast.makeText(MainActivity.this,
                                "Sign in Successfully",
                                Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this,
                                "Login Failed",
                                Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });

    }




    public void addData(View view){

        DbHelper dbhelper = new DbHelper(this);

        long val = dbhelper.addInfo(et_username.getText().toString(),et_password.getText().toString());

        if(val > 0){

            Toast.makeText(this,"Data Insert Successfully", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();
        }
    }
    public void updateInfo(View view){

        DbHelper dbhelper = new DbHelper(this);

        long val = dbhelper.updateInfo(et_username.getText().toString(),et_password.getText().toString());

        if(val > 0){

            Toast.makeText(this,"Data Insert Successfully", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this,"Data Insert Successfully",Toast.LENGTH_SHORT).show();
        }
    }

    public void SignIn(View view){

        DbHelper dbhelper = new DbHelper(this);

        List userNames = dbhelper.readAllInfo("username");
        List passwords = dbhelper.readAllInfo("password");

        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        if(userNames.indexOf(username)>=0){

            if(passwords.get(userNames.indexOf(username)).equals(password)){
                Toast.makeText(this,"Sign in Successfully",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,"Login Failed",Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void deleteData(View view){

        DbHelper dbHelper = new DbHelper(this);
        dbHelper.deleteInfo(et_username.getText().toString());

        Toast.makeText(this,"Delete Successful",Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onResume() {
        super.onResume();

        /*saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                D
            }

            }
        });*/

    }

    /*DBHelper dbhelper = new DBHelper(this);
SQLiteDatabase db = dbhelper.getWritableDatabase();
Cursor cursor = db.query(Table,
    username,
    isCurrentUser + " = ? ",
    new String []{"true"},
    null, null, null);
cursor.moveToFirst();
String currentUsername = cursor.getString(cursor.getColumnIndex("username"));*/


}