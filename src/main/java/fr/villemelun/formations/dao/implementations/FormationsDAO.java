/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villemelun.formations.dao.implementations;

import fr.villemelun.formations.dao.interfaces.IFormationsDAO;
import fr.villemelun.formations.models.Formations;
import fr.villemelun.formations.models.ReportBean;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
public class FormationsDAO implements IFormationsDAO {
    
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
     * Add Formations
     *
     * @param  Formations user
     */
    @Override
    public void addFormation(Formations formation) {
        getSessionFactory().getCurrentSession().save(formation);
    }
 
    /**
     * Delete Agents
     *
     * @param  Agents user
     */
    @Override
    public void deleteFormations(Formations formation) {
        getSessionFactory().getCurrentSession().delete(formation);
    }
 
    /**
     * Update Agents
     *
     * @param  Agents user
     */
    @Override
    public void updateFormations(Formations formation) {
        getSessionFactory().getCurrentSession().update(formation);
    }
 
    /**
     * Get Agents
     *
     * @param  int Agents Id
     * @return Agents
     */
    @Override
    public Formations getFormationsById(int id) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from Formations where id=?")
                                            .setParameter(0, id).list();
        return (Formations)list.get(0);
    }
 
    /**
     * Get Agents List
     *
     * @return List - Agents list
     */
    @Override
    public List<Formations> getFormations() {
        List list = getSessionFactory().getCurrentSession().createQuery("from Formations").list();
        return list;
    }
    
    @Override
    public Map getAlphaByServices(Short idService) {
        Map resultat = new HashMap();
        
        List list = getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT a.libelle, count(distinct(b.id)) FROM services a, agents b, formations c where a.id=b.serviceId and b.id=c.agentId and typeformationsId = 7 and a.id=? group by a.libelle")
                .setParameter(0, idService).list();
        
        if (list!=null && list.size()>0) {
            Object[] ob = (Object[])list.get(0);
            resultat.put(ob[0], ob[1]);       
        }
        return resultat;
    }
    
    @Override
    public Map getFrancaisByServices(Short idService) {
        Map resultat = new HashMap();
        
        List list = getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT a.libelle, count(distinct(b.id)) FROM services a, agents b, formations c where a.id=b.serviceId and b.id=c.agentId and typeformationsId = 6 and a.id=? group by a.libelle")
                .setParameter(0, idService).list();
        
        if (list!=null && list.size()>0) {
            Object[] ob = (Object[])list.get(0);
            resultat.put(ob[0], ob[1]);
        }
        return resultat;
    }
    
    @Override
    public Map getCnfptByServices(Short idService) {
        Map resultat = new HashMap();
        
        List list = getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT c.intitule, count(distinct(b.id)) FROM services a, agents b, formations c where a.id=b.serviceId and b.id=c.agentId and typeformationsId = 2 and a.id=? group by c.intitule")
                .setParameter(0, idService).list();
        
        for (Iterator it = list.iterator(); it.hasNext();) {
            Object[] object = (Object[])it.next();
            resultat.put(object[0], object[1]);
        }
        return resultat;
    }
    
    @Override
    public Map getVaeDipByServices(Short idService) {
        Map resultat = new HashMap();
       
        List list = getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT c.intitule, count(distinct(b.id)), c.organisme, c.cout FROM services a, agents b, formations c where a.id=b.serviceId and b.id=c.agentId and typeformationsId=12 and a.id=? group by c.intitule")
                .setParameter(0, idService).list();
        
        for (Iterator it = list.iterator(); it.hasNext();) {
            ReportBean report = new ReportBean();
            Object[] object = (Object[])it.next();
            report.setCout(object[3]);
            report.setOrganisme((String)object[2]);
            report.setCompte(object[1]);
            resultat.put(object[0], report);
        }       
         return resultat;
    }
    
    @Override
    public Map getDipByServices(Short idService) {
        Map resultat = new HashMap();
       
        List list = getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT c.intitule, count(distinct(b.id)), c.organisme, c.cout FROM services a, agents b, formations c where a.id=b.serviceId and b.id=c.agentId and typeformationsId=11 and a.id=? group by c.intitule")
                .setParameter(0, idService).list();
        
        for (Iterator it = list.iterator(); it.hasNext();) {
            ReportBean report = new ReportBean();
            Object[] object = (Object[])it.next();
            report.setCout(object[3]);
            report.setOrganisme((String)object[2]);
            report.setCompte(object[1]);
            resultat.put(object[0], report);
        }       
         return resultat;
    }
    
    @Override
    public Map getInfoByServices(Short idService) {
        Map resultat = new HashMap();
        
        List list = getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT c.intitule, count(distinct(b.id)), c.organisme, c.cout FROM services a, agents b, formations c where a.id=b.serviceId and b.id=c.agentId and typeformationsId = 9 and a.id=? group by c.intitule")
                .setParameter(0, idService).list();
        
        for (Iterator it = list.iterator(); it.hasNext();) {
            ReportBean report = new ReportBean();
            Object[] object = (Object[])it.next();
            report.setCout(object[3]);
            report.setOrganisme((String)object[2]);
            report.setCompte(object[1]);
            resultat.put(object[0], report);
        }       
        return resultat;
    }
    
    @Override
    public Map getAutreByServices(Short idService) {
        Map resultat = new HashMap();
        
        List list = getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT c.intitule, count(distinct(b.id)), c.organisme, c.cout FROM services a, agents b, formations c where a.id=b.serviceId and b.id=c.agentId and typeformationsId = 10 and a.id=? group by c.intitule")
                .setParameter(0, idService).list();
        
        for (Iterator it = list.iterator(); it.hasNext();) {
            ReportBean report = new ReportBean();
            Object[] object = (Object[])it.next();
            report.setCout(object[3]);
            report.setOrganisme((String)object[2]);
            report.setCompte(object[1]);
            resultat.put(object[0], report);
        }       
        return resultat;
    }
    
     @Override
    public Map getConcoursByServices(Short idService) {
        Map resultat = new HashMap();
        
        List list = getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT d.libelle, count(distinct(b.id)) FROM services a, agents b, formations c, listevaleurs d where a.id=b.serviceId and b.id=c.agentId and d.id=c.listevaleursId and typeformationsId = 4 and a.id=? group by c.listevaleursId")
                .setParameter(0, idService).list();
        
        for (Iterator it = list.iterator(); it.hasNext();) {
            Object[] object = (Object[])it.next();
            resultat.put(object[0], object[1]);
        }       
        return resultat;
    }
     
    @Override
    public Map getExamensByServices(Short idService) {
        Map resultat = new HashMap();
        
        List list = getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT d.libelle, count(distinct(b.id)) FROM services a, agents b, formations c, listevaleurs d where a.id=b.serviceId and b.id=c.agentId and d.id=c.listevaleursId and typeformationsId = 5 and a.id=? group by c.listevaleursId")
                .setParameter(0, idService).list();
        
        for (Iterator it = list.iterator(); it.hasNext();) {
            Object[] object = (Object[])it.next();
            resultat.put(object[0], object[1]);
        }       
        return resultat;
    }
    
    @Override
    public Map getPrepExamensByServices(Short idService) {
        Map resultat = new HashMap();
        
        List list = getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT d.libelle, count(distinct(b.id)) FROM services a, agents b, formations c, listevaleurs d where a.id=b.serviceId and b.id=c.agentId and d.id=c.listevaleursId and typeformationsId = 17 and a.id=? group by c.listevaleursId")
                .setParameter(0, idService).list();
        
        for (Iterator it = list.iterator(); it.hasNext();) {
            Object[] object = (Object[])it.next();
            resultat.put(object[0], object[1]);
        }       
        return resultat;
    }
    
    @Override
    public Map getPrepConcoursByServices(Short idService) {
        Map resultat = new HashMap();
        
        List list = getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT d.libelle, count(distinct(b.id)) FROM services a, agents b, formations c, listevaleurs d where a.id=b.serviceId and b.id=c.agentId and d.id=c.listevaleursId and typeformationsId = 16 and a.id=? group by c.listevaleursId")
                .setParameter(0, idService).list();
        
        for (Iterator it = list.iterator(); it.hasNext();) {
            Object[] object = (Object[])it.next();
            resultat.put(object[0], object[1]);
        }       
        return resultat;
    }
    
    @Override
    public Map getHygByServices(Short idService) {
        Map resultat = new HashMap();
        
        List list = getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT d.libelle, count(distinct(b.id)) FROM services a, agents b, formations c, listevaleurs d where a.id=b.serviceId and b.id=c.agentId and d.id=c.listevaleursId and typeformationsId = 3 and a.id=? group by c.listevaleursId")
                .setParameter(0, idService).list();
        
        for (Iterator it = list.iterator(); it.hasNext();) {
            Object[] object = (Object[])it.next();
            resultat.put(object[0], object[1]);
        }       
        return resultat;
    }
    
    @Override
    public Map getInfosByServices(Short idService) {
        Map resultat = new HashMap();
        
        List list = getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT d.libelle, count(distinct(b.id)) FROM services a, agents b, formations c, listevaleurs d where a.id=b.serviceId and b.id=c.agentId and d.id=c.listevaleursId and typeformationsId = 14 and a.id=? group by c.listevaleursId")
                .setParameter(0, idService).list();
        
        for (Iterator it = list.iterator(); it.hasNext();) {
            Object[] object = (Object[])it.next();
            resultat.put(object[0], object[1]);
        }       
        return resultat;
    }
    
    @Override
    public Map getPoliceServices(Short idService) {
        Map resultat = new HashMap();
        
        List list = getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT d.libelle, count(distinct(b.id)) FROM services a, agents b, formations c, listevaleurs d where a.id=b.serviceId and b.id=c.agentId and d.id=c.listevaleursId and typeformationsId = 1 and a.id=? group by c.listevaleursId")
                .setParameter(0, idService).list();
        
        for (Iterator it = list.iterator(); it.hasNext();) {
            Object[] object = (Object[])it.next();
            resultat.put(object[0], object[1]);
        }       
        return resultat;
    }
}
