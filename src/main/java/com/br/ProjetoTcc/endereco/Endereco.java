/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.ProjetoTcc.endereco;

import com.br.GeoRegulariza.Generic.GenericEntity;
import com.br.ProjetoTcc.bairro.Bairro;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
public class Endereco extends GenericEntity {
    
    @Id
    @SequenceGenerator(name = "seq_endereco", sequenceName = "seq_endereco", allocationSize = 1)
    @GeneratedValue(generator = "seq_endereco", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String cep;

    private String logradouro;

    private String numero;

    private String complemento;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Bairro bairro;
    
}
