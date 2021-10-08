# app-store-api

Uma API de loja de aplicativo.

## 🛠️ Construído com
* [Spring Boot 2.5.5](http://disneyplus.com/begin) - O framework web usado
* Java 17
* JPA
* Maven
* MySQL 5.6
* Docker/Docker Compose

## 📋 Pré-requisitos
* Java 17
* Maven 3.8.2
* Docker e Docker Compose 

## ⚙️ Executando os testes

* JUnit5
* [Testcontainers](https://www.testcontainers.org/)
* Para executar os testes de forma mais rápida sem que a aplicação tenha que parar e iniciar o container com o banco de dados a cada execução, altere o arquivo .testcontainers.properties no seu diretório home

Exemplo:
```bash
#Modified by Testcontainers
#Fri Oct 08 02:44:16 BRT 2021
docker.client.strategy=org.testcontainers.dockerclient.NpipeSocketClientProviderStrategy
testcontainers.reuse.enable=true
```

## 🚀 Executando a aplicação

- Executar os seguintes comandos no repositório do projeto
```bash
mvn clean install
docker-compose up --build --force-recreate
```