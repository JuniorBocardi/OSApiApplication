/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.antonio.OSApiApplication.api.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import local.antonio.OSApiApplication.domain.model.Cliente;
import local.antonio.OSApiApplication.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ClienteController {
   
    @Autowired
    private ClienteRepository clienteRepository;
   
    @PersistenceContext
    private EntityManager manager;
   
    @GetMapping("/clientes")
    public List<Cliente> listas() {

        return manager.createQuery("from Cliente", Cliente.class)
                .getResultList();
    }
   
   
   
    @GetMapping("/clientes/{clienteID}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteID) {
       
        Optional<Cliente> cliente = clienteRepository.findById(clienteID);
       
        if (cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
   
    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
    return clienteRepository.save(cliente);
}
   
    @PutMapping("/clientes/{clienteID}")    
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteID, @RequestBody Cliente cliente) {
       
        if (!clienteRepository.existsById(clienteID)) {
            return ResponseEntity.notFound().build();
        }
       
        cliente.setId(clienteID);
        cliente = clienteRepository.save(cliente);
        return ResponseEntity.ok(cliente);
               
    }
   
    @DeleteMapping("/cliente/{clienteID}")
    public ResponseEntity<Void> excluir(@PathVariable Long clienteID) {
       
        if (!clienteRepository.existsById(clienteID)){
            return ResponseEntity.notFound().build();
        }
       
        clienteRepository.deleteById(clienteID);
        return ResponseEntity.noContent().build();
        }
    }




//VOCE PAROU NA PAGINA 4, NO 7., NAO ESTA FUNCIONANDO VAI TER QUE CHAMAR O KGE


//VOCE PAROU HJ DIA 7 NO 6.1 DA PARTE 4, ACABA ESSA MERDA