import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ninho here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ninho extends PlataformSuperClass
{
    // O passarinho deve pousar no ninho para pontuar

    public Ninho() {
        super(1); // Velocidade horizontal inicial

        GreenfootImage ninho = new GreenfootImage("ninho.png");  
        ninho.scale(150,50);
        setImage(ninho);
    }

    public void act()
    {
        setLocation(getX() + getVelocidadeHorizontal(), getY());
        // Verifica se o ninho atingiu os limites laterais do mundo

        if (getX() <= 50 || getX() >= 800) {
            mudaVelocidadeHorizontal();
        }
    }

}
