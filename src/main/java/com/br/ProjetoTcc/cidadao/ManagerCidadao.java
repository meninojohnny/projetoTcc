/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.ProjetoTcc.cidadao;

import com.br.GeoRegulariza.Generic.GenericManager;
import com.br.GeoRegulariza.utilitarie.Msg;
import com.br.GeoRegulariza.utilitarie.Utils;
import com.br.ProjetoTcc.usuario.Usuario;
import com.br.ProjetoTcc.bairro.Bairro;
import com.br.ProjetoTcc.endereco.Endereco;
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
    
    private Cidadao cidadao;
    
    private Usuario usuario;
    
    private List<Solicitacao> listSolicitacao = new ArrayList<>();

    @Override
    public void load(String param) {
        this.cidadao = this.cidadaoServico.find(Long.valueOf(param));
    }
    
    public void salvar() {
        if (Utils.isNotEmpty(this.cidadao.getId())) {
            this.cidadaoServico.update(cidadao);
        } else {
            
            usuario.setLogin(cidadao.getCpf());
            this.usuarioServico.save(usuario);
            
            this.cidadaoServico.save(cidadao);
        }
        
        Msg.messagemInfoRedirect(Msg.SuccessFull, getUrlView());
    }

    @Override
    public void instantiate() {
        instanciarCidadao();
        instnaciarUsuario();
    }
    
    public void instanciarCidadao() {
        this.cidadao = new Cidadao();
        this.cidadao.setEndereco(new Endereco());
        this.cidadao.getEndereco().setBairro(new Bairro());
    }
    
    public void instnaciarUsuario() {
        this.usuario = new Usuario();
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
    
}
