package br.com.redis.pocredis.repository;

import br.com.redis.pocredis.domain.ClientCacheRedis;
import org.springframework.data.repository.CrudRepository;

public interface ClientRedisRepository extends CrudRepository<ClientCacheRedis, Long> {
}
