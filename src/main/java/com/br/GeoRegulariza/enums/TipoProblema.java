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
public enum TipoProblema {
    
    LAMPADA_QUEIMADA("Lâmpada Queimada"),
    LAMPADA_PISCANDO("Lâmpada Piscando"),
    POSTE_APAGADO("Poste totamlmente apagado"),
    OUTRO("Outro");
    
    private final String name;
    
    TipoProblema(String name) {
        this.name = name;
    }
    
}
