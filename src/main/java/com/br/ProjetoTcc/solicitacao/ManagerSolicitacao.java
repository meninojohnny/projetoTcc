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
import com.br.ProjetoTcc.cidadao.Cidadao;
import com.br.ProjetoTcc.cidadao.CidadaoServico;
import com.br.ProjetoTcc.grupo.Grupo;
import com.br.ProjetoTcc.historico.HistoricoServico;
import com.br.ProjetoTcc.usuario.Usuario;
import com.br.ProjetoTcc.usuario.UsuarioServico;
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

    @EJB
    private CidadaoServico cidadaoServico;

    @EJB
    private UsuarioServico usuarioServico;

    private Solicitacao solicitacao;

    private List<Solicitacao> listSolicitacao;

    private List<Historico> listHistorico;

    private Usuario usuarioLogado;

    @Override
    public void load(String param) {
        instanciarUsuario();
        this.solicitacao = this.solicitacaoServico.find(Long.valueOf(param));
    }

    @Override
    public void instantiate() {
        instanciarUsuario();
        instanciarSolicitacao();
    }

    public void instanciarSolicitacao() {
        this.solicitacao = new Solicitacao();
        this.solicitacao.setHistoricos(new ArrayList());
        this.solicitacao.setCidadao(new Cidadao());
        this.listSolicitacao = new ArrayList<>();
    }

    public void instanciarUsuario() {
        this.usuarioLogado = this.usuarioServico.getCurrentUser();
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
            this.solicitacao.getHistoricos().add(historico);

            if (Utils.isNotEmpty(this.usuarioLogado.getIsCidadao()) && this.usuarioLogado.getIsCidadao()) {
                this.solicitacao.setCidadao(this.usuarioLogado.getCidadao());
            }

            this.solicitacaoServico.save(solicitacao);
        }

        Msg.messagemInfoRedirect(Msg.SuccessFull, getUrlView());
    }

    public void pesquisar() {
        if (Utils.isNotEmpty(this.usuarioLogado.getIsCidadao()) && this.usuarioLogado.getIsCidadao()) {
            this.solicitacao.getCidadao().setId(this.usuarioLogado.getCidadao().getId());
        }
        
        this.listSolicitacao = this.solicitacaoServico.findSolicitacao(this.solicitacao);
    }

    public String gerarNumeroProtocolo() {
        Random random = new Random();
        return String.valueOf(10000000 + random.nextInt(90000000));
    }

    public String dataFormatada(Date date) {
        return DateUtil.formatarData(date);
    }

    public String backgroundStatus(StatusSolicitacao status) {
        switch (status) {
            case PENDENTE:
                return "#ffb700";
            case EM_ANDAMENTO:
                return "#1e96fc";
            case CONCLUIDO:
                return "#16db65";
            default:
                break;
        }
        return "";
    }

    public boolean renderedUserGestorAdm() {
        for (Grupo g : this.usuarioLogado.getGrupos()) {
            if (g.getNome().equals("gestor") || g.getNome().equals("administrador")) {
                return true;
            }
        }
        return false;
    }

    public List<Cidadao> selectCidadao() {
        return this.cidadaoServico.findCidadao(new Cidadao());
    }

    public void adicionarCidadao(Cidadao cidadao) {
        this.solicitacao.setCidadao(cidadao);
    }
    
    public String nomeCidadao(Solicitacao solicitacao) {
        if (Utils.isNotEmpty(solicitacao.getCidadao())) {
            return solicitacao.getCidadao().getNome();
        } else {
            return "";
        }
    }

}
