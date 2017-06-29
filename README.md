# laboratorio

Projeto de Estudo de MicroService

Provê serviços REST para Gerenciar cadastro de socio-torcedor, no qual será utilizado para os servicos REST

Tecnologias

Esse projeto utiliza swagger, spring-boot, spring-data, spring-boot-actuator, zuul, eureka e Ribbon, como banco de dados em memória (h2). Arquitetura Arquitetura em 3 camadas (resource, service, repository). Todos os servicos estão cadastrados no eureka que atua como um service registry, atraves do RibbonClient consigo efetuar a comunicação e fazendo o balnaceamneto dos microservices que irei utilizar. Zuul atua como porta de entrada para infraestrutura de servidores.

Obs: baixar os projetos Eureka, Zuul, Clube, Campanha e Socio-torcedor:
Crie quantas instancias desejar de cada service Clube, Campanha e Socio apenas mudando a porta

Inicie os seguintes comandos java -jar zuul-proxy.jar

java -jar eureka-server.jar

java -jar clube-0.0.1-SNAPSHOT.jar --port=????

java -jar campanha-0.0.1-SNAPSHOT.jar --port=????

java -jar socio-torcedor-0.0.1-SNAPSHOT.jar --port=????

apos o inicio dos serviços acesse o seguinte link http://localhost:8761/

com esse link conseguimos ver todos os serviços que estão UP

para chamar os serviçoes acesse os seguintes links:

http://localhost:8089/socio-service/socio/api/socios

http://localhost:8089/socio-service/socio/api/id/1

http://localhost:8089/clube-service/clube/api/clubes

http://localhost:8089/clube-service/clube/api/find/1

http://localhost:8089/clube-service/clube/api/ids/1,2

http://localhost:8089/clube-service/clube/api/novo

http://localhost:8089/clube-service/clube/api/atualizar

http://localhost:8089/clube-service/clube/api/excluir/1

http://localhost:8089/campanha-service/campanha/api/campanhas

http://localhost:8089/campanha-service/campanha/api/id/1

http://localhost:8089/campanha-service/campanha/api/ids/1,2

http://localhost:8089/campanha-service/campanha/api/novo

http://localhost:8089/campanha-service/campanha/api/atualizar

http://localhost:8089/campanha-service/campanha/api/excluir/1

Proximas implementações é adicionar o hystrix para qdo. aconter um fallback, direcionar para um outro microservice. Alterar o banco H2 para MongoDB
