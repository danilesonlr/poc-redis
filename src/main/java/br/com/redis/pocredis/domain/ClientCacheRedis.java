package br.com.redis.pocredis.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "client-repository", timeToLive = 60)
public class ClientCacheRedis {
    @Indexed
    private long id;
    private String nome;
    private String empresa;
}
