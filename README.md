#  Sistema de Denúncias (Backend Gatomia)

API RESTful desenvolvida com **Java** e **Spring Boot** focada na proteção infantil. O objetivo do sistema é gerenciar denúncias de abuso, garantindo a integridade e a persistência dos dados para fins legais e assistenciais.

> ℹ️ **Contexto do Projeto:**
> Este sistema foi originalmente concebido e desenvolvido em **C# (.NET)** como um projeto acadêmico universitário. Esta versão representa uma refatoração e migração completa para o ecossistema **Java**, criada para demonstrar domínio sobre arquitetura de software, Spring Framework e boas práticas de Backend.

##  Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3**
* **Spring Data JPA** (Hibernate)
* **PostgreSQL** (Banco de Dados)
* **Maven** (Gerenciador de Dependências)
* **Bean Validation** (Para garantir dados válidos)

##  Funcionalidades e Arquitetura

* **CRUD Completo:** Criação, Leitura, Atualização e Exclusão de usuários.
* **Padrão DTO (Data Transfer Object):** Separação entre a entidade do banco e os dados expostos na API (ex: não retornamos a senha nas consultas).
* **Soft Delete (Exclusão Lógica):**
    * Ao deletar um usuário, ele **não** é removido fisicamente do banco.
    * O sistema preenche o campo `deleted_at` com a data da exclusão.
    * Nas consultas (`GET`), o sistema filtra automaticamente, trazendo apenas usuários ativos.
* **Tratamento de Erros:** Respostas HTTP adequadas (201, 204, 404, 405).

##  Como Rodar o Projeto

### 1. Pré-requisitos
* Ter o Java 21 (JDK) instalado.
* Ter o PostgreSQL rodando.

### 2. Configuração do Banco de Dados
Crie um banco de dados no PostgreSQL chamado `gatomia_db`.
Em seguida, execute o script SQL abaixo para garantir a estrutura correta:

```sql
CREATE TABLE tb_users (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    phone VARCHAR(20),
    password_hash VARCHAR(255),
    role VARCHAR(20),
    deleted_at TIMESTAMP
);
```
### 3. Configuração do Application Properties
No arquivo `src/main/resources/application.properties`, configure suas credenciais:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/gatomia_db
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA_AQUI
spring.jpa.hibernate.ddl-auto=update
```
## Documentação da API (Endpoints)
### 1. Criar Usuário
* **Método:**`POST`
* **URL:**`/users`
```json
{
  "name": "Maria Developer",
  "email": "maria@teste.com",
  "cpf": "123.456.789-00",
  "phone": "11999999999",
  "password": "SenhaForte123"
}
```
### 2. Listar Usuários(Ativos)
* **Método:**`GET`
* **URL:**`/users`
* **Retorno:** Lista de usuários (sem exibir excluidos).

### 3. Atualizar Usuário
* **Método:**`PUT`
* **URL:**`/users/{id}`(Substitua o `{id}` pelo UUID do usuário)
* **Obs:** Permite alterar apenas Nome e Telefone.
```json
{
  "name": "Maria Silva Editado",
  "phone": "21988887777"
}
```
### 4. Deletar usuário
* **Método:**`DELETE`
* **URL:**`/users/{id}`(Substitua o `{id}` pelo UUID do usuário que deseja deletar)
* **Comportamento:** Retorna status `204 No Content`. Ao listar usuários ele desaparece e no banco de dados é marcado como deletado.


---
Desenvolvido por Victor Portolani