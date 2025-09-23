/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.ProjetoTcc.usuario;

import com.br.GeoRegulariza.Generic.GenericService;
import jakarta.ejb.Stateless;

/**
 *
 * @author johnny
 */

@Stateless
public class UsuarioServico extends GenericService<Usuario> {
    
    public UsuarioServico() {
        super(Usuario.class);
    }
    
}
