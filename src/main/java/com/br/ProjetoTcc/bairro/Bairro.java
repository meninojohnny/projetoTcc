/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.ProjetoTcc.bairro;

import com.br.GeoRegulariza.Generic.GenericEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Bairro extends GenericEntity {
    
    @Id
    @SequenceGenerator(sequenceName = "seq_bairro", name = "seq_bairro", allocationSize = 50000)
    @GeneratedValue(generator = "seq_bairro", strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String nome;
    
}
