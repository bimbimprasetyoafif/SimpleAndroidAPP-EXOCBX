package com.dicoding.assosiate.exo_cbxprofile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setTitle("Detail Profile");

        getIntentNow();
    }

    private void getIntentNow(){
        String name_detail = getIntent().getStringExtra("name");
        String date_detail = getIntent().getStringExtra("date");
        String desc_detail = getIntent().getStringExtra("desc");
        String img_detail = getIntent().getStringExtra("imgdetail");

        setIntentNow(name_detail, date_detail, desc_detail, img_detail);

    }

    private void setIntentNow(String name, String date, String des, String imgDetail){
        int imgdtl = getResources().getIdentifier(imgDetail,null,getPackageName());
        TextView nameIntent = findViewById(R.id.name_detail);
        TextView dobIntent = findViewById(R.id.ttl_detail);
        TextView descIntent = findViewById(R.id.describtion);
        ImageView imgdetailIntent = findViewById(R.id.img_detail);

        nameIntent.setText(name);
        dobIntent.setText(date);
        descIntent.setText(des);
        imgdetailIntent.setImageResource(imgdtl);
    }
}
