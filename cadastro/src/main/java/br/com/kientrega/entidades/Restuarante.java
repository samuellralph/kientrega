package br.com.kientrega.entidades;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "restaurante")
public class Restuarante  extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String proprietario;
    public Long cnpj;
    public String nome;
    @ManyToOne
    public Localizacao localizacao;
    @OneToMany
    public List<Prato> pratos;
    @UpdateTimestamp
    public Date dataCriacao;
    @UpdateTimestamp
    public Date atualizacao;
}
