/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.br.GeoRegulariza.enums;

import lombok.Getter;

/**
 *
 * @author johnny
 */

@Getter
public enum StatusSolicitacao {
    
    PENDENTE("Pendente"),
    EM_ANDAMENTO("Em Andamento"),
    CONCLUIDO("Concluido");
    
    private final String nome; 
    
    StatusSolicitacao(String nome) {
        this.nome = nome;
    }
    
}
