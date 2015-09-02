/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villemelun.formations.services.interfaces;

import fr.villemelun.formations.models.FormaServ;
import fr.villemelun.formations.models.Formations;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nanard
 */
public interface IFormationsService {
    /**
     * Add Agents
     *
     * @param  Agents user
     */
    public void addFormations(Formations formation);
 
    /**
     * Update Formations
     *
     * @param  Formations user
     */
    public void updateFormations(Formations formation);
 
    /**
     * Delete Formations
     *
     * @param  Formations user
     */
    public void deleteFormations(Formations formation);
 
    /**
     * Get Formations
     *
     * @param  int Formations Id
     */
    public Formations getFormationsById(int id);
 
    /**
     * Get Formations List
     *
     * @return List - Formations list
     */
    public List<Formations> getFormations();
    
    public Map getAlphaByServices(Short idService);
    
    public FormaServ getFormations(Short idService);
}
