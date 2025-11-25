package br.com.lunarsystems.missoes.model;

import br.com.lunarsystems.missoes.model.enums.ResultadoMissao;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Missao implements Serializable {

    private String codigo;
    private String nome;
    private LocalDate dataLancamento;
    private LocalDate dataRetorno;
    private String destino;
    private String objetivo;
    private ResultadoMissao resultado;
    private Nave nave;
    private List<Astronauta> astronautas = new ArrayList<>();

    public Missao(String codigo, String nome, LocalDate dataLancamento,
                  String destino, String objetivo, Nave nave) {

        this.codigo = codigo;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.destino = destino;
        this.objetivo = objetivo;
        this.nave = nave;
        this.resultado = ResultadoMissao.EM_ANDAMENTO;
    }

    public String getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public LocalDate getDataLancamento() { return dataLancamento; }
    public LocalDate getDataRetorno() { return dataRetorno; }
    public String getDestino() { return destino; }
    public String getObjetivo() { return objetivo; }
    public ResultadoMissao getResultado() { return resultado; }
    public Nave getNave() { return nave; }
    public List<Astronauta> getAstronautas() { return astronautas; }

    public void setDataRetorno(LocalDate dataRetorno) { this.dataRetorno = dataRetorno; }

    public void setResultado(ResultadoMissao resultado) { this.resultado = resultado; }

    public long getDuracaoDias() {
        if (dataRetorno == null) return 0;
        return ChronoUnit.DAYS.between(dataLancamento, dataRetorno);
    }

    public void adicionarAstronauta(Astronauta a) {
        astronautas.add(a);
    }

    @Override
    public String toString() {
        return "Missão " + nome + " (" + codigo + ") - " + destino +
                "\nObjetivo: " + objetivo +
                "\nNave: " + nave +
                "\nAstronautas: " + astronautas.size() +
                "\nStatus: " + resultado;
    }
}
