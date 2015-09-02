/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villemelun.formations.managed.bean;


import fr.villemelun.formations.models.FormaServ;
import fr.villemelun.formations.models.ReportBean;
import fr.villemelun.formations.models.Services;
import fr.villemelun.formations.services.interfaces.IAgentsService;
import fr.villemelun.formations.services.interfaces.IFormationsService;
import fr.villemelun.formations.services.interfaces.IListevaleursService;
import fr.villemelun.formations.services.interfaces.IServicesService;
import fr.villemelun.formations.services.interfaces.ITypeformationsService;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;


/**
 *
 * @author nanard
 */
@ManagedBean(name="servicesMB")
@ViewScoped
public class ServicesManagedBean implements Serializable {

    private static final long serialVersionUID = 10L;
    private static final String SUCCESS = "success";
    private static final String ERROR   = "error";
    private Integer progress;

    
    @ManagedProperty(value="#{ServicesService}")
    IServicesService servicesService;
    
     //Spring Agents Service is injected...
    @ManagedProperty(value="#{AgentsService}")
    IAgentsService agentsService;
    
    @ManagedProperty(value="#{TypeformationsService}")
    ITypeformationsService typeService;
    
    @ManagedProperty(value="#{ListevaleursService}")
    IListevaleursService listeValeurService;
    
    @ManagedProperty(value="#{FormationsService}")
    IFormationsService formationsService;  

    List<Services> servicesList = new ArrayList<Services>();

    public void setServicesList(List<Services> servicesList) {
        this.servicesList = servicesList;
    }
    
    /**
     * Creates a new instance of AgentsManagedBean
     */
    public ServicesManagedBean() {
     
    }
    
    @PostConstruct
    public void init() {
    }
    
    /**
     * Reset Fields
     *
     */
    public void reset() {
        //servicesList = new ArrayList<Services>();
    }

    public Integer getProgress() {
        if(progress!= null && progress > 100)  
                progress = 99;
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }
    
    public void onComplete() {  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Progress Completed", "Progress Completed"));  
    } 
    
    public void getBoard() {
        InputStream is = null;
        OutputStream sortie = null;
        if(progress == null) progress = 0;
        try {
            is = new BufferedInputStream(new FileInputStream("c:\\board_template.xls"));
            sortie = new BufferedOutputStream(new FileOutputStream("c:\\tableau.xls"));
            XLSTransformer transformer = new XLSTransformer();
            
            progress = progress + 1;
            
            servicesList = new ArrayList<>();
            servicesList.addAll(getServicesService().getServices());
            progress = progress + 1;
            
            getFormationsService().getAlphaByServices(new Short("19"));
            
            List sheetNames = new ArrayList();
            List services = new ArrayList();
            List<FormaServ> services3 = new ArrayList();
            Map services2 = new HashMap();
            for (Services service : servicesList) {
                Map map = new HashMap();
                FormaServ formServ = getFormationsService().getFormations(service.getId());
                formServ.setService(service);
                map.put("service", formServ);
                
                //services.add(formServ);
                services.add(map);
                services3.add(formServ);
                sheetNames.add(service.getLibelle());
            }
            services2.put("serv", services);
            progress = progress + 38;
            
//remplissage pour illetrisme
            String illetrisme ="";
            String illetrismeCount ="";
            int compteurIlletrisme = 0;
            
            Map formaAutre = new HashMap();
            Map formaCNFPT = new HashMap();
            Map formaConcours = new HashMap();
            Map formaExamens = new HashMap();
            Map formaPrepaC = new HashMap();
            Map formaPrepaE = new HashMap();
            Map formaHyg = new HashMap();
            Map formaInfo = new HashMap();
            Map formaLogi = new HashMap();
            Map formaVAE = new HashMap();
            Map tmpMap = new HashMap();
            
            
            for (FormaServ formServ : services3) {
                progress = progress + 1;
                illetrisme=illetrisme + formServ.getService().getLibelle() + "\n";
                if(formServ.getFrancais().size()>0) {
                    illetrismeCount=illetrismeCount + formServ.getFrancais().values().iterator().next() + "\n";
                    compteurIlletrisme = compteurIlletrisme + ((BigInteger)formServ.getFrancais().values().iterator().next()).intValue();
                }else {
                    illetrismeCount=illetrismeCount + "\n";
                    compteurIlletrisme = compteurIlletrisme + 0;
                    formServ.getFrancais().put("vide", "vide");
                }
                
                
                //formation autre
                for (Object forma : formServ.getAutre().entrySet()) {
                    tmpMap = new HashMap();
                    
                    ReportBean val = (ReportBean)((Map.Entry)forma).getValue();
                    if(formaAutre.get(((Map.Entry)forma).getKey())!=null) {
                        tmpMap = (Map)formaAutre.get(((Map.Entry)forma).getKey());
                        Map.Entry entree = (Map.Entry)tmpMap.entrySet().iterator().next();
                        String tmpStr1 = entree.getKey() + "\n" + formServ.getService().getLibelle();
                        String tmpStr2 = entree.getValue() + "\n" + val.getCompte();
                        formaAutre.put(((Map.Entry)forma).getKey()+"?", ((BigInteger)val.getCompte()).add((BigInteger)formaAutre.get(((Map.Entry)forma).getKey()+"?")));
                        tmpMap = new HashMap();
                        tmpMap.put(tmpStr1, tmpStr2);
                    }else{
                        tmpMap.put(formServ.getService().getLibelle(), val.getCompte());
                        formaAutre.put(((Map.Entry)forma).getKey()+"?", val.getCompte());                  
                    }
                    formaAutre.put(((Map.Entry)forma).getKey(), tmpMap);                  
                }
                
                //formation CNFPT
                for (Object forma : formServ.getCnfpt().entrySet()) {
                    tmpMap = new HashMap();
                    
                    BigInteger val = (BigInteger)((Map.Entry)forma).getValue();
                    if(formaCNFPT.get(((Map.Entry)forma).getKey())!=null) {
                        tmpMap = (Map)formaCNFPT.get(((Map.Entry)forma).getKey());
                        Map.Entry entree = (Map.Entry)tmpMap.entrySet().iterator().next();
                        String tmpStr1 = entree.getKey() + "\n" + formServ.getService().getLibelle();
                        String tmpStr2 = entree.getValue() + "\n" + val;
                        formaCNFPT.put(((Map.Entry)forma).getKey()+"?", val.add((BigInteger)formaCNFPT.get(((Map.Entry)forma).getKey()+"?")));
                        tmpMap = new HashMap();
                        tmpMap.put(tmpStr1, tmpStr2);
                    }else{
                        tmpMap.put(formServ.getService().getLibelle(), val);
                        formaCNFPT.put(((Map.Entry)forma).getKey()+"?", val);                  
                    }
                    formaCNFPT.put(((Map.Entry)forma).getKey(), tmpMap);                  
                }
                
                //formation concours
                for (Object forma : formServ.getConcours().entrySet()) {
                    tmpMap = new HashMap();
                    
                    BigInteger val = (BigInteger)((Map.Entry)forma).getValue();
                    if(formaConcours.get(((Map.Entry)forma).getKey())!=null) {
                        tmpMap = (Map)formaConcours.get(((Map.Entry)forma).getKey());
                        Map.Entry entree = (Map.Entry)tmpMap.entrySet().iterator().next();
                        String tmpStr1 = entree.getKey() + "\n" + formServ.getService().getLibelle();
                        String tmpStr2 = entree.getValue() + "\n" + val;
                        formaConcours.put(((Map.Entry)forma).getKey()+"?", val.add((BigInteger)formaConcours.get(((Map.Entry)forma).getKey()+"?")));
                        tmpMap = new HashMap();
                        tmpMap.put(tmpStr1, tmpStr2);
                    }else{
                        tmpMap.put(formServ.getService().getLibelle(), val);
                        formaConcours.put(((Map.Entry)forma).getKey()+"?", val);                  
                    }
                    formaConcours.put(((Map.Entry)forma).getKey(), tmpMap);                  
                }
                
                 //formation examens
                for (Object forma : formServ.getExamens().entrySet()) {
                    tmpMap = new HashMap();
                    
                    BigInteger val = (BigInteger)((Map.Entry)forma).getValue();
                    if(formaExamens.get(((Map.Entry)forma).getKey())!=null) {
                        tmpMap = (Map)formaExamens.get(((Map.Entry)forma).getKey());
                        Map.Entry entree = (Map.Entry)tmpMap.entrySet().iterator().next();
                        String tmpStr1 = entree.getKey() + "\n" + formServ.getService().getLibelle();
                        String tmpStr2 = entree.getValue() + "\n" + val;
                        formaExamens.put(((Map.Entry)forma).getKey()+"?", val.add((BigInteger)formaExamens.get(((Map.Entry)forma).getKey()+"?")));
                        tmpMap = new HashMap();
                        tmpMap.put(tmpStr1, tmpStr2);
                    }else{
                        tmpMap.put(formServ.getService().getLibelle(), val);
                        formaExamens.put(((Map.Entry)forma).getKey()+"?", val);                  
                    }
                    formaExamens.put(((Map.Entry)forma).getKey(), tmpMap);                  
                }
                
                 //formation prepa-examens
                for (Object forma : formServ.getPrepExams().entrySet()) {
                    tmpMap = new HashMap();
                    
                    BigInteger val = (BigInteger)((Map.Entry)forma).getValue();
                    if(formaPrepaE.get(((Map.Entry)forma).getKey())!=null) {
                        tmpMap = (Map)formaPrepaE.get(((Map.Entry)forma).getKey());
                        Map.Entry entree = (Map.Entry)tmpMap.entrySet().iterator().next();
                        String tmpStr1 = entree.getKey() + "\n" + formServ.getService().getLibelle();
                        String tmpStr2 = entree.getValue() + "\n" + val;
                        formaPrepaE.put(((Map.Entry)forma).getKey()+"?", val.add((BigInteger)formaPrepaE.get(((Map.Entry)forma).getKey()+"?")));
                        tmpMap = new HashMap();
                        tmpMap.put(tmpStr1, tmpStr2);
                    }else{
                        tmpMap.put(formServ.getService().getLibelle(), val);
                        formaPrepaE.put(((Map.Entry)forma).getKey()+"?", val);                  
                    }
                    formaPrepaE.put(((Map.Entry)forma).getKey(), tmpMap);                  
                }
                
                //formation prepa-concours
                for (Object forma : formServ.getPrepConcours().entrySet()) {
                    tmpMap = new HashMap();
                    
                    BigInteger val = (BigInteger)((Map.Entry)forma).getValue();
                    if(formaPrepaC.get(((Map.Entry)forma).getKey())!=null) {
                        tmpMap = (Map)formaPrepaC.get(((Map.Entry)forma).getKey());
                        Map.Entry entree = (Map.Entry)tmpMap.entrySet().iterator().next();
                        String tmpStr1 = entree.getKey() + "\n" + formServ.getService().getLibelle();
                        String tmpStr2 = entree.getValue() + "\n" + val;
                        formaPrepaC.put(((Map.Entry)forma).getKey()+"?", val.add((BigInteger)formaPrepaC.get(((Map.Entry)forma).getKey()+"?")));
                        tmpMap = new HashMap();
                        tmpMap.put(tmpStr1, tmpStr2);
                    }else{
                        tmpMap.put(formServ.getService().getLibelle(), val);
                        formaPrepaC.put(((Map.Entry)forma).getKey()+"?", val);                  
                    }
                    formaPrepaC.put(((Map.Entry)forma).getKey(), tmpMap);                  
                }
                
                //formation hygiene
                for (Object forma : formServ.getHygiene().entrySet()) {
                    tmpMap = new HashMap();
                    
                    BigInteger val = (BigInteger)((Map.Entry)forma).getValue();
                    if(formaHyg.get(((Map.Entry)forma).getKey())!=null) {
                        tmpMap = (Map)formaHyg.get(((Map.Entry)forma).getKey());
                        Map.Entry entree = (Map.Entry)tmpMap.entrySet().iterator().next();
                        String tmpStr1 = entree.getKey() + "\n" + formServ.getService().getLibelle();
                        String tmpStr2 = entree.getValue() + "\n" + val;
                        formaHyg.put(((Map.Entry)forma).getKey()+"?", val.add((BigInteger)formaHyg.get(((Map.Entry)forma).getKey()+"?")));
                        tmpMap = new HashMap();
                        tmpMap.put(tmpStr1, tmpStr2);
                    }else{
                        tmpMap.put(formServ.getService().getLibelle(), val);
                        formaHyg.put(((Map.Entry)forma).getKey()+"?", val);                  
                    }
                    formaHyg.put(((Map.Entry)forma).getKey(), tmpMap);                  
                }
                
                 //formation informatique
                for (Object forma : formServ.getInfo().entrySet()) {
                    tmpMap = new HashMap();
                    
                    ReportBean val = (ReportBean)((Map.Entry)forma).getValue();
                    if(formaInfo.get(((Map.Entry)forma).getKey())!=null) {
                        tmpMap = (Map)formaInfo.get(((Map.Entry)forma).getKey());
                        Map.Entry entree = (Map.Entry)tmpMap.entrySet().iterator().next();
                        String tmpStr1 = entree.getKey() + "\n" + formServ.getService().getLibelle();
                        String tmpStr2 = entree.getValue() + "\n" + val.getCompte();
                        formaInfo.put(((Map.Entry)forma).getKey()+"?", ((BigInteger)val.getCompte()).add((BigInteger)formaInfo.get(((Map.Entry)forma).getKey()+"?")));
                        tmpMap = new HashMap();
                        tmpMap.put(tmpStr1, tmpStr2);
                    }else{
                        tmpMap.put(formServ.getService().getLibelle(), val.getCompte());
                        formaInfo.put(((Map.Entry)forma).getKey()+"?", val.getCompte());                  
                    }
                    formaInfo.put(((Map.Entry)forma).getKey(), tmpMap);                  
                }
                
                  //formation Logiciels
                for (Object forma : formServ.getLogi().entrySet()) {
                    tmpMap = new HashMap();
                    
                    BigInteger val = (BigInteger)((Map.Entry)forma).getValue();
                    if(formaLogi.get(((Map.Entry)forma).getKey())!=null) {
                        tmpMap = (Map)formaLogi.get(((Map.Entry)forma).getKey());
                        Map.Entry entree = (Map.Entry)tmpMap.entrySet().iterator().next();
                        String tmpStr1 = entree.getKey() + "\n" + formServ.getService().getLibelle();
                        String tmpStr2 = entree.getValue() + "\n" + val;
                        formaLogi.put(((Map.Entry)forma).getKey()+"?", val.add((BigInteger)formaLogi.get(((Map.Entry)forma).getKey()+"?")));
                        tmpMap = new HashMap();
                        tmpMap.put(tmpStr1, tmpStr2);
                    }else{
                        tmpMap.put(formServ.getService().getLibelle(), val);
                        formaLogi.put(((Map.Entry)forma).getKey()+"?", val);                  
                    }
                    formaLogi.put(((Map.Entry)forma).getKey(), tmpMap);                  
                }
                
                //formation vae
                for (Object forma : formServ.getVae().entrySet()) {
                    tmpMap = new HashMap();
                    
                    ReportBean val = (ReportBean)((Map.Entry)forma).getValue();
                    if(formaVAE.get(((Map.Entry)forma).getKey())!=null) {
                        tmpMap = (Map)formaVAE.get(((Map.Entry)forma).getKey());
                        Map.Entry entree = (Map.Entry)tmpMap.entrySet().iterator().next();
                        String tmpStr1 = entree.getKey() + "\n" + formServ.getService().getLibelle();
                        String tmpStr2 = entree.getValue() + "\n" + val.getCompte();
                        formaVAE.put(((Map.Entry)forma).getKey()+"?", ((BigInteger)val.getCompte()).add((BigInteger)formaVAE.get(((Map.Entry)forma).getKey()+"?")));
//                        formaVAE.put(((Map.Entry)forma).getKey()+"!", ((String)formaVAE.get(((Map.Entry)forma).getKey()+"!")) + "\n" + val.getPrenom() + " " + val.getNom());
                        tmpMap = new HashMap();
                        tmpMap.put(tmpStr1, tmpStr2);
                    }else{
                        tmpMap.put(formServ.getService().getLibelle(), val.getCompte());
                        formaVAE.put(((Map.Entry)forma).getKey()+"?", val.getCompte());
//                        formaVAE.put(((Map.Entry)forma).getKey()+"!", val.getPrenom() + " " + val.getNom());
                    }
                    formaVAE.put(((Map.Entry)forma).getKey(), tmpMap);                  
                }
                
            }
            services2.put("alpha1", illetrisme);
            services2.put("alpha2", illetrismeCount);
            services2.put("alpha3", compteurIlletrisme);
            services2.put("autre", formaAutre);
            services2.put("cnfpt", formaCNFPT);
            services2.put("concours", formaConcours);
            services2.put("examens", formaExamens);
            services2.put("prepae", formaPrepaE);
            services2.put("prepac", formaPrepaC);
            services2.put("hyg", formaHyg);
            services2.put("info", formaInfo);
            services2.put("logi", formaLogi);
            services2.put("vae", formaVAE);
            progress = progress + 1;
                       
            try {
                HSSFWorkbook resultWorkbook = (HSSFWorkbook)transformer.transformMultipleSheetsList(is, services, sheetNames, "map", services2, 0);
                progress = 100;
                resultWorkbook.write(sortie);               
            } catch (ParsePropertyException ex) {
                Logger.getLogger(ServicesManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidFormatException ex) {
                Logger.getLogger(ServicesManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ServicesManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ServicesManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                is.close();
                sortie.close();
            } catch (IOException ex) {
                Logger.getLogger(ServicesManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
   
    /**
     * Get Agents List
     *
     * @return List - Agents List
     */
    public List<Services> getServicesList() {
        servicesList = new ArrayList<>();
        servicesList.addAll(getServicesService().getServices());
        return servicesList;
    }
    
    public IServicesService getServicesService() {
        return servicesService;
    }

    public void setServicesService(IServicesService servicesService) {
        this.servicesService = servicesService;
    }
    
    public IAgentsService getAgentsService() {
        return agentsService;
    }

    public void setAgentsService(IAgentsService agentsService) {
        this.agentsService = agentsService;
    }

    public ITypeformationsService getTypeService() {
        return typeService;
    }

    public void setTypeService(ITypeformationsService typeService) {
        this.typeService = typeService;
    }

    public IListevaleursService getListeValeurService() {
        return listeValeurService;
    }

    public void setListeValeurService(IListevaleursService listeValeurService) {
        this.listeValeurService = listeValeurService;
    }

    public IFormationsService getFormationsService() {
        return formationsService;
    }

    public void setFormationsService(IFormationsService formationsService) {
        this.formationsService = formationsService;
    }
    
}