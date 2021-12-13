package com.fsm.caffeapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AfficherCommandeActivity  extends AppCompatActivity {
TextView detail_txt;
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_commande);
        detail_txt = findViewById(R.id.tvAfficherCommande);
        Intent intent = getIntent();
        int id =intent.getIntExtra("id",0);
        detail_txt.setText( getCmdDetails(id));

    }
    @SuppressLint("WrongConstant")
    public String getCmdDetails(int id){
       SQLiteDatabase sqlDb = openOrCreateDatabase("mydatabase.db", SQLiteDatabase.OPEN_READWRITE, null);

        Cursor resultSet = sqlDb.rawQuery("SELECT * FROM commandes where id="+id, null);
        if (resultSet.moveToFirst()) {
            //lire une ligne et verifier s’il y en a d’autres
                String desc = resultSet.getString(1);
                return desc;


        }else {
            Log.e("error not found", "user can't be found or database empty");
            return "pas de detail pour cette commande";
        }
    }
}
