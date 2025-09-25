/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.ProjetoTcc.solicitacao;

import com.br.GeoRegulariza.Generic.GenericService;
import com.br.ProjetoTcc.cidadao.Cidadao;
import jakarta.ejb.Stateless;
import jakarta.persistence.Query;
import java.util.List;

/**
 *
 * @author johnny
 */

@Stateless
public class SolicitacaoServico extends GenericService<Solicitacao> {
    
    public SolicitacaoServico() {
        super(Solicitacao.class);
    }
    
    public List<Solicitacao> findByCidadao(Cidadao cidadao ) {
        String sql = "select s from Solicitacao s where s.active = true and s.cidadao = :cidadao ";
        
        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("cidadao", cidadao);
        
        return query.getResultList();
    }
    
}
