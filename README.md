# Demo Todo Spring Boot

## Visão Geral
Aplicação REST construída com Spring Boot 3 para gerenciar tarefas simples (TODOs). O projeto utiliza H2 em memória, validação com Jakarta Bean Validation e segue o estilo modular MVC: controladores recebem DTOs validados, delegam a serviços isolados e persistem entidades via Spring Data JPA.

## Estrutura do Projeto
- `src/main/java/com/univirtus/todo/demo` – ponto de entrada `DemoApplication`, controlador de saúde (`HealthController`) e módulos de domínio.
- `src/main/java/com/univirtus/todo/demo/todo` – módulo TODO com `entity`, `dto`, `service`, `controller`, `mapper` e `repository`.
- `src/main/resources` – configurações (`application.properties`) e diretórios para templates/estáticos.
- `requests.http` – coleções de chamadas para a extensão REST Client.

## Pré-requisitos
- Java 25 (Spring Boot utiliza toolchain para garantir a versão).
- Gradle wrapper incluído (`./gradlew`).
- Opcional: VS Code com extensão REST Client habilitada (`rest-client.enableScriptExecution=true` para armazenar variáveis globais).

## Como Executar
```bash
./gradlew bootRun
```
O serviço inicia em `http://localhost:8080`. As tabelas do H2 são geradas automaticamente ao subir a aplicação.

## Testes
```bash
./gradlew test
```
Use `./gradlew clean test` antes de reproduzir problemas ou validar PRs.

## Endpoints Principais
- `GET /` – verificação de saúde.
- `POST /api/v1/todos` – cria tarefa; requer `title`, `description`, `dueDate` (yyyy-MM-dd) e `responsible`.
- `GET /api/v1/todos` – lista todas as tarefas.
- `GET /api/v1/todos/{id}` – consulta tarefa específica.
- `PUT /api/v1/todos/{id}` – atualiza campos existentes; validações impedem strings vazias.
- `DELETE /api/v1/todos/{id}` – remove tarefa.

Para testar rapidamente, utilize `requests.http`: ele contém cenários de criação, listagem, atualização, exclusão e casos de erro (por exemplo, título vazio ou ID inexistente).

## Convenções de Código
- Entidades JPA com `@Getter`/`@Setter` para evitar `equals/hashCode` automáticos.
- Serviços expõem um método `execute` por operação, promovendo Single Responsibility.
- Mensagens de validação estão em português para feedback direto aos usuários da API.
- Respostas de erro padronizadas via `RestExceptionHandler`.

## Próximos Passos Recomendados
- Adicionar autenticação/usuários quando necessário.
- Configurar perfis (`application-dev.yml`, `application-prod.yml`) e migrações com Flyway se precisar persistir dados além da execução local.
