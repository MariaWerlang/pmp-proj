# Documentação do Projeto — Integração com Graylog, MongoDB e ORM
 
## Módulo 1 — Serviço de Pessoas

Este componente disponibiliza uma API REST dedicada ao cadastro e administração de registros de pessoas.

O banco de dados utilizado é o MongoDB, aproveitando sua flexibilidade para armazenar documentos

com atributos como identificador, nome, data de nascimento e status de atividade.
 
A persistência é feita por meio de um ORM compatível com MongoDB, fornecendo abstração entre aplicação e banco.

Todos os eventos e operações são enviados para o Graylog, permitindo monitoramento centralizado e análise de logs.
 
A arquitetura do módulo separa domínio e infraestrutura, garantindo baixo acoplamento e preservando as regras de negócio.
 
---
 
## Módulo 2 — Autenticação e API Gateway

Este módulo é composto por duas partes principais:
 
### Serviço de Login

- Responsável por autenticação e gerenciamento de usuários.

- Armazena credenciais utilizando MongoDB + ORM.

- Gera tokens JWT para acesso seguro às demais APIs.

- Implementa fluxo de Basic Auth convertido em Bearer Token.
 
### API Gateway

- Centraliza o acesso às APIs do sistema.

- Disponibiliza rotas tanto da API de Pessoas quanto da API de Login.

- Valida JWT antes de liberar endpoints protegidos.

- Organiza e controla o tráfego de entrada em todo o ecossistema.
 
A orquestração é feita via Docker, incluindo containers do MongoDB, Graylog e das próprias APIs.
 
---
 
## Módulo 3 — Kafka Producer e Lambda Listener

Este módulo adiciona comunicação assíncrona:
 
### Kafka Producer (integrado à API de Login)

Quando a rota de recuperação de senha é acionada, o producer envia uma mensagem ao tópico `forget`,

com os dados necessários para iniciar o processo de redefinição.
 
### Lambda Listener

- Construído em Java/Spring Boot e empacotado para execução serverless.

- Escuta o tópico `forget`.

- Ao receber uma mensagem, imprime no console qual usuário solicitou redefinição de senha.
 
A imagem Docker deste módulo é gerada e publicada automaticamente no Docker Hub através de GitHub Actions.
 
---
 
## Módulo 4 — Kafka Consumers com Alta Disponibilidade

Este módulo adiciona consumidores redundantes:
 
### Consumer Replicado

- Dois consumidores idênticos executando em contêineres distintos.

- Compartilham o mesmo `groupId`.

- Apenas um processa cada mensagem recebida no tópico `forget`, garantindo escalabilidade.

- Se um consumidor falhar, o outro assume automaticamente.
 
Cada instância envia seus logs ao Graylog, mantendo observabilidade centralizada.
 
---
 
## Pós-Desenvolvimento — CI/CD com Docker Hub

Cada módulo possui pipeline próprio no GitHub Actions.

Os workflows:
 
- constroem imagens Docker

- realizam login no Docker Hub

- publicam automaticamente as imagens
 
Com isso, o ambiente fica pronto para orquestração futura via docker-compose ou Kubernetes.
 
---
 
## Informações Adicionais

- Java 25 + Spring Boot 4.0.0

- Testes realizados com Insomnia

- Arquitetura preparada para execução em Docker
 
---
 
## Passos para execução

1. Clone todas as branches do repositório.

2. Dentro de cada módulo, rode:

   docker build -t <nome_da_branch>:latest .

3. No módulo `pmp-gateway`, execute:

   docker-compose up -d

 
