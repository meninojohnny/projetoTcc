/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.ProjetoTcc.cidadao;

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
public class CidadaoServico extends GenericService<Cidadao> {

    public CidadaoServico() {
        super(Cidadao.class);
    }

    public List<Cidadao> findCidadao(Cidadao cidadao) {
        String sql = "select c from Cidadao c where c.active = true";

        if (Utils.isNotEmpty(cidadao.getNome())) {
            sql += "and upper(c.nome) like upper(:nome)";
        }

        if (Utils.isNotEmpty(cidadao.getCpf())) {
            sql += "and c.cpf like :cpf";
        }

        Query query = getEntityManager().createQuery(sql);

        if (Utils.isNotEmpty(cidadao.getNome())) {
            query.setParameter("nome", "%" + cidadao.getNome() + "%");
        }
        
        if (Utils.isNotEmpty(cidadao.getCpf())) {
            query.setParameter("cpf", cidadao.getCpf());
        }

        return query.getResultList();
    }

}
