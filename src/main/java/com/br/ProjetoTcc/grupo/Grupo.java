/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.ProjetoTcc.grupo;

import com.br.GeoRegulariza.Generic.GenericEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author johnny
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Grupo extends GenericEntity {
    
    @Id
    @SequenceGenerator(sequenceName = "seq_grupo", name = "seq_grupo", initialValue = 10, allocationSize = 1)
    @GeneratedValue(generator = "seq_grupo", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;
    
}
