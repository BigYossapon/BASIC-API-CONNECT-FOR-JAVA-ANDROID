package com.example.api_connect;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import Adapter.AdapterList;
import Api.User_API;
import Model.UserInfo;

public class MainActivity extends AppCompatActivity {
    TextView txt_progressbar;
    ProgressDialog progressbar;
    ImageButton adddata;
    RecyclerView RecyclerMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adddata = (ImageButton) findViewById(R.id.AddMainButton);
        RecyclerMain = (RecyclerView) findViewById(R.id.RecyclerMain);

        progressbar = new ProgressDialog(MainActivity.this);
        progressbar.setCanceledOnTouchOutside(false);
        progressbar.setCancelable(false);
        progressbar.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    return true; // Consumed
                }
                else {
                    return false; // Not consumed
                }
            }
        });

        adddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(MainActivity.this,AddActivity.class);
                startActivity(newActivity);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        RefreshList();
    }

    public void RefreshList(){

        //cookies
        // RefreshLists(info);
        progressbar.show();
        progressbar.setContentView(R.layout.progress_dialog);
        txt_progressbar = (TextView) progressbar.findViewById(R.id.txt_progressbar);
        txt_progressbar.setText("Loading...");
        progressbar.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        UserInfo userinfo = new UserInfo();
        userinfo.setID(1);
        int id=0;
        new User_API().GET_DistributeProfile(MainActivity.this,id,new User_API.DistributeProfileListener() {

            @Override
            public void onListener( List<UserInfo> info, Integer http_code) {
                if(http_code.equals(200)){

                    AdapterList adapter = new AdapterList(getApplicationContext(), info) ;

//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

                    RecyclerMain.setHasFixedSize(true);
                    RecyclerMain.setLayoutManager(layoutManager);
                    RecyclerMain.setAdapter(adapter);
                    //progressbar.cancel();

                    progressbar.cancel();
                }else if(http_code.equals(504)){
                    //reconnect ,lost connect
                    progressbar.cancel();
                    Toast.makeText(MainActivity.this,"Internet is not Connected, Please check your internet 504",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    //reconnect ,lost connect
                    progressbar.cancel();
                    Toast.makeText(MainActivity.this,"Internet is not Connected, Please check your internet",Toast.LENGTH_SHORT).show();

//                    RefreshLists(info);
                }

            }

        });

    }
}