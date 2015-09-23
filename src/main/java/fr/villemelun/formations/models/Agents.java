package fr.villemelun.formations.models;
// Generated 29 juin 2013 22:46:43 by Hibernate Tools 3.2.1.GA


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.faces.model.ListDataModel;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.primefaces.model.SelectableDataModel;
import org.springframework.context.annotation.Lazy;

/**
 * Agents generated by hbm2java
 */
@Entity
@Table(name="agents"
    ,catalog="formations"    
)
public class Agents extends ListDataModel<Agents> implements java.io.Serializable, SelectableDataModel<Agents> {


     private Integer id;
     private Services services;
     private String nom;
     private String prenom;
     private Integer ordre = new Integer(0);
     private Set<Formations> formationses = new TreeSet<Formations>();

    public Agents() {
    }

    public Agents(List<Agents> data) {  
        super(data);  
    } 
	
    public Agents(Services services, String nom, String prenom) {
        this.services = services;
        this.nom = nom;
        this.prenom = prenom;
    }
    public Agents(Services services, String nom, String prenom, Set<Formations> formationses) {
       this.services = services;
       this.nom = nom;
       this.prenom = prenom;
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
@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="serviceId", nullable=false)
    public Services getServices() {
        return this.services;
    }
    
    public void setServices(Services services) {
        this.services = services;
    }
    
    @Column(name="nom", nullable=false, length=128)
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    @Column(name="ordre", nullable=true)
    public Integer getOrdre() {
        return ordre;
    }

    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }
    
    @Column(name="prenom", nullable=false, length=128)
    public String getPrenom() {
        return this.prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="agents")
@OrderBy("id")
    public Set<Formations> getFormationses() {
        return this.formationses;
    }
    
    public void setFormationses(Set<Formations> formationses) {
        this.formationses = formationses;
    }

@Override  
    public Agents getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<Agents> agents = (List<Agents>) getWrappedData();  
          
        for(Agents agent : agents) {  
            if(agent.getId().equals(rowKey))  
                return agent;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Agents agent) {  
        return agent.getId();  
    }
    
    @Transient
    public List<Formations> getFormationList() {
            
        if (this.formationses == null) {
            return null;
        } 
            
        return new ArrayList<>(this.formationses);           
    }
}


