Provê serviços REST para Gerenciar cadastro de Clube de Futebol, no qual será utilizado para os servicos REST de Campanha e socio-torcedor

#Tecnologias 
Esse projeto utiliza swagger, spring-boot, spring-data, spring-boot-actuator com banco de dados em memória (h2).

#Arquitetura Arquitetura em 3 camadas (resource, service, repository).

#Como iniciar o projeto mvn spring-boot:run -Dserver.port=8080
Ajuste o parâmetro server.port para o valor de sua preferência.
via linha de comando: java -jar clube-0.0.1-SNAPSHOT.jar --port=8080
#Acessando a aplicação http://localhost:8080/

A página inicial da aplicação é uma documentação escrita em swagger, os serviços listados podem ser utilizados sem necessidade dessa interface HTML.