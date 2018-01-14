/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author niemi
 */
@Entity
@Table(name = "KLASA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Klasa.findAll", query = "SELECT k FROM Klasa k")
    , @NamedQuery(name = "Klasa.findById", query = "SELECT k FROM Klasa k WHERE k.id = :id")
    , @NamedQuery(name = "Klasa.findByNazwa", query = "SELECT k FROM Klasa k WHERE k.nazwa = :nazwa")})
public class Klasa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 40)
    @Column(name = "NAZWA")
    private String nazwa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idKlasa")
    private Collection<PrzedmiotKlas> przedmiotKlasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idKlasa")
    private Collection<Uczen> uczenCollection;
    @JoinColumn(name = "ID_NAUCZYCIEL", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Nauczyciel idNauczyciel;

    public Klasa() {
    }

    public Klasa(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @XmlTransient
    public Collection<PrzedmiotKlas> getPrzedmiotKlasCollection() {
        return przedmiotKlasCollection;
    }

    public void setPrzedmiotKlasCollection(Collection<PrzedmiotKlas> przedmiotKlasCollection) {
        this.przedmiotKlasCollection = przedmiotKlasCollection;
    }

    @XmlTransient
    public Collection<Uczen> getUczenCollection() {
        return uczenCollection;
    }

    public void setUczenCollection(Collection<Uczen> uczenCollection) {
        this.uczenCollection = uczenCollection;
    }

    public Nauczyciel getIdNauczyciel() {
        return idNauczyciel;
    }

    public void setIdNauczyciel(Nauczyciel idNauczyciel) {
        this.idNauczyciel = idNauczyciel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Klasa)) {
            return false;
        }
        Klasa other = (Klasa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Klasa[ id=" + id + " ]";
    }
    
}
