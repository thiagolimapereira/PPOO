package jogadoresAtivos;

import series.Periodicidade;
import series.SerieDados;
import java.util.ArrayList;

public class Dados {
    private SerieDadosJogadoresAtivos jogadoresAtivosCS;
    private SerieDadosJogadoresAtivos jogadoresAtivosValorant;
    
    public Dados(){
        jogadoresAtivosCS = new SerieDadosJogadoresAtivos("Jogadores de CS",
         1, 10, Periodicidade.DIARIA);
        
        jogadoresAtivosCS.adicionarDado(1, 130000);
        jogadoresAtivosCS.adicionarDado(2, 190000);
        jogadoresAtivosCS.adicionarDado(3, 150000);
        jogadoresAtivosCS.adicionarDado(4, 110000);
        jogadoresAtivosCS.adicionarDado(5, 140000);
        jogadoresAtivosCS.adicionarDado(6, 180000);
        jogadoresAtivosCS.adicionarDado(7, 200000);
        jogadoresAtivosCS.adicionarDado(8, 210000);
        jogadoresAtivosCS.adicionarDado(9, 170000);
        jogadoresAtivosCS.adicionarDado(10, 120000);

        jogadoresAtivosValorant = new SerieDadosJogadoresAtivos("Jogadores de Valorant",
         1, 10, Periodicidade.DIARIA);

        jogadoresAtivosValorant.adicionarDado(1, 140000);
        jogadoresAtivosValorant.adicionarDado(2, 170000);
        jogadoresAtivosValorant.adicionarDado(3, 190000);
        jogadoresAtivosValorant.adicionarDado(4, 180000);
        jogadoresAtivosValorant.adicionarDado(5, 150000);
        jogadoresAtivosValorant.adicionarDado(6, 130000);
        jogadoresAtivosValorant.adicionarDado(7, 110000);
        jogadoresAtivosValorant.adicionarDado(8, 90000);
        jogadoresAtivosValorant.adicionarDado(9, 100000);
        jogadoresAtivosValorant.adicionarDado(10, 120000);
    }

    public ArrayList<SerieDados> obterSeries(){
        ArrayList<SerieDados> lista = new ArrayList<>();
        lista.add(jogadoresAtivosCS);
        lista.add(jogadoresAtivosValorant);
        return lista;
    }
}