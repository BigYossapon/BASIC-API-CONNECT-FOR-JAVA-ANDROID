package com.example.api_connect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import Api.User_API;
import Model.UserInfo;

public class AddActivity extends AppCompatActivity {
    EditText edittext_add_name,edittext_add_phone,edittext_add_mail,edittext_add_address;
    Button button_add_confirm,button_add_clear;
    ImageButton button_add_back;
    String name,phone,mail,address;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        edittext_add_name = (EditText) findViewById(R.id.editText_add_name);
        edittext_add_phone = (EditText) findViewById(R.id.editText_add_phone);
        edittext_add_mail = (EditText) findViewById(R.id.editText_add_mail);
        edittext_add_address = (EditText) findViewById(R.id.editText_add_address);
        button_add_confirm = (Button) findViewById(R.id.button_add_confirm);
        button_add_clear = (Button) findViewById(R.id.button_add_clear);
        button_add_back = (ImageButton) findViewById(R.id.button_add_back);

        button_add_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(AddActivity.this,MainActivity.class);
                startActivity(newActivity);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        button_add_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edittext_add_name.getText().toString();
                phone = edittext_add_phone.getText().toString();
                mail = edittext_add_mail.getText().toString();
                address = edittext_add_address.getText().toString();
                id = 0;

                UserInfo userinfo = new UserInfo();
                userinfo.setName(name);
                userinfo.setPhone(phone);
                userinfo.setMail(mail);
                userinfo.setAddress(address);
                userinfo.setID(id);

                new User_API().POST_DistributeUserinfo(AddActivity.this, userinfo, new User_API.DistributeAddUserinfoListener() {
                    @Override
                    public void onListener(Integer http_code) {
                        if(http_code == 200){
                            Toast.makeText(AddActivity.this, "success", Toast.LENGTH_SHORT).show();
                            Intent newActivity = new Intent(AddActivity.this,MainActivity.class);
                            startActivity(newActivity);
                            finish();
                        }
                        else{
                            Toast.makeText(AddActivity.this, "no connect api", Toast.LENGTH_SHORT).show();
                        }
                    }


                });


            }
        });

        button_add_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edittext_add_name.setText("");
                edittext_add_phone.setText("");
                edittext_add_mail.setText("");
                edittext_add_address.setText("");
            }
        });
    }
}