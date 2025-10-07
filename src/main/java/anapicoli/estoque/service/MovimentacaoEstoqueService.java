package anapicoli.estoque.service;

import anapicoli.estoque.model.MovimentacaoEstoque;
import anapicoli.estoque.model.Produto;
import anapicoli.estoque.repository.MovimentacaoEstoqueRepository;
import anapicoli.estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class MovimentacaoEstoqueService {

    @Autowired
    private MovimentacaoEstoqueRepository movimentacaoRepo;

    @Autowired
    private ProdutoRepository produtoRepo;

    public MovimentacaoEstoque registrarMovimentacao(MovimentacaoEstoque mov) {
        Produto produto = produtoRepo.findById(mov.getProduto().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        if (mov.getTipo() == MovimentacaoEstoque.TipoMovimentacao.ENTRADA) {
            produto.setQuantidade(produto.getQuantidade() + mov.getQuantidade());
        } else if (mov.getTipo() == MovimentacaoEstoque.TipoMovimentacao.SAIDA) {
            if (produto.getQuantidade() < mov.getQuantidade()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Estoque insuficiente");
            }
            produto.setQuantidade(produto.getQuantidade() - mov.getQuantidade());
        }

        mov.setData(LocalDateTime.now());
        produtoRepo.save(produto);
        return movimentacaoRepo.save(mov);
    }
}