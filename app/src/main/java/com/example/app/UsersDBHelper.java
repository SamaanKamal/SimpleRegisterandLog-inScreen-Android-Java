package com.example.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UsersDBHelper extends SQLiteOpenHelper {
    private static final String databaseName="usersDatabase";
    SQLiteDatabase usersDatabase;
    public UsersDBHelper(@Nullable Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(ID INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT NOT NULL,email TEXT NOT NULL, password TEXT NOT NULL,phone INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS users");
        onCreate(db);
    }


    public void CreateNewUser(String username,String email,String password,String phone){
        ContentValues row1 = new ContentValues();
        usersDatabase=getWritableDatabase();
        usersDatabase.execSQL(" DROP TABLE IF EXISTS users");
        usersDatabase.execSQL("CREATE TABLE users(ID INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT NOT NULL,email TEXT NOT NULL, password TEXT NOT NULL,phone INTEGER)");


        String AdminUserName="Admin";
        String AdminPassword="Admin";
        String AdminEmail="Admin@gmail.com";
        String AdminPhone="0158009";


        row1.put("username",AdminUserName);
        row1.put("email",AdminEmail);
        row1.put("password",AdminPassword);
        row1.put("phone",AdminPhone);
        usersDatabase.insert("users",null,row1);


        ContentValues row = new ContentValues();
        usersDatabase=getWritableDatabase();

        row.put("username",username);
        row.put("email",email);
        row.put("password",password);
        row.put("phone",phone);
        usersDatabase.insert("users",null,row);

        usersDatabase.close();


    }

    public boolean CheckUser(String username,String password){
        usersDatabase = getReadableDatabase();
        String[] columns ={ "ID" };
        String[] Details = { username , password };
        //Cursor cursor = usersDatabase.query("users",columns,"username =?"+ "and" + "password=?",Details,null,null,null);
        Cursor cursor = usersDatabase.rawQuery("select * from users where username=? and password =?",new String[] { username, password});
        int number= cursor.getCount();
        usersDatabase.close();
        if(number>0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
    public boolean CheckResetUser(String email) {
        usersDatabase = getReadableDatabase();
        String[] columns = { "ID" };
        String[] Details = { email };
        Cursor cursor = usersDatabase.query("users", columns, "email =?", Details, null, null, null);
        int number = cursor.getCount();
        usersDatabase.close();
        if (number > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void updatePass(String Email,String Password)
    {
        usersDatabase=getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("email",Email);
        cv.put("password",Password);
        String[] args = { Email };
        usersDatabase.update("users",cv,"email =?",args);
        usersDatabase.close();
    }
}
