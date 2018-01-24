/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
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

/**
 *
 * @author niemi
 */
@Entity
@Table(name = "KLASA")
@NamedQueries({
    @NamedQuery(name = "Klasa.findAll", query = "SELECT k FROM Klasa k")})
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "klasa")
    private Collection<PrzedmiotKlas> przedmiotKlasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "klasa")
    private Collection<Uczen> uczenCollection;
    @JoinColumn(name = "ID_NAUCZYCIEL", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Nauczyciel nauczyciel;

    public Klasa() {
    }
    public Klasa(String nazwa,Nauczyciel nauczyciel)
    {
        this.nazwa = nazwa;
        this.nauczyciel = nauczyciel;
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

    public Collection<PrzedmiotKlas> getPrzedmiotKlasCollection() {
        return przedmiotKlasCollection;
    }

    public void setPrzedmiotKlasCollection(Collection<PrzedmiotKlas> przedmiotKlasCollection) {
        this.przedmiotKlasCollection = przedmiotKlasCollection;
    }

    public Collection<Uczen> getUczenCollection() {
        return uczenCollection;
    }

    public void setUczenCollection(Collection<Uczen> uczenCollection) {
        this.uczenCollection = uczenCollection;
    }

    public Nauczyciel getNauczyciel() {
        return nauczyciel;
    }

    public void setNauczyciel(Nauczyciel nauczyciel) {
        this.nauczyciel = nauczyciel;
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
        return "Entities.Klasa[ id=" + id + " ]";
    }
    
}
