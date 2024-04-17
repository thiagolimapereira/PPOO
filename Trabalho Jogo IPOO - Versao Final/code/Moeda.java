import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Moeda here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Moeda extends Actor
{
    private GreenfootImage[] imagens;  // Array para armazenar diferentes frames da animação
    private int indiceImagemAtual;     // Índice da imagem atual na animação
    private int passosParaAtualizarImagem;  // Número de passos antes de atualizar a imagem
    private int passosDesdeUltimaAtualizacaoImagem;  // Contador de passos desde a última atualização da imagem

    public Moeda(){
        imagens = new GreenfootImage[10];
        // usamos um loop for que rodará 4 vezes
        for (int i = 0; i < imagens.length; i++) {
            imagens[i] = new GreenfootImage("Gold_" + (i+1) + ".png");
            imagens[i].scale(imagens[i].getWidth()/15 , imagens[i].getHeight()/15);
        }

        indiceImagemAtual = 0;
        setImage(imagens[indiceImagemAtual]);
        passosParaAtualizarImagem = 5;
        passosDesdeUltimaAtualizacaoImagem=0;
    }

    /**
     * Act - do whatever the Moeda wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
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
