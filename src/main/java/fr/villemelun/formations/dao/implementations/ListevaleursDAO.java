/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villemelun.formations.dao.implementations;

import fr.villemelun.formations.dao.interfaces.IListevaleursDAO;
import fr.villemelun.formations.models.Listevaleurs;
import java.util.ArrayList;
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
public class ListevaleursDAO implements IListevaleursDAO {

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
     * Add Listevaleurs
     *
     * @param  Listevaleurs user
     */
    @Override
    public void addListevaleur(Listevaleurs listevaleur) {
        getSessionFactory().getCurrentSession().save(listevaleur);
    }
 
    /**
     * Delete Agents
     *
     * @param  Agents user
     */
    @Override
    public void deleteListevaleurs(Listevaleurs listevaleur) {
        getSessionFactory().getCurrentSession().delete(listevaleur);
    }
 
    /**
     * Update Agents
     *
     * @param  Agents user
     */
    @Override
    public void updateListevaleurs(Listevaleurs listevaleur) {
        getSessionFactory().getCurrentSession().update(listevaleur);
    }
 
    /**
     * Get Agents
     *
     * @param  int Agents Id
     * @return Agents
     */
    @Override
    public Listevaleurs getListevaleursById(int id) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from Listevaleurs where id=?")
                                            .setParameter(0, id).list();
        return (Listevaleurs)list.get(0);
    }
 
    /**
     * Get Agents List
     *
     * @return List - Agents list
     */
    @Override
    public List<Listevaleurs> getListevaleurs() {
        List list = getSessionFactory().getCurrentSession().createQuery("from Listevaleurs").list();
        return list;
    }
    
    /**
     * Permet d'obtenir la liste de valeur par formation
     * @return 
     */
 
    @Override
    public List<Listevaleurs> getListevaleursByTypeFormation(int typeFormation) {
       List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from Listevaleurs where type_formation_id=?")
                                            .setParameter(0, typeFormation).list();
      
        return list;
    }
    
}
