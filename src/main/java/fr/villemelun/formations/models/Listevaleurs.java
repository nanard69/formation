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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="listevaleurs"
    ,catalog="formations"
)
public class Listevaleurs  implements java.io.Serializable {


     private Integer id;
     private Typeformations typeformations;
     private String libelle;
     private Set<Formations> formationses = new HashSet<Formations>(0);

    public Listevaleurs() {
    }

	
    public Listevaleurs(Typeformations typeformations, String libelle) {
        this.typeformations = typeformations;
        this.libelle = libelle;
    }
    public Listevaleurs(Typeformations typeformations, String libelle, Set<Formations> formationses) {
       this.typeformations = typeformations;
       this.libelle = libelle;
       this.formationses = formationses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="type_formation_id", nullable=false)
    public Typeformations getTypeformations() {
        return this.typeformations;
    }
    
    public void setTypeformations(Typeformations typeformations) {
        this.typeformations = typeformations;
    }
    
    @Column(name="libelle", nullable=false, length=250)
    public String getLibelle() {
        return this.libelle;
    }
    
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="listevaleurs")
    public Set<Formations> getFormationses() {
        return this.formationses;
    }
    
    public void setFormationses(Set<Formations> formationses) {
        this.formationses = formationses;
    }




}


