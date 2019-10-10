package com.example.noteyourday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//@BindView(R.id.saveButton) Button saveButton;
//@BindView(R.id.dayEditText) EditText dayEdit;
//@BindView(R.id.nameOfApp) TextView nameOfApp;

    private Button saveButton;
    private EditText dayEdit;
    private  TextView nameOfApp;
    private ListView feelView;
    private String[] dayFeelings=new String[]{"Happy","Proud","Loved","Fantastic","Great","Crazy",
    "Relaxed","Angry","Devilish","Blessed","Cheerful","Energetic","Beautiful","Excited","Lucky","Disappointed","Sleepy","In love","Satisfied"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
saveButton=(Button) findViewById(R.id.saveButton) ;
dayEdit=(EditText) findViewById(R.id.dayEditText);
nameOfApp=(TextView) findViewById(R.id.myDayFeeling);
//        ButterKnife.bind(this);
        saveButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if (v==saveButton){
            Intent day=new Intent(MainActivity.this, MyDiaryFeeling.class);
            String myDay=dayEdit.getText().toString();
            Toast.makeText(MainActivity.this,myDay, Toast.LENGTH_LONG).show();
            day.putExtra("myDay",myDay);
            startActivity(day);
        }
    }
}
