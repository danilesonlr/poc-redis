package br.com.redis.pocredis.repository;

import br.com.redis.pocredis.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
