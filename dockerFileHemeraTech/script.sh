#!/bin/bash

#Cria a imagem do docker
docker build -t dockerfile .

#executar o container com suas variaveis de ambiente
docker run -d -p 3306:3306 --name ContainerGrupo7 -e MYSQL_ROOT_PASSWORD=urubu100 -e MYSQL_DATABASE=HemeraTech dockerfile
