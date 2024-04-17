import greenfoot.*;

/**
 * Controlador de sons
 */
public class GameSounds  
{
    private GreenfootSound musicaDeFundo;
    private GreenfootSound somMorte;
    private GreenfootSound somNinho;
    private GreenfootSound somJogoPerdido;
    private GreenfootSound somJogoGanho;
    private GreenfootSound somDePulo;
    private GreenfootSound somDeMoeda;

    private int volume;

    public GameSounds() {
        musicaDeFundo = new GreenfootSound("Som1.mp3");// Som de fundo do jogo
        somNinho = new GreenfootSound("hitNest.mp3"); //Som de pontuação
        somMorte = new GreenfootSound("deathSound.mp3"); //som de morte
        somJogoPerdido = new GreenfootSound("gameLost.mp3"); //som de perder o jogo
        somJogoGanho = new GreenfootSound("victory.mp3"); //som de vitoria
        somDePulo = new GreenfootSound("jump.mp3"); //som de pulo
        somDeMoeda = new GreenfootSound("money.mp3"); //som de pulo

        volume = 50;
    }

    //Altera o volume do jogo
    public void alterarVolume(){
        if ( Greenfoot.isKeyDown("-") ){
            volume--;
        }
        if( Greenfoot.isKeyDown("p")){
            volume++ ;
        }
        ajustarTodosVolumes();

    }

    //Ajusta todos os sons do jogo de maneira otimizada
    private void ajustarTodosVolumes() {
        GreenfootSound[] todosSons = {musicaDeFundo, somNinho, somMorte, somJogoPerdido, somJogoGanho, somDePulo};
        for (GreenfootSound som : todosSons) {
            som.setVolume(volume);
        }
    }

    //Toca som
    public void tocarSom(String som) {
        switch(som) {
            case "musicaDeFundo":
                musicaDeFundo.playLoop();
                break;

            case "somNinho":
                somNinho.play();
                break;

            case "somMorte":
                somMorte.play();
                break;

            case "somJogoPerdido":
                somJogoPerdido.play();
                break;

            case "somJogoGanho":
                somJogoGanho.play();
                break;

            case "somDePulo":
                somDePulo.play();
                break;
            
            case "somMoeda":
                somDeMoeda.play();
                break;
        }
    }

    //Para a musica de fundo
    public void pararMusicaDeFundo() {
        musicaDeFundo.stop();
    }
}
