package com.fsm.caffeapp;

import android.graphics.drawable.Drawable;

class Article {
    String description;
    String categorie;
    int tarif;
    int Image;
    public Article(String description, String categorie, int  tarif, int image) {
        this.description = description;
        this.tarif = tarif;
        this.Image=image;
        this.categorie=categorie;
    }
    public Article(String description, int  tarif) {
        this.description = description;
        this.tarif = tarif;

    }
    public Article(String description, String categorie, int  tarif) {
        this.description = description;
        this.tarif = tarif;
        this.categorie=categorie;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTarif() {
        return tarif;
    }

    public void setTarif(int tarif) {
        this.tarif = tarif;
    }

public int getImage(){
        return  this.Image;
}

    public void setImage(int image) {
        Image = image;
    }
}