/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villemelun.formations.services.interfaces;

import fr.villemelun.formations.models.Typeformations;
import java.util.List;

/**
 *
 * @author nanard
 */
public interface ITypeformationsService {
    /**
     * Add Typeformations
     *
     * @param  Typeformations user
     */
    public void addTypeformations(Typeformations typeformation);
 
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
     * @return List - Typeformations list
     */
    public List<Typeformations> getTypeformations();
}
