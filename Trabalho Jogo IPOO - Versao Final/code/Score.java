import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A classe cria um placar com pontos atuais e recorde geral.
 */
public class Score extends Actor
{
    private int score;
    private int recorde;
    private int recordeJogador;
    private long tempoInicial;

    /**
     * Inicializa um novo placar com um recorde definido
     */
    public Score(int recorde){
        score = 0;
        this.recorde = recorde;
        recordeJogador=0;
        tempoInicial = System.currentTimeMillis();
        atualizarScore();
    }

    /**
     * Mostra na tela o placar contendo os pontos atuais e o recorde
     */
    public void atualizarScore()
    {
        setImage(new GreenfootImage("Pontos: " + score + "\nRecorde: " + recorde + "\nTempo: "+ tempoDecorrido(), 24, Color.WHITE, null));
    }

    /**
     * Atualiza o placar de acordo com os pontos adquiridos
     */
    public void atualizarPontosScore(int pontos)
    {
        score += pontos;

        if (score > recorde){
            recorde = score;
        }

        if (score > recordeJogador){
            recordeJogador = score;
        }

        atualizarScore();
    }

    /**
     * Retorna o valor do score 
     */
    public int getScore() {
        return score;
    }

    /**
     * Retorna o valor do recorde 
     */
    public int getRecorde() {
        return recorde;
    }

    /**
     * Retorna o valor do recorde do jogador
     */
    public int getRecordeJogador() {
        return recordeJogador;
    }

    /**
     * Zera o placar
     */
    public void zerarScore()
    {
        score = 0;
        atualizarScore();
    }

    /**
     * Calcula e formata o tempo decorrido desde o tempo inicial em minutos e segundos.
     */
    public String tempoDecorrido() {
        // Calcula a diferença entre o tempo atual e o tempo inicial
        long tempoDecorrido = System.currentTimeMillis() - tempoInicial;

        // Converte milissegundos para minutos e segundos
        long segundos = tempoDecorrido / 1000;
        long minutos = segundos / 60;
        segundos = segundos % 60;

        // Formata a string no formato "MM:SS"
        return String.format("%02d:%02d", minutos, segundos);
    }

}
