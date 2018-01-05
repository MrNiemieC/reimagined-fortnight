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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Student
 */
@Entity
@Table(name = "NAUCZYCIEL")
@NamedQueries({
    @NamedQuery(name = "Nauczyciel.findAll", query = "SELECT n FROM Nauczyciel n")})
public class Nauczyciel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 40)
    @Column(name = "IMIE")
    private String imie;
    @Size(max = 40)
    @Column(name = "NAZWISKO")
    private String nazwisko;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nauczyciel")
    private Collection<PrzedmiotKlas> przedmiotKlasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nauczyciel")
    private Collection<Klasa> klasaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nauczyciel")
    private Collection<Przydzialy> przydzialyCollection;

    public Nauczyciel() {
    }

    public Nauczyciel(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Collection<PrzedmiotKlas> getPrzedmiotKlasCollection() {
        return przedmiotKlasCollection;
    }

    public void setPrzedmiotKlasCollection(Collection<PrzedmiotKlas> przedmiotKlasCollection) {
        this.przedmiotKlasCollection = przedmiotKlasCollection;
    }

    public Collection<Klasa> getKlasaCollection() {
        return klasaCollection;
    }

    public void setKlasaCollection(Collection<Klasa> klasaCollection) {
        this.klasaCollection = klasaCollection;
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
        if (!(object instanceof Nauczyciel)) {
            return false;
        }
        Nauczyciel other = (Nauczyciel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Nauczyciel[ id=" + id + " ]";
    }
    
}