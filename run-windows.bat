@echo off
REM Script para executar o projeto no Windows
REM Este script limpa o cache, recompila e executa o projeto

echo ========================================
echo Executando Projeto Java com Nitrite
echo ========================================
echo.

echo [1/4] Limpando projeto...
call mvn clean
if %errorlevel% neq 0 (
    echo ERRO: Falha ao limpar o projeto
    pause
    exit /b 1
)

echo.
echo [2/4] Atualizando dependências...
call mvn dependency:resolve -U
if %errorlevel% neq 0 (
    echo ERRO: Falha ao atualizar dependências
    pause
    exit /b 1
)

echo.
echo [3/4] Compilando projeto...
call mvn compile
if %errorlevel% neq 0 (
    echo ERRO: Falha na compilação
    pause
    exit /b 1
)

echo.
echo [4/4] Criando JAR com dependências (recomendado para Windows)...
call mvn package
if %errorlevel% neq 0 (
    echo ERRO: Falha ao criar JAR
    pause
    exit /b 1
)

echo.
echo ========================================
echo Executando aplicacao...
echo ========================================
echo.

java -jar target\aulajava-1.0-SNAPSHOT.jar

if %errorlevel% neq 0 (
    echo.
    echo ERRO: Falha ao executar a aplicacao
    echo.
    echo Tentando metodo alternativo com exec-maven-plugin...
    call mvn exec:java
)

pause

