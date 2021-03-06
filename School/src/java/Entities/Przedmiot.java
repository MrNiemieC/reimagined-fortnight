/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author niemi
 */
@Entity
@Table(name = "PRZEDMIOT")
@NamedQueries({
    @NamedQuery(name = "Przedmiot.findAll", query = "SELECT p FROM Przedmiot p")})
public class Przedmiot implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 40)
    @Column(name = "NAZWA")
    private String nazwa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "przedmiot")
    private Collection<PrzedmiotKlas> przedmiotKlasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "przedmiot")
    private Collection<Przydzialy> przydzialyCollection;

    public Przedmiot() {
    }
    public Przedmiot(String nazwa) {
        this.nazwa=nazwa;
    }

    public Przedmiot(Integer id) {
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

    public Collection<PrzedmiotKlas> getPrzedmiotKlasCollection() {
        return przedmiotKlasCollection;
    }

    public void setPrzedmiotKlasCollection(Collection<PrzedmiotKlas> przedmiotKlasCollection) {
        this.przedmiotKlasCollection = przedmiotKlasCollection;
    }

    public Collection<Przydzialy> getPrzydzialyCollection() {
        return przydzialyCollection;
    }

    public void setPrzydzialyCollection(Collection<Przydzialy> przydzialyCollection) {
        this.przydzialyCollection = przydzialyCollection;
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
        if (!(object instanceof Przedmiot)) {
            return false;
        }
        Przedmiot other = (Przedmiot) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Przedmiot[ id=" + id + " ]";
    }
    
}
