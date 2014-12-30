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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author sgl
 */
@Entity
@Table(name = "DETAIL_PRODUIT")
@NamedQueries({
    @NamedQuery(name = "DetailProduit.findAll", query = "SELECT d FROM DetailProduit d"),
    @NamedQuery(name = "DetailProduit.findById", query = "SELECT d FROM DetailProduit d WHERE d.id = :id")})
public class DetailProduit implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Lob
    @Column(name = "IMAGE")
    private String image;

    @JoinColumn(name = "ID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Produit produit;

    public DetailProduit() {
    }

    public DetailProduit(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
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
        if (!(object instanceof DetailProduit)) {
            return false;
        }
        DetailProduit other = (DetailProduit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.sebglon.modele.DetailProduit[ id=" + id + " ]";
    }
    
}
