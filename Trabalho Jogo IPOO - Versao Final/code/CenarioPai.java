import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * Classe que define o cenário do jogo, contendo os elementos do jogo.
 * 
 * @author (Thiago Lima Pereira - 202310057, André Araújo Mendonça - 202310982, Lilian Carla de Freitas - 202310885, Lucas Scommegna - 202310208)
 * @version (27/10/2023)
 */  

public class CenarioPai extends World {
    private Score score;
    private Passaro passaro;
    private Plataforma plataforma;
    private Ninho ninho;
    private GameSounds controladorDeSom;
    private int scoreController;
    private int controleMoeda;
    private boolean gameOver = false;
    private Historico historico;
    private Jogador jogador;
    private String textura;
    

    public CenarioPai(int largura, int altura, int celula, String imagemDeFundo, int recorde, Historico historico, String textura) {
        super(largura, altura, celula); //Define um mundo, com as escalas ainda não criadas
        setBackground(imagemDeFundo); // Define a imagem de fundo do mundo
        
        String nome = null;
        while (nome == null || nome.trim().isEmpty()){
            nome = Greenfoot.ask("Digite seu nome:");
        }
        jogador = new Jogador(nome);
        this.historico = historico;
        
        this.textura = textura;
        
        score = new Score(recorde); // Cria um novo objeto da classe Score
        passaro = new Passaro(); // Cria um novo objeto da classe Passaro
        plataforma = new Plataforma(Greenfoot.getRandomNumber(3) + 2, Greenfoot.getRandomNumber(80) + 75, textura); // Cria um novo objeto da classe Plataforma
        ninho = new Ninho(); // Cria um novo objeto da Classe Ninho
        controladorDeSom = new GameSounds(); //Controla os sons do jogo
        adicionarObjetosAoMundo(); // Adiciona atores ao mundo.
    }
    
     /**
     * Adiciona os atores principais (passaro, plataforma, ninho e score) ao mundo.
     */
    private void adicionarObjetosAoMundo() {
        addObject(passaro, getWidth() / 2, getHeight() - 830); // Adiciona a peça ao mundo
        addObject(plataforma, getWidth() / 2, getHeight() - 500); // Adiciona a plataforma ao mundo
        addObject(ninho, getWidth() / 2, getHeight() - 20); // Adiciona o ninho ao mundo
        addObject(score, 770, 45); // Adiciona o score no mundo
    }

    public void act() {
        if (!gameOver) {
            controladorDeSom.tocarSom("musicaDeFundo");
            controladorDeSom.alterarVolume();
            score.atualizarScore();
            // Verifica se o passaro passou dos limites de altura do mundo
            if (passaro.getY() >= 880) {
                controladorDeSom.tocarSom("somMorte");
                removerEAdicionarPassaro();
                score.atualizarPontosScore(-1); // Caso tenha passado dos limites de altura, perde um ponto
            }
            // Verifca se o passaro caiu no ninho
            if (passaro.verificarToqueNinho()) {
                controladorDeSom.tocarSom("somNinho");
                removerEAdicionarPassaro();
                score.atualizarPontosScore(1); // Ganha um ponto caso o passaro tenha caido dentro do ninho
            }
            // Verifica se o passaro tocou na plataforma
            if (passaro.verificarToquePlataforma()) {
                controladorDeSom.tocarSom("somMorte");
                removerEAdicionarPassaro();
                score.atualizarPontosScore(-5); // Perde 5 pontos de score caso tenha encostado na plataforma
            }
            // Verifca se o passaro tocou alguma moeda
            if (passaro.verificarToqueMoeda()) {
                controladorDeSom.tocarSom("somMoeda");
                score.atualizarPontosScore(5); // Ganha 5 pontos caso o passaro tenha pegado uma moeda
            }
            
            // Condição para aumentar a dificuldade do jogo
            if (score.getScore() >= (scoreController+10) && score.getScore() > 0 && score.getScore() != scoreController && score.getScore() > scoreController) {
                aumentarDificuldadeJogo();
            }
            
            //A cada 7 pontos, terá 25% de chance de aparecer uma moeda em um local aleatório do jogo
            if (score.getScore() % 5 == 0 && score.getScore() > 0 && score.getScore() != controleMoeda && score.getScore() > controleMoeda) {
                if(Greenfoot.getRandomNumber(4) == 1){
                    Moeda moeda = new Moeda();
                    addObject(moeda, obterNumeroAleatorioEntreValores(15, 820),obterNumeroAleatorioEntreValores(25, 815));
                }
                controleMoeda = score.getScore();
            }
            
            // Condição de derrota
            if (score.getScore() < 0) {
                //historico.getScores(score.getRecorde());
                jogador.setDados(score.getRecordeJogador(), score.tempoDecorrido());
                historico.addJogador(jogador);
                encerrarJogo("somJogoPerdido", "GameOver, aperte enter para voltar ao menu principal!");
            }
            
            // Condicao de vitoria
            if (score.getScore() >= 100) {
                jogador.setDados(score.getRecordeJogador(), score.tempoDecorrido());
                historico.addJogador(jogador);
                encerrarJogo("somJogoGanho", "Parabens! Voce terminou o jogo. Obrigado por jogar.\nAperte enter para voltar ao menu principal!");
            }
        } else {
            reiniciarJogoAoPressionarEnter();
        }
    }
    // Remove o passaro existente, e adiciona outro objeto passaro na posição inicial
    private void removerEAdicionarPassaro() {
        removeObject(passaro);
        passaro = new Passaro();
        addObject(passaro, getWidth() / 2, getHeight() - 830);
    }
    
    // Aumenta a dificuldade do jogo
    private void aumentarDificuldadeJogo() {
        scoreController = score.getScore();
        int possibilidade = Greenfoot.getRandomNumber(4);
        
        if (possibilidade<=2){
            Plataforma novaPlataforma = new Plataforma(Greenfoot.getRandomNumber(6) + 1, Greenfoot.getRandomNumber(125) + 75, textura);
                addObject(novaPlataforma, getWidth() / 2, getHeight() - obterNumeroAleatorioEntreValores(300, 600));
        }
        else{
            ninho.aumentaVelocidadeHorizontal();
        }

    }

    private void encerrarJogo(String somEncerramento, String mensagemEncerramento) {
        gameOver = true;
        controladorDeSom.pararMusicaDeFundo();
        controladorDeSom.tocarSom(somEncerramento);
        showText(mensagemEncerramento, getWidth() / 2, getHeight() / 2);
    }

    private void reiniciarJogoAoPressionarEnter() {
         // Tela de fim de jogo, esperando pela tecla Enter
        if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new MenuInicial(score.getRecorde(), historico));  // Reinicia o jogo
            gameOver = false; // Reseta o estado de fim de jogo
        }
    }
    
    
    // Retorna um numero aleatório que esteja entre os valores de inicio e fim
    public int obterNumeroAleatorioEntreValores(int inicio, int fim) {
        int random = Greenfoot.getRandomNumber(fim - inicio + 1);
        return random + inicio;
    }
}

