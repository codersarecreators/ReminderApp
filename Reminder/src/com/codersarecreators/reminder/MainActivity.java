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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// set the contextObj
		// Populate list view with Reminders
		contextObj = this;
		ListView listView = (ListView) findViewById(R.id.listView);

		// Currently hardcoded. We have to get the list from TableReminder in
		// database
		ArrayList<String> reminderList = new ArrayList<String>();
		reminderList.add("Fill Petrol");
		reminderList.add("Wake up Sid :-)");
		reminderList.add("Prepare weekly report");
		reminderList.add("Today is cient call");
		reminderList.add("Bike Servicing");

		// Pass the list of Reminders to ArrayAdapter
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, reminderList);
		listView.setAdapter(adapter);

		// Listener set on items in the list
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				MyToast.RaiseToast("Item in the List clicked");
			}
		});
		DatabaseGateway.GetDbGateWay();
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

		// Launches new Activity AddReminder which has functionalities to add a
		// new Reminder
		Intent intent = new Intent(this, AddReminder.class);
		startActivity(intent);

	}

	// Function called when we click on -- sign
	public void displayDeleteReminderDialogue(View view) {
		// Displays a dialogue with list of reminders and checkboxes. User can
		// select the reminders he wants to delete and click on delete

		// Currently hardcoded. We have to get the list from TableReminder in
		// database
		ArrayList<String> reminderlist = new ArrayList<String>();
		reminderlist.add("Fill Petrol");
		reminderlist.add("Wake up Sid :-)");
		reminderlist.add("Prepare weekly report");
		reminderlist.add("Today is cient call");
		reminderlist.add("Bike Servicing");

		final Dialog deleteReminderDialogue = new Dialog(this);
		deleteReminderDialogue.setCancelable(true);

		// delete_reminder_dialogue.xml is the View which will be inflated on
		// the dialogue
		View deleteReminderDialogueView = getLayoutInflater().inflate(
				R.layout.delete_reminder_dialogue, null);

		ListView listview = (ListView) deleteReminderDialogueView
				.findViewById(R.id.listView);

		// Fill the list view with list of reminders and checkboxes, so need
		// CustomAdapter
		CustomListAdapterDeleteReminderDialog customListAdapterDeleteReminderDialog = new CustomListAdapterDeleteReminderDialog(
				deleteReminderDialogueView.getContext(), reminderlist);

		listview.setAdapter(customListAdapterDeleteReminderDialog);

		deleteReminderDialogue.setContentView(deleteReminderDialogueView);

		deleteReminderDialogue.show();
	}

}
