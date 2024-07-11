import jogadoresAtivos.Dados;
import series.SerieDados;
import series.VisualizadorSeries;

import java.util.List;

public class Principal {
    public void executar() {
        Dados dados = new Dados();

        List<SerieDados> serieDados = dados.obterSeries();
        
        /*for (SerieDados s : serieDados) {
            System.out.println("Dados da Serie " + s.obterIdentificacao() + " (" + s.obterPeriodicidade() + ")");
            for (int i = s.obterInicioPeriodo(); i <= s.obterFimPeriodo(); i++) {
                System.out.println("Periodo " + i + ": " + (int)s.obterDado(i));
            }
            System.out.println();
        }*/

        VisualizadorSeries visualizador = new VisualizadorSeries(serieDados);

        visualizador.exibir();
    }
}
