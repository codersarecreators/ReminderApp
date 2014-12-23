package com.codersarecreators.reminder;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

public class CustomListAdapterDeleteReminderDialog extends BaseAdapter {

	//private ArrayList<ReminderObject> listReminder;
	private ArrayList<String> listReminderNotes = null;
	private LayoutInflater layoutInflater;

	public CustomListAdapterDeleteReminderDialog(Context context,
			ArrayList<String> listString) {
		/*if(listReminderNotes == null)
			listReminderNotes = new ArrayList<String>();
		if(listReminderNotes.size() > 0)
		{
			listReminderNotes.clear();
		}
		else
		{
			will actually never come here!
		}
		for(int i=0 ;i<listReminder.size();i++)
		{
			listReminderNotes.add(listReminder.get(i).getTime() + ": " + listReminder.get(i).getNote());
		}*/
		listReminderNotes = listString;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return listReminderNotes.size();
	}

	@Override
	public Object getItem(int position) {
		return listReminderNotes.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder;
		if (view == null) {
			view = layoutInflater.inflate(R.layout.delete_reminder_list_item,
					null);
			holder = new ViewHolder();

			holder.checkboxView = (CheckBox) view
					.findViewById(R.id.delete_reminder_checkbox);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.checkboxView.setText(listReminderNotes.get(position).toString());

		return view;
	}

	static class ViewHolder {
		CheckBox checkboxView;
	}

}
