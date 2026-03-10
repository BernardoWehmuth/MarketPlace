# 🛒 MarketPlace API

API REST desenvolvida em **Java com Spring Boot** para simular o funcionamento de um **Marketplace**, permitindo o gerenciamento de **usuários, lojas, itens, ofertas e pedidos**.

O sistema representa o fluxo básico de um marketplace real, onde produtos são criados, lojas criam ofertas e usuários podem realizar pedidos contendo essas ofertas e suas respectivas quantidades.

Este projeto foi desenvolvido com o objetivo de praticar **arquitetura de aplicações backend**, **APIs REST**, **persistência com banco de dados relacional** e **containerização com Docker**.

---

# 🚀 Tecnologias Utilizadas

## Backend

* Java
* Spring Boot
* Spring Data JPA
* Maven

## Banco de Dados

* MySQL

## Infraestrutura

* Docker
* Docker Compose

## Controle de Versão

* Git

---

# 📂 Estrutura do Projeto

A aplicação segue uma **arquitetura em camadas**, separando responsabilidades entre controle de requisições, regras de negócio e persistência de dados.

```
src/main/java/edu/marketplace

controller/
    UsuarioController
    LojaController
    ItemController
    OfertaController
    PedidoController

service/
    UsuarioService
    LojaService
    ItemService
    OfertaService
    PedidoService

repository/
    UsuarioRepository
    LojaRepository
    ItemRepository
    OfertaRepository
    PedidoRepository

dto/
    UsuarioRequestDTO
    UsuarioResponseDTO
    LojaRequestDTO
    LojaResponseDTO
    ItemRequestDTO
    ItemResponseDTO
    OfertaRequestDTO
    OfertaResponseDTO
    PedidoRequestDTO
    PedidoOfertaRequestDTO
    PedidoOfertaResponseDTO

models/
    UsuarioModel
    LojaModel
    ItemModel
    OfertaModel
    PedidoModel
    PedidoOfertaModel

config/
    CorsConfig
    GlobalExceptionHandler
```

---

# 🧠 Arquitetura da Aplicação

A aplicação utiliza o padrão **Layered Architecture**, separando o sistema em camadas:

```
Client
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
Database
```

### Controller

Responsável por receber as **requisições HTTP** e retornar as respostas da API.

### Service

Contém as **regras de negócio da aplicação**, sendo responsável pela lógica do sistema.

### Repository

Camada responsável pela **comunicação com o banco de dados** utilizando **Spring Data JPA**.

### DTO (Data Transfer Object)

Objetos utilizados para **transferência de dados entre cliente e servidor**, evitando exposição direta das entidades.

### Model (Entidades)

Representam as **tabelas do banco de dados**.

---

# 🧱 Modelagem de Dados

O sistema é composto pelas seguintes entidades:

### Usuário

Representa os usuários da plataforma.

### Loja

Representa os vendedores dentro do marketplace.

### Item

Representa os produtos cadastrados no sistema.

### Oferta

Representa uma oferta de venda para um item específico.

Uma oferta contém informações como:

* preço
* quantidade disponível

### Pedido

Representa uma compra realizada por um usuário.

### PedidoOferta

Tabela intermediária que relaciona:

```
Pedido
Oferta
```

Essa entidade permite que um pedido contenha **várias ofertas diferentes**, possibilitando que um usuário compre produtos de diferentes lojas dentro de um único pedido.

Estrutura lógica:

```
Pedido
   ↓
PedidoOferta
   ↓
Oferta
```

Isso cria uma relação **N:N entre Pedido e Oferta**.

---

# ⚙️ Funcionalidades

O sistema permite realizar operações **CRUD básicas** para as principais entidades.

## Usuários

* Criar usuários
* Listar usuários
* Buscar usuário pelo ID

## Lojas

* Cadastro de lojas
* Consulta de lojas

## Itens

* Cadastro de produtos
* Consulta de produtos

## Ofertas

* Criar ofertas para itens
* Listar ofertas
* Atualizar ofertas sem afetar pedidos anteriores
* Adicionar quantidade para as ofertas

## Pedidos

* Criar pedidos que contenha uma lista de ofertas e uma quantidade especifica para cada uma delas e o id do comprador
* Consultar pedidos realizados

---

# 🖥️ Como Executar o Projeto

## 1️⃣ Clonar o repositório

```
git clone https://github.com/BernardoWehmuth/MarketPlace.git
```

---

## 2️⃣ Entrar na pasta do projeto

```
cd MarketPlace
```

---

## 3️⃣ Criar arquivo .env com as variaveis do seu ambiente

```
DB_URL=jdbc:mysql://localhost:3306/marketplace
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

BASE_URL=seu_ip_e_porta_de_frontend
```

---

## 4️⃣ Iniciar o banco de dados com Docker

```
docker-compose up -d
```

---

## 5️⃣ Executar a aplicação

Usando Maven:

```
mvn spring-boot:run
```

ou utilizando o Maven Wrapper:

```
./mvnw spring-boot:run
```

---

# 🌐 Acesso à API

Após iniciar o projeto, a API estará disponível em:

```
http://localhost:8080/marketplace
```

---

# 📡 Endpoints da API

Principais endpoints disponíveis:

```
/usuarios
/lojas
/itens
/ofertas
/pedidos
```
---

# 📊 Fluxo do Marketplace

1. **Itens** e **Usuários** são cadastrados
2. Lojas são cadastradas com **Usuários** como proprietários
3. **Ofertas** são criadas a partir de **Itens**
4. Pedidos são criados contendo **Ofertas** selecionadas com suas respectivas quantidades

---

# Exemplos de Criações

**Usuário**

```
EndPoint:
http://localhost:8080/marketplace/usuarios

Body(Json)
{
    "usuario": "bernardo_wehmuth",
    "nome": "Bernardo Wehmuth",
    "senha": "12345678",
    "email": "bernardowehmuth@gmail.com"
}

Body de Retorno(Json)
{
    "id": 1,
    "nome": "Bernardo Wehmuth",
    "email": "bernardowehmuth@gmail.com"
}

```
**Item**

```
EndPoint:
http://localhost:8080/marketplace/itens

Body(Json)
{
    "nome": "Corda Náutica",
    "descricao": "Material de Alta Performance, Projetado para Suportar Condições Marítimas Extremas"
}

Body de Retorno(Json)
{
    "id": 1,
    "nome": "Corda Náutica",
    "descricao": "Material de Alta Performance, Projetado para Suportar Condições Marítimas Extremas"
}
```
**Loja**

```
EndPoint:
http://localhost:8080/marketplace/lojas/{usuarioId}

Neste Exemplo o usuarioId é 1 o que corresponde ao usuario Bernardo Wehmuth:

http://localhost:8080/marketplace/lojas/1

Body(Json)
{
    "nome": "Loja do Bernardo",
    "cnpj": "12345678901234"
}

Body de Retorno(Json)
{
    "id": 1,
    "nome": "Loja do Bernardo",
    "cnpj": "12345678901234",
    "nomeProprietario": "Bernardo Wehmuth"
}
```
**Oferta**

```
EndPoint:
http://localhost:8080/marketplace/ofertas

Body(Json)
{
    "lojaId": 1,
    "itemId": 1,
    "preco": 15,
    "quantidade": 35 
}

Body de Retorno(Json)
{
    "id": 1,
    "nomeItem": "Corda Náutica",
    "descricaoItem": "Material de Alta Performance, Projetado para Suportar Condições Marítimas Extremas",
    "precoUnitario": 15.0,
    "quantidade": 35
}
```
**Pedido**

```
EndPoint:
http://localhost:8080/marketplace/pedidos

Body(Json)
{
    "compradorId": 1,
    "ofertas": [
        {
            "ofertaId": 1,
            "quantidade": 5
        }
    ]
}

Body de Retorno(Json)
{
    "id": 1,
    "dataPedido": "2026-03-10T20:14:41.975264002",
    "valorTotal": 75.0,
    "comprador": {
        "id": 1,
        "nome": "Alisson Pereira",
        "email": "pereira.alisson@gmail.com"
    },
    "loja": {
        "id": 1,
        "nome": "Loja do Jeremias",
        "cnpj": "10711683980",
        "nomeProprietario": "Alisson Pereira"
    },
    "itens": [
        {
            "id": 1,
            "nomeItem": "Corda Náutica",
            "precoUnitario": 15.0,
            "quantidade": 5,
            "precoTotal": 75.0
        }
    ]
}
```
---

# 🎯 Objetivo do Projeto

Este projeto foi desenvolvido para praticar conceitos importantes de desenvolvimento backend:

* Desenvolvimento de **APIs REST com Spring Boot**
* Organização de aplicações usando **arquitetura em camadas**
* Persistência de dados com **Spring Data JPA**
* Modelagem de banco de dados relacional
* Containerização de ambiente com **Docker**
