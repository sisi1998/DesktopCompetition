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
public class Equipe {
    private int id;
    private String Nom;
     private List<Competition> competitions = new ArrayList<Competition>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }

    public Equipe(int id, String Nom) {
        this.id = id;
        this.Nom = Nom;
    }

    public Equipe(String Nom) {
        this.Nom = Nom;
    }

    public Equipe(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return Nom;
    }
     
     public Equipe() {
        
    }
    
}
