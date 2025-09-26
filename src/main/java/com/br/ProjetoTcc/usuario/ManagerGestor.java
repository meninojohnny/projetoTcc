/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.ProjetoTcc.usuario;

import com.br.GeoRegulariza.Generic.GenericManager;
import com.br.GeoRegulariza.utilitarie.Msg;
import com.br.GeoRegulariza.utilitarie.SenhaCripto;
import com.br.GeoRegulariza.utilitarie.Utils;
import com.br.ProjetoTcc.grupo.GrupoServico;
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
public class ManagerGestor extends GenericManager {
    
    @EJB
    private UsuarioServico usuarioServico;
    
    @EJB
    private GrupoServico grupoServico;
    
    private Usuario gestor;
    
    private List<Usuario> listGestor;
    
    private String repetirSenha;

    @Override
    public void load(String param) {
        this.gestor = this.usuarioServico.find(Long.valueOf(param));
    }

    @Override
    public void instantiate() {
        instanciarGestor();
    }
    
    public void instanciarGestor() {
        this.gestor = new Usuario();
        this.gestor.setGrupos(new ArrayList<>());
        this.gestor.getGrupos().add(this.grupoServico.findByName("gestor"));
        this.listGestor = new ArrayList<>();
    }

    @Override
    public String getUrlSearch() {
        return "";
    }

    @Override
    public String getUrlView() {
        return "gestor.xhtml?view=" + gestor.getId();
    }
    
    public void salvar() {
        if (Utils.isNotEmpty(this.gestor.getId())) {
            this.usuarioServico.update(gestor);
        } else {

            if (this.gestor.getSenha().equals(this.repetirSenha)) {
                this.gestor.setSenha(SenhaCripto.encryptPassword(this.gestor.getSenha()));
                
                this.usuarioServico.save(gestor);

                Msg.messagemInfoRedirect(Msg.SuccessFull, getUrlView());

            } else {
                Msg.messagemInfo("As senhas não coincidem!");
            }

        }
    }
    
    public void pesquisar() {
        this.listGestor = this.usuarioServico.findGestor(this.gestor);
    }
    
}
