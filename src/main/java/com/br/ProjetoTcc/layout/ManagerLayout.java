/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.ProjetoTcc.layout;

import com.br.GeoRegulariza.Generic.GenericManager;
import com.br.GeoRegulariza.utilitarie.Utils;
import com.br.ProjetoTcc.grupo.Grupo;
import com.br.ProjetoTcc.usuario.Usuario;
import com.br.ProjetoTcc.usuario.UsuarioServico;
import jakarta.ejb.EJB;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
public class ManagerLayout extends GenericManager {

    @EJB
    private UsuarioServico usuarioServico;

    private Usuario usuarioLogado;

    @Override
    public void load(String param) {
        instanciarUsuario();
    }

    @Override
    public void instantiate() {
        instanciarUsuario();
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
        return "";
    }

    public boolean renderedUserGestor() {
        for (Grupo g : this.usuarioLogado.getGrupos()) {
            if (g.getNome().equals("gestor") || g.getNome().equals("administrador")) {
                return true;
            }
        }
        return false;
    }
    
    public boolean renderedUserCidadao() {
        for (Grupo g : this.usuarioLogado.getGrupos()) {
            if (g.getNome().equals("cidadao")) {
                return true;
            }
        }
        return false;
    }
    
    public boolean renderedUserAdmin() {
        for (Grupo g : this.usuarioLogado.getGrupos()) {
            if (g.getNome().equals("administrador")) {
                return true;
            }
        }
        return false;
    }

    public String loginName() {
        if (Utils.isNotEmpty(this.usuarioLogado)) {
            for (Grupo g : this.usuarioLogado.getGrupos()) {
                if (g.getNome().equals("administrador") || g.getNome().equals("gestor")) {
                    return usuarioLogado.getLogin();
                } else if (g.getNome().equals("cidadao")) {
                    if (Utils.isNotEmpty(this.usuarioLogado.getCidadao())) {
                        List<String> list = new ArrayList<>(Arrays.asList(usuarioLogado.getCidadao().getNome().trim().split(" ")));
                        if (Utils.isNotEmpty(list)) {
                            return list.get(0);
                        } else {
                            return this.usuarioLogado.getCidadao().getNome();
                        }
                    }
                }
            }
        }
        return "";
    }
    
    public void logout() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if (session != null) {
            try {
                session.invalidate();
                System.out.println("Sessão do usuário invalidada com sucesso.");
            } catch (IllegalStateException e) {
                System.err.println("Erro ao invalidar a sessão: " + e.getMessage());
            }
        }
        String contextPath = facesContext.getExternalContext().getRequestContextPath();
        facesContext.getExternalContext().redirect(contextPath + "/");
    }

}
