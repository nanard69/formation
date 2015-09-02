/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villemelun.formations.services.interfaces;

import fr.villemelun.formations.models.Listevaleurs;
import java.util.List;

/**
 *
 * @author nanard
 */
public interface IListevaleursService {
    /**
     * Add Agents
     *
     * @param  Agents user
     */
    public void addListevaleur(Listevaleurs listevaleur);
 
    /**
     * Update Listevaleurs
     *
     * @param  Listevaleurs user
     */
    public void updateListevaleurs(Listevaleurs listevaleur);
 
    /**
     * Delete Listevaleurs
     *
     * @param  Listevaleurs user
     */
    public void deleteListevaleurs(Listevaleurs listevaleur);
 
    /**
     * Get Listevaleurs
     *
     * @param  int Listevaleurs Id
     */
    public Listevaleurs getListevaleursById(int id);
 
    /**
     * Get Listevaleurs List
     *
     * @return List - Listevaleurs list
     */
    public List<Listevaleurs> getListevaleurs();
    
    /**
     * Permet d'obtenir la liste de valeur par formation
     * @param typeFormation
     * @return 
     */
    public List<Listevaleurs> getListevaleursByTypeFormation(int typeFormation);
}
