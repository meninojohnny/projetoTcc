/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.ProjetoTcc.solicitacao;

import com.br.GeoRegulariza.Generic.GenericManager;
import com.br.GeoRegulariza.enums.StatusSolicitacao;
import com.br.GeoRegulariza.enums.TipoProblema;
import com.br.GeoRegulariza.utilitarie.DateUtil;
import com.br.GeoRegulariza.utilitarie.Msg;
import com.br.GeoRegulariza.utilitarie.Utils;
import com.br.ProjetoTcc.Historico;
import com.br.ProjetoTcc.historico.HistoricoServico;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author johnny
 */

@Getter
@Setter
@Named
@ViewScoped
public class ManagerSolicitacao extends GenericManager {
    
    @EJB
    private SolicitacaoServico solicitacaoServico;
    
    @EJB
    private HistoricoServico historicoServico;
    
    private Solicitacao solicitacao;
    
    private List<Solicitacao> listSolicitacao;
    
    private List<Historico> listHistorico;

    @Override
    public void load(String param) {
        this.solicitacao = this.solicitacaoServico.find(Long.valueOf(param));
    }

    @Override
    public void instantiate() {
        instanciarSolicitacao();
    }
    
    public void instanciarSolicitacao() {
        this.solicitacao = new Solicitacao();
        this.solicitacao.setListHistorico(new ArrayList());
        this.listSolicitacao = new ArrayList<>();
    }

    @Override
    public String getUrlSearch() {
        return ""; 
    }

    @Override
    public String getUrlView() {
        return "solicitacao.xhtml?view=" + solicitacao.getId();
    }
    
    public void salvar() {
        if (Utils.isNotEmpty(solicitacao.getId())) {
            this.solicitacaoServico.update(solicitacao);
        } else {
            
            Historico historico = new Historico();
            historico.setDescricao("Registro da solicitação");
            historico.setStatusSolicitacao(StatusSolicitacao.PENDENTE);
            
            this.solicitacao.setNumeroProtocolo(gerarNumeroProtocolo());
            this.solicitacao.setStatusSolicitacao(StatusSolicitacao.PENDENTE);
            this.solicitacao.getListHistorico().add(historico);
            
            this.solicitacaoServico.save(solicitacao);
        }
        
        Msg.messagemInfoRedirect(Msg.SuccessFull, getUrlView());
    }
    
    public void pesquisar() {
        this.listSolicitacao = this.solicitacaoServico.findAll();
    }
    
    public String gerarNumeroProtocolo() {
        Random random = new Random();
        return String.valueOf(10000000 + random.nextInt(90000000));
    }
    
    public String dataFormatada(Date date) {
        return DateUtil.formatarData(date);
    }
    
}
