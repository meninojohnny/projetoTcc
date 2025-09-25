/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.ProjetoTcc.grupo;

import com.br.GeoRegulariza.Generic.GenericService;
import com.br.GeoRegulariza.utilitarie.Utils;
import jakarta.ejb.Stateless;
import jakarta.persistence.Query;

/**
 *
 * @author johnny
 */

@Stateless
public class GrupoServico extends GenericService<Grupo> {
    
    public GrupoServico() {
        super(Grupo.class);
    }
    
    public Grupo findByName(String nome) {
        String sql = "select g from Grupo g where g.active = true and g.nome like :nome";
        
        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("nome", nome);
        
        if (Utils.isNotEmpty(query.getResultList())) {
            return (Grupo) query.getSingleResult();
        } else {
            return null;
        }
        
    }
    
}
