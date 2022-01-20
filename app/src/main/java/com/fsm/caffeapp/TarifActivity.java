package com.fsm.caffeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TarifActivity extends AppCompatActivity {
    EditText tarifCafe,tarifCappucin,tarifJus,tarifLimonade,tarifThe,tarifCocacola,tarifFanta,tarifEauPetite,tarifEauGrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tarif);
        tarifCafe =findViewById(R.id.tarifCafe);
        tarifCappucin =findViewById(R.id.tarifCappucin);
        tarifThe =findViewById(R.id.tarifThe);
        tarifJus =findViewById(R.id.tarifJusOrange);
        tarifLimonade =findViewById(R.id.tarifLimonade);
        tarifCocacola =findViewById(R.id.tarifCocacola);
        tarifFanta =findViewById(R.id.tarifFanta);
        tarifEauPetite =findViewById(R.id.tarifEau0_5);
        tarifEauGrand =findViewById(R.id.tarifEau1_5);
         readTarif();


    }



    public int getTarif(String nom){
        @SuppressLint("WrongConstant") SQLiteDatabase sqlDb = openOrCreateDatabase("mydatabase.db",
                SQLiteDatabase.OPEN_READWRITE, null);

        Cursor resultSet = sqlDb.rawQuery("Select tarif from articles where description like '"+nom+"'",null);
        resultSet.moveToFirst();
        int tarif = resultSet.getInt(0);
        return tarif;
    }

    public void changeTarif(String desc,int tarif){
        @SuppressLint("WrongConstant") SQLiteDatabase sqlDb = openOrCreateDatabase("mydatabase.db",
                SQLiteDatabase.OPEN_READWRITE, null);
        ContentValues newValues = new ContentValues();
        newValues.put("tarif", tarif);
        sqlDb.update("articles", newValues, "description like '"+desc+"'", null);

    }



    public void readTarif(){
        tarifCafe.setText(getTarif("café")+"");
        tarifFanta.setText(getTarif("fanta")+"");
        tarifCocacola.setText(getTarif("coca-cola")+"");
        tarifThe.setText(getTarif("thé")+"");
        tarifJus.setText(getTarif("jus%")+"");
        tarifLimonade.setText(getTarif("limonade")+"");
        tarifEauPetite.setText(getTarif("eau 1/2 litre")+"");
        tarifEauGrand.setText(getTarif("eau 1.5 litre")+"");
        tarifCappucin.setText(getTarif("cappucin")+"");

    }

    public void saveChange(View view) {
        changeTarif("café",Integer.parseInt(tarifCafe.getText().toString()));
        changeTarif("thé",Integer.parseInt(tarifThe.getText().toString()));
        changeTarif("cappucin",Integer.parseInt(tarifCappucin.getText().toString()));
        changeTarif("limonade",Integer.parseInt(tarifLimonade.getText().toString()));
        changeTarif("jus%",Integer.parseInt(tarifJus.getText().toString()));
        changeTarif("fanta",Integer.parseInt(tarifFanta.getText().toString()));
        changeTarif("coca-cola",Integer.parseInt(tarifCocacola.getText().toString()));
        changeTarif("eau 1/2 litre",Integer.parseInt(tarifEauPetite.getText().toString()));
        changeTarif("eau 1.5 litre",Integer.parseInt(tarifEauGrand.getText().toString()));
        Intent i= new Intent(TarifActivity.this,MainActivity.class);
        startActivity(i);
    }
}
