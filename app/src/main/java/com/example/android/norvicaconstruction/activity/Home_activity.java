package com.example.android.norvicaconstruction.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.android.norvicaconstruction.R;

public class Home_activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView inventoryImgview;
    private ImageView equimImgview;
    private ImageView taskImgview;
    private ImageView requirImgview;
    private ImageView emergImgview;
    ImageView labor_imageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activity);
        findViews();
    }
    private void findViews() {
        labor_imageview = (ImageView)findViewById( R.id.labor_imageview);
        inventoryImgview = (ImageView)findViewById( R.id.inventory_imgview );
        equimImgview = (ImageView)findViewById( R.id.equim_imgview );
        taskImgview = (ImageView)findViewById( R.id.task_imgview );
        requirImgview = (ImageView)findViewById( R.id.requir_imgview );
        emergImgview = (ImageView)findViewById( R.id.emerg_imgview );


        labor_imageview .setOnClickListener(this);
        inventoryImgview .setOnClickListener(this);
        equimImgview .setOnClickListener(this);
        taskImgview.setOnClickListener(this);
        requirImgview .setOnClickListener(this);
        emergImgview .setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v==labor_imageview)
        {
            Intent intent=new Intent(Home_activity.this ,Labor_activity_demo.class);
            startActivity(intent);
        }
        else if (v==inventoryImgview)
        {
            Intent intent=new Intent(Home_activity.this ,Labor_Class.class);
            startActivity(intent);
        }
        else if (v==equimImgview)
        {
            Intent intent=new Intent(Home_activity.this ,Labor_activity.class);
            startActivity(intent);
        }
        else if (v==taskImgview)
        {
            Intent intent=new Intent(Home_activity.this ,Labor_activity.class);
            startActivity(intent);
        }
        else if (v==requirImgview)
        {
            Intent intent=new Intent(Home_activity.this ,Labor_activity.class);
            startActivity(intent);
        }
        else if (v==emergImgview)
        {
            Intent intent=new Intent(Home_activity.this ,Labor_activity.class);
            startActivity(intent);
        }

    }
}
