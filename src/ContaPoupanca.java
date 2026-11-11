public class ContaPoupanca extends Conta {
    private final double taxaRendimento;

    public ContaPoupanca(String titular, double saldoInicial, double taxaRendimento) {
        super(titular, saldoInicial);
        if (taxaRendimento < 0) {
            throw new IllegalArgumentException("A taxa de rendimento não pode ser negativa.");
        }
        this.taxaRendimento = taxaRendimento;
    }

    public void render() {
        saldo += saldo * taxaRendimento;
    }

    @Override
    public void exibirExtrato() {
        super.exibirExtrato();
        System.out.printf("Taxa de rendimento: %.2f%%%n", taxaRendimento * 100);
    }

    public double getTaxaRendimento() {
        return taxaRendimento;
    }
}
