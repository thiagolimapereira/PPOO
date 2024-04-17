import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MenuInicial here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MenuInicial extends World
{
    private Seta seta; //seta Um objeto Seta que indica a posição no menu.
    private boolean teclaPressionada; //teclaPressionada Indica se uma tecla foi pressionada para evitar múltiplas ações.
    private int posicaoSeta; //posicaoSeta A posição atual da seta no menu.
    private GreenfootImage fundo; //fundo Imagem de fundo do menu
    private int recorde; // recorde Pontuação recorde do jogo
    private Historico historico; //historico Objeto histórico para registrar dados do jogador.

    public MenuInicial() {    
        super(900, 900, 1); 
        fundo = new GreenfootImage("SabiaSilvestre.png");
        setBackground(fundo);

        seta= new Seta ();
        addObject(seta, 298 , 303);
        recorde=0;

        historico = new Historico (); 
    }

    /**
     * Construtor sobrecarregado para o menu inicial.
     * Inicializa o mundo do menu com uma imagem de fundo padrão, uma seta, e um histórico existente.
     */

    public MenuInicial(int recorde,Historico historico) {    
        super(900, 900, 1); 
        fundo = new GreenfootImage("SabiaSilvestre.png");
        setBackground(fundo);

        seta= new Seta ();
        addObject(seta, 298 , 303);
        this.recorde=recorde;
        this.historico = historico;
    }

    public void act(){
        // Verifica se a tecla "enter" foi pressionada e se uma ação ainda não foi executada
        if (Greenfoot.isKeyDown("enter") && !teclaPressionada) {
            // Obtém a posição atual da seta no menu
            posicaoSeta = seta.getPosicao();

            // Navega para o cenário correspondente à posição da seta
            if(posicaoSeta == 1) {
                removeObject(seta);
                Greenfoot.setWorld(new CenarioDia(recorde, historico));
            }

            if(posicaoSeta == 2){
                removeObject(seta);
                Greenfoot.setWorld(new CenarioNoite(recorde, historico));
            }

            // Exibe o histórico na tela ao selecionar a opção correspondente
            if (posicaoSeta == 3){
                seta.setPosicao(5);
                seta.mudarPosicao();
                fundo = new GreenfootImage("histórico.png");
                setBackground(fundo);

                showText(historico.listarHistorico(), 435, 520);
            }

            // Exibe as instruções do jogo ao selecionar a opção correspondente
            if (posicaoSeta == 4){
                seta.setPosicao(5);
                seta.mudarPosicao();
                fundo = new GreenfootImage("ComoJogar.png");
                setBackground(fundo);
            }

            // Reinicia a tela, removendo a exibição do histórico ou instruções
            if (posicaoSeta == 5){
                showText("", 435, 520);
                seta.setPosicao(1);
                seta.mudarPosicao();
                fundo = new GreenfootImage("SabiaSilvestre.png");
                setBackground(fundo);
            }

            // Evita múltiplas execuções da ação enquanto a tecla estiver pressionada
            teclaPressionada = true;
        }

        // Verifica se a tecla "up" foi pressionada, se uma ação ainda não foi executada e a posição da seta é válida
        if (Greenfoot.isKeyDown("up") && !teclaPressionada && seta.getPosicao() > 0 && seta.getPosicao() < 5) {
            // Move a seta para cima no menu
            seta.moverCima();
            // Evita múltiplas execuções da ação enquanto a tecla estiver pressionada
            teclaPressionada = true;
        }

        // Verifica se a tecla "down" foi pressionada, se uma ação ainda não foi executada e a posição da seta é válida
        if (Greenfoot.isKeyDown("down") && !teclaPressionada && seta.getPosicao() > 0 && seta.getPosicao() < 5) {
            // Move a seta para baixo no menu
            seta.moverBaixo();
            // Evita múltiplas execuções da ação enquanto a tecla estiver pressionada
            teclaPressionada = true;
        }

        // Reinicia o estado da tecla pressionada quando nenhuma tecla relevante está sendo pressionada
        if (!Greenfoot.isKeyDown("enter") && !Greenfoot.isKeyDown("up") && !Greenfoot.isKeyDown("down")) {
            teclaPressionada = false;
        }
    }

}
