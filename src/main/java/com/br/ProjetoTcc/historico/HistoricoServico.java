/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.ProjetoTcc.historico;

import com.br.GeoRegulariza.Generic.GenericService;
import com.br.ProjetoTcc.Historico;
import jakarta.ejb.Stateless;

/**
 *
 * @author johnny
 */

@Stateless
public class HistoricoServico extends GenericService<Historico> {
    
    public HistoricoServico() {
        super(Historico.class);
    }
    
}
