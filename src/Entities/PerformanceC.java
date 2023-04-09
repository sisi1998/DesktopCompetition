/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Siwar
 */
public class PerformanceC {
    private int id;
    private User Idjoueur;
     private Competition Idcom;
    private String Apps,Mins,Buts,PointsDecisives,Jaune,Rouge,TpM,Pr,AerienG,HdM,Note;

    public int getId() {
        return id;
    }

   

    public String getApps() {
        return Apps;
    }

    public String getMins() {
        return Mins;
    }

    public String getButs() {
        return Buts;
    }

    public String getPointsDecisives() {
        return PointsDecisives;
    }

    public String getJaune() {
        return Jaune;
    }

    public String getRouge() {
        return Rouge;
    }

    public String getTpM() {
        return TpM;
    }

    public String getPr() {
        return Pr;
    }

    public String getAerienG() {
        return AerienG;
    }

    public String getHdM() {
        return HdM;
    }

    public String getNote() {
        return Note;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public void setApps(String Apps) {
        this.Apps = Apps;
    }

    public void setMins(String Mins) {
        this.Mins = Mins;
    }

    public void setButs(String Buts) {
        this.Buts = Buts;
    }

    public void setPointsDecisives(String PointsDecisives) {
        this.PointsDecisives = PointsDecisives;
    }

    public void setJaune(String Jaune) {
        this.Jaune = Jaune;
    }

    public void setRouge(String Rouge) {
        this.Rouge = Rouge;
    }

    public void setTpM(String TpM) {
        this.TpM = TpM;
    }

    public void setPr(String Pr) {
        this.Pr = Pr;
    }

    public void setAerienG(String AerienG) {
        this.AerienG = AerienG;
    }

    public void setHdM(String HdM) {
        this.HdM = HdM;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public PerformanceC(int id, User joueur, Competition Idcom, String Apps, String Mins, String Buts, String PointsDecisives, String Jaune, String Rouge, String TpM, String Pr, String AerienG, String HdM, String Note) {
        this.id = id;
        this.Idjoueur = Idjoueur;
        this.Idcom = Idcom;
        this.Apps = Apps;
        this.Mins = Mins;
        this.Buts = Buts;
        this.PointsDecisives = PointsDecisives;
        this.Jaune = Jaune;
        this.Rouge = Rouge;
        this.TpM = TpM;
        this.Pr = Pr;
        this.AerienG = AerienG;
        this.HdM = HdM;
        this.Note = Note;
    }

    public User getIdjoueur() {
        return Idjoueur;
    }

    public void setIdjoueur(User Idjoueur) {
        this.Idjoueur = Idjoueur;
    }

    public Competition getIdcom() {
        return Idcom;
    }

    public void setIdcom(Competition Idcom) {
        this.Idcom = Idcom;
    }

    public PerformanceC(int id) {
        this.id = id;
    }

    public PerformanceC(User Idjoueur, Competition Idcom, String Apps, String Mins, String Buts, String PointsDecisives, String Jaune, String Rouge, String TpM, String Pr, String AerienG, String HdM, String Note) {
        this.Idjoueur = Idjoueur;
        this.Idcom = Idcom;
        this.Apps = Apps;
        this.Mins = Mins;
        this.Buts = Buts;
        this.PointsDecisives = PointsDecisives;
        this.Jaune = Jaune;
        this.Rouge = Rouge;
        this.TpM = TpM;
        this.Pr = Pr;
        this.AerienG = AerienG;
        this.HdM = HdM;
        this.Note = Note;
    }

    @Override
    public String toString() {
        return "PerformanceC{" + "id=" + id + ", Idjoueur=" + Idjoueur + ", Idcom=" + Idcom + ", Apps=" + Apps + ", Mins=" + Mins + ", Buts=" + Buts + ", PointsDecisives=" + PointsDecisives + ", Jaune=" + Jaune + ", Rouge=" + Rouge + ", TpM=" + TpM + ", Pr=" + Pr + ", AerienG=" + AerienG + ", HdM=" + HdM + ", Note=" + Note + '}';
    }

   

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PerformanceC other = (PerformanceC) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.Idjoueur != other.Idjoueur) {
            return false;
        }
        if (this.Idcom != other.Idcom) {
            return false;
        }
        return true;
    }

    public PerformanceC() {
    }

    public PerformanceC(User Idjoueur, Competition Idcom) {
        this.Idjoueur = Idjoueur;
        this.Idcom = Idcom;
    }

    
    
    
}
