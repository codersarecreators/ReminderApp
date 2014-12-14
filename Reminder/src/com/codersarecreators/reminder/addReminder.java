package com.codersarecreators.reminder;

import java.util.Calendar;
import java.util.StringTokenizer;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class addReminder extends Activity {
	
	 Button btn ;
	 TextView txtView1, txtView2;
	 String initialDate;
	 private String initialMonth;
	 private String initialYear;
	 private String initialHr;
	 private String initialMin;
	 private DatePickerDialog dateDialog = null;
	 private TimePickerDialog timeDialog = null;
	 private int year, month, day;
	 private int hr,min;
	 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_reminder);
		
		
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
	//Display date picker dialogue
	public void displayDatePicker(View view){
		Calendar calendar = null;
		txtView1 = (TextView) findViewById(R.id.textView1);
        String preExistingDate = (String) txtView1.getText().toString();
        
        if(preExistingDate != null && !preExistingDate.equals("")){
            StringTokenizer st = new StringTokenizer(preExistingDate,"/");
                initialMonth = st.nextToken();
                initialDate = st.nextToken();
                initialYear = st.nextToken();
                if(dateDialog == null)
                dateDialog = new DatePickerDialog(view.getContext(),
                                 new PickDate(),Integer.parseInt(initialYear),
                                 Integer.parseInt(initialMonth),
                                 Integer.parseInt(initialDate));
               
                
        } else {
        	calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
          
            if(dateDialog == null)
            dateDialog = new DatePickerDialog(view.getContext(),new PickDate(),year,month,
                                               day);
           
        }
          
          dateDialog.show();
      }
      
  	
	public void saveReminder(View view){
		
		Toast.makeText(getApplicationContext(), "Reminder is set", Toast.LENGTH_LONG+1).show();
	}
	
	
	public void SMSDialogue(View view){
		final Dialog dialog = new Dialog(view.getContext());
		dialog.setCancelable(true);
		dialog.setTitle("Mobile Number");
		dialog.setContentView(R.layout.contacts_dialog);
		Button selectContacts =(Button)dialog.findViewById(R.id.contacts);
		selectContacts.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Toast.makeText(v.getContext(), "Inside selectContacts",Toast.LENGTH_LONG ).show();

			}
			
		});
		
		  
		dialog.show();
	}
	
	
	//Date Picker Listener
	private class PickDate implements DatePickerDialog.OnDateSetListener {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		
	        txtView1.setText(monthOfYear+1+"/"+dayOfMonth+"/"+year);
	        dateDialog.hide();
		}
}
	
	// Display Time Picker Dialogue
	public void displayTimePicker(View view){
		Calendar calendar = null;
		txtView2 = (TextView) findViewById(R.id.textView2);
        String preExistingTime = (String) txtView2.getText().toString();
        
        if(preExistingTime != null && !preExistingTime.equals("")){
            StringTokenizer st = new StringTokenizer(preExistingTime,":");
                initialHr = st.nextToken();
                initialMin = st.nextToken();
                if(timeDialog == null)
                	timeDialog = new TimePickerDialog(view.getContext(),
                                 new PickTime(),Integer.parseInt(initialHr),
                                 Integer.parseInt(initialMin),false);
              
                
        } else {
        	calendar = Calendar.getInstance();
            hr = calendar.get(Calendar.HOUR);
            min = calendar.get(Calendar.MINUTE);
           
          
            if(timeDialog == null)
            timeDialog = new TimePickerDialog(view.getContext(),new PickTime(),hr,min,
                                               false);
          
        }
          
          timeDialog.show();
      }
      
	// Time Picker Listener
	private class PickTime implements TimePickerDialog.OnTimeSetListener {

		@Override
		public void onTimeSet(TimePicker view, int hr, int min) {
			txtView2.setText(hr+":"+min);
			timeDialog.hide();
		}
	}
	
}
