/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villemelun.formations.services.implementations;

import fr.villemelun.formations.dao.interfaces.IListevaleursDAO;
import fr.villemelun.formations.models.Listevaleurs;
import fr.villemelun.formations.services.interfaces.IListevaleursService;
import java.util.ArrayList;
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
public class ListevaleursService implements IListevaleursService {
 
    // ListevaleurDAO is injected...
    @Autowired
    IListevaleursDAO listevaleursDAO;
 
    /**
     * Add Listevaleur
     *
     * @param  Listevaleur user
     */
    @Transactional(readOnly = false)
    @Override
    public void addListevaleur(Listevaleurs listevaleur) {
        getListevaleursDAO().addListevaleur(listevaleur);
    }
 
    /**
     * Delete Listevaleur
     *
     * @param  Listevaleur user
     */
    @Transactional(readOnly = false)
    @Override
    public void deleteListevaleurs(Listevaleurs listevaleur) {
        getListevaleursDAO().deleteListevaleurs(listevaleur);
    }
 
    /**
     * Update Listevaleur
     *
     * @param  Listevaleur user
     */
    @Transactional(readOnly = false)
    @Override
    public void updateListevaleurs(Listevaleurs listevaleur) {
        getListevaleursDAO().updateListevaleurs(listevaleur);
    }
 
    /**
     * Get Agents
     *
     * @param  int Agents Id
     */
    @Override
    public Listevaleurs getListevaleursById(int id) {
        return getListevaleursDAO().getListevaleursById(id);
    }
 
    /**
     * Get Agents List
     *
     */
    @Override
    public List<Listevaleurs> getListevaleurs() {
        return getListevaleursDAO().getListevaleurs();
    }
 
    /**
     * Get Agents DAO
     *
     * @return IAgentsDAO - Agents DAO
     */
    public IListevaleursDAO getListevaleursDAO() {
        return listevaleursDAO;
    }
 
    /**
     * Set Agents DAO
     *
     * @param IAgentsDAO - Agents DAO
     */
    public void setListevaleursDAO(IListevaleursDAO listevaleursDAO) {
        this.listevaleursDAO = listevaleursDAO;
    }
    
     /**
     * Permet d'obtenir la liste de valeur par formation
     * @param typeFormation
     * @return 
     */
    @Override
    public List<Listevaleurs> getListevaleursByTypeFormation(int typeFormation) {
        return getListevaleursDAO().getListevaleursByTypeFormation(typeFormation);
    }
    
}
