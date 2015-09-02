/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villemelun.formations.dao.interfaces;

import fr.villemelun.formations.models.Services;
import java.util.List;

/**
 *
 * @author nanard
 */
public interface IServicesDAO {
     /**
     * Add Agents
     *
     * @param  Agents user
     */
    public void addService(Services service);
 
    /**
     * Update Services
     *
     * @param  Services user
     */
    public void updateServices(Services service);
 
    /**
     * Delete Services
     *
     * @param  Services user
     */
    public void deleteServices(Services service);
 
    /**
     * Get Services
     *
     * @param  int Services Id
     */
    public Services getServicesById(int id);
 
    /**
     * Get Services List
     *
     */
    public List<Services> getServices();

    /**
     * Pour obtenir le service d'un demandeur
     * @param service
     * @return 
     */
    public Services getServicesByDemandeur(Services service);
}
