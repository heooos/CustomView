package com.jkxy.customtestdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class DrawPieAty extends AppCompatActivity /*implements ServiceConnection, View.OnClickListener*/ {

    private ArrayList<DataInfo> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main);

        LinearLayout rootA = new LinearLayout(this);
        rootA.setOrientation(LinearLayout.VERTICAL);

        PieView view = new PieView(this);


        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lp.weight = 1;
        rootA.addView(view,lp);
        list = (ArrayList) getIntent().getSerializableExtra("data");
        view.setStartAngle(0);
        view.setData(list);
        setContentView(rootA);


    }


}
