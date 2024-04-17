public class Conta {
    private double saldo;
    private double limite;
    private Cliente cliente;
    private int numeroConta;
    private static int ultimaConta = 100;

    public Conta (Cliente cliente, double limite){
        this(cliente, limite, 0);
    }

    public Conta (Cliente cliente, double limite, double saldo){
        this.cliente = cliente;
        this.limite = limite;        
        if (saldo<limite*-1){
            this.saldo = limite*-1;
        }
        else this.saldo = saldo;
        ultimaConta++;
        numeroConta = ultimaConta;
    }

    public double getSaldo(){
        return saldo;
    }

    public Cliente getCliente(){
        return cliente;
    }

    public boolean possivelSacar(double quantia){
        return ((saldo-quantia) >= (limite*-1));
    }

    public boolean sacar(double quantia){
        if(possivelSacar(quantia)){
            saldo -= quantia;
            return true;
        }
        return false;
    }

    public void depositar(double quantia){
        saldo += quantia;
    }
    
    public int getNumeroConta(){
        return numeroConta;
    }

    public boolean transferir(double quantia, Conta destino){
        if (sacar(quantia)){
            destino.depositar(quantia);
            return true;
        }
        return false;
    }
}
