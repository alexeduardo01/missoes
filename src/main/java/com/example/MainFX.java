package com.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

/**
 * Aplicação JavaFX com CRUD completo para Person
 */
public class MainFX extends Application {
    private PersonService personService;
    private ObservableList<Person> personList;
    private TableView<Person> tableView;
    
    // Campos do formulário
    private TextField idField;
    private TextField nomeField;
    private TextField idadeField;
    private TextField emailField;
    private Button btnSalvar;
    private Button btnNovo;
    private Button btnEditar;
    private Button btnExcluir;
    private Button btnCancelar;
    
    private Person pessoaSelecionada;

    @Override
    public void start(Stage primaryStage) {
        try {
            personService = new PersonService();
            personList = FXCollections.observableArrayList();
            
            // Carregar dados iniciais
            carregarDados();

            primaryStage.setTitle("Gerenciamento de Pessoas - CRUD");
            
            BorderPane root = new BorderPane();
            root.setPadding(new Insets(10));

            // Criar layout principal
            VBox mainLayout = new VBox(10);
            mainLayout.setPadding(new Insets(10));

            // Tabela de pessoas
            tableView = criarTabela();
            
            // Formulário
            GridPane formulario = criarFormulario();

            // Botões de ação
            HBox botoesLayout = criarBotoes();

            mainLayout.getChildren().addAll(
                new Label("Lista de Pessoas:"),
                tableView,
                new Separator(),
                new Label("Formulário:"),
                formulario,
                botoesLayout
            );

            root.setCenter(mainLayout);

            Scene scene = new Scene(root, 800, 700);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Ação ao fechar a janela
            primaryStage.setOnCloseRequest(e -> {
                personService.close();
                Platform.exit();
            });

        } catch (Exception e) {
            e.printStackTrace();
            mostrarErro("Erro ao iniciar aplicação", e.getMessage());
        }
    }

    /**
     * Cria a tabela de pessoas
     */
    private TableView<Person> criarTabela() {
        TableView<Person> table = new TableView<>();
        table.setItems(personList);
        table.setPrefHeight(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Colunas
        TableColumn<Person, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(200);

        TableColumn<Person, String> nomeCol = new TableColumn<>("Nome");
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
        nomeCol.setPrefWidth(200);

        TableColumn<Person, Integer> idadeCol = new TableColumn<>("Idade");
        idadeCol.setCellValueFactory(new PropertyValueFactory<>("idade"));
        idadeCol.setPrefWidth(100);

        TableColumn<Person, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.setPrefWidth(250);

        table.getColumns().addAll(idCol, nomeCol, idadeCol, emailCol);

        // Seleção na tabela
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                preencherFormulario(newSelection);
                btnEditar.setDisable(false);
                btnExcluir.setDisable(false);
                pessoaSelecionada = newSelection;
            }
        });

        return table;
    }

    /**
     * Cria o formulário de entrada
     */
    private GridPane criarFormulario() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        // ID (oculto, gerado automaticamente)
        idField = new TextField();
        idField.setVisible(false);
        idField.setManaged(false);

        // Nome
        grid.add(new Label("Nome:"), 0, 0);
        nomeField = new TextField();
        nomeField.setPrefWidth(300);
        grid.add(nomeField, 1, 0);

        // Idade
        grid.add(new Label("Idade:"), 0, 1);
        idadeField = new TextField();
        idadeField.setPrefWidth(300);
        // Validar que apenas números são digitados
        idadeField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                idadeField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        grid.add(idadeField, 1, 1);

        // Email
        grid.add(new Label("Email:"), 0, 2);
        emailField = new TextField();
        emailField.setPrefWidth(300);
        grid.add(emailField, 1, 2);

        return grid;
    }

    /**
     * Cria os botões de ação
     */
    private HBox criarBotoes() {
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(10));

        btnNovo = new Button("Novo");
        btnNovo.setOnAction(e -> novo());

        btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> salvar());

        btnEditar = new Button("Editar");
        btnEditar.setDisable(true);
        btnEditar.setOnAction(e -> editar());

        btnExcluir = new Button("Excluir");
        btnExcluir.setDisable(true);
        btnExcluir.setOnAction(e -> excluir());

        btnCancelar = new Button("Cancelar");
        btnCancelar.setOnAction(e -> cancelar());

        hbox.getChildren().addAll(btnNovo, btnSalvar, btnEditar, btnExcluir, btnCancelar);

        return hbox;
    }

    /**
     * Carrega dados do banco para a tabela
     */
    private void carregarDados() {
        try {
            List<Person> pessoas = personService.findAll();
            personList.clear();
            personList.addAll(pessoas);
        } catch (Exception e) {
            mostrarErro("Erro ao carregar dados", e.getMessage());
        }
    }

    /**
     * Ação do botão Novo
     */
    private void novo() {
        limparFormulario();
        pessoaSelecionada = null;
        tableView.getSelectionModel().clearSelection();
        btnEditar.setDisable(true);
        btnExcluir.setDisable(true);
        nomeField.requestFocus();
    }

    /**
     * Ação do botão Salvar
     */
    private void salvar() {
        try {
            if (!validarFormulario()) {
                return;
            }

            Person person = criarPersonDoFormulario();

            if (pessoaSelecionada == null) {
                // Criar nova pessoa
                personService.create(person);
                mostrarSucesso("Pessoa cadastrada com sucesso!");
            } else {
                // Atualizar pessoa existente
                person.setId(pessoaSelecionada.getId());
                personService.update(person);
                mostrarSucesso("Pessoa atualizada com sucesso!");
            }

            carregarDados();
            limparFormulario();
            pessoaSelecionada = null;
            tableView.getSelectionModel().clearSelection();
            btnEditar.setDisable(true);
            btnExcluir.setDisable(true);

        } catch (Exception e) {
            mostrarErro("Erro ao salvar pessoa", e.getMessage());
        }
    }

    /**
     * Ação do botão Editar
     */
    private void editar() {
        if (pessoaSelecionada != null) {
            nomeField.requestFocus();
        }
    }

    /**
     * Ação do botão Excluir
     */
    private void excluir() {
        if (pessoaSelecionada != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Exclusão");
            alert.setHeaderText("Excluir Pessoa");
            alert.setContentText("Deseja realmente excluir " + pessoaSelecionada.getNome() + "?");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        personService.delete(pessoaSelecionada.getId());
                        mostrarSucesso("Pessoa excluída com sucesso!");
                        carregarDados();
                        limparFormulario();
                        pessoaSelecionada = null;
                        btnEditar.setDisable(true);
                        btnExcluir.setDisable(true);
                    } catch (Exception e) {
                        mostrarErro("Erro ao excluir pessoa", e.getMessage());
                    }
                }
            });
        }
    }

    /**
     * Ação do botão Cancelar
     */
    private void cancelar() {
        limparFormulario();
        pessoaSelecionada = null;
        tableView.getSelectionModel().clearSelection();
        btnEditar.setDisable(true);
        btnExcluir.setDisable(true);
    }

    /**
     * Preenche o formulário com os dados de uma pessoa
     */
    private void preencherFormulario(Person person) {
        idField.setText(person.getId());
        nomeField.setText(person.getNome());
        idadeField.setText(String.valueOf(person.getIdade()));
        emailField.setText(person.getEmail());
    }

    /**
     * Limpa o formulário
     */
    private void limparFormulario() {
        idField.clear();
        nomeField.clear();
        idadeField.clear();
        emailField.clear();
    }

    /**
     * Cria um objeto Person a partir do formulário
     */
    private Person criarPersonDoFormulario() {
        String nome = nomeField.getText().trim();
        int idade = Integer.parseInt(idadeField.getText().trim());
        String email = emailField.getText().trim();

        return new Person(null, nome, idade, email);
    }

    /**
     * Valida o formulário
     */
    private boolean validarFormulario() {
        if (nomeField.getText().trim().isEmpty()) {
            mostrarErro("Validação", "O nome é obrigatório!");
            nomeField.requestFocus();
            return false;
        }

        if (idadeField.getText().trim().isEmpty()) {
            mostrarErro("Validação", "A idade é obrigatória!");
            idadeField.requestFocus();
            return false;
        }

        try {
            int idade = Integer.parseInt(idadeField.getText().trim());
            if (idade < 0 || idade > 150) {
                mostrarErro("Validação", "A idade deve estar entre 0 e 150!");
                idadeField.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            mostrarErro("Validação", "A idade deve ser um número válido!");
            idadeField.requestFocus();
            return false;
        }

        if (emailField.getText().trim().isEmpty()) {
            mostrarErro("Validação", "O email é obrigatório!");
            emailField.requestFocus();
            return false;
        }

        // Validação simples de email
        String email = emailField.getText().trim();
        if (!email.contains("@") || !email.contains(".")) {
            mostrarErro("Validação", "Por favor, insira um email válido!");
            emailField.requestFocus();
            return false;
        }

        return true;
    }

    /**
     * Mostra mensagem de erro
     */
    private void mostrarErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    /**
     * Mostra mensagem de sucesso
     */
    private void mostrarSucesso(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

