import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * A classe cria um passaro e define suas funções.
 */
public class Passaro extends Actor
{
    private int largura;
    private int altura;

    private int pulos;

    private GreenfootImage[] imagens;
    private int indiceImagemAtual;
    private int passosParaAtualizarImagem;
    private int passosDesdeUltimaAtualizacaoImagem;

    private GameSounds controladorDeSom;

    private int velocidade;

    private boolean botaoPressionadoD;
    private boolean fezAcaoD;
    private int tempoPressionadoD;

    private boolean botaoPressionadoE;
    private boolean fezAcaoE;
    private int tempoPressionadoE;

    private boolean botaoPressionadoC;
    private boolean fezAcaoC;
    private int contagemPulo;

    private boolean virado;
    private boolean viradoPraDireita;

    /**
     * Controi uma peca com as caracteristicas iniciais padrao
     */
    public Passaro()
    {  
        // tamanho padrao do passaro
        largura = 40;
        altura = 40; 

        pulos = 3;

        // velocidade inicial do passaro
        velocidade = 2; 

        //definicao padrao dos estados
        botaoPressionadoD = false;
        fezAcaoD = false;
        tempoPressionadoD = 0;
        botaoPressionadoE = false;
        fezAcaoE = false;
        tempoPressionadoE = 0;
        botaoPressionadoC = false;
        fezAcaoC = false;
        contagemPulo = 0;
        virado = false;
        viradoPraDireita = true;

        controladorDeSom = new GameSounds();

        imagens = new GreenfootImage[4];
        // usamos um loop for que rodará 4 vezes
        for (int i = 0; i < imagens.length; i++) {
            imagens[i] = new GreenfootImage(i+1 + ".png");
        }
        imagens[0].scale(40,28);
        imagens[1].scale(40,23);
        imagens[2].scale(40,28);
        imagens[3].scale(40,18);
        indiceImagemAtual = 0;
        setImage(imagens[indiceImagemAtual]);
        passosParaAtualizarImagem = 13;
        passosDesdeUltimaAtualizacaoImagem=0;
    }

    /**
     * Move o passaro pra direita
     */
    public void moverParaDireita()
    {
        if (!viradoPraDireita){
            inverterImagem();
            viradoPraDireita = true;
        }
        setLocation(getX()+9, getY());
    }

    /**
     * Move o passaro pra esquerda
     */
    public void moverParaEsquerda()
    {
        if (viradoPraDireita){
            inverterImagem();
            viradoPraDireita = false;
        }
        setLocation(getX()-9, getY());
        
    }

    /**
     * Faz o passaro cair
     */
    public void cair()
    {
        setLocation(getX(), getY()+velocidade);
    }

    /**
     * Move o passaro mais rapidamente para baixo
     */
    public void  acelerar()
    {
        setLocation(getX(), getY() + 5*velocidade);
    }

    /**
     * Faz com que o pássaro pule
     */
    public void  pular()
    {
        pulos -= 1;
        contagemPulo = 7;
    }

    /**
     * Aumenta a velocidade dos movimentos do jogo
     */
    public void aumentaVelocidade()
    {
        velocidade += 2;
    }

    /**
     * Verifica para que a peca se mova a cada acionamento
     * e se continuar segurando o botão, de tempos em tempos 
     * ela se moverá novamente 
     */
    public void  verificarPulo(){
        // Verifica se o botão seta para a esquerda foi pressionada
        if (pulos > 0){
            if (Greenfoot.isKeyDown("up")) {
                botaoPressionadoC = true;
            } else {
                botaoPressionadoC = false;
            }

            // Verifica se a seta para a esquerda foi pressionada e a peça ainda não moveu
            if (botaoPressionadoC && !fezAcaoC) {
                controladorDeSom.tocarSom("somDePulo");
                pular();
                fezAcaoC = true; // Define a variável de controle como true
            }

            // Verifica se a seta para esquerda foi liberada
            if (!botaoPressionadoC) {
                fezAcaoC = false; // Redefine a variável de controle para que a peça possa se mover no primeiro clique novamente
            }
        }
    }

    public void  verificarBaixo(){
        // Verifica se o botão seta para a esquerda foi pressionada
        if (Greenfoot.isKeyDown("down")) {
            acelerar(); 
            setImage(imagens[3]);
            if (viradoPraDireita){
            setRotation(90);
        }
        else{
            setRotation(270);
        }
            virado = true;
        } 

        if (!Greenfoot.isKeyDown("down") && virado){
            setRotation(0);
            virado = false;
        }
    }

    /**
     * Verifica se o passaro toca o ninho
     */
    public boolean verificarToqueNinho() {
        boolean tocou = false;
        if (isTouching(Ninho.class)){
            tocou = true;
        }
        return tocou;
    }

    /**
     * Verifica se o passaro toca alguma plataforma
     */
    public boolean verificarToquePlataforma() {
        boolean tocou = false;
        if (isTouching(Plataforma.class)){
            tocou = true;
        }
        return tocou;
    }

    /**
     * Verifica se o passaro toca alguma moeda
     */
    public boolean verificarToqueMoeda() {
        boolean tocou = false;
        if (isTouching(Moeda.class)){
            removeTouching(Moeda.class);
            tocou = true;
        }
        return tocou;
    }

    private void inverterImagem()
    {
        for (GreenfootImage imagem : imagens) {
            imagem.mirrorHorizontally();
        }
        setImage(imagens[indiceImagemAtual]);
    }

    public void act()
    {        
        cair(); // Faz a peça cair gradualmente.
        

        if (getX() < 820){ 
            if (Greenfoot.isKeyDown("right")) moverParaDireita();
        } // Verifica se a peça pode se mover para a direita e chama o método da classe Peca
        if (getX() > 20){ 
            if (Greenfoot.isKeyDown("left")) moverParaEsquerda();
        } // Verifica se a peça pode se mover para a esquerda e chama o método da classe Peca
        verificarBaixo();
        if (getY() > 25) verificarPulo();
        if(contagemPulo > 0){
            setLocation(getX(), getY() - 8*velocidade);
            contagemPulo--;
        }

        passosDesdeUltimaAtualizacaoImagem++;
        // se já se passaram os passos necessários para atualizar a imagem
        if (passosDesdeUltimaAtualizacaoImagem >= passosParaAtualizarImagem) {
            // a imagem será atualizada, então voltamos o valor da variável para zero
            passosDesdeUltimaAtualizacaoImagem = 0;

            // e atualizamos a imagem (é o código que já tínhamos feito antes)
            indiceImagemAtual++;
            if (indiceImagemAtual >= imagens.length) {
                indiceImagemAtual = 0;
            }
            setImage(imagens[indiceImagemAtual]);
        }
    }
}
