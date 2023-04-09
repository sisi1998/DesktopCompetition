/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Siwar
 */
public class Competition {
 private int id;
 private String date;
 private Arena Idarena;
 private List<Equipe> equipes = new ArrayList<Equipe>();
 private String etat;
 private Equipe Idwinner;
 private String nom;
  private String image;
 private List<PerformanceC> performances = new ArrayList<>();

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public Arena getArena() {
        return Idarena;
    }

    public List<Equipe> getEsuipes() {
        return equipes;
    }

    public String getEtat() {
        return etat;
    }

    public Equipe getEquipe() {
        return Idwinner;
    }

    public String getNom() {
        return nom;
    }

    public List<PerformanceC> getPerformances() {
        return performances;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String Date) {
        this.date = Date;
    }

    public void setArena(Arena arena) {
        this.Idarena = arena;
    }

    public void setEsuipes(List<Equipe> esuipes) {
        this.equipes = esuipes;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setEquipe(Equipe equipe) {
        this.Idwinner = equipe;
    }

    public void setNom(String Nom) {
        this.nom = Nom;
    }

    public Arena getIdarena() {
        return Idarena;
    }

    public void setIdarena(Arena Idarena) {
        this.Idarena = Idarena;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = equipes;
    }

    public Equipe getIdwinner() {
        return Idwinner;
    }

    public void setIdwinner(Equipe Idwinner) {
        this.Idwinner = Idwinner;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    
    public void setPerformances(List<PerformanceC> performances) {
        this.performances = performances;
    }

    public Competition(String date, Arena arena, String etat, Equipe equipe, String nom, String image) {
        
        this.date = date;
        this.Idarena = arena;
        this.etat = etat;
        this.Idwinner = equipe;
        this.nom = nom;
        this.image = image;
    }

    public Competition(int id, String date, Arena Idarena, String etat, Equipe Idwinner, String nom, String image) {
        this.id = id;
        this.date = date;
        this.Idarena = Idarena;
        this.etat = etat;
        this.Idwinner = Idwinner;
        this.nom = nom;
        this.image = image;
    }

    public Competition(String date, Arena arena, String etat,  String nom, String image) {
        this.date = date;
        this.Idarena = arena;
        this.etat = etat;
       // this.Idwinner = equipe;
        this.nom = nom;
        this.image = image;
        this.Idwinner = null;
    }

    public Competition(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return nom;
    }
 
   public Competition() {
       
    }

}
