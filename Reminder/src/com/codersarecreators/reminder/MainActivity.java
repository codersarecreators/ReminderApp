package com.codersarecreators.reminder;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static Context contextObj = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //set the contextObj
        //Populate list view with Reminders
        contextObj = this;
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayList<String> reminderList = new ArrayList<String>();
        reminderList.add("Fill Petrol");
        reminderList.add("Wake up Sid :-)");
        reminderList.add("Prepare weekly report");
        reminderList.add("Today is cient call");
        reminderList.add("Bike Servicing");
       
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , reminderList);
        listView.setAdapter(adapter);
        DatabaseGateway.GetDbGateWay();
    }


    //GetContext Method
    public static Context GetContext()
    {
    	return contextObj;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    // Function called when clicked on + sign 
    public void addReminder(View view){
    	
    	//Launches new Activity addReminder 
		Intent intent = new Intent(this, addReminder.class);
		startActivity(intent);
		
	}
    
   
    //Function called when we click on -- sign
    public void deleteReminder(View view){
    	
    	ArrayList<String> reminderlist = new ArrayList<String>();
    	reminderlist.add("Fill Petrol");
    	reminderlist.add("Wake up Sid :-)");
    	reminderlist.add("Prepare weekly report");
    	reminderlist.add("Today is cient call");
    	reminderlist.add( "Bike Servicing");
    	
    	final Dialog dialog = new Dialog(this);
    	View deleterminderpopup = getLayoutInflater().inflate(R.layout.deletereminderpopup, null);
        ListView listview = (ListView) deleterminderpopup.findViewById(R.id.listView);

    	dialog.setCancelable(true);
	
		
		CustomListAdapterDialog adapter = new
		        CustomListAdapterDialog(deleterminderpopup.getContext(), reminderlist);
		
		listview.setAdapter(adapter);
		        dialog.setContentView(deleterminderpopup);
		        /*list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		                @Override
		                public void onItemClick(AdapterView<?> parent, View view,
		                                        int position, long id) {
		                    Toast.makeText(MainActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
		                }
		            });
		  }*/
		        
		dialog.show();
    }

}
