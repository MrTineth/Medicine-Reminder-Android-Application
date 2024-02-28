package com.example.medmanager.mydatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

public class MedicalDB extends SQLiteOpenHelper {
    public static MedicalDB sInstance;
    //Constructs a new instance of the MedicalDB class.
    public static synchronized MedicalDB getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MedicalDB(context.getApplicationContext());
        }
        return sInstance;
    }
    
    public static final int version = 1;
    public MedicalDB(Context context) {
        super(context,"database",null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create the user table
        String create_user_table = "CREATE TABLE USER (" +
                                        "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                        "USER_NAME TEXT NOT NULL);";
        // Create the medicine table
        String create_med_table = "CREATE TABLE MEDICINE (" +
                                        "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        "MED_NAME TEXT NOT NULL," +
                                        "QTY INTEGER NOT NULL," +
                                        "DATE_TIME TEXT NOT NULL," +
                                        "DAYS TEXT NOT NULL," +
                                        "USER_ID INT NOT NULL," +
                                        "ENABLE INT NOT NULL,"+
                                        "FOREIGN KEY(USER_ID) REFERENCES USER(_id)" +
                                        "ON DELETE CASCADE);";
        db.execSQL(create_user_table);
        db.execSQL(create_med_table);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    //    USER CRUD OPERATIONS:
    //Adds a new user to the database.
    public void addUser(SQLiteDatabase db, @NonNull String name)
    {
        ContentValues values = new ContentValues();
        values.put("USER_NAME",name);
        db.insert("USER",null,values);
    }
    //Deletes a user from the database.
    public void deleteUser(SQLiteDatabase db, @NonNull String user_id){
        db.delete("USER","_id=?",new String[]{user_id});
    }
    //Updates a user's information in the database.

    public void updateUser(SQLiteDatabase db, @NonNull int user_id, @NonNull String name){
        ContentValues values = new ContentValues();
        values.put("USER_NAME",name);
        db.update("USER",values,"_id=?",new String[]{""+user_id});
    }

    public String getUserName(SQLiteDatabase db, @NonNull int user_id){
        Cursor user = db.rawQuery("SELECT * FROM USER WHERE _id="+user_id+";",new String[]{});
        user.moveToFirst();
        return user.getString(1);
    }

//    MEDICINE CRUD OPERATION:
//Add Medicine for Database
    public void addMedicine(SQLiteDatabase db, @NonNull int user_id, @NonNull String med_name, @NonNull int quantity, @NonNull String date_time, @NonNull String days){
        ContentValues values = new ContentValues();
        values.put("USER_ID",user_id);
        values.put("MED_NAME",med_name);
        values.put("QTY",quantity);
        values.put("DATE_TIME",date_time);
        values.put("DAYS",days);
        values.put("ENABLE",false);
        db.insert("MEDICINE",null,values);
    }

    public void deleteMedicine(SQLiteDatabase db, @NonNull int med_id){
        db.delete("MEDICINE","_id=?",new String[]{""+med_id});
    }
    //Update Medicine for Database
    public void updateMedicine(SQLiteDatabase db,@NonNull int med_id, @NonNull String med_name, @NonNull int quantity, @NonNull String date_time, @NonNull String days){
        ContentValues values = new ContentValues();
        values.put("MED_NAME",med_name);
        values.put("QTY",quantity);
        values.put("DATE_TIME",date_time);
        values.put("DAYS",days);
        db.update("MEDICINE",values,"_id=?",new String[]{""+med_id});
    }
    //Sets the enable status of a medicine in the database.
    public void setEnable(SQLiteDatabase db,int id,int b){
        ContentValues values = new ContentValues();
        values.put("ENABLE",b);
        db.update("MEDICINE",values,"_id=?",new String[]{""+id});
    }
    //Get the list of users from the "USER" table.
    public Cursor getUserList(SQLiteDatabase db){
        Cursor cursor = db.rawQuery("SELECT _id, USER_NAME FROM USER",new String[]{});
        return cursor;
    }

    //Retrieves the list of medicines associated with a specific user.
    public Cursor getMedicineListById(SQLiteDatabase db,int user_id){
        Cursor cursor = db.rawQuery("SELECT * FROM MEDICINE WHERE USER_ID="+user_id+";",new String[]{});
        return cursor;
    }

    //Retrieves the details of a specific medicine.
    public Cursor getMedicine(SQLiteDatabase db, int med_id){
        Cursor cursor = db.rawQuery("SELECT * FROM MEDICINE WHERE _id="+ med_id+";",new String[]{});
        return cursor;
    }

}


