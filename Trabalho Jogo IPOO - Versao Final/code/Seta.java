import greenfoot.*;

/**
 * Classe que representa uma seta que pode ser movida para cima ou para baixo entre cinco posições.
 * As posições são usadas para selecionar opções em um menu.
 * 
 * A seta possui uma imagem que é ajustada para representar visualmente sua posição.
 * A posição é usada para determinar a seleção do menu.
 */
public class Seta extends Actor {
    private GreenfootImage imagem; // Imagem da seta
    private int posicao; // Representa a posição da seta no menu

    /**
     * Construtor da classe Seta. Inicializa a imagem e a posição da seta.
     */
    public Seta(){
        imagem = new GreenfootImage("SetaDireita.png");
        imagem.scale(imagem.getWidth()/5 , imagem.getHeight()/5);
        setImage(imagem); // Define a imagem do ator

        posicao = 1; // Inicializa a posição como 1 por padrão
    }

    /**
     * Obtém a posição atual da seta.
     */
    public int getPosicao () {
        return posicao;
    }

    /**
     * Define a posição da seta no menu.
     */
    public void setPosicao (int posicao){
        this.posicao = posicao;
    }

    /**
     * Move a seta para baixo no menu, alterando sua posição.
     */
    public void moverBaixo (){
        if (posicao < 4){
            posicao++;
        } else {
            posicao = 1; // Volta para a primeira posição se estiver na última
        }
        mudarPosicao();
    }

    /**
     * Move a seta para cima no menu, alterando sua posição.
     */
    public void moverCima (){
        if (posicao > 1){
            posicao--;
        } else {
            posicao = 4; // Volta para a última posição se estiver na primeira
        }
        mudarPosicao();
    }

    /**
     * Atualiza a posição visual da seta com base na posição lógica.
     */
    public void mudarPosicao (){
        // Define as coordenadas da seta com base na posição
        if (posicao == 1){
            setLocation(298 ,303);
            
        } else if (posicao == 2){
            setLocation(284 ,365);
        
        } else if (posicao == 3){
            setLocation(329 ,429);
            
        } else if (posicao == 4){
            setLocation(299 ,492);
            
        } else if (posicao == 5){
            setLocation(345 ,830);
        }
    }
}
