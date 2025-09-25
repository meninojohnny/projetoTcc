/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.ProjetoTcc.usuario;

import com.br.GeoRegulariza.Generic.GenericService;
import com.br.GeoRegulariza.utilitarie.Utils;
import jakarta.ejb.Stateless;
import jakarta.faces.context.FacesContext;
import jakarta.persistence.Query;
import java.security.Principal;
import java.util.List;

/**
 *
 * @author johnny
 */

@Stateless
public class UsuarioServico extends GenericService<Usuario> {
    
    public UsuarioServico() {
        super(Usuario.class);
    }
    
    public Usuario getCurrentUser() {
        if (Utils.isNotEmpty(FacesContext.getCurrentInstance())) {
            final Principal userPrincipal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (userPrincipal != null) {
                return verifySystemUserForLogin(userPrincipal.getName());
            }
        }
        return null;
    }
    
    public Usuario verifySystemUserForLogin(String nome) {
        if (nome == null) {
            return null;
        }
        List<Usuario> usuarios;
        try {
            final String sql = "select u from Usuario u where u.login like :nome and u.active = true";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("nome", nome);
            usuarios = query.getResultList();

            if (usuarios.isEmpty()) {
                return null;
            }
            return usuarios.get(0);

        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }
    
    public List<Usuario> findGestor(Usuario gestor) {
        String sql = "select u from Usuario u where u.active = true ";
        
        Query query = getEntityManager().createQuery(sql);
        
        return query.getResultList();
    }
    
}
