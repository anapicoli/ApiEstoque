package anapicoli.estoque.controller;

import anapicoli.estoque.model.Produto;
import anapicoli.estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepo;

    @GetMapping
    public Page<Produto> listar(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return produtoRepo.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepo.findById(id);
        return produto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody Produto produto) {
        Produto salvo = produtoRepo.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto novo) {
        return produtoRepo.findById(id)
                .map(p -> {
                    p.setNome(novo.getNome());
                    p.setDescricao(novo.getDescricao());
                    p.setPreco(novo.getPreco());
                    p.setQuantidade(novo.getQuantidade());
                    p.setCategoria(novo.getCategoria());
                    p.setFornecedor(novo.getFornecedor());
                    return ResponseEntity.ok(produtoRepo.save(p));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!produtoRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produtoRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}