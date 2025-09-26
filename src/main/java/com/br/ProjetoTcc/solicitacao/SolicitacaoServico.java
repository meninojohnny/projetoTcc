/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.ProjetoTcc.solicitacao;

import com.br.GeoRegulariza.Generic.GenericService;
import com.br.GeoRegulariza.utilitarie.Utils;
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
    
    public List<Solicitacao> findSolicitacao(Solicitacao solicitacao) {
        
        String sql = "select s from Solicitacao s "
                + "left join s.cidadao c "
                + "where s.active = true";
        
        if (Utils.isNotEmpty(solicitacao.getNumeroProtocolo())) {
            sql += " and s.numeroProtocolo = :numeroProtocolo";
        }
        
        if (Utils.isNotEmpty(solicitacao.getTipoProblema())) {
            sql += " and s.tipoProblema = :tipoProblema";
        }
        
        if (Utils.isNotEmpty(solicitacao.getCidadao().getId())) {
            sql += " and c.id = :cidadaoId";
        }
        
        if (Utils.isNotEmpty(solicitacao.getCidadao().getCpf())) {
            sql += " and c.cpf = :cpf";
        }
        
        if (Utils.isNotEmpty(solicitacao.getCidadao().getNome())) {
            sql += " and upper(c.nome) like upper(:nome)";
        }
        
        Query query = getEntityManager().createQuery(sql);
        
        if (Utils.isNotEmpty(solicitacao.getNumeroProtocolo())) {
            query.setParameter("numeroProtocolo", solicitacao.getNumeroProtocolo());
        }
        
         if (Utils.isNotEmpty(solicitacao.getTipoProblema())) {
            query.setParameter("tipoProblema", solicitacao.getTipoProblema());
        }
         
        if (Utils.isNotEmpty(solicitacao.getCidadao().getId())) {
           query.setParameter("cidadaoId", solicitacao.getCidadao().getId());
        }
        
        if (Utils.isNotEmpty(solicitacao.getCidadao().getNome())) {
           query.setParameter("nome", "%" + solicitacao.getCidadao().getNome() + "%");
        }
        
        if (Utils.isNotEmpty(solicitacao.getCidadao().getCpf())) {
           query.setParameter("cpf", solicitacao.getCidadao().getCpf());
        }
        
        
        return query.getResultList();
    }
    
}
