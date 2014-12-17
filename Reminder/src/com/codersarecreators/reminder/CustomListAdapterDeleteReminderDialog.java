package com.codersarecreators.reminder;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

public class CustomListAdapterDeleteReminderDialog extends BaseAdapter {

	private ArrayList<String> reminderNotes;

	private LayoutInflater layoutInflater;

	public CustomListAdapterDeleteReminderDialog(Context context,
			ArrayList<String> reminderNotes) {
		this.reminderNotes = reminderNotes;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return reminderNotes.size();
	}

	@Override
	public Object getItem(int position) {
		return reminderNotes.get(position);
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
		holder.checkboxView.setText(reminderNotes.get(position).toString());

		return view;
	}

	static class ViewHolder {
		CheckBox checkboxView;
	}

}
