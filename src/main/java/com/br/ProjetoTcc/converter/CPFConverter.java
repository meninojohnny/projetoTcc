/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.ProjetoTcc.converter;

import com.br.GeoRegulariza.utilitarie.Characters;
import com.br.GeoRegulariza.utilitarie.Utils;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author johnny
 */

@FacesConverter(value = "cpfConverter")
public class CPFConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        if (Utils.isNotEmpty(value)) {
            return Characters.removecaracter(value);
        }
        
        return null;
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        if (Utils.isNotEmpty(value)) {
            return Characters.addMask((String) value, Characters.cpfMask);
        }
        
        return null;
        
    }
    
}

