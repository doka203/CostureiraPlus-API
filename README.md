# 🧵 CostureiraPlus API

API REST para o projeto **CostureiraPlus**, uma solução para a gestão de serviços e pedidos de uma costureira. O sistema foi desenvolvido em Java utilizando o framework Spring Boot.

## 📝 Descrição

O CostureiraPlus oferece uma plataforma para que costureiras possam gerenciar seus clientes, organizar pedidos, controlar o financeiro e agendar visitas de forma eficiente. A API foi projetada para ser o backend de uma aplicação web/mobile, fornecendo todos os endpoints necessários para a operação do sistema.

## ✨ Funcionalidades Principais

  * **Gestão de Pessoas e Usuários**:

      * CRUD completo para Pessoas (Clientes e Costureiras).
      * Busca de pessoas por ID, CPF ou e-mail.
      * Criação e gerenciamento de Usuários (com perfis de `CLIENTE` e `COSTUREIRA`) associados a uma pessoa.

  * **Controle de Pedidos**:

      * CRUD completo para Pedidos.
      * Associação de um pedido a um cliente e a uma costureira.
      * Possibilidade de cancelar um pedido (alterando seu status).

  * **Sistema Financeiro**:

      * Geração automática de parcelas de Pagamento ao criar um pedido.
      * Atualização automática das parcelas caso o valor ou o número de parcelas do pedido seja alterado.
      * Endpoint para registrar a data de pagamento de uma parcela.

  * **Agenda e Organização**:

      * Agendamento de **Visitas** (provas, entregas, etc.) entre cliente e costureira.
      * Criação de **Lembretes** importantes associados a cada pedido.
      * Endpoint para consultar a agenda de visitas por período e por cliente.

## 🛠️ Tecnologias Utilizadas

  * **Backend**: Java 21, Spring Boot
  * **Persistência de Dados**: Spring Data JPA, Hibernate
  * **Banco de Dados**: H2 (ambiente de teste), com suporte para MySQL e PostgreSQL (produção)
  * **Documentação da API**: Springdoc OpenAPI (Swagger UI)
  * **Gerenciador de Dependências**: Maven

## 🚀 Como Executar o Projeto

Siga os passos abaixo para executar a aplicação localmente.

### Pré-requisitos

  * [Java Development Kit (JDK) 21](https://www.google.com/search?q=https://www.oracle.com/java/technologies/downloads/%23jdk21) ou superior.
  * [Apache Maven](https://maven.apache.org/download.cgi) 3.8 ou superior.
  * Um cliente Git para clonar o repositório.

### Passos

1.  **Clone o repositório:**

    ```bash
    git clone https://github.com/seu-usuario/costureiraplus.git
    cd costureiraplus
    ```

2.  **Configuração do Banco de Dados:**
    Por padrão, a aplicação utiliza um banco de dados em memória (H2) para o perfil de `test`. Os dados iniciais são carregados a partir do arquivo `src/main/resources/import.sql`.

    Para usar um banco de dados diferente (MySQL, PostgreSQL), você precisará criar um arquivo `application-prod.properties` (ou outro perfil) e configurar as propriedades de datasource do Spring.

3.  **Execute a aplicação:**
    Utilize o plugin do Maven para iniciar o servidor.

    ```bash
    mvn spring-boot:run
    ```

    O servidor será iniciado na porta `8080`.

## 📖 Documentação da API

A API está documentada com **Swagger UI**, que é gerada automaticamente pelo Springdoc. Após iniciar a aplicação, você pode acessar a documentação interativa no seu navegador:

➡️ **[http://localhost:8080/swagger-ui.html](https://www.google.com/search?q=http://localhost:8080/swagger-ui.html)**

Lá você encontrará todos os endpoints, modelos de dados e poderá testar as requisições diretamente pela interface.

## 🗺️ Endpoints Principais

Aqui estão alguns dos principais endpoints disponíveis:

| Método HTTP | Rota                               | Descrição                                         |
| :---------- | :--------------------------------- | :-------------------------------------------------- |
| `GET`       | `/pessoas`                         | Lista todas as pessoas.                             |
| `GET`       | `/pessoas/{id}`                    | Busca uma pessoa por ID.                            |
| `GET`       | `/pessoas/cpf/{cpf}`               | Busca uma pessoa por CPF.                           |
| `POST`      | `/pessoas`                         | Cria uma nova pessoa.                               |
| `GET`       | `/usuarios`                        | Lista todos os usuários.                            |
| `GET`       | `/usuarios/{id}/pedidos`           | Lista todos os pedidos de um usuário.               |
| `GET`       | `/pedidos`                         | Lista todos os pedidos.                             |
| `POST`      | `/pedidos`                         | Cria um novo pedido e suas parcelas.                |
| `PUT`       | `/pedidos/{id}`                    | Atualiza um pedido.                                 |
| `PUT`       | `/pedidos/{id}/cancelar`           | Cancela um pedido.                                  |
| `GET`       | `/pedidos/{id}/pagamentos`         | Lista os pagamentos de um pedido.                   |
| `PUT`       | `/pagamentos/{id}/registrar-pagamento` | Registra a data de pagamento de uma parcela.      |
| `GET`       | `/visitas/agenda`                  | Busca visitas por um período de datas e cliente.    |

-----
