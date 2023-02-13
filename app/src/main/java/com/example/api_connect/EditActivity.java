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

import org.w3c.dom.Text;

import Api.User_API;
import Model.UserInfo;

public class EditActivity extends AppCompatActivity {
    TextView text_edit_id;
    EditText edittext_edit_name,edittext_edit_phone,edittext_edit_mail,edittext_edit_address;
    Button button_edit_confirm,button_edit_clear;
    ImageButton button_edit_back;
    String name,phone,mail,address,name_get,phone_get,mail_get,address_get;
    int id_get;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        text_edit_id = (TextView) findViewById(R.id.text_edit_id);
        edittext_edit_name = (EditText) findViewById(R.id.editText_edit_name);
        edittext_edit_phone = (EditText) findViewById(R.id.editText_edit_phone);
        edittext_edit_mail = (EditText) findViewById(R.id.editText_edit_mail);
        edittext_edit_address = (EditText) findViewById(R.id.editText_edit_address);
        button_edit_confirm = (Button) findViewById(R.id.button_edit_confirm);
        button_edit_clear = (Button) findViewById(R.id.button_edit_clear);
        button_edit_back = (ImageButton) findViewById(R.id.button_edit_back);

        button_edit_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(EditActivity.this,MainActivity.class);
                startActivity(newActivity);
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent get = getIntent();
        Bundle data = get.getExtras();
        if(data!=null){
             name_get = String.valueOf(data.get("name"));
             phone_get = String.valueOf(data.get("phone"));
             mail_get = String.valueOf(data.get("mail"));
             address_get = String.valueOf(data.get("address"));
             id_get = (int) data.get("id");

            id = String.valueOf(id_get);
            text_edit_id.setText(id);
            edittext_edit_name.setText(name_get);
            edittext_edit_phone.setText(phone_get);
            edittext_edit_mail.setText(mail_get);
            edittext_edit_address.setText(address_get);

        }


        button_edit_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edittext_edit_name.getText().toString();
                phone = edittext_edit_phone.getText().toString();
                mail = edittext_edit_mail.getText().toString();
                address = edittext_edit_address.getText().toString();

                UserInfo userinfo = new UserInfo();
                userinfo.setName(name);
                userinfo.setPhone(phone);
                userinfo.setMail(mail);
                userinfo.setAddress(address);
                userinfo.setID(id_get);



                new User_API().PUT_DistributeUserinfo(EditActivity.this, userinfo, new User_API.DistributeEditUserinfoListener() {
                    @Override
                    public void onListener(Integer http_code) {
                        if(http_code == 200){
                            Toast.makeText(EditActivity.this, "success", Toast.LENGTH_SHORT).show();
                            Intent newActivity = new Intent(EditActivity.this,MainActivity.class);
                            startActivity(newActivity);
                            finish();
                        }
                        else{
                            Toast.makeText(EditActivity.this, "no connect api", Toast.LENGTH_SHORT).show();
                        }
                    }


                });


            }
        });

        button_edit_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edittext_edit_name.setText("");
                edittext_edit_phone.setText("");
                edittext_edit_mail.setText("");
                edittext_edit_address.setText("");
            }
        });
    }
}