/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.ProjetoTcc.solicitacao;

import com.br.GeoRegulariza.Generic.GenericEntity;
import com.br.GeoRegulariza.enums.StatusSolicitacao;
import com.br.GeoRegulariza.enums.TipoProblema;
import com.br.ProjetoTcc.Historico;
import com.br.ProjetoTcc.cidadao.Cidadao;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Solicitacao extends GenericEntity {
    
    @Id
    @SequenceGenerator(sequenceName = "seq_solicitacao", name = "seq_solicitacao", allocationSize = 1)
    @GeneratedValue(generator = "seq_solicitacao", strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String numeroPoste;
    
    private String descricao;
    
    private String numeroProtocolo;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Historico> historicos;
    
    @OneToOne
    private Cidadao cidadao;
    
    @Enumerated(EnumType.STRING)
    private TipoProblema tipoProblema;
    
    @Enumerated(EnumType.STRING)
    private StatusSolicitacao statusSolicitacao;
    
}
