/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villemelun.formations.dao.implementations;

import fr.villemelun.formations.dao.interfaces.IServicesDAO;
import fr.villemelun.formations.models.Services;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nanard
 */
@Service
@Transactional
@Repository
public class ServicesDAO implements IServicesDAO {
    
    @Autowired
    private SessionFactory sessionFactory;
 
    /**
     * Get Hibernate Session Factory
     *
     * @return SessionFactory - Hibernate Session Factory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
    /**
     * Set Hibernate Session Factory
     *
     * @param SessionFactory - Hibernate Session Factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
 
    /**
     * Add Services
     *
     * @param  Services user
     */
    @Override
    public void addService(Services service) {
        getSessionFactory().getCurrentSession().save(service);
    }
 
    /**
     * Delete Agents
     *
     * @param  Agents user
     */
    @Override
    public void deleteServices(Services service) {
        getSessionFactory().getCurrentSession().delete(service);
    }
 
    /**
     * Update Agents
     *
     * @param  Agents user
     */
    @Override
    public void updateServices(Services service) {
        getSessionFactory().getCurrentSession().update(service);
    }
 
    /**
     * Get Agents
     *
     * @param  int Agents Id
     * @return Agents
     */
    @Override
    public Services getServicesById(int id) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from Services where id=?")
                                            .setParameter(0, id).list();
        return (Services)list.get(0);
    }
 
    /**
     * Get Agents List
     *
     * @return List - Agents list
     */
    @Override
    public List<Services> getServices() {
        List list = getSessionFactory().getCurrentSession().createQuery("from Services").list();
        return list;
    }
    
    /**
     * Pour obtenir le service d'un demandeur
     * @param service
     * @return 
     */
    @Override
    public Services getServicesByDemandeur(Services service) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from Services where nom=? and prenom=?")
                                            .setParameter(0, service.getNom())
                                            .setParameter(1, service.getPrenom())
                                            .list();
        if(list!=null && list.size()>0) {
            return (Services)list.get(0);
        }
        
        return new Services();
    }
    
}
