package br.com.redis.pocredis.service;

import br.com.redis.pocredis.domain.Client;
import br.com.redis.pocredis.domain.ClientCacheRedis;
import br.com.redis.pocredis.repository.ClientRedisRepository;
import br.com.redis.pocredis.repository.ClientRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class ClientServiceImpl {

    private ClientRepository clientRepository;
    private ClientRedisRepository clientRedisRepository;

    ClientServiceImpl(ClientRepository clientRepository, ClientRedisRepository clientRedisRepository){
        this.clientRepository = clientRepository;
        this.clientRedisRepository = clientRedisRepository;
    }

    public void save(Client client){
        clientRepository.save(client);
    }

    public void delete(Client client){
        clientRepository.delete(client);
    }

    @Cacheable(value = "cleint")
    public Client findById(Long id) throws Exception {
        return clientRepository.findById(id).orElseThrow(() -> new Exception("Não existe registros"));
    }

    public Client findByIDRepository(Long id) throws Exception {
        Optional<ClientCacheRedis> clientRedis = clientRedisRepository.findById(id);
        if(clientRedis.isEmpty()){
            Client client = clientRepository.findById(id).orElseThrow(() -> new Exception("Não existe registros"));
            clientRedisRepository.save(getClientRedis(client));
            return client;
        }
        return new Client(clientRedis.get().getId(), clientRedis.get().getNome(), clientRedis.get().getEmpresa());
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public List findAllRedis(){
        return StreamSupport.stream(clientRedisRepository.findAll().spliterator(), false)
                .filter(elemento -> elemento != null)
                .collect(Collectors.toList());

    }

    private static ClientCacheRedis getClientRedis(Client client) {
        return new ClientCacheRedis(client.getId(), client.getNome(), client.getEmpresa());
    }
}
