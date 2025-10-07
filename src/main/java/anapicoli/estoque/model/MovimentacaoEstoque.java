package anapicoli.estoque.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MovimentacaoEstoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipo;

    private Integer quantidade;
    private LocalDateTime data;
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    public enum TipoMovimentacao {
        ENTRADA, SAIDA
    }
}