/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Student
 */
@Entity
@Table(name = "SPRAWDZIAN")
@NamedQueries({
    @NamedQuery(name = "Sprawdzian.findAll", query = "SELECT s FROM Sprawdzian s")})
public class Sprawdzian implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 40)
    @Column(name = "NAZWA")
    private String nazwa;
    @Column(name = "DATA")
    @Temporal(TemporalType.DATE)
    private Date data;
    @JoinColumn(name = "ID_PRZEDMIOTU_KLASA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PrzedmiotKlas przedmiotKlas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sprawdzian")
    private Collection<Ocena> ocenaCollection;

    public Sprawdzian() {
    }

    public Sprawdzian(Integer id) {
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public PrzedmiotKlas getPrzedmiotKlas() {
        return przedmiotKlas;
    }

    public void setPrzedmiotKlas(PrzedmiotKlas przedmiotKlas) {
        this.przedmiotKlas = przedmiotKlas;
    }

    public Collection<Ocena> getOcenaCollection() {
        return ocenaCollection;
    }

    public void setOcenaCollection(Collection<Ocena> ocenaCollection) {
        this.ocenaCollection = ocenaCollection;
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
        if (!(object instanceof Sprawdzian)) {
            return false;
        }
        Sprawdzian other = (Sprawdzian) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Sprawdzian[ id=" + id + " ]";
    }
    
}
