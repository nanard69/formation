package fr.villemelun.formations.models;
// Generated 19 ao�t 2013 15:41:18 by Hibernate Tools 3.2.1.GA


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Formations generated by hbm2java
 */
@Entity
@Table(name="formations"
    ,catalog="formations"
)
public class Formations  implements java.io.Serializable {


     private Integer id;
     private Typeformations typeformations;
     private Listevaleurs listevaleurs;
     private Agents agents;
     private String intitule;
     private String organisme;
     private Integer cout;
     private String projet;
     private String dif;
     private String debutant;
     private String reconversion;
     private String initiation;
     private String payant;
     private String ren;

    public Formations() {
    }

	
    public Formations(Typeformations typeformations, Agents agents) {
        this.typeformations = typeformations;
        this.agents = agents;
    }
    public Formations(Typeformations typeformations, Listevaleurs listevaleurs, Agents agents, String intitule, String organisme, Integer cout, String projet, String dif, String debutant, String reconversion, String initiation, String perfectionnement, String ren) {
       this.typeformations = typeformations;
       this.listevaleurs = listevaleurs;
       this.agents = agents;
       this.intitule = intitule;
       this.organisme = organisme;
       this.cout = cout;
       this.projet = projet;
       this.dif = dif;
       this.debutant = debutant;
       this.reconversion = reconversion;
       this.initiation = initiation;
       this.payant = perfectionnement;
       this.ren = ren;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="typeformationsId", nullable=false)
    public Typeformations getTypeformations() {
        return this.typeformations;
    }
    
    public void setTypeformations(Typeformations typeformations) {
        this.typeformations = typeformations;
    }
@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="listevaleursId")
    public Listevaleurs getListevaleurs() {
        return this.listevaleurs;
    }
    
    public void setListevaleurs(Listevaleurs listevaleurs) {
        this.listevaleurs = listevaleurs;
    }
@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="agentId", nullable=false)
    public Agents getAgents() {
        return this.agents;
    }
    
    public void setAgents(Agents agents) {
        this.agents = agents;
    }
    
    @Column(name="intitule")
    public String getIntitule() {
        return this.intitule;
    }
    
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
    
    @Column(name="organisme")
    public String getOrganisme() {
        return this.organisme;
    }
    
    public void setOrganisme(String organisme) {
        this.organisme = organisme;
    }
    
    @Column(name="cout")
    public Integer getCout() {
        return this.cout;
    }
    
    public void setCout(Integer cout) {
        this.cout = cout;
    }
    
    @Column(name="projet", length=1)
    public String getProjet() {
        return this.projet;
    }
    
    public void setProjet(String projet) {
        this.projet = projet;
    }
    
    @Column(name="dif", length=1)
    public String getDif() {
        return this.dif;
    }
    
    public void setDif(String dif) {
        this.dif = dif;
    }
    
    @Column(name="debutant", length=1)
    public String getDebutant() {
        return this.debutant;
    }
    
    public void setDebutant(String debutant) {
        this.debutant = debutant;
    }
    
    @Column(name="reconversion", length=1)
    public String getReconversion() {
        return this.reconversion;
    }
    
    public void setReconversion(String reconversion) {
        this.reconversion = reconversion;
    }
    
    @Column(name="initiation", length=1)
    public String getInitiation() {
        return this.initiation;
    }
    
    public void setInitiation(String initiation) {
        this.initiation = initiation;
    }
    
    @Column(name="payant", length=1)
    public String getPayant() {
        return this.payant;
    }
    
    public void setPayant(String payant) {
        this.payant = payant;
    }
    
    @Column(name="ren", length=1)
    public String getRen() {
        return this.ren;
    }
    
    public void setRen(String ren) {
        this.ren = ren;
    }




}


