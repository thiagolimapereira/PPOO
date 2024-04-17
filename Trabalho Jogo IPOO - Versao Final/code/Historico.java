import greenfoot.*;
import java.util.ArrayList;

/**
 * Classe que representa o histórico de jogadores e suas pontuações.
 * Mantém uma lista de jogadores e fornece métodos para adicionar jogadores ao histórico
 * e listar os jogadores mais recentes com suas pontuações e tempos.
 */
public class Historico
{
    private ArrayList<Jogador> historico;

    public Historico()
    {
        historico = new ArrayList<>();
    }

    /**
     * Adiciona um jogador ao histórico.
     */
    public void addJogador(Jogador jogador) {
        historico.add(jogador);
    }

    /**
     * Lista os jogadores mais recentes no histórico com suas pontuações e tempos.
     * Limita a exibição aos últimos 15 jogadores.
     */
    public String listarHistorico (){
        int tamanho = historico.size();
        int counter = 1;
        String message ="";
        while(tamanho - counter >=0 && counter <=15) {
            Jogador jogador = historico.get(tamanho - counter);
            message += "Jogador: ";
            message += addEspacos(jogador.getNome()); 
            message += " Score: " + jogador.getPontuacao() + "   Tempo: " + jogador.getTempo() + "\n";
            counter++;
        }
        return message;
    }

    /**
     * Adiciona espaços à string para formatar corretamente a exibição do nome do jogador.
     * Limita o tamanho total a 20 caracteres
     */
    private String addEspacos(String message){
        int tamanho = message.length();
        if (tamanho >= 11){
            message = message.substring(0 , 9) + "... ";
        }
        else {
            while (tamanho < 19) {
                message += " ";
                tamanho++;
            }
        }
        return message;
    }
}

