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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Student
 */
@Entity
@Table(name = "PRZEDMIOT_KLAS")
@NamedQueries({
    @NamedQuery(name = "PrzedmiotKlas.findAll", query = "SELECT p FROM PrzedmiotKlas p")})
public class PrzedmiotKlas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "przedmiotKlas")
    private Collection<Sprawdzian> sprawdzianCollection;
    @JoinColumn(name = "ID_KLASA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Klasa klasa;
    @JoinColumn(name = "ID_NAUCZYCIEL", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Nauczyciel nauczyciel;
    @JoinColumn(name = "ID_PRZEDMIOT", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Przedmiot przedmiot;

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

    public Collection<Sprawdzian> getSprawdzianCollection() {
        return sprawdzianCollection;
    }

    public void setSprawdzianCollection(Collection<Sprawdzian> sprawdzianCollection) {
        this.sprawdzianCollection = sprawdzianCollection;
    }

    public Klasa getKlasa() {
        return klasa;
    }

    public void setKlasa(Klasa klasa) {
        this.klasa = klasa;
    }

    public Nauczyciel getNauczyciel() {
        return nauczyciel;
    }

    public void setNauczyciel(Nauczyciel nauczyciel) {
        this.nauczyciel = nauczyciel;
    }

    public Przedmiot getPrzedmiot() {
        return przedmiot;
    }

    public void setPrzedmiot(Przedmiot przedmiot) {
        this.przedmiot = przedmiot;
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
