# Teste Ekan #

# Sobre o projeto
A aplicação consiste em um crud de beneficiários de um plano de saúde.

# Tecnologias utilizadas
## Back end
- Java 17
- Spring Boot 3.1.5
- JPA / Hibernate
- Maven
- Flyway
- MySql
- OpenApi

# Como executar o projeto
## Back end
Pré-requisitos: Java 17 e MySql Server

Na aplicação, ao executar, serão criados as tabelas e a inserção do usuário para autenticação. Neste contexto foi utlizado a tecnologia do FLYWAY, que consiste na organização e execução dos scripts SQLs dentro da aplicação.

```bash
# clonar repositório
git clone https://github.com/etoshio/teste-ekan

# criar o schema no banco de dados - Mysql
ekan

# abrir o projeto com o IDE (eclipse ou intelliJ) e abrir o application.yaml 
alterar a propriedade url, username e password do banco de dados mysql

# executar o comando
mvn clean install

# rodar aplicação
mvn spring-boot:run

# abrindo o openapi da aplicação no navegador (Chrome/FireFox)
http://localhost:8080/swagger-ui/index.html

# para autenticar, utilizar o seguinte usuário:
login: teste
senha: teste123
Esses dados estão na tabela usuario, a senha pode ser criptografada com Bcrypt (https://bcrypt.online/)

```

