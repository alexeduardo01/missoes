#!/bin/bash

# Script para executar o projeto Java com classpath correto
# Este script garante que todas as dependências sejam carregadas

echo "Compilando o projeto..."
mvn clean compile

if [ $? -ne 0 ]; then
    echo "Erro na compilação. Verifique os erros acima."
    exit 1
fi

echo "Executando a aplicação..."
mvn exec:java -Dexec.mainClass="com.example.Main" -Dexec.classpathScope=compile

