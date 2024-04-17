import java.util.Scanner;

public class Caixa {
    private Conta conta;
    private Conta conta2;

    public void executar(){
        tratarMenu();
    }

    public void exibirMenu(){
        System.out.println("1. Criar Contas\n" + 
                           "2. Consultar Saldo\n" + 
                           "3. Depositar\n" + 
                           "4. Sacar\n" +
                           "5. Transferir\n" +
                           "6. Sair\n" +
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
            else if (conta == null && (acao.equals("2") || acao.equals("3") || acao.equals("4") || acao.equals("5"))){
                System.out.println("Conta inexistente!\n");
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
            else if (acao.equals("6"));
            else {
                System.out.println("Opcao invalida!\n");  
            }
        } while (!acao.equals("6"));

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

        if (resposta.equals("s")){
            System.out.println("\nQual o saldo da conta?\n");
            double saldo = Double.parseDouble(entrada.nextLine());

            if(conta == null) conta = new Conta(cliente, limite, saldo);
            else conta2 = new Conta(cliente, limite, saldo);
        }
        else{
            if(conta == null) conta = new Conta(cliente, limite);
            else conta2 = new Conta(cliente, limite);
        }

        

        if (conta2 == null) {
            System.out.println("\nNumero da conta criada: " + conta.getNumeroConta() + "\n");
            System.out.println("\nAgora vamos criar a segunda conta...\n");
            criarConta(entrada);
            System.out.println("\nNumero da conta criada: " + conta2.getNumeroConta() + "\n");
        }                
    }

    public void consultarSaldo(Scanner entrada){
        System.out.println("Qual o número da conta que voce deseja consultar?");
        int numeroConta = Integer.parseInt(entrada.nextLine());

        if (numeroConta == conta.getNumeroConta()){
            System.out.println("\nSaldo de " + conta.getCliente().getNome() + ": R$" + conta.getSaldo() + "\n");
        }
        else if(numeroConta == conta2.getNumeroConta()){
            System.out.println("\nSaldo de " + conta2.getCliente().getNome() + ": R$" + conta2.getSaldo() + "\n");
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

        if (numeroConta == conta.getNumeroConta()){
            conta.depositar(quantia);
        }
        else if(numeroConta == conta2.getNumeroConta()){
            conta2.depositar(quantia);
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

        if (numeroConta == conta.getNumeroConta()){
            if (!conta.sacar(quantia)){
                System.out.println("Limite indisponivel!\n");
            }
        }
        else if(numeroConta == conta2.getNumeroConta()){
            if (!conta2.sacar(quantia)){
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

       
        if(!fazerTransferencia(quantia, numeroContaOrigem, numeroContaDestino)){
            System.out.println("\nConta de origem e/ou destino invalida(s)!\n");
        }
    }

    public boolean fazerTransferencia(double quantia, int numeroContaOrigem, int numeroContaDestino){
        if (numeroContaOrigem == conta.getNumeroConta()){
            if(numeroContaDestino == conta2.getNumeroConta()){
                if (!conta.transferir(quantia, conta2)){
                    System.out.println("Limite indisponivel!\n");
                }
                return true;
            }
        }
        else if (numeroContaOrigem == conta2.getNumeroConta()){
            if(numeroContaDestino == conta.getNumeroConta()){
                if (!conta2.transferir(quantia, conta)){
                    System.out.println("Limite indisponivel!\n");
                }
                return true;
            }
        }
        return false;
    }
}
