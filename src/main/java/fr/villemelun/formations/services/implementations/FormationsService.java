/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villemelun.formations.services.implementations;

import fr.villemelun.formations.dao.interfaces.IFormationsDAO;
import fr.villemelun.formations.models.FormaServ;
import fr.villemelun.formations.models.Formations;
import fr.villemelun.formations.models.ReportBean;
import fr.villemelun.formations.services.interfaces.IFormationsService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nanard
 */
@Service
@Transactional(readOnly = true)
public class FormationsService implements IFormationsService {
 
    // FormationsDAO is injected...
    @Autowired
    IFormationsDAO formationsDAO;
 
    /**
     * Add Formations
     *
     * @param  Formations user
     */
    @Transactional(readOnly = false)
    @Override
    public void addFormations(Formations formation) {
        getFormationsDAO().addFormation(formation);
    }
 
    /**
     * Delete Formations
     *
     * @param  Formations user
     */
    @Transactional(readOnly = false)
    @Override
    public void deleteFormations(Formations formation) {
        getFormationsDAO().deleteFormations(formation);
    }
 
    /**
     * Update Agents
     *
     * @param  Agents user
     */
    @Transactional(readOnly = false)
    @Override
    public void updateFormations(Formations formation) {
        getFormationsDAO().updateFormations(formation);
    }
 
    /**
     * Get Agents
     *
     * @param  int Agents Id
     */
    @Override
    public Formations getFormationsById(int id) {
        return getFormationsDAO().getFormationsById(id);
    }
 
    /**
     * Get Agents List
     *
     */
    @Override
    public List<Formations> getFormations() {
        return getFormationsDAO().getFormations();
    }
 
    /**
     * Get Agents DAO
     *
     * @return IAgentsDAO - Agents DAO
     */
    public IFormationsDAO getFormationsDAO() {
        return formationsDAO;
    }
 
    /**
     * Set Agents DAO
     *
     * @param IAgentsDAO - Agents DAO
     */
    public void setFormationsDAO(IFormationsDAO formationsDAO) {
        this.formationsDAO = formationsDAO;
    }
    
    @Override
    public Map getAlphaByServices(Short idService) {
        return getFormationsDAO().getAlphaByServices(idService);
    }
    
    @Override
    public FormaServ getFormations(Short idService) {
        FormaServ resultat = new FormaServ();
        String tmp1 = "";
        String tmp2 = "";
        
        resultat.setAlphaForm(getFormationsDAO().getAlphaByServices(idService));  //plus utilisé en 2015
        resultat.setHygiene(getFormationsDAO().getHygByServices(idService));
        resultat.setFrancais(getFormationsDAO().getFrancaisByServices(idService));
        
        resultat.setLogi(getFormationsDAO().getInfosByServices(idService));
        for (Object entry : resultat.getLogi().entrySet()) {
            tmp1 = tmp1 + ((Map.Entry)entry).getKey() + "\n";
            tmp2 = tmp2 + ((Map.Entry)entry).getValue() + "\n";
        }
        resultat.setLogi2(new HashMap());
        resultat.getLogi2().put(tmp1, tmp2);
        tmp1 = new String("");
        tmp2 = new String("");
        
        resultat.setPolice(getFormationsDAO().getPoliceServices(idService));
        for (Object entry : resultat.getPolice().entrySet()) {
            tmp1 = tmp1 + ((Map.Entry)entry).getKey() + "\n";
            tmp2 = tmp2 + ((Map.Entry)entry).getValue() + "\n";
        }
        resultat.setPolice2(new HashMap());
        resultat.getPolice2().put(tmp1, tmp2);
        tmp1 = new String("");
        tmp2 = new String("");
        
        resultat.setCnfpt(getFormationsDAO().getCnfptByServices(idService));       
        for (Object entry : resultat.getCnfpt().entrySet()) {
            tmp1 = tmp1 + ((Map.Entry)entry).getKey() + "\n";
            tmp2 = tmp2 + ((Map.Entry)entry).getValue() + "\n";
        }
        resultat.setCnfpt2(new HashMap());
        resultat.getCnfpt2().put(tmp1, tmp2);
        tmp1 = new String("");
        tmp2 = new String("");
        
        resultat.setConcours(getFormationsDAO().getConcoursByServices(idService));
        for (Object entry : resultat.getConcours().entrySet()) {
            tmp1 = tmp1 + ((Map.Entry)entry).getKey() + "\n";
            tmp2 = tmp2 + ((Map.Entry)entry).getValue() + "\n";
        }
        resultat.setConcours2(new HashMap());
        resultat.getConcours2().put(tmp1, tmp2);
        tmp1 = new String("");
        tmp2 = new String("");
        
        resultat.setExamens(getFormationsDAO().getExamensByServices(idService));
        for (Object entry : resultat.getExamens().entrySet()) {
            tmp1 = tmp1 + ((Map.Entry)entry).getKey() + "\n";
            tmp2 = tmp2 + ((Map.Entry)entry).getValue() + "\n";
        }
        resultat.setExamens2(new HashMap());
        resultat.getExamens2().put(tmp1, tmp2);
        tmp1 = new String("");
        tmp2 = new String("");
        
        resultat.setPrepConcours(getFormationsDAO().getPrepConcoursByServices(idService));
        for (Object entry : resultat.getPrepConcours().entrySet()) {
            tmp1 = tmp1 + ((Map.Entry)entry).getKey() + "\n";
            tmp2 = tmp2 + ((Map.Entry)entry).getValue() + "\n";
        }
        resultat.setPrepConcours2(new HashMap());
        resultat.getPrepConcours2().put(tmp1, tmp2);
        tmp1 = new String("");
        tmp2 = new String("");
        
        resultat.setPrepExams(getFormationsDAO().getPrepExamensByServices(idService));
        for (Object entry : resultat.getPrepExams().entrySet()) {
            tmp1 = tmp1 + ((Map.Entry)entry).getKey() + "\n";
            tmp2 = tmp2 + ((Map.Entry)entry).getValue() + "\n";
        }
        resultat.setPrepExams2(new HashMap());
        resultat.getPrepExams2().put(tmp1, tmp2);
        tmp1 = new String("");
        tmp2 = new String("");
        
        resultat.setVae(getFormationsDAO().getVaeDipByServices(idService));
        ReportBean tmpBean = new ReportBean();
        for (Object entry : resultat.getVae().entrySet()) {
            tmp1 = tmp1 + ((Map.Entry)entry).getKey() + "\n";
            if(tmpBean.getCompte()!=null) {
                tmpBean.setCompte(tmpBean.getCompte() + ((ReportBean)((Map.Entry)entry).getValue()).getCompte().toString() + "\n");
            }else {
                tmpBean.setCompte(((ReportBean)((Map.Entry)entry).getValue()).getCompte().toString() + "\n");
            }
            tmpBean.setOrganisme(tmpBean.getOrganisme() + ((ReportBean)((Map.Entry)entry).getValue()).getOrganisme() + "\n");
            if(tmpBean.getCout()!=null) {
                tmpBean.setCout(tmpBean.getCout() + ((ReportBean)((Map.Entry)entry).getValue()).getCout().toString() + "\n");
            } else {
                tmpBean.setCout(((ReportBean)((Map.Entry)entry).getValue()).getCout().toString() + "\n");
            }    
        }
        resultat.setVae2(new HashMap());
        resultat.getVae2().put(tmp1, tmpBean);
        tmp1 = new String("");
        
        resultat.setDiplo(getFormationsDAO().getDipByServices(idService));
        tmpBean = new ReportBean();
        for (Object entry : resultat.getDiplo().entrySet()) {
            tmp1 = tmp1 + ((Map.Entry)entry).getKey() + "\n";
            if(tmpBean.getCompte()!=null) {
                tmpBean.setCompte(tmpBean.getCompte() + ((ReportBean)((Map.Entry)entry).getValue()).getCompte().toString() + "\n");
            }else {
                tmpBean.setCompte(((ReportBean)((Map.Entry)entry).getValue()).getCompte().toString() + "\n");
            }
            tmpBean.setOrganisme(tmpBean.getOrganisme() + ((ReportBean)((Map.Entry)entry).getValue()).getOrganisme() + "\n");
            if(tmpBean.getCout()!=null) {
                tmpBean.setCout(tmpBean.getCout() + ((ReportBean)((Map.Entry)entry).getValue()).getCout().toString() + "\n");
            } else {
                tmpBean.setCout(((ReportBean)((Map.Entry)entry).getValue()).getCout().toString() + "\n");
            }    
        }
        resultat.setDiplo2(new HashMap());
        resultat.getDiplo2().put(tmp1, tmpBean);
        tmp1 = new String("");       
              
        resultat.setInfo(getFormationsDAO().getInfoByServices(idService));
        tmpBean = new ReportBean();
        for (Object entry : resultat.getInfo().entrySet()) {
            tmp1 = tmp1 + ((Map.Entry)entry).getKey() + "\n";
            if(tmpBean.getCompte()!=null) {
                tmpBean.setCompte(tmpBean.getCompte() + ((ReportBean)((Map.Entry)entry).getValue()).getCompte().toString() + "\n");
            }else {
                tmpBean.setCompte(((ReportBean)((Map.Entry)entry).getValue()).getCompte().toString() + "\n");
            }
            tmpBean.setOrganisme(tmpBean.getOrganisme() + ((ReportBean)((Map.Entry)entry).getValue()).getOrganisme() + "\n");
            if(tmpBean.getCout()!=null) {
                tmpBean.setCout(tmpBean.getCout() + ((ReportBean)((Map.Entry)entry).getValue()).getCout().toString() + "\n");
            } else {
                tmpBean.setCout(((ReportBean)((Map.Entry)entry).getValue()).getCout().toString() + "\n");
            }    
        }
        resultat.setInfo2(new HashMap());
        resultat.getInfo2().put(tmp1, tmpBean);
        tmp1 = new String("");
        
        resultat.setAutre(getFormationsDAO().getAutreByServices(idService));
        tmpBean = new ReportBean();
        for (Object entry : resultat.getAutre().entrySet()) {
            tmp1 = tmp1 + ((Map.Entry)entry).getKey() + "\n";
            if(tmpBean.getCompte()!=null) {
                tmpBean.setCompte(tmpBean.getCompte() + ((ReportBean)((Map.Entry)entry).getValue()).getCompte().toString() + "\n");
            }else {
                tmpBean.setCompte(((ReportBean)((Map.Entry)entry).getValue()).getCompte().toString() + "\n");
            }
            tmpBean.setOrganisme(tmpBean.getOrganisme() + ((ReportBean)((Map.Entry)entry).getValue()).getOrganisme() + "\n");
            if(tmpBean.getCout()!=null) {
                tmpBean.setCout(tmpBean.getCout() + ((ReportBean)((Map.Entry)entry).getValue()).getCout().toString() + "\n");
            } else {
                tmpBean.setCout(((ReportBean)((Map.Entry)entry).getValue()).getCout().toString() + "\n");
            }    
        }
        resultat.setAutre2(new HashMap());
        resultat.getAutre2().put(tmp1, tmpBean);
        tmp1 = new String("");
        
        return resultat;
    }
}
