# Projeto Java com Nitrite

Projeto básico em Java usando Maven que persiste pessoas em um banco de dados Nitrite.

## Requisitos

- Java 11 ou superior
- Maven 3.6 ou superior

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

```bash
java -cp target/aulajava-1.0-SNAPSHOT.jar:target/dependency/* com.example.Main
```

Ou usando o plugin exec:

```bash
mvn exec:java
```

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

## Notas

- O banco de dados é criado automaticamente na primeira execução
- Cada pessoa recebe um ID único gerado automaticamente (UUID)
- Os dados são persistidos no arquivo `pessoas.db` na raiz do projeto

