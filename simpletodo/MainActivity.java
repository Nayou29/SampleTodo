package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView lvItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItem = (ListView)findViewById(R.id.lvitems);
        items = new ArrayList<>();
        readItem();
        itemsAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, items);
        lvItem.setAdapter(itemsAdapter);


        lvItem.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {

                        items.remove(pos);
                        itemsAdapter.notifyDataSetChanged();
                        writeItems();
                  //      launchComposeViewResult(pos,items.get(pos).toString());
                        return true;
                    }
                }
        );

    }

    public void onAddItem(View view) {
        EditText etNewItem = (EditText) findViewById(R.id.editText);
        String itemText = etNewItem.getText().toString();
        itemsAdapter.add(itemText);
        etNewItem.setText("");
        writeItems();
    }

    public void readItem(){
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e)
        {
            items = new ArrayList<String>();
        }
    }

    private void writeItems(){
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, items);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void launchComposeView(){
        Intent i = new Intent(this, EditItemActivity.class);
        i.putExtra("username","footbar");
        i.putExtra("in_reply_to","george");
        i.putExtra("code",400);
        startActivity(i);
    }

    private final int REQUEST_CODE = 20;

    public void onClick(View view){
        Intent i = new Intent(this,EditItemActivity.class);
        i.putExtra("mode",2);
        startActivityForResult(i,REQUEST_CODE);
    }
    public void launchComposeViewResult(int pos, String valeur){
        Intent i = new Intent(this, EditItemActivity.class);
        //i.putExtra("username","footbar");
      //  i.putExtra("in_reply_to","george");
    //    i.putExtra("code",400);
        i.putExtra("position",pos);
        i.putExtra("todo",valeur);
        startActivity(i);
    }

}
