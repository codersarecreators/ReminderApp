package com.codersarecreators.reminder;

import java.util.ArrayList;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	private static Context contextObj = null;
	private static ArrayList<ReminderObject> listReminderObject = null;
	private static ArrayList<String> listString = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// set the contextObj
		// Populate list view with Reminders
		contextObj = this;
		ListView listView = (ListView) findViewById(R.id.listView);
		listReminderObject = new ArrayList<ReminderObject>();
		// Pass the list of Reminders to ArrayAdapter
		listReminderObject = DatabaseGateway.GetDbGateWay().GetTodaysReminders();
		// writing demo code, that I anticipate, will be removed later on...
		listString = GetListForDisplay();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listString);
		listView.setAdapter(adapter);

		// Listener set on items in the list
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				MyToast.RaiseToast("Item in the List clicked");
			}
		});
	}

	// GetContext Method
	public static Context GetContext() {
		return contextObj;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// Function called when clicked on + sign
	public void displayAddReminderScreen(View view) {
		// Launches new Activity AddReminder which has functionalities to add new Reminder
		Intent intent = new Intent(this, AddReminder.class);
		startActivity(intent);
	}

	// Function called when we click on -- sign
	public void displayDeleteReminderDialogue(View view) {
		 /* what we have to do here is that, we just have to get list of reminders for today.
		 * this has to be retrieved in the way, such that, just one call should get all the current reminders
		 * loading only some of the reminders will also not be very heavy on the database.
		 */
		//listReminderObject = DatabaseGateway.GetDbGateWay().GetTodaysReminders();
		if (listReminderObject.size() > 0) {
			final Dialog deleteReminderDialogue = new Dialog(this);
			deleteReminderDialogue.setCancelable(true);
			// delete_reminder_dialogue.xml is the View which will be inflated
			// on
			// the dialogue
			View deleteReminderDialogueView = getLayoutInflater().inflate(
					R.layout.delete_reminder_dialogue, null);
			ListView listview = (ListView) deleteReminderDialogueView
					.findViewById(R.id.listView);
			// Fill the list view with list of reminders and checkboxes, so need
			// CustomAdapter
			if(listString == null)
			{
				listString = GetListForDisplay();
			}
			CustomListAdapterDeleteReminderDialog customListAdapterDeleteReminderDialog = new CustomListAdapterDeleteReminderDialog(
					deleteReminderDialogueView.getContext(), listString);
			listview.setAdapter(customListAdapterDeleteReminderDialog);
			deleteReminderDialogue.setContentView(deleteReminderDialogueView);
			deleteReminderDialogue.show();
		}
		else
		{
			MyToast.RaiseToast("No Reminders found in the list to delete!");
		}
	}
	
	private ArrayList<String> GetListForDisplay()
	{
		ArrayList<String> listString = new ArrayList<String>();
		for(int i=0;i<listReminderObject.size();i++)
		{
			listString.add(listReminderObject.get(i).getTime() + ": " + listReminderObject.get(i).getNote());
		}
		return listString;
	}
}
