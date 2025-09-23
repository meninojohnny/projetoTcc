/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.ProjetoTcc;

import com.br.GeoRegulariza.Generic.GenericEntity;
import com.br.GeoRegulariza.enums.StatusSolicitacao;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Historico extends GenericEntity {
    
    @Id
    @SequenceGenerator(name = "seq_historico", sequenceName = "seq_historico", allocationSize = 1)
    @GeneratedValue(generator = "seq_historico", strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String descricao;
    
    @Enumerated(EnumType.STRING)
    private StatusSolicitacao statusSolicitacao;
    
}
