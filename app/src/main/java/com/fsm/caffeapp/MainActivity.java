package com.fsm.caffeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase sqlDb;
     static  int a =0;
    ListView lvCommande;
    ArrayList<Integer> arrayList;
    Cursor resultSet;
    ArrayAdapter arrayAdapter;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDb();
        lvCommande = findViewById(R.id.lvListeCommandes);
        arrayList = new ArrayList();
        readData();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        lvCommande.setAdapter(arrayAdapter);
        insertAllArticle();

        lvCommande.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),AfficherCommandeActivity.class);
                intent.putExtra("id",i+1);
                startActivity(intent);

            }
        });
    }


    public void nvCommandes(View view) {
        Intent intent = new Intent(getApplicationContext(),AjouterCommandeActivity.class);
        startActivityForResult(intent,1);
    }

    public  void insert(String categorie,String desc,int tarif){
        ContentValues values = new ContentValues();
        values.put("categorie", categorie);
        values.put("description", desc);
        values.put("tarif", tarif);
        long id = sqlDb.insert("article", null, values);
        if (id == -1) {
            Toast.makeText(this, "Erreur d'insertion dans la base"
                    , Toast.LENGTH_SHORT).show();
        }

    }

    public void readData(){
        resultSet = sqlDb.rawQuery("SELECT * FROM commandes", null);

        if (resultSet.moveToFirst()) {
            do {
                int id = Integer.parseInt(resultSet.getString(0));
                arrayList.add( id);

            } while (resultSet.moveToNext());
        }

        resultSet.close();

    }
    @SuppressLint("WrongConstant")
    public  void createDb(){
        sqlDb = openOrCreateDatabase("mydatabase.db", SQLiteDatabase.OPEN_READWRITE, null);
        sqlDb.execSQL("CREATE TABLE IF NOT EXISTS article (id INTEGER "
                + " PRIMARY KEY AUTOINCREMENT, categorie VARCHAR, description VARCHAR"
                + ", tarif INTEGER);");
        sqlDb.execSQL("CREATE TABLE IF NOT EXISTS commandes (id INTEGER "
                + " PRIMARY KEY AUTOINCREMENT, description VARCHAR);");
    }



    public void insertAllArticle(){
        insert("cafe","café",1500);
        insert("cafe","cappucin",2500);
        insert("the","thé",1000);
        insert("jus","jus d'orange",2300);
        insert("jus","limonade",2300);
        insert("soda","coca-cola",1800);
        insert("soda","fanta",1800);
        insert("eau","eau 1/2 litre",1500);
        insert("eau","eau 1.5 litre",2500);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            if (resultCode==RESULT_OK){
            arrayList.clear();
                readData();
                arrayAdapter.notifyDataSetChanged();

            }
        }
    }

}