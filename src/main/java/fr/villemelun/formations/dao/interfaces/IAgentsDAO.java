/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villemelun.formations.dao.interfaces;

import fr.villemelun.formations.models.Agents;
import java.util.List;

/**
 *
 * @author nanard
 */
public interface IAgentsDAO {
    /**
     * Add Agents
     *
     * @param  Agents user
     */
    public void addAgent(Agents agent);
 
    /**
     * Update Agents
     *
     * @param  Agents user
     */
    public void updateAgents(Agents agent);
 
    /**
     * Delete Agents
     *
     * @param  Agents user
     */
    public void deleteAgents(Agents agent);
 
    /**
     * Get Agents
     *
     * @param  int Agents Id
     */
    public Agents getAgentsById(int id);
 
    /**
     * Get Agents List
     *
     */
    public List<Agents> getAgents();
    
    /**
     * Retourne la liste des agents d'un service
     * @return 
     */
    public List<Agents> getAgentsByService(int idService);
}
