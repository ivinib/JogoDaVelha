package com.example.admin.velha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btnSinglePlayer(View v){
        Toast toast = Toast.makeText(this,"Em desenvolvimento",Toast.LENGTH_LONG);
        toast.show();
    }
    public void btnMultiPlayer(View v){
        Intent i = new Intent(this, SinglePlayerActivity.class);
        startActivity(i);
    }
}
