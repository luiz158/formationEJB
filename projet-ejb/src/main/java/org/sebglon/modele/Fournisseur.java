/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sebglon.modele;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author sgl
 */
@Entity
@Table(name = "FOURNISSEUR", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"LIBELLE"})})
@NamedQueries({
    @NamedQuery(name = "Fournisseur.findAll", query = "SELECT f FROM Fournisseur f"),
    @NamedQuery(name = "Fournisseur.findById", query = "SELECT f FROM Fournisseur f WHERE f.id = :id"),
    @NamedQuery(name = "Fournisseur.findByLibelle", query = "SELECT f FROM Fournisseur f WHERE f.libelle = :libelle")})
public class Fournisseur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "LIBELLE", nullable = false, length = 255)
    private String libelle;
    @Lob
    @Size(max = 32700)
    @Column(name = "ADRESSE", length = 32700)
    private String adresse;

    public Fournisseur() {
    }

    public Fournisseur(Integer id) {
        this.id = id;
    }

    public Fournisseur(Integer id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
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
        if (!(object instanceof Fournisseur)) {
            return false;
        }
        Fournisseur other = (Fournisseur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.sebglon.modele.Fournisseur[ id=" + id + " ]";
    }
    
}
