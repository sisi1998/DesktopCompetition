/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

/**
 *
 * @author Siwar
 */
public interface IserviceP <T> {
 
     void Add(T c);
      void Update(T c);
      void Delete(int id);
      public List<T>affichage();
      public List <T>searchCompetition(T c) ;
}

