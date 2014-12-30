/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sebglon.modele;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

/**
 *
 * @author sgl
 */
public class DebugListener {
    
    @PrePersist
    void prePersit(Object o) {
        System.out.println("perPersist");
    }
    @PostPersist
    void postPersit(Object o) {
        System.out.println("postPersist");
    }
}
