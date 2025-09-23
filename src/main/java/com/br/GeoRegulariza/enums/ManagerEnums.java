/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.br.GeoRegulariza.enums;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.model.SelectItem;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.Getter;
/**
 *
 * @author johnny
 */

@Getter
@Named
@ApplicationScoped
public class ManagerEnums {
    
    public List<SelectItem> getTipoProblema() {
        List<SelectItem> items = new ArrayList<>();
        for (TipoProblema item : TipoProblema.values()) {
            items.add(new SelectItem(item, item.getName()));
        }
        return items;
    }
    
}
