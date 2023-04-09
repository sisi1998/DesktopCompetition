/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Siwar
 */
public interface Iservice <T,C>{
     void Add(T c, List<C> equipes);
      void Update(T c, List<C> equipes, int id);
      void Delete(int id);
      public List<T>affichage();
      public List <T>searchCompetition(T c) ;
      public boolean exists(String Nom);
}
