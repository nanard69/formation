/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villemelun.formations.services.implementations;

import fr.villemelun.formations.dao.interfaces.IServicesDAO;
import fr.villemelun.formations.models.Services;
import fr.villemelun.formations.services.interfaces.IServicesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nanard
 */
@Service
@Transactional(readOnly = true)
public class ServicesService implements IServicesService {
 
    // AgentsDAO is injected...
    @Autowired
    IServicesDAO servicesDAO;
 
    /**
     * Add Services
     *
     * @param  Services user
     */
    @Transactional(readOnly = false)
    @Override
    public void addServices(Services service) {
        getServicesDAO().addService(service);
    }
 
    /**
     * Delete Services
     *
     * @param  Services user
     */
    @Transactional(readOnly = false)
    @Override
    public void deleteServices(Services service) {
        getServicesDAO().deleteServices(service);
    }
 
    /**
     * Update Services
     *
     * @param  Services user
     */
    @Transactional(readOnly = false)
    @Override
    public void updateServices(Services service) {
        getServicesDAO().updateServices(service);
    }
 
    /**
     * Get Agents
     *
     * @param  int Agents Id
     */
    @Override
    public Services getServicesById(int id) {
        return getServicesDAO().getServicesById(id);
    }
 
    /**
     * Get Agents List
     *
     */
    @Override
    public List<Services> getServices() {
        return getServicesDAO().getServices();
    }
 
    /**
     * Get Agents DAO
     *
     * @return IAgentsDAO - Agents DAO
     */
    public IServicesDAO getServicesDAO() {
        return servicesDAO;
    }
 
    /**
     * Set Agents DAO
     *
     * @param IAgentsDAO - Agents DAO
     */
    public void setServicesDAO(IServicesDAO servicesDAO) {
        this.servicesDAO = servicesDAO;
    }
    
    /**
     * Pour obtenir le service d'un demandeur
     * @param service
     * @return 
     */
    public Services getServicesByDemandeur(Services service) {
        return getServicesDAO().getServicesByDemandeur(service);
    }
    
}
