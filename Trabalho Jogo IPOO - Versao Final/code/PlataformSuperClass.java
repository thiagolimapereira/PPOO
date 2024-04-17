import greenfoot.*;

/**
 * Write a description of class PlataformSuperClass here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlataformSuperClass extends Actor
{
    private int velocidadeHorizontal;
    private int largura;

    /**
     * Constructor for objects of class PlataformSuperClass
     */
    public PlataformSuperClass(int velocidadeHorizontal)
    {
        this.velocidadeHorizontal = velocidadeHorizontal;
    }

    /**
     * Construtor para objetos da classe PlataformSuperClass.
     * Inicializa a plataforma com uma velocidade horizontal e largura.
     */
    public PlataformSuperClass(int velocidadeHorizontal, int largura) {
        this.velocidadeHorizontal = velocidadeHorizontal;
        this.largura = largura;
    }

    /**
     * Obtem a velocidade horizontal da plataforma.
     */
    public int getVelocidadeHorizontal() {
        return velocidadeHorizontal;
    }

    /**
     * Muda a direção horizontal de movimentação da plataforma
     */
    public void mudaVelocidadeHorizontal() {
        velocidadeHorizontal = -velocidadeHorizontal;
    }

    /**
     * Aumenta a velocidade horizontal da plataforma.
     * A velocidade é aumentada em 3 unidades, mantendo a direção.
     */
    public void aumentaVelocidadeHorizontal() {
        if(velocidadeHorizontal > 0) {
            velocidadeHorizontal += 3; 
        } else {
            velocidadeHorizontal -= 3;
        }
    }
}
