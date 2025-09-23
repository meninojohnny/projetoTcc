/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.GeoRegulariza.Generic;

import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author johnny
 */

public abstract class GenericManager implements Serializable {

    private String view;
    private String edit;

    @PostConstruct
    public void init() {
        loadParameters();
        if (isView()) {
            load(this.view);
            return;
        }

        if (isEdit()) {
            load(this.edit);
            return;
        }
        if (isCreate()) {
            instantiate();
        }
    }

    public abstract void load(String param);

    public abstract void instantiate();

    public String getTitle() {
        if (isEdit()) {
            return "Editar";
        }

        if (isCreate()) {
            return "Cadastrar";
        }

        if (isView()) {
            return "Visualizar";
        }

        return "";
    }

    public String getIcon() {
        if (isEdit()) {
            return "pi pi-pencil";
        }

        if (isCreate()) {
            return "pi pi-plus";
        }

        if (isView()) {
            return "pi pi-eye";
        }

        return "";
    }

    public boolean isRenderActionButtons() {
        return isView() || isEdit();
    }

    public String getUrlAfterCancel() {
        if (isCreate()) {
            return "index.xhtml";
        } else if (isView()) {
            return getUrlSearch();
        } else if (isEdit()) {
            return getUrlView();
        } else {
            return "index.xhtml";
        }
    }

    public abstract String getUrlSearch();

    public abstract String getUrlView();

    private void loadParameters() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.view = params.get("view");
        this.edit = params.get("edit");
    }

    public boolean isView() {
        return this.view != null && !this.view.isEmpty();
    }

    public boolean isEdit() {
        return this.edit != null && !this.edit.isEmpty();
    }

    public boolean isCreate() {
        return !this.isView() && !this.isEdit();
    }
}
