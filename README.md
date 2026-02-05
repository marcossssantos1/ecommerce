# 🛒 Ecommerce API — Spring Boot

API RESTful de **e-commerce** desenvolvida em **Java + Spring Boot**, com autenticação via **JWT**, persistência com **JPA/Hibernate** e arquitetura organizada em **Controllers, Services, DTOs e Entities**.

Projeto pensado para simular um fluxo real de e-commerce:

* usuário autenticado
* criação de pedidos
* mudança de status do pedido
* geração e processamento de pagamento

---

## 🚀 Tecnologias Utilizadas

* Java 17+
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Security
* JWT (JSON Web Token)
* Hibernate
* Maven
* Banco de dados relacional (MySQL / H2)

---

## 📦 Funcionalidades

✔ Cadastro de usuários com senha criptografada (BCrypt)
✔ Autenticação e autorização via JWT
✔ Endpoints protegidos por token
✔ CRUD de usuários
✔ Criação de pedidos com múltiplos itens
✔ Controle de status do pedido (CRIADO, PENDENTE, CONFIRMADO, CANCELADO)
✔ Criação e processamento de pagamentos
✔ Relacionamentos JPA (OneToMany, OneToOne)
✔ Uso de DTOs para Request e Response
✔ Tratamento global de exceções

---

## 🧱 Estrutura do Projeto

```
src/main/java/com/marcossantos/ecommerce
│
├── controller
├── service
├── repository
├── entity
├── dto
├── enums
├── security
└── exception
```

---

## 🔐 Autenticação (JWT)

### Cadastro de Usuário

```http
POST /usuarios
Content-Type: application/json
```

```json
{
  "nome": "Marcos Santos",
  "email": "marcos@email.com",
  "cpf": "12345678900",
  "senha": "123456",
  "telefone": "11999999999",
  "cep": "01001000",
  "logradouro": "Rua Teste",
  "numero": "100",
  "bairro": "Centro",
  "cidade": "São Paulo",
  "estado": "SP"
}
```

---

### Login

```http
POST /auth/login
Content-Type: application/json
```

```json
{
  "email": "marcos@email.com",
  "senha": "123456"
}
```

Resposta:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsIn...",
  "tipo": "Bearer"
}
```

Utilizar em requisições protegidas:

```
Authorization: Bearer <token>
```

---

## 🛒 Pedidos

### Criar Pedido

```http
POST /pedidos
Authorization: Bearer <token>
Content-Type: application/json
```

```json
{
  "observacao": "Pedido de teste",
  "itens": [
    {
      "nomeProduto": "Notebook",
      "skuProduto": "SKU-001",
      "quantidade": 1,
      "precoUnitario": 3500.00,
      "desconto": 0
    }
  ]
}
```

O pedido é criado com status inicial **CRIADO**.

---

### Fluxo de Status do Pedido

```
CRIADO → PENDENTE → CONFIRMADO → PAGAMENTO
              ↘ CANCELADO
```

---

## 💳 Pagamentos

O pagamento só pode ser criado para pedidos **CONFIRMADOS**.

### Criar Pagamento

```http
POST /pagamentos?pedidoId=1&formaPagamento=PIX
Authorization: Bearer <token>
```

### Processar Pagamento

```http
POST /pagamentos/{id}/processar
Authorization: Bearer <token>
```

### Confirmar Pagamento

```http
POST /pagamentos/{id}/confirmar
Authorization: Bearer <token>
```

---

## 🧾 Entidades Principais

* Usuario
* Pedido
* ItemPedido
* Pagamento

Com relacionamentos:

* Pedido → Usuario (ManyToOne)
* Pedido → ItemPedido (OneToMany)
* Pedido → Pagamento (OneToOne)

---

## ⚠ Tratamento de Erros

A API possui um **ExceptionHandler global**, retornando erros padronizados:

```json
{
  "timestamp": "2026-02-05T12:00:00",
  "status": 404,
  "error": "Recurso não encontrado",
  "message": "Pedido não encontrado",
  "path": "/pedidos/10"
}
```

---

## ▶️ Executar o Projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/marcossssantos1/ecommerce.git
cd ecommerce
```

### 2. Configurar o banco

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=1234
spring.jpa.hibernate.ddl-auto=update
```

### 3. Rodar a aplicação

```bash
mvn spring-boot:run
```

API disponível em:

```
http://localhost:8080
```

---

---

## 👨‍💻 Autor

**Marcos Santos**
Backend Developer — Java & Spring Boot


---

Se você curtiu o projeto, deixa uma ⭐ no repositório 🚀
