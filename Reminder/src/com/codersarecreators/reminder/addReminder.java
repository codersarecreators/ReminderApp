package com.codersarecreators.reminder;

import java.util.Calendar;
import java.util.StringTokenizer;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class addReminder extends Activity {
	
	 Button btn ;
	 EditText   txtView;
	 String initialDate;
	 private String initialMonth;
	 private String initialYear;
	 private DatePickerDialog dialog = null;
	 private Calendar calendar;
	  
	   private int year, month, day;
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
	
	@SuppressWarnings("deprecation")
	public void displayDatePicker(View view){
		Calendar calendar = null;
		txtView = (EditText) findViewById(R.id.textView1);
        String preExistingDate = (String) txtView.getText().toString();
        
        if(preExistingDate != null && !preExistingDate.equals("")){
            StringTokenizer st = new StringTokenizer(preExistingDate,"/");
                initialMonth = st.nextToken();
                initialDate = st.nextToken();
                initialYear = st.nextToken();
                if(dialog == null)
                dialog = new DatePickerDialog(view.getContext(),
                                 new PickDate(),Integer.parseInt(initialYear),
                                 Integer.parseInt(initialMonth),
                                 Integer.parseInt(initialDate));
               /* dialog.updateDate(Integer.parseInt(initialYear),
                                 Integer.parseInt(initialMonth),
                                 Integer.parseInt(initialDate));*/
                
        } else {
        	calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
          
            if(dialog == null)
            dialog = new DatePickerDialog(view.getContext(),new PickDate(),year,month,
                                               day);
           // dialog.updateDate(dtTxt.getTime().getYear(),dtTxt.getTime().getMonth(),
           //                                     dtTxt.getTime().getDay());
        }
          
          dialog.show();
      }
      
  	
	public void saveReminder(View view){
		
		Toast.makeText(getApplicationContext(), "Reminder is set", Toast.LENGTH_LONG+1).show();
	}
	
	
	public void SMSDialogue(View view){
		final Dialog dialog = new Dialog(view.getContext());
		dialog.setCancelable(true);
		dialog.setTitle("Mobile Number");
		dialog.setContentView(R.layout.dialog);
		
		
		dialog.show();
	}
	
	public void selectContacts(View view){
		Toast.makeText(view.getContext(), "Inside selectContacts",Toast.LENGTH_LONG ).show();
		//Intent intent= new Intent(Intent.ACTION_PICK,  Contacts.CONTENT_URI);

       // startActivityForResult(intent, PICK_CONTACT);
	}
	
	private class PickDate implements DatePickerDialog.OnDateSetListener {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			// TODO Auto-generated method stub
		//	view.updateDate(year, monthOfYear, dayOfMonth);
	        txtView.setText(monthOfYear+1+"/"+dayOfMonth+"/"+year);
	        dialog.hide();
		}
}
	}
