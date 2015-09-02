/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villemelun.formations.dao.interfaces;

import fr.villemelun.formations.models.Typeformations;
import java.util.List;

/**
 *
 * @author nanard
 */
public interface ITypeformationsDAO {
     /**
     * Add Agents
     *
     * @param  Agents user
     */
    public void addTypeformation(Typeformations typeformation);
 
    /**
     * Update Typeformations
     *
     * @param  Typeformations user
     */
    public void updateTypeformations(Typeformations typeformation);
 
    /**
     * Delete Typeformations
     *
     * @param  Typeformations user
     */
    public void deleteTypeformations(Typeformations typeformation);
 
    /**
     * Get Typeformations
     *
     * @param  int Typeformations Id
     */
    public Typeformations getTypeformationsById(int id);
 
    /**
     * Get Typeformations List
     *
     */
    public List<Typeformations> getTypeformations();
}
