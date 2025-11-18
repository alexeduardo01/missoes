@echo off
REM Script para executar a aplicação JavaFX no Windows
REM Este script executa a versão JavaFX diretamente

echo ========================================
echo Executando Aplicacao JavaFX
echo ========================================
echo.

echo Compilando e executando aplicacao JavaFX...
echo.

REM Executar com exec-maven-plugin
call mvn clean compile exec:java -Dexec.mainClass=com.example.MainFX

if %errorlevel% neq 0 (
    echo.
    echo ERRO: Falha ao executar a aplicacao
    echo.
    echo Verifique se todas as dependencias foram baixadas corretamente.
    echo Tente executar: mvn clean install
    pause
    exit /b 1
)

pause

