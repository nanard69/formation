package fr.villemelun.formations.models;
// Generated 19 ao�t 2013 15:41:18 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Services generated by hbm2java
 */
@Entity
@Table(name="services"
    ,catalog="formations"
    , uniqueConstraints = @UniqueConstraint(columnNames="libelle") 
)
public class Services  implements java.io.Serializable {


     private Short id;
     private String libelle;
     private String projet;
     private String nom;
     private String prenom;
     private Set<Agents> agentses = new HashSet<Agents>(0);

    public Services() {
    }

	
    public Services(String libelle, String projet, String nom, String prenom) {
        this.libelle = libelle;
        this.projet = projet;
        this.nom = nom;
        this.prenom = prenom;
    }
    public Services(String libelle, String projet, String nom, String prenom, Set<Agents> agentses) {
       this.libelle = libelle;
       this.projet = projet;
       this.nom = nom;
       this.prenom = prenom;
       this.agentses = agentses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id", unique=true, nullable=false)
    public Short getId() {
        return this.id;
    }
    
    public void setId(Short id) {
        this.id = id;
    }
    
    @Column(name="libelle", unique=true, nullable=false)
    public String getLibelle() {
        return this.libelle;
    }
    
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    @Column(name="projet", nullable=false, length=65535)
    public String getProjet() {
        return this.projet;
    }
    
    public void setProjet(String projet) {
        this.projet = projet;
    }
    
    @Column(name="nom", nullable=false, length=128)
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    @Column(name="prenom", nullable=false, length=128)
    public String getPrenom() {
        return this.prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="services")
    public Set<Agents> getAgentses() {
        return this.agentses;
    }
    
    public void setAgentses(Set<Agents> agentses) {
        this.agentses = agentses;
    }




}


