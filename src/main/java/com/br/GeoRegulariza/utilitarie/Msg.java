/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.GeoRegulariza.utilitarie;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.PrimeFaces;

/**
 *
 * @author johnny
 */

public class Msg {
    
    public static final String SuccessFull = "Operação Realizada com Sucesso!";
    public static final String Failure = "Erro ao Realizar a Operação!";
    public static final String PermissaoNegada = "Você não tem permissão para executar esta operação!";

    public static void messagemInfo(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
    }

    public static void messagemHtml(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, ""));
        PrimeFaces.current().executeScript("PF('msgDialog').show();");
    }
    
    public static void messagemError(String message) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));
        } catch (Exception e) {
        }
    }

    public static void messagemWarn(String message) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, ""));
        } catch (Exception e) {
        }
    }

    public static void messagemInfoRedirect(String message, String url) {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
            fc.getExternalContext().getFlash().setKeepMessages(true);
            try {
                context.redirect(url);
            } catch (IOException ex) {
                Logger.getLogger(Msg.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
        }
    }
    public static void redirect( String url) {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getExternalContext().getFlash().setKeepMessages(true);
            try {
                context.redirect(url);
            } catch (IOException ex) {
                Logger.getLogger(Msg.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
        }
    }
    
    public static void messagemErrorRedirect(String message, String url) {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));
            fc.getExternalContext().getFlash().setKeepMessages(true);
            try {
                context.redirect(url);
            } catch (IOException ex) {
                Logger.getLogger(Msg.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
        }
    }

    public static void messagem(String operação_realizada_com_sucesso_, String orientacaoSexualxhtmlvisualizar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
