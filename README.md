# Agendador de Tarefas BFF

Backend para Frontend (BFF) responsável por orquestrar as operações de usuários, tarefas e notificações em um sistema de agendamento de tarefas.

Este projeto foi desenvolvido em Java com Spring Boot e atua como camada de integração entre o cliente e os microsserviços de:

- usuários
- tarefas
- notificações

## Tecnologias

- Java 17
- Spring Boot 3 / 4.1.1
- Spring Cloud OpenFeign
- Spring Security
- Springdoc OpenAPI (Swagger UI)
- Maven
- Docker + Docker Compose

## Estrutura do projeto

```
agendador-tarefas-bff/
├── .github/
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/com/bff/
│   │   │   ├── business/
│   │   │   ├── controller/
│   │   │   ├── infrastructure/
│   │   │   └── BffApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── Dockerfile
├── docker-compose.yml
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .gitignore
├── .gitattributes
└── README.md
```

## Funcionalidades principais

- ✅ Cadastro, login e gestão de usuários
- ✅ Cadastro e consulta de tarefas
- ✅ Atualização de status de tarefas
- ✅ Integração com serviços externos via OpenFeign
- ✅ Autenticação com JWT
- ✅ Documentação de endpoints via Swagger
- ✅ Agendamento de processamento com `@EnableScheduling`

## Serviços integrados

O BFF conversa com os seguintes serviços:

| Serviço | URL | Porta |
|---------|-----|-------|
| Usuários | `http://localhost:8080/usuario` | 8080 |
| Agendador de tarefas | `http://localhost:8081/tarefa` | 8081 |
| Notificação por e-mail | `http://localhost:8082/email` | 8082 |
| **BFF** | `http://localhost:8083` | **8083** |

## Configuração

O arquivo de configuração principal está em:

`src/main/resources/application.properties`

```properties
spring.application.name=bff

usuario.url=http://localhost:8080/usuario
agendadortarefas.url=http://localhost:8081/tarefa
notificacao.url=http://localhost:8082/email

server.port=8083

cron.horario=*/10 * * * * *

usuario.email=admin@admin.com
usuario.senha=1234
```

## Requisitos

- Java 17+
- Maven 3.9+
- Docker e Docker Compose (opcional, para containerização)

## Como executar

### 1️⃣ Clonar o repositório

```bash
git clone https://github.com/lucasviniicius/agendador-tarefas-bff.git
cd agendador-tarefas-bff
```

### 2️⃣ Executar com Maven (local)

```bash
./mvnw clean install
./mvnw spring-boot:run
```

A aplicação ficará disponível em: `http://localhost:8083`

### 3️⃣ Executar com Docker Compose

```bash
docker compose up --build
```

Esse comando levanta os containers dos serviços de usuário, tarefas, notificações, banco Postgres e MongoDB.

## 📚 Documentação da API

A documentação interativa da API (Swagger UI) fica disponível em:

```
http://localhost:8083/swagger-ui/index.html
```

## 🔌 Endpoints principais

### Usuário
- `POST /usuario` - Criar novo usuário
- `POST /usuario/login` - Login
- `GET /usuario` - Buscar usuário por email
- `PUT /usuario` - Atualizar usuário
- `DELETE /usuario/{email}` - Deletar usuário
- `POST /usuario/endereco` - Criar endereço
- `PUT /usuario/endereco` - Atualizar endereço
- `POST /usuario/telefone` - Criar telefone
- `PUT /usuario/telefone` - Atualizar telefone
- `GET /usuario/endereco/{cep}` - Buscar endereço por CEP (ViaCEP)

### Tarefa
- `POST /tarefa` - Criar tarefa
- `GET /tarefa` - Buscar tarefas por email
- `GET /tarefa/eventos` - Buscar tarefas por período
- `PUT /tarefa` - Atualizar tarefa
- `PATCH /tarefa` - Alterar status da tarefa
- `DELETE /tarefa` - Deletar tarefa

## 🔐 Autenticação

A aplicação utiliza **Bearer Token JWT** para autenticação em endpoints protegidos. 

Para acessar endpoints autenticados, inclua o header:
```
Authorization: Bearer <seu_token_jwt>
```

## 📝 Observações

- O BFF centraliza chamadas a outros serviços, evitando acesso direto do cliente a cada backend
- O arquivo `docker-compose.yml` referencia outros projetos do ecossistema
- Implementa padrão de comunicação com microsserviços via OpenFeign
- Inclui tratamento global de exceções em `GlobalExceptionHandler`

## 📦 Dependências principais

```xml
<!-- Spring Boot Web -->
<artifactId>spring-boot-starter-webmvc</artifactId>

<!-- OpenFeign (Client HTTP) -->
<artifactId>spring-cloud-starter-openfeign</artifactId>

<!-- Spring Security -->
<artifactId>spring-boot-starter-security</artifactId>

<!-- Swagger/OpenAPI -->
<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>

<!-- Lombok (Reduz boilerplate) -->
<artifactId>lombok</artifactId>
```

## 🤝 Contribuindo

Para contribuir com este projeto:

1. Faça um fork do repositório
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto não possui uma licença explicitamente definida.

---

**Desenvolvido por:** Lucas Vinícius
