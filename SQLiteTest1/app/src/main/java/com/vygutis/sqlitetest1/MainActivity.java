package com.vygutis.sqlitetest1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase sqLiteDatabase = getBaseContext().openOrCreateDatabase("sqlite-test-1.db", MODE_PRIVATE, null);
//        sqLiteDatabase.execSQL("DROP TABLE contacts");
//        sqLiteDatabase.execSQL("CREATE TABLE contacts(name TEXT, phone INTEGER, email TEXT);");
//        sqLiteDatabase.execSQL("INSERT INTO contacts VALUES('Vygis', 12323, 'vygis@mail.me');");
//        sqLiteDatabase.execSQL("INSERT INTO contacts VALUES('Lina', 45678, 'lina@email.me');");

        Cursor query = sqLiteDatabase.rawQuery("SELECT * from contacts", null);

        if (query.moveToFirst()) {
            do {
                // Cycle through all records
                String name = query.getString(0);
                int phone = query.getInt(1);
                String email = query.getString(2);
                Toast.makeText(getBaseContext(), "Name = " + name + " phone = " + phone + " email = " + email, Toast.LENGTH_LONG).show();
            } while (query.moveToNext());
        } else {
            Toast.makeText(getBaseContext(), "Error retrieving data", Toast.LENGTH_LONG).show();
        }

        query.close();
        sqLiteDatabase.close();
    }
}
