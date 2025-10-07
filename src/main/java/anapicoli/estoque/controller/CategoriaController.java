package anapicoli.estoque.controller;

import anapicoli.estoque.model.Categoria;
import anapicoli.estoque.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepo;

    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {
        return ResponseEntity.ok(categoriaRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepo.findById(id);
        return categoria.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Categoria> criar(@RequestBody Categoria categoria) {
        Categoria salva = categoriaRepo.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @RequestBody Categoria nova) {
        return categoriaRepo.findById(id)
                .map(cat -> {
                    cat.setNome(nova.getNome());
                    return ResponseEntity.ok(categoriaRepo.save(cat));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!categoriaRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoriaRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}