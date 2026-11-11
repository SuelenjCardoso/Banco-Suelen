public class ContaCorrente extends Conta {
    private final double limite;

    public ContaCorrente(String titular, double saldoInicial, double limite) {
        super(titular, saldoInicial);
        if (limite < 0) {
            throw new IllegalArgumentException("O limite não pode ser negativo.");
        }
        this.limite = limite;
    }

    @Override
    public void sacar(double valor) {
        if (valor <= 0) {
            System.out.println("O valor do saque deve ser positivo!");
        } else if (valor > saldo + limite) {
            System.out.println("Limite excedido!");
        } else {
            saldo -= valor;
        }
    }

    @Override
    public void exibirExtrato() {
        super.exibirExtrato();
        System.out.printf("Limite: R$ %.2f%n", limite);
    }

    public double getLimite() {
        return limite;
    }
}
