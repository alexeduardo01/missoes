package br.com.lunarsystems.missoes.view;

import br.com.lunarsystems.missoes.model.Astronauta;
import br.com.lunarsystems.missoes.model.Missao;
import br.com.lunarsystems.missoes.model.Nave;
import br.com.lunarsystems.missoes.persistence.txt.SerializableTxt;
import br.com.lunarsystems.missoes.service.AstronautaService;
import br.com.lunarsystems.missoes.service.MissaoService;
import br.com.lunarsystems.missoes.service.NaveService;

import javax.swing.*;
import java.awt.*;

public class GuiApp extends JFrame {

    private final MissaoService missaoService;
    private final AstronautaService astronautaService;
    private final NaveService naveService;

    public GuiApp() {

        // Repositórios basicos TXT
        missaoService = new MissaoService(new SerializableTxt<>("missoes.txt"));
        astronautaService = new AstronautaService(new SerializableTxt<>("astronautas.txt"));
        naveService = new NaveService(new SerializableTxt<>("naves.txt"));

        setTitle("Sistema Viagem à Lua");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuMissoes = new JMenu("Missões");
        JMenu menuAstronautas = new JMenu("Astronautas");
        JMenu menuNaves = new JMenu("Naves");

        // ---------- MISSÕES ----------
        JMenuItem itemListarMissoes = new JMenuItem("Listar Missões");
        itemListarMissoes.addActionListener(e -> listarMissoes());

        JMenuItem itemCadastrarMissao = new JMenuItem("Cadastrar Missão");
        itemCadastrarMissao.addActionListener(e -> cadastrarMissao());

        menuMissoes.add(itemListarMissoes);
        menuMissoes.add(itemCadastrarMissao);

        // ---------- ASTRONAUTAS ----------
        JMenuItem itemListarAstronautas = new JMenuItem("Listar Astronautas");
        itemListarAstronautas.addActionListener(e -> listarAstronautas());

        JMenuItem itemCadastrarAstronauta = new JMenuItem("Cadastrar Astronauta");
        itemCadastrarAstronauta.addActionListener(e -> cadastrarAstronauta());

        menuAstronautas.add(itemListarAstronautas);
        menuAstronautas.add(itemCadastrarAstronauta);

        // ---------- NAVES ----------
        JMenuItem itemListarNaves = new JMenuItem("Listar Naves");
        itemListarNaves.addActionListener(e -> listarNaves());

        JMenuItem itemCadastrarNave = new JMenuItem("Cadastrar Nave");
        itemCadastrarNave.addActionListener(e -> cadastrarNave());

        menuNaves.add(itemListarNaves);
        menuNaves.add(itemCadastrarNave);

        menuBar.add(menuMissoes);
        menuBar.add(menuAstronautas);
        menuBar.add(menuNaves);

        setJMenuBar(menuBar);

        JLabel label = new JLabel("Sistema Viagem à Lua", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        add(label);
    }

    // =====================================================================
    //                          JANELAS DE CADASTRO
    // =====================================================================

    private void cadastrarMissao() {
        JTextField nome = new JTextField();
        JTextField destino = new JTextField();
        JTextField capacidade = new JTextField();

        Object[] campos = {
            "Nome:", nome,
            "Destino:", destino,
            "Capacidade da nave:", capacidade
        };

        int result = JOptionPane.showConfirmDialog(this, campos, "Cadastrar Missão", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                missaoService.cadastrarMissao(nome.getText(), destino.getText(),
                        Integer.parseInt(capacidade.getText()));
                JOptionPane.showMessageDialog(this, "Missão cadastrada!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
            }
        }
    }

    private void cadastrarAstronauta() {
        JTextField nome = new JTextField();
        JTextField espec = new JTextField();

        Object[] campos = {
            "Nome:", nome,
            "Especialidade:", espec
        };

        int result = JOptionPane.showConfirmDialog(this, campos, "Cadastrar Astronauta", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            astronautaService.cadastrarAstronauta(nome.getText(), espec.getText());
            JOptionPane.showMessageDialog(this, "Astronauta cadastrado!");
        }
    }

    private void cadastrarNave() {
        JTextField nome = new JTextField();
        JTextField capacidade = new JTextField();

        Object[] campos = {
            "Nome:", nome,
            "Capacidade:", capacidade
        };

        int result = JOptionPane.showConfirmDialog(this, campos, "Cadastrar Nave", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            naveService.cadastrarNave(nome.getText(), Integer.parseInt(capacidade.getText()));
            JOptionPane.showMessageDialog(this, "Nave cadastrada!");
        }
    }

    // =====================================================================
    //                                LISTAGENS
    // =====================================================================

    private void listarMissoes() {
        java.util.List<Missao> lista = missaoService.listarMissoes();
        JOptionPane.showMessageDialog(this, lista.toString());
    }

    private void listarAstronautas() {
        java.util.List<Astronauta> lista = astronautaService.listarAstronautas();
        JOptionPane.showMessageDialog(this, lista.toString());
    }

    private void listarNaves() {
        java.util.List<Nave> lista = naveService.listarNaves();
        JOptionPane.showMessageDialog(this, lista.toString());
    }

    // =====================================================================
    //                                MAIN
    // =====================================================================

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GuiApp().setVisible(true));
    }
}
