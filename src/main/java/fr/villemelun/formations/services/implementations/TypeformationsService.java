/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villemelun.formations.services.implementations;

import fr.villemelun.formations.dao.interfaces.ITypeformationsDAO;
import fr.villemelun.formations.models.Typeformations;
import fr.villemelun.formations.services.interfaces.ITypeformationsService;
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
public class TypeformationsService implements ITypeformationsService {
 
    // AgentsDAO is injected...
    @Autowired
    ITypeformationsDAO typeformationsDAO;
 
    /**
     * Add Typeformations
     *
     * @param  Typeformations user
     */
    @Transactional(readOnly = false)
    @Override
    public void addTypeformations(Typeformations typeformation) {
        getTypeformationsDAO().addTypeformation(typeformation);
    }
 
    /**
     * Delete Typeformations
     *
     * @param  Typeformations user
     */
    @Transactional(readOnly = false)
    @Override
    public void deleteTypeformations(Typeformations typeformation) {
        getTypeformationsDAO().deleteTypeformations(typeformation);
    }
 
    /**
     * Update Typeformations
     *
     * @param  Typeformations user
     */
    @Transactional(readOnly = false)
    @Override
    public void updateTypeformations(Typeformations typeformation) {
        getTypeformationsDAO().updateTypeformations(typeformation);
    }
 
    /**
     * Get Agents
     *
     * @param  int Agents Id
     */
    @Override
    public Typeformations getTypeformationsById(int id) {
        return getTypeformationsDAO().getTypeformationsById(id);
    }
 
    /**
     * Get Agents List
     *
     */
    @Override
    public List<Typeformations> getTypeformations() {
        return getTypeformationsDAO().getTypeformations();
    }
 
    /**
     * Get Agents DAO
     *
     * @return IAgentsDAO - Agents DAO
     */
    public ITypeformationsDAO getTypeformationsDAO() {
        return typeformationsDAO;
    }
 
    /**
     * Set Agents DAO
     *
     * @param IAgentsDAO - Agents DAO
     */
    public void setTypeformationsDAO(ITypeformationsDAO typeformationsDAO) {
        this.typeformationsDAO = typeformationsDAO;
    }
    
}
