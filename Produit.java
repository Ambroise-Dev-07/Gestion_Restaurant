package org.example.Model;

public class Produit {
    private int code_produit;
    private String libelle_produit;
    private double prix_vente;
    private int stock_actuel;
    private int seuil_alerte;
    private Categorie categorie;

    public Produit(){

    }

    public Produit(int code_produit,String libelle_produit,
                   double prix_vente,int stock_actuel,int seuil_alerte,Categorie categorie){
        this.code_produit = code_produit;
        this.libelle_produit = libelle_produit;
        this.prix_vente = prix_vente;
        this.stock_actuel = stock_actuel;
        this.seuil_alerte = seuil_alerte;
        this.categorie = categorie;

    }

    public int getCode_produit() {
        return code_produit;
    }

    public void setCode_produit(int code_produit) {

        this.code_produit = code_produit;
    }

    public String getLibelle_produit() {
        return libelle_produit;
    }

    public void setLibelle_produit(String libelle_produit) {

        this.libelle_produit = libelle_produit;
    }

    public double getPrix_vente() {
        return prix_vente;
    }

    public void setPrix_vente(double prix_vente) {
        if(prix_vente > 0){
            this.prix_vente = prix_vente;
        }else {
            throw new IllegalArgumentException("Le prix doit être positif");
        }


    }

    public int getStock_actuel() {
        return stock_actuel;
    }

    public void setStock_actuel(int stock_actuel) {
        if (stock_actuel >= 0){
            this.stock_actuel = stock_actuel;
        }else{
            throw new IllegalArgumentException("Le stock doit être positif");
        }
    }

    public int getSeuil_alerte() {
        return seuil_alerte;
    }

    public void setSeuil_alerte(int seuil_alerte) {

            this.seuil_alerte = seuil_alerte;
    }

    public int getCode_categorie() {
        return code_categorie;
    }

    public void setCategorie(Categorie categorie) {

        this.categorie = categorie;
    }

    //Méthode de la class Produit

    //Méthode Ajouter un Produit

    public void ajouterProduit(int code,String libelle,double prix,Categorie cat,int stock){
        this.code_produit = code;
        this.libelle_produit = libelle;
        if (prix > 0){
            this.prix_vente = prix;
        }
        this.categorie = cat;
        if (stock > 0){
            this.stock_actuel = stock;
        } else {
            throw new IllegalArgumentException("LE stock doit être possitif");
        }
    }

    // Modifier Produit
    public void modifierProduit(){

    }
    //Supprimer produit

    public void supprimerProduit(){

    }



}
