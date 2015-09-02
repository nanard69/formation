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


@Entity
@Table(name="typeformations"
    ,catalog="formations"
    , uniqueConstraints = @UniqueConstraint(columnNames="libelle") 
)
public class Typeformations  implements java.io.Serializable {


     private Integer id;
     private String libelle;
     private Set<Formations> formationses = new HashSet<Formations>(0);
     private Set<Listevaleurs> listevaleurses = new HashSet<Listevaleurs>(0);

    public Typeformations() {
    }

	
    public Typeformations(String libelle) {
        this.libelle = libelle;
    }
    public Typeformations(String libelle, Set<Formations> formationses, Set<Listevaleurs> listevaleurses) {
       this.libelle = libelle;
       this.formationses = formationses;
       this.listevaleurses = listevaleurses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="libelle", unique=true, nullable=false, length=250)
    public String getLibelle() {
        return this.libelle;
    }
    
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="typeformations")
    public Set<Formations> getFormationses() {
        return this.formationses;
    }
    
    public void setFormationses(Set<Formations> formationses) {
        this.formationses = formationses;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="typeformations")
    public Set<Listevaleurs> getListevaleurses() {
        return this.listevaleurses;
    }
    
    public void setListevaleurses(Set<Listevaleurs> listevaleurses) {
        this.listevaleurses = listevaleurses;
    }




}


