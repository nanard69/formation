/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villemelun.formations.dao.implementations;

import fr.villemelun.formations.dao.interfaces.ITypeformationsDAO;
import fr.villemelun.formations.models.Typeformations;
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
public class TypeformationsDAO implements ITypeformationsDAO {
    
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
     * Add Typeformations
     *
     * @param  Typeformations user
     */
    @Override
    public void addTypeformation(Typeformations typeformation) {
        getSessionFactory().getCurrentSession().save(typeformation);
    }
 
    /**
     * Delete Agents
     *
     * @param  Agents user
     */
    @Override
    public void deleteTypeformations(Typeformations typeformation) {
        getSessionFactory().getCurrentSession().delete(typeformation);
    }
 
    /**
     * Update Agents
     *
     * @param  Agents user
     */
    @Override
    public void updateTypeformations(Typeformations typeformation) {
        getSessionFactory().getCurrentSession().update(typeformation);
    }
 
    /**
     * Get Agents
     *
     * @param  int Agents Id
     * @return Agents
     */
    @Override
    public Typeformations getTypeformationsById(int id) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from Typeformations where id=?")
                                            .setParameter(0, id).list();
        return (Typeformations)list.get(0);
    }
 
    /**
     * Get Agents List
     *
     * @return List - Agents list
     */
    @Override
    public List<Typeformations> getTypeformations() {
        List list = getSessionFactory().getCurrentSession().createQuery("from Typeformations").list();
        return list;
    }
}
