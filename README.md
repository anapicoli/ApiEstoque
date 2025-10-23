# 🧭 API de Gerenciamento de Estoque

Este projeto é uma **API RESTful** desenvolvida com **Spring Boot** para gerenciar o estoque de uma loja de eletrônicos, permitindo controlar produtos, categorias, fornecedores e movimentações de estoque (entradas e saídas).

---

## 🚀 Objetivo

Facilitar o controle interno de estoque da loja, oferecendo endpoints para:

* Cadastrar, atualizar, listar e deletar **produtos, categorias e fornecedores**;
* Registrar **movimentações de estoque** (entrada e saída);
* Consultar a quantidade disponível de cada produto.

A API responde em **JSON** e utiliza códigos HTTP adequados para cada operação.

---

## 🛠️ Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot**
* **Spring Web**
* **Spring Data JPA**
* **MySQL (via XAMPP)**
* **Lombok**
* **Spring Validation**
* **Postman** (para testes)

---

## 📂 Estrutura do Projeto

```
src
 └── main
     ├── java
     │    └── anapicoli.estoque
     │         ├── EstoqueApplication.java              # Classe principal
     │         ├── controller
     │         │     ├── CategoriaController.java       # Endpoints de categorias
     │         │     ├── FornecedorController.java      # Endpoints de fornecedores
     │         │     ├── ProdutoController.java         # Endpoints de produtos
     │         │     └── MovimentacaoController.java    # Endpoints de movimentações de estoque
     │         ├── model
     │         │     ├── Categoria.java                 # Modelo Categoria
     │         │     ├── Fornecedor.java               # Modelo Fornecedor
     │         │     ├── Produto.java                  # Modelo Produto
     │         │     └── MovimentacaoEstoque.java      # Modelo Movimentação de Estoque
     │         ├── repository
     │         │     ├── CategoriaRepository.java
     │         │     ├── FornecedorRepository.java
     │         │     ├── ProdutoRepository.java
     │         │     └── MovimentacaoEstoqueRepository.java
     │         └── service
     │               └── MovimentacaoEstoqueService.java # Regras de negócio de entradas/saídas
     └── resources
          └── application.properties                     # Configurações do banco e JPA
```

---

## 🔍 Endpoints

### 🔹 Categorias

| Método | Endpoint             | Descrição                     |
| ------ | -------------------- | ----------------------------- |
| GET    | /api/categorias      | Listar todas as categorias    |
| GET    | /api/categorias/{id} | Buscar categoria por ID       |
| POST   | /api/categorias      | Criar nova categoria          |
| PUT    | /api/categorias/{id} | Atualizar categoria existente |
| DELETE | /api/categorias/{id} | Deletar categoria             |

### 🔹 Fornecedores

| Método | Endpoint               | Descrição                      |
| ------ | ---------------------- | ------------------------------ |
| GET    | /api/fornecedores      | Listar todos os fornecedores   |
| GET    | /api/fornecedores/{id} | Buscar fornecedor por ID       |
| POST   | /api/fornecedores      | Criar novo fornecedor          |
| PUT    | /api/fornecedores/{id} | Atualizar fornecedor existente |
| DELETE | /api/fornecedores/{id} | Deletar fornecedor             |

### 🔹 Produtos

| Método | Endpoint           | Descrição                                   |
| ------ | ------------------ | ------------------------------------------- |
| GET    | /api/produtos      | Listar produtos (com paginação e ordenação) |
| GET    | /api/produtos/{id} | Buscar produto por ID                       |
| POST   | /api/produtos      | Criar novo produto                          |
| PUT    | /api/produtos/{id} | Atualizar produto existente                 |
| DELETE | /api/produtos/{id} | Deletar produto                             |

### 🔹 Movimentações de Estoque

| Método | Endpoint                          | Descrição                             |
| ------ | --------------------------------- | ------------------------------------- |
| GET    | /api/movimentacoes?produtoId={id} | Listar movimentações de um produto    |
| POST   | /api/movimentacoes                | Registrar entrada ou saída de estoque |

---

## 🧪 Testando com Postman

1. Execute o projeto:

```bash
mvn spring-boot:run
```

2. Abra o **Postman**.
3. Crie uma **Collection** chamada `Estoque API`.
4. Configure as requisições conforme os endpoints acima, utilizando **JSON** no corpo (`Body → raw → JSON`) e o header:

```
Content-Type: application/json
```

5. Exemplos de uso:

* Criar categoria:

```json
{
  "nome": "Notebooks"
}
```

* Criar fornecedor:

```json
{
  "nome": "Tech Distribuidora",
  "cnpj": "12.345.678/0001-99",
  "telefone": "(11) 99999-8888",
  "email": "contato@techdistribuidora.com"
}
```

* Criar produto:

```json
{
  "nome": "Notebook Lenovo",
  "descricao": "Intel i5, 8GB RAM, 256GB SSD",
  "preco": 3999.90,
  "quantidade": 10,
  "categoria": { "id": 1 },
  "fornecedor": { "id": 1 }
}
```

* Registrar entrada de estoque:

```json
{
  "tipo": "ENTRADA",
  "quantidade": 5,
  "observacao": "Chegada de novos notebooks",
  "produto": { "id": 1 }
}
```

* Registrar saída de estoque:

```json
{
  "tipo": "SAIDA",
  "quantidade": 3,
  "observacao": "Venda de 3 notebooks",
  "produto": { "id": 1 }
}
```

---

## 💡 Funcionamento Interno

* **Controller:** recebe as requisições HTTP e retorna JSON.
* **Service:** contém regras de negócio (por exemplo, validar estoque suficiente antes de registrar saída).
* **Repository:** abstrai o acesso ao banco MySQL via JPA.
* **Model:** representa as entidades do sistema (Produto, Categoria, Fornecedor, MovimentacaoEstoque).

> Ao registrar uma entrada, a quantidade do produto aumenta.
> Ao registrar uma saída, o sistema verifica se há estoque suficiente; caso contrário, retorna **HTTP 400**.

---

## 🧾 Casos de Teste

1. Criar produto válido → HTTP 201 Created
2. Atualizar produto existente → HTTP 200 OK
3. Excluir produto inexistente → HTTP 404 Not Found
4. Registrar entrada → estoque aumenta
5. Registrar saída com estoque suficiente → estoque diminui
6. Registrar saída com estoque insuficiente → HTTP 400 Bad Request
7. Validar campos obrigatórios → HTTP 400 Bad Request

---

## 🧑‍💻 Autor

**Ana Picoli**
