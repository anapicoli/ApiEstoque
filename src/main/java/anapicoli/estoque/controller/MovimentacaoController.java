package anapicoli.estoque.controller;

import anapicoli.estoque.model.MovimentacaoEstoque;
import anapicoli.estoque.repository.MovimentacaoEstoqueRepository;
import anapicoli.estoque.service.MovimentacaoEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/movimentacoes")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoEstoqueService movimentacaoService;

    @Autowired
    private MovimentacaoEstoqueRepository movimentacaoRepo;

    @PostMapping
    public ResponseEntity<MovimentacaoEstoque> registrar(@RequestBody MovimentacaoEstoque mov) {
        MovimentacaoEstoque salva = movimentacaoService.registrarMovimentacao(mov);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @GetMapping
    public ResponseEntity<List<MovimentacaoEstoque>> listarPorProduto(@RequestParam Long produtoId) {
        return ResponseEntity.ok(movimentacaoRepo.findByProdutoId(produtoId));
    }
}