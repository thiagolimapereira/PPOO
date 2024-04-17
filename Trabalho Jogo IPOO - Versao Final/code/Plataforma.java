import greenfoot.*;

/**
 * A classe cria uma plataforma e define suas funções.
 */
public class Plataforma extends PlataformSuperClass {
    private int velocidadeHorizontal;

    /**
     * Controi uma plataforma com as caracteristicas iniciais padrao
     */
    public Plataforma(int velocidade, int largura, String textura) {
        super(velocidade, largura); // Cria uma plataforma com a velocidade especificada

        GreenfootImage plataforma = new GreenfootImage(textura); // Cria uma plataforma com as devidas proporções 
        plataforma.scale(largura,50);
        setImage(plataforma);
    }


    /**
     * Faz com que a plataforma se movimente em um movimento de vai e vem
     */
    public void act() {
        setLocation(getX() + getVelocidadeHorizontal(), getY());

        if (getX() <= 50 || getX() >= 800) {
            mudaVelocidadeHorizontal();
        }
    }
}
