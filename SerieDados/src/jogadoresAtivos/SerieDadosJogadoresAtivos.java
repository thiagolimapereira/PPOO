package jogadoresAtivos;

import series.Periodicidade;
import series.SerieDados;
import java.util.HashMap;

public class SerieDadosJogadoresAtivos implements SerieDados{
    private String identificacao;
    private int inicioPeriodo;
    private int fimPeriodo;
    private Periodicidade periodicidade;
    private HashMap<Integer, Double> dados;

    public SerieDadosJogadoresAtivos(String identificacao, int inicioPeriodo, int fimPeriodo, Periodicidade periodicidade){
        this.identificacao = identificacao;
        this.inicioPeriodo = inicioPeriodo;
        this.fimPeriodo = fimPeriodo;
        this.periodicidade = periodicidade;
        dados = new HashMap<>();
    }
    
    @Override
    public String obterIdentificacao(){
        return identificacao;
    }

    @Override
    public int obterInicioPeriodo(){
        return inicioPeriodo;
    }

    @Override
    public int obterFimPeriodo(){
        return fimPeriodo;
    }

    @Override
    public Periodicidade obterPeriodicidade(){
        return periodicidade;
    }

    @Override
    public double obterDado(int periodo){
        if (periodo < inicioPeriodo || periodo > fimPeriodo){
            System.out.println("O periodo informado esta fora do intervalo definido pera a serie de dados!");
        }
        return dados.get(periodo);     
    }

    public void adicionarDado(int periodo, double dado){
        if (periodo < inicioPeriodo || periodo > fimPeriodo){
            System.out.println("O periodo informado esta fora do intervalo definido pera a serie de dados!");
            return;
        }
        dados.put(periodo, dado);
    }
}