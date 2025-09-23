package com.br.ProjetoTcc.pessoa;


import com.br.GeoRegulariza.Generic.GenericEntity;
import com.br.ProjetoTcc.endereco.Endereco;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author johnny
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa extends GenericEntity {

    @Id
    @SequenceGenerator(sequenceName = "seq_pessoa", name = "seq_pessoa", allocationSize = 1)
    @GeneratedValue(generator = "seq_pessoa", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;
    
    private String cpf;
    
    private String email;

    private String telefone;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;
    
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

}

