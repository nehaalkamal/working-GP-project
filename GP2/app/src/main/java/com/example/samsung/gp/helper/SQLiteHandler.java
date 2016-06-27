package com.example.samsung.gp.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class SQLiteHandler extends SQLiteOpenHelper {

	private static final String TAG = SQLiteHandler.class.getSimpleName();

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 3; //5letha 3

	// Database Name
	private static final String DATABASE_NAME = "android_api";

	// Login table name
	private static final String TABLE_USER = "user";

	// Login Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_Password = "password";
	private static final String KEY_Location= "Location";
	private static final String KEY_Biography = "Biography";
	private static final String KEY_Gender = "Gender";
	private static final String KEY_UserImage = "UserImage";
	private static final String KEY_birthDate = "birthDate";
	private static final String KEY_UID = "uid";
	private static final String KEY_CREATED_AT = "created_at";

	public SQLiteHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
	/*	String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
				+ KEY_EMAIL + " TEXT UNIQUE," + KEY_UID + " TEXT,"+ " TEXT,"+ KEY_Password + " TEXT,"
				+ KEY_Location + " TEXT," + KEY_Biography + " TEXT,"
				+ KEY_Gender + " TEXT," + KEY_UserImage + " TEXT,"
				+ KEY_birthDate + " TEXT," + KEY_CREATED_AT + " TEXT" + ")";*/ //talet satr ghlt
		String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
				+ KEY_EMAIL + " TEXT UNIQUE," + KEY_UID + " TEXT,"+ KEY_Password + " TEXT,"
				+ KEY_Location + " TEXT," + KEY_Biography + " TEXT,"
				+ KEY_Gender + " TEXT," + KEY_UserImage + " TEXT,"
				+ KEY_birthDate + " TEXT," + KEY_CREATED_AT + " TEXT" + ")";
		db.execSQL(CREATE_LOGIN_TABLE);

		Log.d(TAG, "Database tables created");
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

		// Create tables again
		onCreate(db);
	}

	/**
	 * Storing user details in database
	 * */
	public void addUser(String name, String email,String password, String uid, String Location, String Biography, String Gender, String UserImage, String birthDate, String created_at) { // Biography birthDate Gender Location UserImage
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, name); // Name
		values.put(KEY_EMAIL, email); // Email
		values.put(KEY_Password, password);
		values.put(KEY_UID, uid);
		values.put(KEY_Location, Location);
		values.put(KEY_Biography, Biography);
		values.put(KEY_Gender, Gender);
		values.put(KEY_UserImage, UserImage);
		values.put(KEY_birthDate, birthDate);
		values.put(KEY_CREATED_AT, created_at); // Created At

		// Inserting Row
		long id = db.insert(TABLE_USER, null, values);
		db.close(); // Closing database connection

		Log.d(TAG, "New user inserted into sqlite: " + id);
	}

	public void Updateuser(String name,String password, String Location, String birthDate, String Gender, String Biography ,String email) { // Biography birthDate Gender Location UserImage
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, name); // Name
		values.put(KEY_Password, password);
		values.put(KEY_Location, Location);
		values.put(KEY_Biography, Biography);
		values.put(KEY_Gender, Gender);
		values.put(KEY_birthDate, birthDate);
		// Inserting Row
		long id = db.update(TABLE_USER, values, "email=" + "'" + email + "'", null);
		db.close(); // Closing database connection

		Log.d(TAG, "Update data completed: " + id);
	}

	public void UpdatePhoto(String email, String UserImage) { // Biography birthDate Gender Location UserImage
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_UserImage, UserImage);
		// Inserting Row
		long id = db.update(TABLE_USER, values, "email=" + "'" + email + "'", null);
		db.close(); // Closing database connection

		Log.d(TAG, "profile photo changed " + id);
	}

	/**
	 * Getting user data from database
	 * */
	public HashMap<String, String> getUserDetails() {
		HashMap<String, String> user = new HashMap<String, String>();
		String selectQuery = "SELECT  * FROM " + TABLE_USER;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Move to first row
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			user.put("name", cursor.getString(1));
			user.put("email", cursor.getString(2));
			user.put("password", cursor.getString(4));
			user.put("uid", cursor.getString(3));
			user.put("Location", cursor.getString(5));
			user.put("Biography", cursor.getString(6));
			user.put("Gender", cursor.getString(7));
			user.put("UserImage", cursor.getString(8));
			user.put("birthDate", cursor.getString(9));
			user.put("created_at", cursor.getString(10));
		}
		cursor.close();
		db.close();
		// return user
		Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

		return user;
	}
	/**
	 * Re crate database Delete all tables and create them again
	 * */
	public void deleteUsers() {
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete(TABLE_USER, null, null);
		db.close();

		Log.d(TAG, "Deleted all user info from sqlite");
	}

}
