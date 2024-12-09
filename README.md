# GeoRota

GeoRota é uma aplicação Spring Boot que gerencia locais e conexões entre eles. Ela fornece uma API RESTful para adicionar locais, conectá-los e recuperar informações sobre as conexões e as melhores rotas entre pontos.

## Objetivo

O objetivo do GeoRota é fornecer uma aplicação que gerencia locais e as conexões entre eles, permitindo que os usuários adicionem novos locais, conectem esses locais e obtenham informações sobre as conexões e as melhores rotas entre pontos específicos. Isso pode ser útil para diversos casos de uso, como planejamento de rotas, análise de redes de transporte, logística, entre outros.

## Tecnologias

- **Java**: Linguagem de programação usada para o desenvolvimento.
- **Spring Boot**: Framework usado para criar a aplicação.
- **Maven**: Ferramenta de automação de build usada para gerenciar dependências e construir o projeto.

## Estrutura do Projeto

- `src/main/java/com/georota/georota/GeorotaApplication.java`: Classe principal para executar a aplicação Spring Boot.
- `src/main/java/com/georota/georota/controller/MapaController.java`: Classe controladora que define os endpoints da API RESTful.
- `src/main/java/com/georota/georota/maps/services/Cidade.java`: Classe de serviço que contém a lógica de negócios para gerenciar locais e conexões.

## Endpoints da API

### Adicionar Local

- **URL**: `/mapa/adicionar`
- **Método**: `POST`
- **Parâmetros**:
  - `nomePonto` (String): Nome do local.
  - `logradouro` (String): Endereço do local.
- **Resposta**: JSON com uma mensagem de sucesso e os detalhes do local adicionado.

### Buscar Local

- **URL**: `/mapa/buscar-local`
- **Método**: `GET`
- **Parâmetros**:
  - `nomePonto` (String): Nome do local a ser buscado.
- **Resposta**: JSON com os detalhes do local ou um erro 404 se não for encontrado.

### Conectar Locais

- **URL**: `/mapa/conectar-elos`
- **Método**: `POST`
- **Parâmetros**:
  - `nomeOrigem` (String): Nome do local de origem.
  - `nomeDestino` (String): Nome do local de destino.
- **Resposta**: JSON com uma mensagem de sucesso ou uma mensagem de erro se os locais já estiverem conectados ou não existirem.

### Obter Conexões

- **URL**: `/mapa/conexoes`
- **Método**: `GET`
- **Resposta**: JSON com a lista de todos os locais e suas conexões.

### Obter Melhor Rota

- **URL**: `/mapa/melhor-rota`
- **Método**: `GET`
- **Parâmetros**:
  - `nomeOrigem` (String): Nome do local de origem.
  - `nomeDestino` (String): Nome do local de destino.
  - `modo` (String): Modo de transporte (ex.: dirigindo, caminhando).
- **Resposta**: JSON com os detalhes da melhor rota ou uma mensagem de erro se os locais não existirem.

## Executando a Aplicação

1. Clone o repositório.
2. Navegue até o diretório do projeto.
3. Execute `mvn clean install` para construir o projeto.
4. Execute `mvn spring-boot:run` para iniciar a aplicação.
5. A aplicação estará disponível em `http://localhost:8080`.

## Exemplo de Uso

Para adicionar um local:

```sh
curl -X POST "http://localhost:8080/mapa/adicionar?nomePonto=Praça%20da%20Sé&logradouro=Se,%20São%20Paulo%20-%20SP,%2001001-000"# GeoRota
