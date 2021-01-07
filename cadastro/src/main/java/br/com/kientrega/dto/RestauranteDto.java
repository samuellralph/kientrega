package br.com.kientrega.dto;

import br.com.kientrega.entidades.Localizacao;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

public class RestauranteDto {

    public String proprietario;
    public Long cnpj;
    public String nome;
    public Localizacao localizacao;

}
