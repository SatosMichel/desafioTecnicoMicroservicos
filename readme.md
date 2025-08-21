# Desafio de Microsserviços com Spring Boot e Spring Cloud 🚀

## Status do Projeto: Concluído ✅

-----

### Tabela de Conteúdos

  * [Descrição do Projeto]
  * [Arquitetura da Solução]
  * [Tecnologias Utilizadas]
  * [Funcionalidades]
  * [Como Executar o Projeto]
  * [Como Testar a Aplicação]
  * [Autor]

-----

### Descrição do Projeto

Este projeto foi desenvolvido como parte de um desafio técnico da DIO (Digital Innovation One). O objetivo é aplicar conceitos de uma arquitetura moderna baseada em microsserviços utilizando o ecossistema Spring (Spring Boot e Spring Cloud).

O sistema simula um ambiente de e-commerce simplificado, composto por um catálogo de produtos e um serviço para criação de pedidos. A comunicação entre os serviços é gerenciada por um Service Discovery e centralizada através de um API Gateway, que também é responsável pela camada de segurança.

-----

### Arquitetura da Solução

A arquitetura é composta por quatro serviços principais que trabalham em conjunto:

  * **`service-discovery` (Spring Cloud Eureka):** Atua como o "cérebro" da arquitetura. É responsável por manter um registro de todos os outros microsserviços ativos, permitindo que eles se encontrem e se comuniquem dinamicamente na rede.
  * **`api-gateway` (Spring Cloud Gateway):** Funciona como a "porta de entrada" para todo o sistema. Todas as requisições externas passam por ele, que as valida (verificando o token de autorização) e as redireciona para o microsserviço apropriado.
  * **`ms-produtos` (Microsserviço de Produtos):** Serviço responsável pela lógica de negócio relacionada ao catálogo de produtos. Ele permite cadastrar, consultar e listar produtos, persistindo as informações em um banco de dados em memória (H2).
  * **`ms-pedidos` (Microsserviço de Pedidos):** Serviço responsável por simular a criação de um pedido. Ele se comunica com o `ms-produtos` para obter informações dos itens e processar a solicitação.

**Diagrama de Fluxo:**

```
[Browser] -> [API Gateway] -> [Service Discovery]
                  |
                  |--> [ms-produtos] <--> [H2 Database]
                  |
                  '--> [ms-pedidos]  --> [ms-produtos]
```

-----

### Tecnologias Utilizadas

  - **Java 21**
  - **Spring Boot 3.2.5**
  - **Spring Cloud 2023.0.1**
      - Spring Cloud Gateway (API Gateway)
      - Spring Cloud Eureka (Service Discovery)
      - OpenFeign (Comunicação entre serviços)
  - **Maven 3.9+** (Gerenciador de dependências)
  - **H2 Database** (Banco de dados em memória)
  - **Postman** (Ferramenta para testes de API)

-----

### Funcionalidades

  - **`ms-produtos`**:

      - `POST /produtos`: Cria um novo produto.
      - `GET /produtos`: Lista todos os produtos cadastrados.
      - `GET /produtos/{id}`: Busca um produto específico pelo seu ID.

  - **`ms-pedidos`**:

      - `POST /pedidos`: Simula a criação de um pedido a partir de uma lista de IDs de produtos.

  - **`api-gateway`**:

      - Centraliza o acesso a todos os endpoints.
      - Valida um token de autorização fixo (`Bearer Token`) em todas as requisições.

-----

### Como Executar o Projeto

#### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina:

  - [Java Development Kit (JDK) 21](https://www.oracle.com/java/technologies/downloads/%23jdk21-windows)
  - [Apache Maven](https://maven.apache.org/download.cgi)
  - [Git](https://git-scm.com/downloads)

#### Passos para Execução

1.  **Clone o repositório:**

    ```bash
    git clone https://github.com/seu-usuario/seu-repositorio.git
    cd seu-repositorio
    ```

2.  **Compile todos os módulos:**
    Na pasta raiz do projeto (`sistema-pedidos-parent`), execute o comando Maven para limpar e instalar todas as dependências.

    ```bash
    mvn clean install
    ```

3.  **Inicie os serviços:**
    Você precisará de **4 terminais** abertos, um para cada serviço. Inicie-os na seguinte ordem:

      * **Terminal 1: Service Discovery**
        ```bash
        cd service-discovery
        mvn spring-boot:run
        ```
      * **Terminal 2: MS-Produtos**
        ```bash
        cd ms-produtos
        mvn spring-boot:run
        ```
      * **Terminal 3: MS-Pedidos**
        ```bash
        cd ms-pedidos
        mvn spring-boot:run
        ```
      * **Terminal 4: API-Gateway**
        ```bash
        cd api-gateway
        mvn spring-boot:run
        ```

Ao final, você pode verificar o painel do Eureka em `http://localhost:8761` para ver todos os serviços registrados e com status `UP`.

-----

### Como Testar a Aplicação

Todas as requisições devem ser feitas através do **API Gateway** (porta `8700`) e devem conter um cabeçalho de autorização com um token fixo. Utilize uma ferramenta como o **Postman** para os testes.

**Configuração de Segurança:**

  - **Tipo de Autorização:** `Bearer Token`
  - **Token:** `meu-token-secreto`

#### Tabela de Endpoints

| Método | URL (via Gateway) | Protegido | Corpo da Requisição (Exemplo JSON) | Descrição |
| :--- | :--- | :---: | :--- | :--- |
| `POST` | `http://localhost:8700/produtos` | Sim | `{"nome": "Notebook", "descricao": "Notebook de última geração", "preco": 5000.00}` | Cria um novo produto. |
| `GET` | `http://localhost:8700/produtos` | Sim | N/A | Lista todos os produtos. |
| `POST` | `http://localhost:8700/pedidos` | Sim | `[1, 2]` | Cria um novo pedido com os produtos de ID 1 e 2. |

-----

Claro\! Aqui está a seção "Autor" do seu `README.md` atualizada com as novas informações.

Você pode copiar este bloco e substituir a seção `### Autor` no seu arquivo.

-----

### Autor

**Michel Rebouças**

  * **GitHub:** [https://github.com/Michel-Reboucas](https://github.com/Michel-Reboucas)
  * **LinkedIn:** [https://br.linkedin.com/in/michel-santos-rebouças-5a81b561](https://br.linkedin.com/in/michel-santos-rebou%C3%A7as-5a81b561)
  * **Instagram:** [@satosmichel\_oficial](https://www.instagram.com/satosmichel_oficial)
  * **WhatsApp:** [(71) 98736-4775](https://web.whatsapp.com/send?phone=557187364775)