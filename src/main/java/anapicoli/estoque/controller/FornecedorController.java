package anapicoli.estoque.controller;

import anapicoli.estoque.model.Fornecedor;
import anapicoli.estoque.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorRepository fornecedorRepo;

    @GetMapping
    public ResponseEntity<List<Fornecedor>> listar() {
        return ResponseEntity.ok(fornecedorRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> buscarPorId(@PathVariable Long id) {
        Optional<Fornecedor> fornecedor = fornecedorRepo.findById(id);
        return fornecedor.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Fornecedor> criar(@RequestBody Fornecedor fornecedor) {
        Fornecedor salvo = fornecedorRepo.save(fornecedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> atualizar(@PathVariable Long id, @RequestBody Fornecedor novo) {
        return fornecedorRepo.findById(id)
                .map(f -> {
                    f.setNome(novo.getNome());
                    f.setCnpj(novo.getCnpj());
                    f.setTelefone(novo.getTelefone());
                    f.setEmail(novo.getEmail());
                    return ResponseEntity.ok(fornecedorRepo.save(f));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!fornecedorRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        fornecedorRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}