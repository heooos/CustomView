package com.jkxy.customtestdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int index=0;
    RelativeLayout rl;
    private ArrayList<DataInfo> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        rl = (RelativeLayout) findViewById(R.id.rl);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                if (index<9){
                    RelativeLayout.LayoutParams lp0 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    lp0.setMargins(100,index*100,0,0);
                    lp0.addRule(RelativeLayout.ALIGN_BOTTOM,index+10086);
                    rl.addView(addTv(),lp0);

                    RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    lp1.setMargins(0,10+index*100,0,0);
                    lp1.addRule(RelativeLayout.RIGHT_OF,index+10000);
                    rl.addView(addEt(),lp1);

                    index++;
                }else {
                    Toast.makeText(MainActivity.this,"最多9组数据",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button2:

                for (int i = 1; i <= index ; i++) {
                    DataInfo info = new DataInfo();
                    EditText text = (EditText) findViewById(index-i+10086);
                    Log.d("text",text.getText().toString());
                    info.setValue(Integer.valueOf(text.getText().toString()));
                    list.add(info);
                }
                Intent i = new Intent(MainActivity.this,DrawPieAty.class);
                i.putExtra("data",list);
                startActivity(i);
                finish();
                break;
            case R.id.button3:
                if (index>0){

                    rl.removeView(findViewById(index-1+10086));
                    rl.removeView(findViewById(index-1+10000));
                    index--;
                }else {
                    Toast.makeText(MainActivity.this,"所有数据已经移除",Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    private EditText addEt(){
        EditText editText = new EditText(this);
        editText.setId(index+10086);
        editText.setWidth(100);
        editText.setText("1");
        editText.setInputType(0x00000002);
        System.out.println(editText.getId());
        return editText;
    }

    private TextView addTv(){
        TextView textView = new TextView(this);
        textView.setId(index+10000);
        textView.setPadding(0,0,0,15);
        textView.setText("第"+(index+1)+"组数据:");
        return textView;
    }
}
