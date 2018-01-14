/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author niemi
 */
@Entity
@Table(name = "OCENA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ocena.findAll", query = "SELECT o FROM Ocena o")
    , @NamedQuery(name = "Ocena.findById", query = "SELECT o FROM Ocena o WHERE o.id = :id")
    , @NamedQuery(name = "Ocena.findByWartosc", query = "SELECT o FROM Ocena o WHERE o.wartosc = :wartosc")})
public class Ocena implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "WARTOSC")
    private BigDecimal wartosc;
    @JoinColumn(name = "ID_SPRAWDZIAN", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Sprawdzian idSprawdzian;
    @JoinColumn(name = "ID_UCZEN", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Uczen idUczen;

    public Ocena() {
    }

    public Ocena(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getWartosc() {
        return wartosc;
    }

    public void setWartosc(BigDecimal wartosc) {
        this.wartosc = wartosc;
    }

    public Sprawdzian getIdSprawdzian() {
        return idSprawdzian;
    }

    public void setIdSprawdzian(Sprawdzian idSprawdzian) {
        this.idSprawdzian = idSprawdzian;
    }

    public Uczen getIdUczen() {
        return idUczen;
    }

    public void setIdUczen(Uczen idUczen) {
        this.idUczen = idUczen;
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
        if (!(object instanceof Ocena)) {
            return false;
        }
        Ocena other = (Ocena) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Ocena[ id=" + id + " ]";
    }
    
}
