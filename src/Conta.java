public class Conta {
    private final String titular;
    protected double saldo;

    public Conta(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public void depositar(double valor) {
        if (valor <= 0) {
            System.out.println("O valor do depósito deve ser positivo!");
            return;
        }
        saldo += valor;
    }

    public void sacar(double valor) {
        if (valor <= 0) {
            System.out.println("O valor do saque deve ser positivo!");
        } else if (valor > saldo) {
            System.out.println("Saldo insuficiente!");
        } else {
            saldo -= valor;
        }
    }

    public void exibirExtrato() {
        System.out.printf("Titular: %s%nSaldo: R$ %.2f%n", titular, saldo);
    }

    public double getSaldo() {
        return saldo;
    }
}
