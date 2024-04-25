package br.com.redis.pocredis.controller;

import br.com.redis.pocredis.domain.Client;
import br.com.redis.pocredis.service.ClientServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class RedisController {


    private ClientServiceImpl clientService;

    RedisController(ClientServiceImpl clientService){
        this.clientService = clientService;
    }

    @PostMapping("save")
    public ResponseEntity save(@RequestBody Client client){
        clientService.save(client);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(clientService.findById(id));
    }


    @GetMapping
    public ResponseEntity findAll(){
        return ResponseEntity.ok(clientService.findAll());
    }


    @GetMapping("/clean/cache")
    @CacheEvict(cacheNames = "cleint", allEntries = true)
    public ResponseEntity clearCache() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/clean/cache/repositoy")
    @CacheEvict(cacheNames = "client-repository", allEntries = true)
    public ResponseEntity clearRepository() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("find/by/repository/redis/{id}")
    public ResponseEntity findByIdRepository(@PathVariable  Long id) throws Exception {
        return ResponseEntity.ok(clientService.findByIDRepository(id));
    }

    @GetMapping("find/all/repository/redis")
    public ResponseEntity findAllRedis(){
        return ResponseEntity.ok(clientService.findAllRedis());
    }
}
