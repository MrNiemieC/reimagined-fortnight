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
@Table(name = "UCZEN")
@NamedQueries({
    @NamedQuery(name = "Uczen.findAll", query = "SELECT u FROM Uczen u")})
public class Uczen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 40)
    @Column(name = "IMIE")
    private String imie;
    @Size(max = 40)
    @Column(name = "NAZWISKO")
    private String nazwisko;
    @JoinColumn(name = "ID_KLASA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Klasa klasa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uczen")
    private Collection<Ocena> ocenaCollection;
    @OneToMany(mappedBy = "uczen")
    private Collection<Users> usersCollection;
            
    public String wyswietl()
    {
        return imie+" "+nazwisko;
    }

    public Uczen() {
    }
    
    public Uczen(String imie,String nazwisko,Klasa klasa) {
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.klasa=klasa;
    }

    public Uczen(Integer id) {
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

    public Klasa getKlasa() {
        return klasa;
    }

    public void setKlasa(Klasa klasa) {
        this.klasa = klasa;
    }

    public Collection<Ocena> getOcenaCollection() {
        return ocenaCollection;
    }

    public void setOcenaCollection(Collection<Ocena> ocenaCollection) {
        this.ocenaCollection = ocenaCollection;
    }

    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
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
        if (!(object instanceof Uczen)) {
            return false;
        }
        Uczen other = (Uczen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Uczen[ id=" + id + " ]";
    }
    
}
