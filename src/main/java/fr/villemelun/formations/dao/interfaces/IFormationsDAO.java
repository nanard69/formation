/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villemelun.formations.dao.interfaces;

import fr.villemelun.formations.models.Formations;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nanard
 */
public interface IFormationsDAO {
 /**
     * Add Formations
     *
     * @param  Formations user
     */
    public void addFormation(Formations formation);
 
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
     */
    public List<Formations> getFormations();
    
    public Map getAlphaByServices(Short idService);
    
    public Map getFrancaisByServices(Short idService);
    
    public Map getCnfptByServices(Short idService);
    
    public Map getVaeDipByServices(Short idService);
    
    public Map getDipByServices(Short idService);
    
    public Map getInfoByServices(Short idService);
    
    public Map getAutreByServices(Short idService);
    
    public Map getConcoursByServices(Short idService);
    
    public Map getExamensByServices(Short idService);
    
    public Map getPrepExamensByServices(Short idService);
    
    public Map getPrepConcoursByServices(Short idService);
    
    public Map getHygByServices(Short idService);
    
    public Map getInfosByServices(Short idService);
    
    public Map getPoliceServices(Short idService);
}
