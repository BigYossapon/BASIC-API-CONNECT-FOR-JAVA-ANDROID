package Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api_connect.AddActivity;
import com.example.api_connect.EditActivity;
import com.example.api_connect.MainActivity;
import com.example.api_connect.R;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import Api.User_API;
import Model.UserInfo;

public class AdapterList extends RecyclerView.Adapter<AdapterList.ItemHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<UserInfo> listUser;
    Dialog mydialog;
    Context pr;


    public AdapterList(Context context, List<UserInfo> listUser){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.listUser = listUser;

    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v = layoutInflater.inflate(R.layout.layout_data,parent,false);
        pr = parent.getContext();
        return new AdapterList.ItemHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int i) {

        mydialog = new Dialog(pr);
        mydialog.setContentView(R.layout.option);
        mydialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        UserInfo info = listUser.get(i);
        String id = String.valueOf(info.getID());
        holder.id.setText(id);
        holder.name.setText(info.getName());
        holder.address.setText(info.getAddress());
        holder.mail.setText(info.getMail());
        holder.phone.setText(info.getPhone());

        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn_cancel = (Button) mydialog.findViewById(R.id.option_cancel);
                Button btn_edit = (Button) mydialog.findViewById(R.id.option_edit);
                Button btn_delete = (Button) mydialog.findViewById(R.id.option_delete);
                btn_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = info.getName();
                        String phone = info.getPhone();
                        String mail = info.getMail();
                        String address = info.getAddress();
                        int id = info.getID();
                        Intent newActivity = new Intent(pr, EditActivity.class);
                        newActivity.putExtra("id",id);
                        newActivity.putExtra("name",name);
                        newActivity.putExtra("phone",phone);
                        newActivity.putExtra("mail",mail);
                        newActivity.putExtra("address",address);
                        pr.startActivity(newActivity);
                    }
                });
                btn_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id_del = info.getID();
                        new User_API().DELETE_DistributeUserinfo(pr, id_del, new User_API.DistributeDeleteUserinfoListener() {

                            @Override
                            public void onListener(Integer http_code) {
                                if(http_code.equals(200)){
                                    Toast.makeText(pr,"Delete Complete",Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    //reconnect ,lost connect
                                    Toast.makeText(pr,"Internet is not Connected, Please check your internet",Toast.LENGTH_SHORT).show();

//                    RefreshLists(info);
                                }
                            }


                        });

                    }
                });
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mydialog.dismiss();
                    }
                });
                mydialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {

        return listUser.size();

    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        final TextView id,name,phone,address,mail;
        final LinearLayoutCompat click;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.data_id);
            name = (TextView) itemView.findViewById(R.id.data_name);
            phone = (TextView) itemView.findViewById(R.id.data_phone);
            address = (TextView) itemView.findViewById(R.id.data_address);
            mail = (TextView) itemView.findViewById(R.id.data_mail);
            click = (LinearLayoutCompat) itemView.findViewById(R.id.click);

        }





    }


}
