/*package com.codersarecreators.reminder;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.EditText;

class PickDate implements DatePickerDialog.OnDateSetListener {
	EditText txtView ;;
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			// TODO Auto-generated method stub
		//	view.updateDate(year, monthOfYear, dayOfMonth);
			txtView = (EditText) getbasecontext().findViewById(R.id.textView1);
	        txtView.setText(monthOfYear+1+"/"+dayOfMonth+"/"+year);
	        dialog.hide();
		}*/