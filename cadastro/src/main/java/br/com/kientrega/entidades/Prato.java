package br.com.kientrega.entidades;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jdk.jfr.consumer.RecordedStackTrace;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "prato")
public class Prato extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String nome;
    public String descricao;
    @ManyToOne
    public Restuarante restaurante;
    public BigDecimal preco;
}
