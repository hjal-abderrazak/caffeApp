package com.fsm.caffeapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AjouterCommandeActivity extends AppCompatActivity {
    Button menu_btn,commande_btn;
    ListView listArticle,listCommande;
    View commandeLayout,MenuLayout;
    int a;
    Article a0;
    SQLiteDatabase sqlDb;
    CustomAdapter commandeAdapter, articleAdapter;
    ArrayList<Article> arrayListCommandes, arrayListArticle;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sqlDb = openOrCreateDatabase("mydatabase.db", SQLiteDatabase.OPEN_READWRITE, null);
        a=0;
        commandeLayout =getLayoutInflater().inflate(R.layout.activity_ajouter_commande,null);
        MenuLayout = getLayoutInflater().inflate(R.layout.activity_ajouter_commande_menu,null);
        menu_btn = commandeLayout.findViewById(R.id.btnAfficherMenu);
        commande_btn = MenuLayout.findViewById(R.id.btnAfficherCommande);
        setContentView(commandeLayout);
        listArticle =MenuLayout.findViewById(R.id.lvArticlesMenu);
        listCommande =commandeLayout.findViewById(R.id.lvArticlesCommande);
        arrayListArticle = new ArrayList<Article>();
         arrayListCommandes = new ArrayList<Article>();
         readData();
         articleAdapter = new CustomAdapter(this, arrayListArticle);
         commandeAdapter = new CustomAdapter(this, arrayListCommandes);
         listArticle.setAdapter(articleAdapter);




        listArticle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),AjoutArticleActivity.class);
                intent.putExtra("tarif", arrayListArticle.get(i).getTarif());
                intent.putExtra("categorie", arrayListArticle.get(i).getCategorie());
                intent.putExtra("description", arrayListArticle.get(i).getDescription());
                startActivityForResult(intent,0);
            }
        });


        commande_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(commandeLayout);

            }
        });
        menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(MenuLayout);

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0){
            if (resultCode==RESULT_OK){
                setContentView(commandeLayout);




                int tarif =data.getIntExtra("tarif",0);
                String description = data.getStringExtra("description");
                String categorie = data.getStringExtra("categorie");

            if (categorie.equals(com.fsm.caffeapp.Utils.CATEGORIE_CAFE)) {
                arrayListCommandes.add(new Article(description,categorie,tarif,R.drawable.cafe));

            }
            else if (categorie.equals(com.fsm.caffeapp.Utils.CATEGORIE_THE)) {
                arrayListCommandes.add(new Article(description,categorie,tarif,R.drawable.the));
            }
            else if (categorie.equals(com.fsm.caffeapp.Utils.CATEGORIE_JUS)) {
                arrayListCommandes.add(new Article(description,categorie,tarif,R.drawable.jus));
            }
            else if (categorie.equals(com.fsm.caffeapp.Utils.CATEGORIE_SODA)) {

                arrayListCommandes.add(new Article(description,categorie,tarif,R.drawable.soda));
            }
            else {
                arrayListCommandes.add(new Article(description,categorie,tarif,R.drawable.eau));
            }




                synchronized(commandeAdapter){

                    commandeAdapter.notify();

                }
                listCommande.setAdapter(commandeAdapter);
            }
            else {

            }

        }
    }

    public void readData(){
        arrayListArticle.clear();
        Cursor resultSet = sqlDb.rawQuery("SELECT * FROM articles", null);
        if (resultSet.moveToFirst()) {
            do {
                String id = resultSet.getString(0);
                String categorie = resultSet.getString(1);
                String description = resultSet.getString(2);
                int tarif = Integer.parseInt(resultSet.getString(3));

                if (categorie.equals(com.fsm.caffeapp.Utils.CATEGORIE_CAFE)) {
                    arrayListArticle.add(new Article(description,categorie,tarif,R.drawable.cafe));

                }
                else if (categorie.equals(com.fsm.caffeapp.Utils.CATEGORIE_THE)) {
                    arrayListArticle.add(new Article(description,categorie,tarif,R.drawable.the));
                }
                else if (categorie.equals(com.fsm.caffeapp.Utils.CATEGORIE_JUS)) {
                    arrayListArticle.add(new Article(description,categorie,tarif,R.drawable.jus));
                }
                else if (categorie.equals(com.fsm.caffeapp.Utils.CATEGORIE_SODA)) {

                    arrayListArticle.add(new Article(description,categorie,tarif,R.drawable.soda));
                }
                else {
                    arrayListArticle.add(new Article(description,categorie,tarif,R.drawable.eau));
                }




                System.out.println(categorie +" "+description+" "+tarif);
            } while (resultSet.moveToNext());
        }

        resultSet.close();

    }

    public void ajoutCmd(View view) {
        String desc = "";
        int monatantTotal = 0;
        for (int i = 0; i < arrayListCommandes.size(); i++) {
            desc = desc + arrayListCommandes.get(i).description + " \n";
            monatantTotal += arrayListCommandes.get(i).tarif;

        }
        desc = desc + "\n ----------- \n montant : " + monatantTotal;
        ContentValues values = new ContentValues();
        values.put("description", desc);
        long id = sqlDb.insert("commandes", null, values);
        if (id == -1) {
            Toast.makeText(this, "Erreur d'insertion dans la base", Toast.LENGTH_SHORT).show();

        }
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        setResult(RESULT_OK, intent);
        finish();
    }



    public void annulerCommandes(View view) {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}
