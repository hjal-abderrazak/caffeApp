package com.fsm.caffeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.core.motion.utils.Utils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AjoutArticleActivity extends AppCompatActivity {
    String categorie, description;
    int nombreUnites, tarifUnitaire, tarifTotal;
    TextView tvNombreUnites, tvTarifTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_article);

        Intent intent = getIntent();
        categorie = intent.getStringExtra("categorie");
        description = intent.getStringExtra("description");
        tarifUnitaire = intent.getIntExtra("tarif", 0);

        ImageView ivCategorieArticle = (ImageView) findViewById(R.id.ivCategorieArticle);
        if (categorie.equals(com.fsm.caffeapp.Utils.CATEGORIE_CAFE)) {
            ivCategorieArticle.setImageResource(R.drawable.cafe);
        }
        else if (categorie.equals(com.fsm.caffeapp.Utils.CATEGORIE_THE)) {
            ivCategorieArticle.setImageResource(R.drawable.the);
        }
        else if (categorie.equals(com.fsm.caffeapp.Utils.CATEGORIE_JUS)) {
            ivCategorieArticle.setImageResource(R.drawable.jus);
        }
        else if (categorie.equals(com.fsm.caffeapp.Utils.CATEGORIE_SODA)) {
            ivCategorieArticle.setImageResource(R.drawable.soda);
        }
        else {
            ivCategorieArticle.setImageResource(R.drawable.eau);
        }

        TextView tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvDescription.setText(description);

        TextView tvTarifUnitaire = (TextView) findViewById(R.id.tvTarifUnitaire);
        tvTarifUnitaire.setText("" + tarifUnitaire);

        nombreUnites = 1;
        tvNombreUnites = (TextView) findViewById(R.id.tvNombreUnites);
        tvNombreUnites.setText("" + nombreUnites);

        Button btnArticleMoins = (Button)findViewById(R.id.btnArticleMoins);
        btnArticleMoins.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (nombreUnites > 1) {
                    nombreUnites -= 1;
                }

                tvNombreUnites.setText("" + nombreUnites);
                tarifTotal = nombreUnites * tarifUnitaire;
                tvTarifTotal.setText("" + tarifTotal);
            }
        });

        Button btnArticlePlus = (Button)findViewById(R.id.btnArticlePlus);
        btnArticlePlus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nombreUnites += 1;

                tvNombreUnites.setText("" + nombreUnites);
                tarifTotal = nombreUnites * tarifUnitaire;
                tvTarifTotal.setText("" + tarifTotal);
            }
        });

        tarifTotal = tarifUnitaire;
        tvTarifTotal = (TextView)findViewById(R.id.tvTarifTotal);
        tvTarifTotal.setText("" + tarifTotal);

        Button btnAjouterArticle = (Button)findViewById(R.id.btnAjouterArticle);
        btnAjouterArticle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ajouterArticle();
            }
        });

        Button btnAnnulerArticle = (Button)findViewById(R.id.btnAnnulerArticle);
        btnAnnulerArticle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                annulerArticle();
            }
        });
    }

    private void ajouterArticle() {
        Intent intent = new Intent();
        intent.putExtra("categorie", categorie);
        intent.putExtra("description", nombreUnites + " x " + description);
        intent.putExtra("tarif", tarifTotal);
        setResult(0, intent);
        finish();
    }

    private void annulerArticle() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}