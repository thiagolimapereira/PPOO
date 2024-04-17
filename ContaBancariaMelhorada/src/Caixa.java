import java.util.Scanner;
import java.util.ArrayList;

public class Caixa {
    private ArrayList<Conta> contas;

    public Caixa(){
        contas=new ArrayList<>();
    }

    public void executar(){
        tratarMenu();
    }

    public void exibirMenu(){
        System.out.println("1. Criar Conta\n" + 
                           "2. Consultar Saldo\n" + 
                           "3. Depositar\n" + 
                           "4. Sacar\n" +
                           "5. Transferir\n" +
                           "6. Remover Conta\n" +
                           "7. Imprimir Todas as Contas\n" +
                           "8. Filtrar Contas por Nome\n" +
                           "9. Sair\n" +
                           "\nDigite sua opção:\n");
    }

    public void tratarMenu(){
        Scanner entrada = new Scanner(System.in);   
        String acao;     

        do {
            exibirMenu();
            acao = entrada.nextLine();
            
            if (acao.equals("1")){
                criarConta(entrada);
            }
            else if (contas.isEmpty() && (acao.equals("2") || acao.equals("3") || acao.equals("4") || acao.equals("5") || acao.equals("6") || acao.equals("7") || acao.equals("8"))){
                System.out.println("Nenhuma conta cadastrada!\n");
            }
            else if (acao.equals("2")){
                consultarSaldo(entrada);
            }
            else if (acao.equals("3")){
                depositar(entrada);
            }
            else if (acao.equals("4")){
                sacar(entrada);
            }
            else if (acao.equals("5")){
                transferir(entrada);
            }
            else if (acao.equals("6")){
                removerConta(entrada);
            }
            else if (acao.equals("7")){
                imprimirContas();
            }
            else if (acao.equals("8")){
                filtrarPorNome(entrada);
            }
            else if (acao.equals("9"));
            else {
                System.out.println("Opcao invalida!\n");  
            }
        } while (!acao.equals("9"));

        entrada.close();
    }

    public void criarConta(Scanner entrada){
        System.out.println("\nDeseja criar uma conta com saldo pre-definido? [s/n]\n");
        
        String resposta = entrada.nextLine();
            
        while (!(resposta.equals("s") || resposta.equals("n"))){
            System.out.println("\nResposta invalida, responda 's' ou 'n'!\n");
            resposta = entrada.nextLine();
        }
        

        System.out.println("\nQual o nome do cliente?\n");
        String nome = entrada.nextLine();

        System.out.println("\nQual o CPF do cliente?\n");
        String cpf = entrada.nextLine();

        Cliente cliente = new Cliente(nome, cpf); 

        System.out.println("\nQual o limite da conta?\n");
        double limite = Double.parseDouble(entrada.nextLine());

        Conta conta;

        if (resposta.equals("s")){
            System.out.println("\nQual o saldo da conta?\n");
            double saldo = Double.parseDouble(entrada.nextLine());
            conta = new Conta(cliente, limite, saldo);
        }
        else{
            conta = new Conta(cliente, limite);
        }

        contas.add(conta);
    
        System.out.println("\nNumero da conta criada: " + conta.getNumeroConta() + "\n");       
    }

    public int AcharPosConta(int numConta) {
    	for (int i = 0; i<contas.size();i++) {
        		if (contas.get(i).getNumeroConta() == numConta) {
            		return i;
        	}
    	}
    	return -1;
	}


    public void consultarSaldo(Scanner entrada){
        System.out.println("Qual o número da conta que voce deseja consultar?");
        int numeroConta = Integer.parseInt(entrada.nextLine());

        int posConta = AcharPosConta(numeroConta);

        if (posConta>=0){
            System.out.println(contas.get(posConta).imprimirSaldo());
        }
        else{
            System.out.println("\nConta inexistente!\n");
        }        
    }

    public void depositar(Scanner entrada){
        System.out.println("\nQual a quantia a ser depositada?\n");

        double quantia = Double.parseDouble(entrada.nextLine());

        System.out.println("Qual o número da conta que voce deseja depositar?");
        int numeroConta = Integer.parseInt(entrada.nextLine());

        int posConta = AcharPosConta(numeroConta);

        if (posConta>=0){
            contas.get(posConta).depositar(quantia);;
        }
        else{
            System.out.println("\nConta inexistente!\n");
        }
    }

    public void sacar(Scanner entrada){
        System.out.println("\nQual a quantia a ser sacada?\n");

        double quantia = Double.parseDouble(entrada.nextLine());

        System.out.println("Qual o número da conta que voce deseja sacar?");
        int numeroConta = Integer.parseInt(entrada.nextLine());

        int posConta = AcharPosConta(numeroConta);

        if (posConta>=0){
            if (!contas.get(posConta).sacar(quantia)){
                System.out.println("Limite indisponivel!\n");
            }
        }
        else{
            System.out.println("\nConta inexistente!\n");
        }
       
    }

    public void transferir(Scanner entrada){
        System.out.println("\nQual a quantia a ser transferida?\n");

        double quantia = Double.parseDouble(entrada.nextLine());

        System.out.println("Qual o número da conta de origem?");
        int numeroContaOrigem = Integer.parseInt(entrada.nextLine());

        System.out.println("Qual o número da conta de destino?");
        int numeroContaDestino = Integer.parseInt(entrada.nextLine());

        int posContaOrigem = AcharPosConta(numeroContaOrigem);
        int posContaDestino = AcharPosConta(numeroContaDestino);
        
        if(posContaOrigem>=0 && posContaDestino>=0){
            if (!contas.get(posContaOrigem).transferir(quantia, contas.get(posContaDestino))){
                System.out.println("Limite indisponivel!\n");
            }
            else {
                System.out.println("Transferencia realizada com sucesso!\n");
            }
        }
        else{
            if (posContaOrigem<0) 
            System.out.println("\nConta de origem invalida!\n");
        
            if (posContaDestino<0) 
                System.out.println("\nConta de destino invalida!\n");
        }
    }

    public void imprimirContas(){
        for (Conta conta : contas) {
            System.out.println(conta.imprimirDados());
        }
        System.out.println("\n");
    }

    public void removerConta(Scanner entrada){
        System.out.println("Qual o número da conta que voce deseja remover?");
        int numeroConta = Integer.parseInt(entrada.nextLine());

        int posConta = AcharPosConta(numeroConta);

        if (posConta>=0){
            if (contas.get(posConta).getSaldo() == 0){
                contas.remove(posConta);
                System.out.println("\nConta removida com sucesso!\n");
            }
            else if (contas.get(posConta).getSaldo() > 0) 
                System.out.println("\nNao eh possivel remover contas com saldo positivo!\n");
            else 
            System.out.println("\nNao eh possivel remover contas em debito!\n");
        }
        else{
            System.out.println("\nConta inexistente!\n");
        }
    }

    public void filtrarPorNome(Scanner entrada){
        boolean achou = false;
        System.out.println("\nQual o nome que voce deseja filtrar?\n");
        String nome = entrada.nextLine().toLowerCase();

        for (Conta conta : contas) {
            if (conta.getCliente().getNome().toLowerCase().contains(nome)) {
                System.out.println(conta.imprimirDados());
                achou = true;
            }            
        }
        if (!achou) {
            System.out.println("\nNenhum cliente encontrado!");
        }
        System.out.println("\n");
    }
}
