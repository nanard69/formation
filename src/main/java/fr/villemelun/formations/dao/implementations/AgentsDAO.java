/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villemelun.formations.dao.implementations;

import fr.villemelun.formations.dao.interfaces.IAgentsDAO;
import fr.villemelun.formations.models.Agents;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nanard
 */
@Transactional
@Repository
public class AgentsDAO implements IAgentsDAO {
    
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
     * Add Agents
     *
     * @param  Agents user
     */
    @Override
    public void addAgent(Agents agent) {
        getSessionFactory().getCurrentSession().save(agent);
    }
 
    /**
     * Delete Agents
     *
     * @param  Agents user
     */
    @Override
    public void deleteAgents(Agents agent) {
        getSessionFactory().getCurrentSession().delete(agent);
    }
 
    /**
     * Update Agents
     *
     * @param  Agents user
     */
    @Override
    public void updateAgents(Agents agent) {
        getSessionFactory().getCurrentSession().update(agent);
    }
 
    /**
     * Get Agents
     *
     * @param  int Agents Id
     * @return Agents
     */
    @Override
    public Agents getAgentsById(int id) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from Agents where id=?")
                                            .setParameter(0, id).list();
        return (Agents)list.get(0);
    }
 
    /**
     * Get Agents List
     *
     * @return List - Agents list
     */
    @Override 
    public List<Agents> getAgents() {
        List list = getSessionFactory().getCurrentSession().createQuery("from Agents order by nom").list();
        return list;
    }
    
    /**
     * Retourne la liste des agents d'un service
     * @return 
     */
    public List<Agents> getAgentsByService(int idService) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from Agents where serviceId=? order by nom")
                                            .setParameter(0, idService).list();
        return list;
    }
    
}
