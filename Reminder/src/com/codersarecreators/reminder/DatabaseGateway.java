package com.codersarecreators.reminder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseGateway extends SQLiteOpenHelper {
	
	private static Context contextObj = MainActivity.GetContext();
	private static String DB_NAME = "ReminderAppDb";
	private static int DB_VERSION = 1;
	private static String TABLE_REMINDER = "TABLE_REMINDER";
	private static String TABLE_MESSAGESERVICE = "TABLE_SMS";
	//the singleton object
	private static DatabaseGateway dbGateWayObj = null;
	private static SQLiteDatabase dbObj = null;
	
	//constructor
	private DatabaseGateway()
	{
		super(contextObj,DB_NAME,null,DB_VERSION);
	}

	@Override
	public void onConfigure(SQLiteDatabase db)
	{
		super.onConfigure(db);
		//set the foreign key constraint to true
		db.setForeignKeyConstraintsEnabled(true);
	}
	
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

		/*
		 * Refer to the tables' design and create the tables accordingly.
		 */
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	public static DatabaseGateway GetDbGateWay()
	{
		if(null == dbGateWayObj)
		{
			dbGateWayObj = new DatabaseGateway();
		}
		if(null == dbObj)
		{
			dbObj = dbGateWayObj.getWritableDatabase();
		}
		return dbGateWayObj;
	}

	private void onInsert()
	{
		/*
		 * This method will basically take parameter of the type Reminder.
		 * Surround this method with try catch blocks and raise appropriate toast if the reminder is added or deleted.
		 * this method will be called from the business logic, with Reminder Obj as the parameter.
		*/
	}
}
