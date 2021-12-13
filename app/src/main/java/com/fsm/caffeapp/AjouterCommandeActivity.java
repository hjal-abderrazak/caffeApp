package com.fsm.caffeapp;

import android.content.Intent;
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
    CustomAdapter customAdapter1;
    ArrayList<Article> cmdList,arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        commandeLayout =getLayoutInflater().inflate(R.layout.activity_ajouter_commande,null);
        MenuLayout = getLayoutInflater().inflate(R.layout.activity_ajouter_commande_menu,null);
        menu_btn = commandeLayout.findViewById(R.id.btnAfficherMenu);
        commande_btn = MenuLayout.findViewById(R.id.btnAfficherCommande);
        setContentView(commandeLayout);
        listArticle =MenuLayout.findViewById(R.id.lvArticlesMenu);
        listCommande =commandeLayout.findViewById(R.id.lvArticlesCommande);
        arrayList = new ArrayList<Article>();
         cmdList = new ArrayList<Article>();
         cmdList.add(new Article("hhhh","hhh",20));
        arrayList.add(new Article("cafe",Utils.CATEGORIE_CAFE,4  ,R.drawable.cafe       ));
        arrayList.add(new Article("jus d'orange",Utils.CATEGORIE_JUS, 6  ,R.drawable.jus   ));
        arrayList.add(new Article("the vert",Utils.CATEGORIE_THE,2   ,R.drawable.the  ));
        arrayList.add(new Article("eau sabrine",Utils.CATEGORIE_EAU,3   ,R.drawable.eau  ));
        arrayList.add(new Article("Fanta",Utils.CATEGORIE_SODA,7  ,R.drawable.soda   ));
        CustomAdapter customAdapter = new CustomAdapter(this, arrayList);
         customAdapter1 = new CustomAdapter(this, cmdList);
        listArticle.setAdapter(customAdapter);
        listCommande.setAdapter(customAdapter1);
        listArticle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),AjoutArticleActivity.class);
                intent.putExtra("tarif",arrayList.get(i).getTarif());
                intent.putExtra("categorie",arrayList.get(i).getCategorie());
                intent.putExtra("description",arrayList.get(i).getDescription());
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
                setContentView(commandeLayout);
                Intent intent = getIntent();
                int tarif =data.getIntExtra("tarif",0);
                String description = data.getStringExtra("description");
                String categorie = data.getStringExtra("categorie");

            if (categorie.equals(com.fsm.caffeapp.Utils.CATEGORIE_CAFE)) {
                cmdList.add(new Article(description,categorie,tarif,R.drawable.cafe));

            }
            else if (categorie.equals(com.fsm.caffeapp.Utils.CATEGORIE_THE)) {
                cmdList.add(new Article(description,categorie,tarif,R.drawable.the));
            }
            else if (categorie.equals(com.fsm.caffeapp.Utils.CATEGORIE_JUS)) {
                cmdList.add(new Article(description,categorie,tarif,R.drawable.jus));
            }
            else if (categorie.equals(com.fsm.caffeapp.Utils.CATEGORIE_SODA)) {
                cmdList.add(new Article(description,categorie,tarif,R.drawable.soda));
            }
            else {
                cmdList.add(new Article(description,categorie,tarif,R.drawable.eau));
            }




                synchronized(customAdapter1){
                    customAdapter1.notify();
                }







        }
    }

    private void methodeLongue() {
        try {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        customAdapter1.notifyAll();
                    }
                });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setLayout(){

    }
}
