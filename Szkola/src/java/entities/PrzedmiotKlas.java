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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author niemi
 */
@Entity
@Table(name = "PRZEDMIOT_KLAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrzedmiotKlas.findAll", query = "SELECT p FROM PrzedmiotKlas p")
    , @NamedQuery(name = "PrzedmiotKlas.findById", query = "SELECT p FROM PrzedmiotKlas p WHERE p.id = :id")})
public class PrzedmiotKlas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPrzedmiotuKlasa")
    private Collection<Sprawdzian> sprawdzianCollection;
    @JoinColumn(name = "ID_KLASA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Klasa idKlasa;
    @JoinColumn(name = "ID_NAUCZYCIEL", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Nauczyciel idNauczyciel;
    @JoinColumn(name = "ID_PRZEDMIOT", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Przedmiot idPrzedmiot;

    public PrzedmiotKlas() {
    }

    public PrzedmiotKlas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public Collection<Sprawdzian> getSprawdzianCollection() {
        return sprawdzianCollection;
    }

    public void setSprawdzianCollection(Collection<Sprawdzian> sprawdzianCollection) {
        this.sprawdzianCollection = sprawdzianCollection;
    }

    public Klasa getIdKlasa() {
        return idKlasa;
    }

    public void setIdKlasa(Klasa idKlasa) {
        this.idKlasa = idKlasa;
    }

    public Nauczyciel getIdNauczyciel() {
        return idNauczyciel;
    }

    public void setIdNauczyciel(Nauczyciel idNauczyciel) {
        this.idNauczyciel = idNauczyciel;
    }

    public Przedmiot getIdPrzedmiot() {
        return idPrzedmiot;
    }

    public void setIdPrzedmiot(Przedmiot idPrzedmiot) {
        this.idPrzedmiot = idPrzedmiot;
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
        if (!(object instanceof PrzedmiotKlas)) {
            return false;
        }
        PrzedmiotKlas other = (PrzedmiotKlas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PrzedmiotKlas[ id=" + id + " ]";
    }
    
}
