/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.ProjetoTcc.cidadao;

import com.br.ProjetoTcc.grupo.GrupoServico;
import com.br.GeoRegulariza.Generic.GenericManager;
import com.br.GeoRegulariza.utilitarie.Msg;
import com.br.GeoRegulariza.utilitarie.SenhaCripto;
import com.br.GeoRegulariza.utilitarie.Utils;
import com.br.ProjetoTcc.usuario.Usuario;
import com.br.ProjetoTcc.bairro.Bairro;
import com.br.ProjetoTcc.endereco.Endereco;
import com.br.ProjetoTcc.grupo.Grupo;
import com.br.ProjetoTcc.solicitacao.Solicitacao;
import com.br.ProjetoTcc.solicitacao.SolicitacaoServico;
import com.br.ProjetoTcc.usuario.UsuarioServico;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;
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
public class ManagerCidadao extends GenericManager {

    @EJB
    private CidadaoServico cidadaoServico;

    @EJB
    private SolicitacaoServico solicitacaoServico;

    @EJB
    private UsuarioServico usuarioServico;
    
    @EJB
    private GrupoServico grupoServico;

    private Cidadao cidadao;
    
    private List<Cidadao> listCidadao;

    private Usuario usuario;

    private List<Solicitacao> listSolicitacao = new ArrayList<>();

    private String repetirSenha;

    @Override
    public void load(String param) {
        instanciarUsuario();
        this.cidadao = this.cidadaoServico.find(Long.valueOf(param));
        this.listSolicitacao = this.solicitacaoServico.findByCidadao(cidadao);
    }

    public void salvar() {
        if (Utils.isNotEmpty(this.cidadao.getId())) {
            this.cidadaoServico.update(cidadao);
        } else {

            if (this.usuario.getSenha().equals(this.repetirSenha)) {
                usuario.setIsCidadao(true);
                usuario.setLogin(cidadao.getCpf());
                this.usuario.setSenha(SenhaCripto.encryptPassword(this.usuario.getSenha()));

                this.cidadaoServico.save(cidadao);
                
                this.usuario.setCidadao(cidadao);

                this.usuarioServico.save(usuario);

                Msg.messagemInfoRedirect(Msg.SuccessFull, getUrlView());

            } else {
                Msg.messagemInfo("As senhas não coincidem!");
            }

        }

    }

    @Override
    public void instantiate() {
        instanciarCidadao();
        instanciarUsuario();
    }

    public void instanciarCidadao() {
        this.cidadao = new Cidadao();
        this.cidadao.setEndereco(new Endereco());
        this.cidadao.getEndereco().setBairro(new Bairro());
        this.listCidadao = new ArrayList<>();
    }

    public void instanciarUsuario() {
        this.usuario = new Usuario();
        this.usuario.setGrupos(new ArrayList<>());
        this.usuario.getGrupos().add(this.grupoServico.findByName("cidadao"));
    }

    @Override
    public String getUrlSearch() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUrlView() {
        return "cidadao.xhtml?view=" + this.cidadao.getId();
    }

    public void pesquisarCep() {
    }
    
    public void pesquisar() {
        this.listCidadao = this.cidadaoServico.findCidadao(cidadao);
    }
    
    public Integer qtdSolicitacao(Cidadao cidadao) {
        List<Solicitacao> list = this.solicitacaoServico.findByCidadao(cidadao);
        if (Utils.isNotEmpty(list)) {
            return list.size();
        } else {
            return 0;
        }
    }

}
