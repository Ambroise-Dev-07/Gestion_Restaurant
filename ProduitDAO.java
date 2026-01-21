package org.example.DAO;

import org.example.Entities.Categorie;
import org.example.Entities.Produit;
import org.example.DAO.DBConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO {

    // Ajouter un produit
    public void Ajouter(Produit p) throws SQLException {
        String sql = "INSERT INTO PRODUIT(libelle_produit, prix_vente, stock_actuel, seuil_alerte, code_categorie) VALUES(?,?,?,?,?)";
        PreparedStatement ps = DBConnexion.getConnection().prepareStatement(sql);
        ps.setString(1, p.getLibelle_produit());
        ps.setDouble(2, p.getPrix_vente());
        ps.setInt(3, p.getStock_actuel());
        ps.setInt(4, p.getSeuil_alerte());
        ps.setInt(5, p.getCategorie().getCode_categorie());
        ps.executeUpdate();
    }

    // Modifier un produit
    public void Modifier(Produit p) throws SQLException {
        String sql = "UPDATE PRODUIT SET libelle_produit=?, prix_vente=?, stock_actuel=?, seuil_alerte=?, code_categorie=? WHERE code_produit=?";
        PreparedStatement ps = DBConnexion.getConnection().prepareStatement(sql);
        ps.setString(1, p.getLibelle_produit());
        ps.setDouble(2, p.getPrix_vente());
        ps.setInt(3, p.getStock_actuel());
        ps.setInt(4, p.getSeuil_alerte());
        ps.setInt(5, p.getCategorie().getCode_categorie());
        ps.setInt(6, p.getCode_produit());
        ps.executeUpdate();
    }

    // Supprimer un produit
    public void Supprimer(int id) throws SQLException {
        String sql = "DELETE FROM PRODUIT WHERE code_produit=?";
        PreparedStatement ps = DBConnexion.getConnection().prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    // Rechercher par id
    public Produit TrouverParId(int id) throws SQLException {
        String sql = """
            SELECT p.*, c.code_categorie, c.libelle_categorie
            FROM PRODUIT p
            JOIN CATEGORIE c ON p.code_categorie = c.code_categorie
            WHERE p.code_produit = ?
        """;
        PreparedStatement ps = DBConnexion.getConnection().prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            Categorie c = new Categorie(
                    rs.getInt("code_categorie"),
                    rs.getString("libelle_categorie")
            );

            Produit p = new Produit();
            p.setCode_produit(rs.getInt("code_produit"));
            p.setLibelle_produit(rs.getString("libelle_produit"));
            p.setPrix_vente(rs.getDouble("prix_vente"));
            p.setStock_actuel(rs.getInt("stock_actuel"));
            p.setSeuil_alerte(rs.getInt("seuil_alerte"));
            p.setCategorie(c);

            return p;
        }
        return null;
    }

    // Lister tous les produits
    public List<Produit> lister() throws SQLException {
        List<Produit> liste = new ArrayList<>();
        String sql = """
            SELECT p.*, c.code_categorie, c.libelle_categorie
            FROM PRODUIT p
            JOIN CATEGORIE c ON p.code_categorie = c.code_categorie
        """;
        Statement st = DBConnexion.getConnection().createStatement();
        ResultSet rs = st.executeQuery(sql);

        while(rs.next()){
            Categorie c = new Categorie(
                    rs.getInt("code_categorie"),
                    rs.getString("libelle_categorie")
            );

            Produit p = new Produit();
            p.setCode_produit(rs.getInt("code_produit"));
            p.setLibelle_produit(rs.getString("libelle_produit"));
            p.setPrix_vente(rs.getDouble("prix_vente"));
            p.setStock_actuel(rs.getInt("stock_actuel"));
            p.setSeuil_alerte(rs.getInt("seuil_alerte"));
            p.setCategorie(c);

            liste.add(p);
        }
        return liste;
    }

    // Lister produits en alerte
    public List<Produit> listerEnAlerte() throws SQLException {
        List<Produit> liste = new ArrayList<>();
        String sql = """
            SELECT p.*, c.code_categorie, c.libelle_categorie
            FROM PRODUIT p
            JOIN CATEGORIE c ON p.code_categorie = c.code_categorie
            WHERE p.stock_actuel <= p.seuil_alerte
        """;
        Statement st = DBConnexion.getConnection().createStatement();
        ResultSet rs = st.executeQuery(sql);

        while(rs.next()){
            Categorie c = new Categorie(
                    rs.getInt("code_categorie"),
                    rs.getString("libelle_categorie")
            );

            Produit p = new Produit();
            p.setCode_produit(rs.getInt("code_produit"));
            p.setLibelle_produit(rs.getString("libelle_produit"));
            p.setPrix_vente(rs.getDouble("prix_vente"));
            p.setStock_actuel(rs.getInt("stock_actuel"));
            p.setSeuil_alerte(rs.getInt("seuil_alerte"));
            p.setCategorie(c);

            liste.add(p);
        }
        return liste;
    }
}
