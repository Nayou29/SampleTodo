package com.codepath.simpletodo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class EditItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

    }

    public void onSubmit (View v){
        this.finish();
    }

    protected void OnCreate(Bundle savedInstanceState){
        String username = getIntent().getStringExtra("todo");
       // String inReplyTo = getIntent().getStringExtra("in_reply_to");
        int code = getIntent().getIntExtra("pos",0);
    }
}
