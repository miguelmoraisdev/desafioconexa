# Conexa Desafio 1
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/miguelmoraisdev/desafioconexa1/blob/master/LICENCE) 

# Sobre o projeto
O Conexa Desafio 1 é uma API-REST que usa FeignClient para buscar na [SwapiAPI]( https://swapi.dev/) os filmes de Star Wars que tenham como personagem envolvido Luke Skywalker.

O Conexa Desafio 1 busca esses filmes na SwapiAPI e retorna uma paginação(de 10 itens por página) com os filmes no novo formarto com os campos “title”, “episode_id”, “director” e “release_date”.

O campo “release_date” vem no novo formato dd/MM/yyyy.

Essa busca pode ser feita usando filtagrem pelo "title" e/ou "episodeId".

Para esse projeto foram desenvolvidos testes unitários das camadas de service e controller.

Foi utilizado a ferramenta OpenAPI (Swagger) para documentação dos endpoints da aplicaçação:
http://localhost:8081/swagger-ui/index.html

> Status do Projeto: :heavy_check_mark: :warning: (concluido)

# Funcionalidades

:heavy_check_mark: Buscar uma paginação de filmes que tenham Luke Skywalker como personagem envolvido e trazer cada filme no formato:

```bash
{
    "title": "A New Hope",
    "episode_id": "4",
    "director": "George Lucas",
    "release_date": "25/05/1977"
}
```

# Tecnologias utilizadas
- Java 17
- Spring Boot
- Maven
- Feign Client
- OpenApi (Swagger)
- Testes (Junit e Mockito)

# Pré-requisitos

:warning: [Java-17]

:warning: [Intellij IDEA]

:warning: [Spring Boot]

# Como rodar a aplicação :arrow_forward:

```bash
# clonar repositório
# HTTPS
git clone https://github.com/miguelmoraisdev/desafioconexa1.git

# SSH
git clone git@github.com:miguelmoraisdev/desafioconexa1.git

# entrar na pasta do projeto back end
cd desafioconexa1

# executar o projeto
./mvnw spring-boot:run
```
Você também pode abrir o projeto por uma IDE

Apoś baixar o projeto, você pode abrir o Intellij IDEA. Aós abri-lo, clique em:
- File -> Open 

- Procure o local onde está o projeto e o selecione.

- Por fim click em OK.

- Configure a IDE para rodar com o Java 17 e a classe principal do programa. 

# Autor

Miguel Augusto de Morais Junior

https://www.linkedin.com/in/miguel-morais-04a9ab1b0/
