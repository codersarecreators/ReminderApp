package com.codersarecreators.reminder;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayList<String> list = new ArrayList<String>();
        list.add("Fill Petrol");
        list.add("Wake up Sid :-)");
        list.add("Prepare weekly report");
        list.add("Today is cient call");
        list.add("Bike Servicing");
       
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , list);
       listView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void addReminder(View view){
		Intent intent = new Intent(this, addReminder.class);
		startActivity(intent);
		
	}
    public void selectContacts(View view){
		Toast.makeText(view.getContext(), "Inside selectContacts",Toast.LENGTH_LONG ).show();
		//Intent intent= new Intent(Intent.ACTION_PICK,  Contacts.CONTENT_URI);

       // startActivityForResult(intent, PICK_CONTACT);
	}

}
