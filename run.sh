#!/bin/bash

# run.sh

# 1. Busca os valores no Bitwarden e exporta como variáveis de ambiente
# Substitua os "ID_..." pelos IDs reais do seu cofre
export DB_HOST_18=$(bws secret get "ID_DO_DB_HOST" --output json | jq -r '.value')
export DB18_PORT=$(bws secret get "ID_DO_DB_PORTA" --output json | jq -r '.value')
export DB18_NAME=$(bws secret get "ID_DO_DB_NOME" --output json | jq -r '.value')
export DB_USER=$(bws secret get "ID_DO_DB_USUARIO" --output json | jq -r '.value')
export DB_PASSWORD=$(bws secret get "ID_DO_DB_SENHA" --output json | jq -r '.value')

# 2. Inicia o Spring Boot
./mvnw spring-boot:run