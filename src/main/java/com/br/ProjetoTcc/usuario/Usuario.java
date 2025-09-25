/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.ProjetoTcc.usuario;

import com.br.ProjetoTcc.cidadao.Cidadao;
import com.br.ProjetoTcc.grupo.Grupo;
import com.br.ProjetoTcc.pessoa.Pessoa;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author johnny
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Usuario extends Pessoa {
    
    private String senha;
    
    private String login;
    
    @OneToOne
    private Cidadao cidadao;
    
    private Boolean isCidadao;
    
    @OneToMany
    private List<Grupo> grupos;
    
}
