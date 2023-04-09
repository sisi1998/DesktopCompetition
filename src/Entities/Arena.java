/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.logging.Logger;

/**
 *
 * @author Siwar
 */
public class Arena {
    private int id;

    public Arena() {
    }
   private String Nom;
    private static final Logger LOG = Logger.getLogger(Arena.class.getName());

    public Arena(String Nom) {
        this.Nom = Nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Arena(int id, String Nom) {
        this.id = id;
        this.Nom = Nom;
    }

    

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    @Override
    public String toString() {
        return   Nom ;
    }
    
}
