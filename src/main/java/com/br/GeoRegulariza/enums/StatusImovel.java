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
public enum StatusImovel {
    
    REGULARIZADO("Regularizado", "#dcfce7", "#166534");
    
    private final String name;
    private final String backgroundColor;
    private final String color;
    
    StatusImovel(String name, String backgroundColor, String color) {
        this.name = name;
        this.backgroundColor = backgroundColor;
        this.color = color;
    }
}
