package br.com.redis.pocredis.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client implements Serializable {
    @Id
    private long id;
    @Column
    private String nome;
    @Column
    private String empresa;
}


