/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
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
import javax.validation.constraints.Size;

/**
 *
 * @author niemi
 */
@Entity
@Table(name = "USERS")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 100)
    @Column(name = "LOGIN")
    private String login;
    @Size(max = 100)
    @Column(name = "PASSWORD")
    private String password;
    @JoinColumn(name = "ID_DYREKTOR", referencedColumnName = "ID")
    @ManyToOne
    private Dyrektor dyrektor;
    @JoinColumn(name = "ID_NAUCZYCIEL", referencedColumnName = "ID")
    @ManyToOne
    private Nauczyciel nauczyciel;
    @JoinColumn(name = "ID_UCZEN", referencedColumnName = "ID")
    @ManyToOne
    private Uczen uczen;

    public Users() {
    }
    public Users(String login,String password,Dyrektor dyrektor,Nauczyciel nauczyciel,Uczen uczen) {
        this.login=login;
        this.password=password;
        this.dyrektor=dyrektor;
        this.nauczyciel=nauczyciel;
        this.uczen=uczen;
    }

    public Users(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Dyrektor getDyrektor() {
        return dyrektor;
    }

    public void setDyrektor(Dyrektor dyrektor) {
        this.dyrektor = dyrektor;
    }

    public Nauczyciel getNauczyciel() {
        return nauczyciel;
    }

    public void setNauczyciel(Nauczyciel nauczyciel) {
        this.nauczyciel = nauczyciel;
    }

    public Uczen getUczen() {
        return uczen;
    }

    public void setUczen(Uczen uczen) {
        this.uczen = uczen;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Users[ id=" + id + " ]";
    }
    
}
