/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sebglon.modele;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author sgl
 */
@Entity
@Table(name = "PRODUIT", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"LIBELLE"})})
@NamedQueries({
    @NamedQuery(name = Produit.Q_FINDALL, query = "SELECT p FROM Produit p"),
    @NamedQuery(name = "Produit.findById", query = "SELECT p FROM Produit p WHERE p.id = :id"),
    @NamedQuery(name = "Produit.findByLibelle", query = "SELECT p FROM Produit p WHERE p.libelle = :libelle"),
    @NamedQuery(name = "Produit.findByCategorie", query = "SELECT p FROM Produit p WHERE p.categorie = :categorie"),
    @NamedQuery(name = "Produit.findByDateCreation", query = "SELECT p FROM Produit p WHERE p.dateCreation = :dateCreation"),
    @NamedQuery(name = "Produit.findByMaj", query = "SELECT p FROM Produit p WHERE p.maj = :maj")})
public class Produit implements Serializable {

    
    private static final long serialVersionUID = 1L;
    public static final String Q_FINDALL = "Produit.findAll";
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "LIBELLE", nullable = false, length = 255)
    private String libelle;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "CATEGORIE", nullable = false, length = 255)
    private CategorieProduit categorie;

    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_CREATION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar dateCreation;

    @Column(name = "MAJ")
    @Temporal(TemporalType.TIME)
    private Calendar maj;

    @NotNull
    @JoinColumn(name = "ID_FOURNISSEUR", referencedColumnName = "ID", nullable = false)
    @ManyToOne
    private Fournisseur fournisseur;
    
    @Transient
    private Integer age;

    
    @PrePersist
    private void udateDateCreation() {
        dateCreation = Calendar.getInstance();
        validate();
    }
    
    @PreUpdate
    private void updateMaj() {
        maj = Calendar.getInstance();
        validate();
    }
    

    private void validate() {
        if (maj !=null && dateCreation.getTime().after(maj.getTime()) ) {
            throw new IllegalArgumentException("La date de MAJ ne peut être intéfieur à la date de création");
        }
    }
    
    @PostLoad
    @PostPersist
    @PostUpdate
    public void calculAge() {
        if (dateCreation==null) {
            age = 0;
        } else {   
            age = (Calendar.getInstance().getTime().compareTo(dateCreation.getTime())) / (1000 * 60 * 60 * 24);
        }
    }
    
    protected Produit() {
    }

    public Produit(String libelle, CategorieProduit categorie, Calendar dateCreation) {
        this.libelle = libelle;
        this.categorie = categorie;
        this.dateCreation = dateCreation;
    }

    public Integer getId() {
        return id;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public CategorieProduit getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieProduit categorie) {
        this.categorie = categorie;
    }

    public Calendar getDateCreation() {
        return dateCreation;
    }

    protected void setDateCreation(Calendar dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Calendar getMaj() {
        return maj;
    }

    public void setMaj(Calendar maj) {
        this.maj = maj;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.sebglon.modele.Produit[ id=" + id + " ]";
    }

}
