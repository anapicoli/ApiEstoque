package anapicoli.estoque.repository;

import anapicoli.estoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {}