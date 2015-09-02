/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villemelun.formations.managed.bean;

import fr.villemelun.formations.models.Agents;
import fr.villemelun.formations.models.Formations;
import fr.villemelun.formations.models.Listevaleurs;
import fr.villemelun.formations.models.Services;
import fr.villemelun.formations.models.Typeformations;
import fr.villemelun.formations.services.interfaces.IAgentsService;
import fr.villemelun.formations.services.interfaces.IFormationsService;
import fr.villemelun.formations.services.interfaces.IListevaleursService;
import fr.villemelun.formations.services.interfaces.IServicesService;
import fr.villemelun.formations.services.interfaces.ITypeformationsService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author nanard
 */
@ManagedBean(name="agentsMB")
@ViewScoped
public class AgentsManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "success";
    private static final String ERROR   = "error";
 
    //Spring Agents Service is injected...
    @ManagedProperty(value="#{AgentsService}")
    IAgentsService agentsService;
    
    @ManagedProperty(value="#{ServicesService}")
    IServicesService servicesService; 
    
    @ManagedProperty(value="#{TypeformationsService}")
    ITypeformationsService typeService;
    
    @ManagedProperty(value="#{ListevaleursService}")
    IListevaleursService listeValeurService;
    
    @ManagedProperty(value="#{FormationsService}")
    IFormationsService formationsService;
 
    List<Agents> agentsList = new ArrayList<Agents>();
    List<Typeformations> typeList = new ArrayList<Typeformations>();
    List<Listevaleurs> police = new ArrayList<Listevaleurs>();
    List<Listevaleurs> hygiene = new ArrayList<Listevaleurs>();
    List<Listevaleurs> informatique = new ArrayList<Listevaleurs>();
    List<Listevaleurs> examens = new ArrayList<Listevaleurs>();
    List<Listevaleurs> concours = new ArrayList<Listevaleurs>();
    List<Listevaleurs> prepaexamens = new ArrayList<Listevaleurs>();
    List<Listevaleurs> prepaconcours = new ArrayList<Listevaleurs>();


    private int id;
    private String selectedFormation;
    private String selectedFormat;
    private boolean debutant;
    private boolean reconversion;
    private boolean dif;
    private boolean projetX;
    private boolean initiation;
    private boolean payant;
    private String intitule;
    private String organisme;
    private Integer cout;

    
    private Agents selectedAgent;
    private boolean welcome = true;
    private String nom;
    private String prenom;
    private Agents agent = new Agents();
    private Services services = new Services();
    private Services serviceDem = new Services();
    
    //@ManagedProperty(value="#{param.nomDem}")
    private String nomDem;
   // @ManagedProperty(value="#{param.prenomDem}")
    private String prenomDem;
    
    
    /**
     * Creates a new instance of AgentsManagedBean
     */
    public AgentsManagedBean() {
    }
    
    @PostConstruct
    public void init() {
        selectedAgent=new Agents();
        this.nomDem=(String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nomDem");
        this.prenomDem=(String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prenomDem");
        
        Services demandeur = new Services();
        if(getNomDem()!=null) {
            demandeur.setNom(getNomDem().toUpperCase());
            demandeur.setPrenom(getPrenomDem().toUpperCase());
            this.serviceDem = this.getServicesService().getServicesByDemandeur(demandeur);
        }
        this.typeList = this.getTypeService().getTypeformations();
        
          //construction de la liste des formations police
        this.police=this.getListeValeurService().getListevaleursByTypeFormation(1);
        
        //construction de la liste des formations hygiène
        this.hygiene=this.getListeValeurService().getListevaleursByTypeFormation(3);
        
        //construction de la liste des formations informatique
        this.informatique=this.getListeValeurService().getListevaleursByTypeFormation(14);
        
        //construction de la liste des formations examens
        this.examens=this.getListeValeurService().getListevaleursByTypeFormation(5);
        
        //construction de la liste des formations concours
        this.concours=this.getListeValeurService().getListevaleursByTypeFormation(4);
        
        //construction de la liste des formations prepa examens
        this.prepaexamens=this.getListeValeurService().getListevaleursByTypeFormation(17);
        
        //construction de la liste des formations prepa concours
        this.prepaconcours=this.getListeValeurService().getListevaleursByTypeFormation(16);
              
        if(this.serviceDem.getNom()!=null) {
            this.welcome=false;
        }
    }
    
     /**
     * Add Agents
     *
     * @return String - Response Message
     */
    public String addAgent() {
        try {
            Agents agent = new Agents();
            agent.setId(getId());
            agent.setNom(getNom().toUpperCase());
            agent.setPrenom(getPrenom().toUpperCase());
           // agent.setServices(getServices());
            getAgentsService().addAgents(agent);
            agentsList.add(agent);
            return SUCCESS;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }   
 
        return ERROR;
    }
    
    /**
     * Add Agents
     *
     * @return String - Response Message
     */
    public void ajouterAgent() {
        try {
            Agents agent = new Agents();
            agent.setNom(this.agent.getNom().toUpperCase());
            agent.setPrenom(this.agent.getPrenom().toUpperCase());
            agent.setServices(this.serviceDem);
            getAgentsService().addAgents(agent);          
        } catch (DataAccessException e) {
            e.printStackTrace();
        }   
    }
 
    /**
     * Reset Fields
     *
     */
    public void reset() {
        this.setId(0);
        this.setPrenom("");
        this.setNom("");
        this.setServices(new Services());
    }
    
    /**
     * Permet la sauvegarde du service et d'un nouveau demandeur
     */
    public void sauveService(ActionEvent actionEvent) {
        this.serviceDem.setNom(getNomDem().toUpperCase());
        this.serviceDem.setPrenom(getPrenomDem().toUpperCase());
        this.serviceDem.setProjet("Merci de saisir ici votre projet de service et cliquer sur la disquette à droite pour l'enregistrer.".toUpperCase());   //on le met a blanc pour pouvoir sauver au depart
        this.getServicesService().addServices(this.serviceDem);
    }
 
    /**
     * Get Agents List
     *
     * @return List - Agents List
     */
    public List<Agents> getAgentsList() {
        if(this.serviceDem != null && this.serviceDem.getId()!=null && this.serviceDem.getId()!=0) {
            this.agentsList.clear();
            this.agentsList=agentsService.getAgentsByService(this.serviceDem.getId());
        }

        return agentsList;
    }
    
    /**
     * Pour ajouter une formation police
     */
    public void ajouterPolice() {
        Formations formation = new Formations();
              
        Typeformations type = new Typeformations();
        type.setId(1);
        
        Listevaleurs listeVal = new Listevaleurs();
        listeVal.setId(Integer.parseInt(selectedFormation));
       
        formation.setAgents(this.selectedAgent);
        formation.setListevaleurs(listeVal);
        formation.setTypeformations(type);
        formation.setOrdre(0);
        this.getFormationsService().addFormations(formation);
    }
    
    /**
     * Pour ajouter une formation alphabetisation
     */
    public void ajouterAlpha() {
        Formations formation = new Formations();
              
        Typeformations type = new Typeformations();
        type.setId(7);
       
        formation.setAgents(this.selectedAgent);
        formation.setTypeformations(type);
        formation.setOrdre(0);
        this.getFormationsService().addFormations(formation);
    }
    
    /**
     * Pour ajouter une formation hygiene
     */
    public void ajouterHygiene() {
        Formations formation = new Formations();
              
        Typeformations type = new Typeformations();
        type.setId(3);
        
        Listevaleurs listeVal = new Listevaleurs();
        listeVal.setId(Integer.parseInt(selectedFormation));
       
        formation.setAgents(this.selectedAgent);
        formation.setDebutant(Boolean.toString(this.debutant).substring(0, 1));
        formation.setReconversion(Boolean.toString(this.reconversion).substring(0, 1));
        formation.setDif(Boolean.toString(this.dif).substring(0, 1));
        formation.setProjet(Boolean.toString(this.projetX).substring(0, 1));
        formation.setListevaleurs(listeVal);
        formation.setTypeformations(type);
        formation.setOrdre(0);
        this.getFormationsService().addFormations(formation);
    }
    
    /**
     * Pour ajouter une formation informatique
     */
    public void ajouterInfo() {
        Formations formation = new Formations();
              
        Typeformations type = new Typeformations();
        type.setId(14);
        
        Listevaleurs listeVal = new Listevaleurs();
        listeVal.setId(Integer.parseInt(selectedFormation));
       
        formation.setAgents(this.selectedAgent);
        formation.setDif(Boolean.toString(this.dif).substring(0, 1));
        formation.setProjet(Boolean.toString(this.projetX).substring(0, 1));
        formation.setListevaleurs(listeVal);
        formation.setTypeformations(type);
        formation.setOrdre(0);
        this.getFormationsService().addFormations(formation);
    }
    
    /**
     * Pour ajouter un examen
     */
    public void ajouterExam() {
        Formations formation = new Formations();
              
        Typeformations type = new Typeformations();
        type.setId(5);
        
        Listevaleurs listeVal = new Listevaleurs();
        listeVal.setId(Integer.parseInt(selectedFormation));
       
        formation.setAgents(this.selectedAgent);
        formation.setDif(Boolean.toString(this.dif).substring(0, 1));
        formation.setProjet(Boolean.toString(this.projetX).substring(0, 1));
        formation.setListevaleurs(listeVal);
        formation.setTypeformations(type);
        formation.setOrdre(0);
        this.getFormationsService().addFormations(formation);
    }
    
     /**
     * Pour ajouter un concours
     */
    public void ajouterConcours() {
        Formations formation = new Formations();
              
        Typeformations type = new Typeformations();
        type.setId(4);
        
        Listevaleurs listeVal = new Listevaleurs();
        listeVal.setId(Integer.parseInt(selectedFormation));
       
        formation.setAgents(this.selectedAgent);
        formation.setDif(Boolean.toString(this.dif).substring(0, 1));
        formation.setProjet(Boolean.toString(this.projetX).substring(0, 1));
        formation.setListevaleurs(listeVal);
        formation.setTypeformations(type);
        formation.setOrdre(0);
        this.getFormationsService().addFormations(formation);
    }
    
    /**
     * Pour ajouter une formation diplomante
     */
    public void ajouterDiplome() {
        Formations formation = new Formations();
              
        Typeformations type = new Typeformations();
        type.setId(11);
        
        formation.setAgents(this.selectedAgent);
        formation.setProjet(Boolean.toString(this.projetX).substring(0, 1));
        formation.setOrganisme(this.organisme.toUpperCase());
        formation.setIntitule(this.intitule.toUpperCase());
        formation.setCout(this.cout);
        formation.setTypeformations(type);
        formation.setOrdre(0);
        this.getFormationsService().addFormations(formation);
    }
    
    /**
     * Pour ajouter une VAE
     */
    public void ajouterVAE() {
        Formations formation = new Formations();
              
        Typeformations type = new Typeformations();
        type.setId(12);
        
        formation.setAgents(this.selectedAgent);
        formation.setProjet(Boolean.toString(this.projetX).substring(0, 1));
        formation.setOrganisme(this.organisme.toUpperCase());
        formation.setIntitule(this.intitule.toUpperCase());
        formation.setCout(this.cout);
        formation.setTypeformations(type);
        formation.setOrdre(0);
        this.getFormationsService().addFormations(formation);
    }
    
    /**
     * Pour ajouter une formation logiciel
     */
    public void ajouterLogi() {
        Formations formation = new Formations();
              
        Typeformations type = new Typeformations();
        type.setId(9);
        
        formation.setAgents(this.selectedAgent);
        formation.setDif(Boolean.toString(this.dif).substring(0, 1));
        formation.setProjet(Boolean.toString(this.projetX).substring(0, 1));
        formation.setOrganisme(this.organisme.toUpperCase());
        formation.setIntitule(this.intitule.toUpperCase());
        formation.setCout(this.cout);
        formation.setTypeformations(type);
        formation.setOrdre(0);
        this.getFormationsService().addFormations(formation);
    }
    
     /**
     * Pour ajouter une formation remise a niveau français et maths
     */
    public void ajouterFranc() {
        Formations formation = new Formations();
              
        Typeformations type = new Typeformations();
        type.setId(6);
       
        formation.setAgents(this.selectedAgent);
        formation.setTypeformations(type);
        formation.setOrdre(0);
        this.getFormationsService().addFormations(formation);
    }
    
    /**
     * Pour ajouter une formation d'un organisme privé
     */
    public void ajouterOrga() {
        Formations formation = new Formations();
              
        Typeformations type = new Typeformations();
        type.setId(10);
        
        formation.setAgents(this.selectedAgent);
        formation.setDif(Boolean.toString(this.dif).substring(0, 1));
        formation.setProjet(Boolean.toString(this.projetX).substring(0, 1));
        formation.setOrganisme(this.organisme.toUpperCase());
        formation.setIntitule(this.intitule.toUpperCase());
        formation.setCout(this.cout);
        formation.setTypeformations(type);
        formation.setOrdre(0);
        this.getFormationsService().addFormations(formation);
    }
    
    /**
     * Pour ajouter une formation CNFPT
     */
    public void ajouterCNFPT() {
        Formations formation = new Formations();
              
        Typeformations type = new Typeformations();
        type.setId(2);
        
        formation.setAgents(this.selectedAgent);
        formation.setDif(Boolean.toString(this.dif).substring(0, 1));
        formation.setProjet(Boolean.toString(this.projetX).substring(0, 1));
        formation.setPayant(Boolean.toString(this.payant).substring(0, 1));
        formation.setIntitule(this.intitule.toUpperCase());
        formation.setTypeformations(type);
        formation.setOrdre(0);
        this.getFormationsService().addFormations(formation);
    }
    
    /**
     * Pour ajouter une prepa examen
     */
    public void ajouterPrepaExam() {
        Formations formation = new Formations();
              
        Typeformations type = new Typeformations();
        type.setId(17);
        
        Listevaleurs listeVal = new Listevaleurs();
        listeVal.setId(Integer.parseInt(selectedFormation));
       
        formation.setAgents(this.selectedAgent);
        formation.setDif(Boolean.toString(this.dif).substring(0, 1));
        formation.setProjet(Boolean.toString(this.projetX).substring(0, 1));
        formation.setListevaleurs(listeVal);
        formation.setTypeformations(type);
        formation.setOrdre(0);
        this.getFormationsService().addFormations(formation);
    }
    
     /**
     * Pour ajouter une prepa concours
     */
    public void ajouterPrepaConcours() {
        Formations formation = new Formations();
              
        Typeformations type = new Typeformations();
        type.setId(16);
        
        Listevaleurs listeVal = new Listevaleurs();
        listeVal.setId(Integer.parseInt(selectedFormation));
       
        formation.setAgents(this.selectedAgent);
        formation.setDif(Boolean.toString(this.dif).substring(0, 1));
        formation.setProjet(Boolean.toString(this.projetX).substring(0, 1));
        formation.setListevaleurs(listeVal);
        formation.setTypeformations(type);
        formation.setOrdre(0);
        this.getFormationsService().addFormations(formation);
    }
    
    /**
     * Pour supprimer une formation
     */
    public void supprimerFormation(Formations formatio) {
        if(this.selectedAgent==null) {
            this.selectedAgent=new Agents();
        }
        this.getFormationsService().deleteFormations(formatio);
    }
    
    /**
     * Pour supprimer un agent et ses formations
     */
    public void supprimerAgent() {
        if(this.selectedAgent!=null && this.selectedAgent.getId()!=null) {
           this.getAgentsService().deleteAgents(this.selectedAgent);    
        }
        
    }
    
     /**
     * Pour copier un agent et ses formations
     */
    public void copierAgent() {
        List<Formations> sauveForm = new ArrayList();
        String sauveNom = this.selectedAgent.getNom();
        String sauvePrenom = this.selectedAgent.getPrenom();
        
        this.selectedAgent.setId(null);       
        this.selectedAgent.setNom(getNom().toUpperCase());
        this.selectedAgent.setPrenom(getPrenom().toUpperCase());
        sauveForm.addAll(this.selectedAgent.getFormationses());
        
        this.selectedAgent.getFormationses().clear();
        this.getAgentsService().addAgents(this.selectedAgent);
        
        for (Formations form : sauveForm) {
            form.setAgents(this.selectedAgent);
            this.getFormationsService().addFormations(form);
        }
        this.selectedAgent.setNom(sauveNom);
        this.selectedAgent.setPrenom(sauvePrenom);
        this.selectedAgent.getFormationses().addAll(sauveForm);
        setNom(null);
        setPrenom(null);
    }
    
    /**
     * Pour gérer le bouton de sauvegarde du projet
     */
    public void sauverProjet() {
        if(this.serviceDem!=null) {
            this.getServicesService().updateServices(this.serviceDem);
        }
    }
    
 
    /**
     * Get Agents Service
     *
     * @return IAgentsService - Agents Service
     */
    public IAgentsService getAgentsService() {
        return agentsService;
    }
 
    /**
     * Set Agents Service
     *
     * @param IAgentsService - Agents Service
     */
    public void setAgentsService(IAgentsService agentsService) {
        this.agentsService = agentsService;
    }
 
    /**
     * Set Agents List
     *
     * @param List - Agents List
     */
    public void setAgentsList(List<Agents> agentsList) {
        this.agentsList = agentsList;
    }
 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }
    
    public String getNomDem() {
        return nomDem;
    }

    public void setNomDem(String nomDem) {
        this.nomDem = nomDem;
    }

    public String getPrenomDem() {
        return prenomDem;
    }

    public void setPrenomDem(String prenomDem) {
        this.prenomDem = prenomDem;
    }
    
     public IServicesService getServicesService() {
        return servicesService;
    }

    public void setServicesService(IServicesService servicesService) {
        this.servicesService = servicesService;
    }
    
    public boolean isWelcome() {
        return welcome;
    }

    public void setWelcome(boolean welcome) {
        this.welcome = welcome;
    }
    
    public Services getServiceDem() {
        return serviceDem;
    }

    public void setServiceDem(Services serviceDem) {
        this.serviceDem = serviceDem;
    }
    
    public List<Typeformations> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Typeformations> typeList) {
        this.typeList = typeList;
    }
    
    public ITypeformationsService getTypeService() {
        return typeService;
    }

    public void setTypeService(ITypeformationsService typeService) {
        this.typeService = typeService;
    }
    
    public Agents getAgent() {
        return agent;
    }

    public void setAgent(Agents agent) {
        this.agent = agent;
    }
    
    public IListevaleursService getListeValeurService() {
        return listeValeurService;
    }

    public void setListeValeurService(IListevaleursService listeValeurService) {
        this.listeValeurService = listeValeurService;
    }
    
    public List<Listevaleurs> getPolice() {
        return police;
    }

    public void setPolice(List<Listevaleurs> police) {
        this.police = police;
    }
    
    public IFormationsService getFormationsService() {
        return formationsService;
    }

    public void setFormationsService(IFormationsService formationsService) {
        this.formationsService = formationsService;
    }
    
    public Agents getSelectedAgent() {
        return selectedAgent;
    }

    public void setSelectedAgent(Agents selectedAgent) {
        this.selectedAgent = selectedAgent;
    }
    
    public String getSelectedFormation() {
        return selectedFormation;
    }

    public void setSelectedFormation(String selectedFormation) {
        this.selectedFormation = selectedFormation;
    }
    
    public List<Listevaleurs> getHygiene() {
        return hygiene;
    }

    public void setHygiene(List<Listevaleurs> hygiene) {
        this.hygiene = hygiene;
    }
    
    public boolean isDebutant() {
        return debutant;
    }

    public void setDebutant(boolean debutant) {
        this.debutant = debutant;
    }

    public boolean isReconversion() {
        return reconversion;
    }

    public void setReconversion(boolean reconversion) {
        this.reconversion = reconversion;
    }

    public boolean isDif() {
        return dif;
    }

    public void setDif(boolean dif) {
        this.dif = dif;
    }

    public boolean isProjetX() {
        return projetX;
    }

    public void setProjetX(boolean projetX) {
        this.projetX = projetX;
    }
    
    public boolean isInitiation() {
        return initiation;
    }

    public void setInitiation(boolean initiation) {
        this.initiation = initiation;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getOrganisme() {
        return organisme;
    }

    public void setOrganisme(String organisme) {
        this.organisme = organisme;
    }
    
    public List<Listevaleurs> getInformatique() {
        return informatique;
    }

    public void setInformatique(List<Listevaleurs> informatique) {
        this.informatique = informatique;
    }
    
    public List<Listevaleurs> getExamens() {
        return examens;
    }

    public void setExamens(List<Listevaleurs> examens) {
        this.examens = examens;
    }
    
    public List<Listevaleurs> getConcours() {
        return concours;
    }

    public void setConcours(List<Listevaleurs> concours) {
        this.concours = concours;
    }
    
    public boolean isPayant() {
        return payant;
    }

    public void setPayant(boolean payant) {
        this.payant = payant;
    }

    public Integer getCout() {
        return cout;
    }

    public void setCout(Integer cout) {
        this.cout = cout;
    }
    
    public List<Listevaleurs> getPrepaexamens() {
        return prepaexamens;
    }

    public void setPrepaexamens(List<Listevaleurs> prepaexamens) {
        this.prepaexamens = prepaexamens;
    }

    public List<Listevaleurs> getPrepaconcours() {
        return prepaconcours;
    }

    public void setPrepaconcours(List<Listevaleurs> prepaconcours) {
        this.prepaconcours = prepaconcours;
    }
    
    public String getSelectedFormat() {
        return selectedFormat;
    }

    public void setSelectedFormat(String selectedFormat) {
        this.selectedFormat = selectedFormat;
    }
   //    <!--  <p:commandButton title="Sauver le projet de service" icon="ui-icon-disk" actionListener="#{agentsMB.sauverProjet}" />  --> 
}