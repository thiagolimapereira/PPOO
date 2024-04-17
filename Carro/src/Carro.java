public class Carro {

	// Atributos
	private int velocidade;
	private String modelo;

	// Construtor
	public Carro(String modelo, int velocidade) {
		this.modelo=modelo;
        this.velocidade=velocidade;		
	}

	// Método para retornar a velocidade
	public int getVelocidade() {
		return velocidade;
	}
    // Método para retornar o modelo
	public String getModelo() {
		return modelo;
	}

	// Método para acelerar o carro
    public void acelerar(){
        velocidade+=5;
    }

	// Método para reduzir a velocidade do carro
    public void reduzir(){
        velocidade-=5;
    }
}
