/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villemelun.formations.services.implementations;

import fr.villemelun.formations.dao.interfaces.IAgentsDAO;
import fr.villemelun.formations.models.Agents;
import fr.villemelun.formations.services.interfaces.IAgentsService;
import java.io.Serializable;
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
public class AgentsService implements IAgentsService, Serializable {
 
    private static final long serialVersionUID = 112L;
    // AgentsDAO is injected...
    @Autowired
    IAgentsDAO agentsDAO;
 
    /**
     * Add Agents
     *
     * @param  Agents user
     */
    @Transactional(readOnly = false)
    @Override
    public void addAgents(Agents agent) {
        getAgentsDAO().addAgent(agent);
    }
 
    /**
     * Delete Agents
     *
     * @param  Agents user
     */
    @Transactional(readOnly = false)
    @Override
    public void deleteAgents(Agents agent) {
        getAgentsDAO().deleteAgents(agent);
    }
 
    /**
     * Update Agents
     *
     * @param  Agents user
     */
    @Transactional(readOnly = false)
    @Override
    public void updateAgents(Agents agent) {
        getAgentsDAO().updateAgents(agent);
    }
 
    /**
     * Get Agents
     *
     * @param  int Agents Id
     */
    @Override
    public Agents getAgentsById(int id) {
        return getAgentsDAO().getAgentsById(id);
    }
 
    /**
     * Get Agents List
     *
     */
    @Override
    public List<Agents> getAgents() {
        return getAgentsDAO().getAgents();
    }
 
    /**
     * Get Agents DAO
     *
     * @return IAgentsDAO - Agents DAO
     */
    public IAgentsDAO getAgentsDAO() {
        return agentsDAO;
    }
 
    /**
     * Set Agents DAO
     *
     * @param IAgentsDAO - Agents DAO
     */
    public void setAgentsDAO(IAgentsDAO agentsDAO) {
        this.agentsDAO = agentsDAO;
    }
    
    /**
     * Retourne la liste des agents d'un service
     * @return 
     */
    public List<Agents> getAgentsByService(int idService) {
        return getAgentsDAO().getAgentsByService(idService);
    }
    
}
