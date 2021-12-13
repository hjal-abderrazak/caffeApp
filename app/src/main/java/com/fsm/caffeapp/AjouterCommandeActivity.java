package com.fsm.caffeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AjouterCommandeActivity extends AppCompatActivity {
    Button menu_btn,commande_btn;
    ListView listArticle,listCommande;
    View commandeLayout,MenuLayout;
    int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        a=0;
        commandeLayout =getLayoutInflater().inflate(R.layout.activity_ajouter_commande,null);
        MenuLayout = getLayoutInflater().inflate(R.layout.activity_ajouter_commande_menu,null);
        menu_btn = commandeLayout.findViewById(R.id.btnAfficherMenu);
        commande_btn = MenuLayout.findViewById(R.id.btnAfficherCommande);
        setContentView(commandeLayout);
        listArticle =MenuLayout.findViewById(R.id.lvArticlesMenu);
        listCommande =commandeLayout.findViewById(R.id.lvArticlesCommande);
        ArrayList<Article> arrayList = new ArrayList<Article>();
        arrayList.add(new Article("cafe",Utils.CATEGORIE_CAFE,4  ,R.drawable.cafe       ));
        arrayList.add(new Article("jus d'orange",Utils.CATEGORIE_JUS, 6  ,R.drawable.jus   ));
        arrayList.add(new Article("the vert",Utils.CATEGORIE_THE,2   ,R.drawable.the  ));
        arrayList.add(new Article("eau sabrine",Utils.CATEGORIE_EAU,3   ,R.drawable.eau  ));
        arrayList.add(new Article("Fanta",Utils.CATEGORIE_SODA,7  ,R.drawable.soda   ));
        CustomAdapter customAdapter = new CustomAdapter(this, arrayList);
        listArticle.setAdapter(customAdapter);

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
            if (requestCode==RESULT_OK){



            }
        }
    }
    public void setLayout(){

    }
}
