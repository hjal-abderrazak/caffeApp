package com.fsm.caffeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase sqlDb;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqlDb = openOrCreateDatabase("mydatabase.db", SQLiteDatabase.OPEN_READWRITE, null);
        sqlDb.execSQL("CREATE TABLE IF NOT EXISTS article (id INTEGER "
                + " PRIMARY KEY AUTOINCREMENT, categorie VARCHAR, description VARCHAR"
                + ", tarif VARCHAR);");
        sqlDb.execSQL("CREATE TABLE IF NOT EXISTS commandes (id INTEGER "
                + " PRIMARY KEY AUTOINCREMENT, description VARCHAR);");


    }

    public void nvCommandes(View view) {
        Intent intent = new Intent(getApplicationContext(),AjouterCommandeActivity.class);
        startActivity(intent);
    }
}