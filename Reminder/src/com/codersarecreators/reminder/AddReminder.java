package com.codersarecreators.reminder;

import java.util.UUID;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codersarecreators.factory.AbstractFactory;
import com.codersarecreators.factory.ReminderConcreteFactory;

public class AddReminder extends Activity {

	Button scheduleSMSDialogueContactsBtn, scheduleSMSDialogueDateBtn,
			scheduleSMSDialogueTimeBtn, scheduleSMSDialogueSaveBtn;
	TextView addReminderScreenDateTxtView, addReminderScreenTimeTxtView,
			scheduleSMSDialogueDateTxtView, scheduleSMSDialogueTimeTxtView;
	CheckBox addReminderScreenScheduleSMSChkBox;
	AbstractFactory factoryObject = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_reminder_screen);
		addReminderScreenDateTxtView = (TextView) findViewById(R.id.addReminderScreen_dateTxtView);
		addReminderScreenTimeTxtView = (TextView) findViewById(R.id.addReminderScreen_timeTxtView);
		addReminderScreenScheduleSMSChkBox = (CheckBox) findViewById(R.id.addReminderScreen_scheduleSMSChkBox);
		addReminderScreenScheduleSMSChkBox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							/*
							 * If Checkbox is checked, then Schedule SMS
							 * dialogue is displayed
							 */
							displayScheduleSMSDialogue(buttonView);
						} else {
							/*
							 * When Checkbox is unchecked , the schedule SMS is
							 * deleted from database.
							 */
							MyToast.RaiseToast("Schedule SMS has been removed from list");
						}
					}
				});
	}// end of onCreate Method

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * @param view
	 * @author siddharth Method invoked on clicking on Set Button present on the
	 *         Add ReminderScreen to set the reminder
	 */
	public void setReminder(View view) {

		EditText reminderNote = (EditText) findViewById(R.id.addReminderScreen_notesEditText);
		TextView reminderDate = (TextView) findViewById(R.id.addReminderScreen_dateTxtView);
		TextView reminderTime = (TextView) findViewById(R.id.addReminderScreen_timeTxtView);
		if ((addReminderScreenScheduleSMSChkBox != null)
				&& (addReminderScreenScheduleSMSChkBox.isChecked())) {
			// this means that there has been an addition for sms object as
			// well. Process this later.
		}
		factoryObject = new ReminderConcreteFactory();
		ReminderObject reminderObj = factoryObject.CreateReminderObject(UUID
				.randomUUID().toString(), reminderDate.getText().toString(),
				reminderTime.getText().toString(), reminderNote.getText()
						.toString());
		DatabaseGateway.GetDbGateWay().InsertReminder(reminderObj);
		// factoryObject = new SmsF
	}

	/**
	 * @param view
	 * @author fatema Method invoked on clicking on Cancel button present on Add
	 *         Reminder Screen. It will navigate back to the MainActivity.java
	 *         i.e home screen and reminder won't be saved.
	 */
	public void cancelReminder(View view) {
		Intent intent = new Intent(AddReminder.this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

	// Function called when Schedule SMS checkbox is clicked
	public void displayScheduleSMSDialogue(View view) {

		final Dialog scheduleSMSDialog = new Dialog(view.getContext());
		scheduleSMSDialog.setCancelable(true);
		scheduleSMSDialog.setTitle("Schedule SMS");
		scheduleSMSDialog.setContentView(R.layout.schedule_sms_dialogue);

		scheduleSMSDialogueDateTxtView = (TextView) scheduleSMSDialog
				.findViewById(R.id.scheduleSMSDialogue_dateTxtView);
		scheduleSMSDialogueTimeTxtView = (TextView) scheduleSMSDialog
				.findViewById(R.id.scheduleSMSDialogue_timeTxtView);

		// On Click listener on Contacts image
		scheduleSMSDialogueContactsBtn = (Button) scheduleSMSDialog
				.findViewById(R.id.scheduleSMSDialogue_contactsBtn);
		scheduleSMSDialogueContactsBtn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View view) {
						 /*Contact numbers displayed and user can select number
						 to which he wants to send schedule sms*/
						Toast.makeText(view.getContext(),
								"Clicked on Contacts icon", Toast.LENGTH_LONG)
								.show();
					}
				});

		// On Click Listener on Date Button
		scheduleSMSDialogueDateBtn = (Button) scheduleSMSDialog
				.findViewById(R.id.scheduleSMSDialogue_dateBtn);
		scheduleSMSDialogueDateBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				 /*dateTextView is static text view.
				 scheduleSMSDialogueDateTxtView is assigned to dateTextView
				 where date will be displayed after getting selected.*/
				DatePickerImplementation.dateTextView = scheduleSMSDialogueDateTxtView;
				new DatePickerImplementation().displayDatePickerDialog(view);
			}
		});

		// On Click Listener on Time Button
		scheduleSMSDialogueTimeBtn = (Button) scheduleSMSDialog
				.findViewById(R.id.scheduleSMSDialogue_timeBtn);
		scheduleSMSDialogueTimeBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				 /*Object of TimePickerImplementation class created. TextView
				 where selected date is to be displayed is passed as parameter
				 to the constructor*/
				TimePickerImplementation.timeTextView = scheduleSMSDialogueTimeTxtView;
				new TimePickerImplementation().displayTimePickerDialog(view);
			}
		});

		/* On Click Listener on Save Button.
		 * It should save the values entered in database and also display
		 *  them dynamically on add_reminder_screen
		 */
				scheduleSMSDialogueSaveBtn = (Button) scheduleSMSDialog.findViewById(R.id.scheduleSMSDialogue_saveBtn);
				scheduleSMSDialogueSaveBtn.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View view) {
						//Work in Progress
						TableLayout addReminderScreenScheduleSMSTable =(TableLayout)findViewById(R.id.addReminderScreen_dynamicScheduleSMSTable);
						addReminderScreenScheduleSMSTable.setVisibility(View.VISIBLE);
						scheduleSMSDialog.dismiss();
					}
				});
		scheduleSMSDialog.show();
	}// End of displayScheduleSMSDialogue method

	// Method invoked on clicking on Date Button on Add Reminder Screen
	public void selectDateOnAddReminderScreen(View view) {
		 /*dateTextView is static text view. addReminderScreenDateTxtView is
		 assigned to dateTextView where date will be displayed after getting
		 selected.*/
		DatePickerImplementation.dateTextView = addReminderScreenDateTxtView;
		new DatePickerImplementation().displayDatePickerDialog(view);
	}

	// Method invoked on clicking on Time Button on Add Reminder Screen
	public void selectTimeOnAddReminderScreen(View view) {
		 /*Object of TimePickerImplementation class created. TextView where
		 selected time is to be displayed is passed as parameter to the
		 constructor*/
		TimePickerImplementation.timeTextView = addReminderScreenTimeTxtView;
		new TimePickerImplementation().displayTimePickerDialog(view);
	}

}
