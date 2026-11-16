import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        demonstrarContaCorrente();
        demonstrarContaPoupanca();
        demonstrarContaSalario();
        demonstrarContaInvestimento();
    }

    private static void demonstrarContaCorrente() {
        System.out.println("=== Conta Corrente ===");
        ContaCorrente conta = new ContaCorrente("Ana", 500.00, 300.00);
        conta.sacar(700.00);
        conta.sacar(200.00); // Deve exibir "Limite excedido!"
        conta.exibirExtrato();
    }

    private static void demonstrarContaPoupanca() {
        System.out.println("\n=== Conta Poupança ===");
        ContaPoupanca conta = new ContaPoupanca("Bruno", 1000.00, 0.05);
        conta.depositar(500.00);
        conta.render();
        conta.sacar(2000.00); // Deve exibir "Saldo insuficiente!"
        conta.exibirExtrato();

    }

    private static void demonstrarContaSalario() {
        System.out.println("\n=== Conta Salário ===");
        ContaSalario conta = new ContaSalario("Carla", 2500.00);
        conta.depositar(3200.00); // Exemplo de crédito de salário
        conta.sacar(800.00);
        conta.exibirExtrato();
    }

    private static void demonstrarContaInvestimento() {
        System.out.println("\n=== Conta Investimento ===");
        LocalDate vencimento = LocalDate.now().plusMonths(2);
        ContaInvestimento investimento = new ContaInvestimento(
                "INV-001",
                "Diego",
                new BigDecimal("0.01"), // Taxa mensal de 1%
                vencimento
        );

        investimento.aplicar(new BigDecimal("1000.00"));
        System.out.println("Conta: " + investimento.getNumeroConta());
        System.out.println("Titular: " + investimento.getTitular());
        System.out.println("Saldo aplicado: R$ " + investimento.getSaldo());
        System.out.println("Vencimento: " + investimento.getDataVencimento());

        try {
            investimento.resgatar();
        } catch (IllegalStateException e) {
            System.out.println("Resgate antecipado bloqueado: " + e.getMessage());
        }

        // Simula o resgate no vencimento sem aguardar dois meses.
        BigDecimal valorResgatado = investimento.resgatar(vencimento);
        System.out.println("Valor resgatado na simulação: R$ " + valorResgatado);
        System.out.println("Saldo após resgate: R$ " + investimento.getSaldo());
    }
}
