import java.util.Scanner;

public class Principal {
    private Carro[] carro; // Vetor para armazenar os carros
    private int posicoesOcupadas; // Número de posições ocupadas no vetor de carros

    public Principal (){
        carro = new Carro[5]; // Inicializa o vetor
        posicoesOcupadas = 0; // Inicializa o número de posições ocupadas como 0
    }

    public void executar() {
        exibirMenu(); // Chama o método para exibir o menu
        tratarMenu(); // Chama o método para tratar as opções do menu
    }

	// Exibe as opções do menu
    private void exibirMenu(){
        System.out.println("\n1)  Criar o carro\n" + 
                        "2)  Acelerar o carro\n" + 
                        "3)  Reduzir a velocidade\n" + 
                        "4)  Exibir os dados do(s) carro(s)\n" + 
                        "5)  Sair\n" + 
                        "\nDigite sua opção:\n");
    }

	// Método para encontrar um carro no vetor pelo modelo
    private int encontrarCarro(String modelo){
        for (int i=0; i<posicoesOcupadas; i++) {
            if (carro[i].getModelo().toLowerCase().equals(modelo.toLowerCase())) {
                return i; // Retorna o índice do carro se encontrado
            }
        }
        return -1; // Retorna -1 se o carro não for encontrado
    }

	// Método para tratar o menu
    private void tratarMenu(){
        Scanner entrada = new Scanner(System.in);
        String acao;
        acao = entrada.nextLine(); 

        // Loop para continuar executando o menu até que a opção "5" (Sair) seja selecionada
        while (!acao.equals("5")){
            if (carro[0] == null && !acao.equals("1")){
                System.out.println("\nComando invalido!\n");
            }
            else if (acao.equals("1")) {
                // Opção para criar um novo carro
                if (posicoesOcupadas < 5){
                    String modelo;
                    System.out.println("Qual o modelo do carro?\n");
                    modelo = entrada.nextLine();
                    carro[posicoesOcupadas] = new Carro(modelo, 0); // Cria um novo objeto Carro e adiciona ao vetor
                    posicoesOcupadas++;
                }
                else {
					// Opção se o vetor estiver cheio
                    System.out.println("Nao eh possivel adicionar mais carros!\n");
                }
                exibirMenu();
            }
            else if (acao.equals("2")) {
                // Opção para acelerar um carro
                String modelo;
                int posCarro;
                System.out.println("Qual carro voce deseja acelerar?\n");
                modelo = entrada.nextLine();
                posCarro = encontrarCarro(modelo);
                if (posCarro != -1) {
                    carro[posCarro].acelerar(); // Chama o método acelerar() do objeto Carro
                }
                else {
                    System.out.println("Modelo nao encontrado!\n");
                }               
                exibirMenu();
            }
            else if (acao.equals("3")) {
                // Opção para reduzir a velocidade de um carro
                String modelo;
                int posCarro;
                System.out.println("Qual carro voce deseja desacelerar?\n");
                modelo = entrada.nextLine();
                posCarro = encontrarCarro(modelo);
                if (posCarro != -1) {
                    carro[posCarro].reduzir(); // Chama o método reduzir() do objeto Carro
                }
                else {
                    System.out.println("Modelo nao encontrado!\n");
                }               
                exibirMenu();
            }
            else if (acao.equals("4")) {
                // Opção para exibir os dados de todos os carros
                for (int i=0; i<posicoesOcupadas; i++) {
                    System.out.println("\nModelo: " + carro[i].getModelo() + "\nVelocidade: " + carro[i].getVelocidade() + "\n");
                }
                
                exibirMenu();
            }
            else {
                System.out.println("\nComando invalido!\n");
            }
                
            acao = entrada.nextLine(); // Solicita uma nova entrada do usuário para a próxima iteração do loop
        }

        entrada.close(); // Fecha o Scanner
    }
}
