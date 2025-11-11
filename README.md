# Projeto Java com Nitrite

Projeto básico em Java usando Maven que persiste pessoas em um banco de dados Nitrite.

## Requisitos

- Java 11 ou superior
- Maven 3.6 ou superior

## Instalação do Maven

### macOS

#### Opção 1: Usando Homebrew (Recomendado)

```bash
# Instalar Homebrew (se não tiver)
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

# Instalar Maven
brew install maven

# Verificar instalação
mvn -version
```

#### Opção 2: Instalação Manual

1. Baixe o Maven do site oficial: https://maven.apache.org/download.cgi
2. Extraia o arquivo em um diretório (ex: `/usr/local/apache-maven`)
3. Configure as variáveis de ambiente no arquivo `~/.zshrc` ou `~/.bash_profile`:

```bash
export MAVEN_HOME=/usr/local/apache-maven
export PATH=$MAVEN_HOME/bin:$PATH
```

4. Recarregue o terminal ou execute:
```bash
source ~/.zshrc  # ou source ~/.bash_profile
```

5. Verifique a instalação:
```bash
mvn -version
```

### Linux (Ubuntu/Debian)

```bash
# Atualizar lista de pacotes
sudo apt update

# Instalar Maven
sudo apt install maven

# Verificar instalação
mvn -version
```

### Linux (Fedora/RedHat/CentOS)

```bash
# Instalar Maven
sudo dnf install maven
# ou
sudo yum install maven

# Verificar instalação
mvn -version
```

### Windows

#### Opção 1: Usando Chocolatey

```powershell
# Instalar Chocolatey (se não tiver)
# Execute no PowerShell como administrador:
Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))

# Instalar Maven
choco install maven

# Verificar instalação
mvn -version
```

#### Opção 2: Instalação Manual

1. Baixe o Maven do site oficial: https://maven.apache.org/download.cgi
2. Extraia o arquivo em um diretório (ex: `C:\Program Files\Apache\maven`)
3. Configure as variáveis de ambiente:
   - Abra "Variáveis de Ambiente" no Painel de Controle
   - Adicione `MAVEN_HOME` com o valor do diretório do Maven (ex: `C:\Program Files\Apache\maven`)
   - Adicione `%MAVEN_HOME%\bin` à variável `PATH`
4. Abra um novo terminal e verifique:
```cmd
mvn -version
```

### Verificar Instalação

Após a instalação, execute o seguinte comando para verificar:

```bash
mvn -version
```

Você deve ver uma saída similar a:
```
Apache Maven 3.9.x
Maven home: /usr/local/apache-maven
Java version: 11.x.x, vendor: ...
Java home: ...
Default locale: ...
OS name: "mac os x", version: "...", arch: "...", family: "mac"
```

### Instalação do Java

Se você ainda não tiver o Java instalado:

**macOS:**
```bash
brew install openjdk@11
```

**Linux (Ubuntu/Debian):**
```bash
sudo apt install openjdk-11-jdk
```

**Windows:**
Baixe e instale o JDK 11 do site da Oracle ou OpenJDK.

## Estrutura do Projeto

```
aulajava/
├── pom.xml
├── README.md
└── src/
    └── main/
        └── java/
            └── com/
                └── example/
                    ├── Person.java
                    └── Main.java
```

## Funcionalidades

- Adicionar pessoas ao banco de dados
- Listar todas as pessoas cadastradas
- Buscar pessoa por ID
- Persistência em banco Nitrite (arquivo `pessoas.db`)

## Comandos para Executar o Projeto

### 1. Compilar o projeto

```bash
mvn clean compile
```

### 2. Executar a aplicação

```bash
mvn exec:java -Dexec.mainClass="com.example.Main"
```

### 3. Compilar e executar em um único comando

```bash
mvn clean compile exec:java -Dexec.mainClass="com.example.Main"
```

### 4. Gerar JAR executável

```bash
mvn clean package
```

Isso criará um JAR em `target/aulajava-1.0-SNAPSHOT.jar`

### 5. Executar o JAR

**Linux/macOS:**
```bash
java -cp target/aulajava-1.0-SNAPSHOT.jar:target/dependency/* com.example.Main
```

**Windows:**
```cmd
java -jar target\aulajava-1.0-SNAPSHOT.jar
```

Ou usando o plugin exec:

```bash
mvn exec:java
```

### 6. Executar no Windows (Script Automatizado)

Use o script `run-windows.bat` que automatiza todo o processo:

```cmd
run-windows.bat
```

Este script:
- Limpa o projeto
- Atualiza as dependências
- Compila o projeto
- Cria um JAR com todas as dependências
- Executa a aplicação

## Como Usar

1. Execute a aplicação usando um dos comandos acima
2. Escolha uma opção no menu:
   - **1**: Adicionar uma nova pessoa (solicita nome, idade e email)
   - **2**: Listar todas as pessoas cadastradas
   - **3**: Buscar uma pessoa por ID
   - **4**: Sair da aplicação

## Banco de Dados

O banco de dados Nitrite é criado automaticamente como um arquivo local chamado `pessoas.db` na raiz do projeto. Os dados são persistidos neste arquivo e mantidos entre execuções.

## Dependências

- **Nitrite 3.4.4**: Banco de dados NoSQL embarcado
- **Jackson 2.15.2**: Serialização JSON (usado pelo Nitrite)

## Exemplo de Uso

```
=== Sistema de Gerenciamento de Pessoas ===
Banco de dados: pessoas.db

Escolha uma opção:
1 - Adicionar pessoa
2 - Listar todas as pessoas
3 - Buscar pessoa por ID
4 - Sair
Opção: 1

=== Adicionar Nova Pessoa ===
Nome: João Silva
Idade: 30
Email: joao@example.com
Pessoa adicionada com sucesso! ID: 123e4567-e89b-12d3-a456-426614174000
```

## Limpeza

Para remover os arquivos compilados e o banco de dados:

```bash
mvn clean
rm pessoas.db
```

## Troubleshooting

### Erro: `NoClassDefFoundError: com/fasterxml/jackson/annotation/JsonKey` (Windows)

Este erro pode ocorrer no Windows devido a problemas com o classpath do Maven ou cache corrompido. Siga estes passos:

#### Solução 1: Limpar Cache do Maven

```bash
# Limpar cache local do Maven
mvn dependency:purge-local-repository

# Ou deletar manualmente o cache (Windows)
# Pasta: C:\Users\<seu-usuario>\.m2\repository\com\fasterxml\jackson
```

#### Solução 2: Limpar e Recompilar

```bash
# Limpar projeto completamente
mvn clean

# Limpar cache e reinstalar dependências
mvn clean install -U

# Executar novamente
mvn exec:java
```

#### Solução 3: Usar JAR com Dependências (Recomendado para Windows)

```bash
# Criar JAR com todas as dependências incluídas
mvn clean package

# Executar o JAR (Windows)
java -jar target\aulajava-1.0-SNAPSHOT.jar
```

#### Solução 4: Verificar Versões das Dependências

```bash
# Verificar quais versões do Jackson estão sendo usadas
mvn dependency:tree | findstr jackson

# Deve mostrar todas as dependências na versão 2.15.2
```

#### Solução 5: Verificar Versão do Java

```bash
# Verificar versão do Java
java -version

# Deve ser Java 11 ou superior
javac -version
```

#### Solução 6: Executar com Classpath Explícito (Windows)

```cmd
# Compilar
mvn clean compile

# Executar com classpath explícito
java -cp "target/classes;target/dependency/*" com.example.Main
```

#### Diferenças entre Windows e Linux/macOS

- **Separador de caminho**: Windows usa `;` enquanto Linux/macOS usa `:`
- **Cache do Maven**: Pode estar corrompido no Windows
- **Classpath**: O exec-maven-plugin pode ter problemas no Windows
- **Variáveis de ambiente**: Podem estar configuradas incorretamente

### Outros Problemas Comuns

#### Erro: "mvn: command not found"

- Verifique se o Maven está instalado: `mvn -version`
- Verifique se o Maven está no PATH do sistema
- No Windows, reinicie o terminal após instalar o Maven

#### Erro: "java: command not found"

- Verifique se o Java está instalado: `java -version`
- Verifique se o Java está no PATH do sistema
- Configure a variável `JAVA_HOME` no Windows

#### Erro: "Cannot find or load main class"

- Verifique se o projeto foi compilado: `mvn compile`
- Verifique se a classe Main existe em `src/main/java/com/example/Main.java`

## Notas

- O banco de dados é criado automaticamente na primeira execução
- Cada pessoa recebe um ID único gerado automaticamente (UUID)
- Os dados são persistidos no arquivo `pessoas.db` na raiz do projeto
- **Windows**: Recomenda-se usar o JAR com dependências (`mvn package`) em vez de `mvn exec:java` para evitar problemas de classpath

