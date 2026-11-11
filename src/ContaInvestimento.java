import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Objects;

public class ContaInvestimento {
    private final String numeroConta;
    private final String titular;
    private BigDecimal saldo;
    private final BigDecimal taxaRendimentoMensal;
    private final LocalDate dataVencimento;
    private YearMonth ultimoMesRendido;

    public ContaInvestimento(String numeroConta, String titular,
                             BigDecimal taxaRendimentoMensal, LocalDate dataVencimento) {
        this.numeroConta = Objects.requireNonNull(numeroConta, "O número da conta é obrigatório.");
        this.titular = Objects.requireNonNull(titular, "O titular é obrigatório.");
        this.taxaRendimentoMensal = Objects.requireNonNull(taxaRendimentoMensal,
                "A taxa de rendimento é obrigatória.");
        this.dataVencimento = Objects.requireNonNull(dataVencimento, "A data de vencimento é obrigatória.");

        if (taxaRendimentoMensal.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("A taxa de rendimento não pode ser negativa.");
        }
        this.saldo = BigDecimal.ZERO;
        this.ultimoMesRendido = YearMonth.from(LocalDate.now());
    }

    public void aplicar(BigDecimal valor) {
        Objects.requireNonNull(valor, "O valor aplicado é obrigatório.");
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor aplicado deve ser maior que zero.");
        }
        saldo = saldo.add(valor);
    }

    /** Aplica uma vez o rendimento bruto de um mês, com capitalização mensal. */
    public void aplicarRendimentoMensal() {
        LocalDate hoje = LocalDate.now();
        if (hoje.isAfter(dataVencimento)) {
            throw new IllegalStateException("O investimento já venceu.");
        }

        YearMonth mesAtual = YearMonth.from(hoje);
        if (!mesAtual.isAfter(ultimoMesRendido)) {
            throw new IllegalStateException("O rendimento deste mês já foi aplicado.");
        }

        BigDecimal rendimento = saldo.multiply(taxaRendimentoMensal);
        saldo = saldo.add(rendimento).setScale(2, RoundingMode.HALF_EVEN);
        ultimoMesRendido = mesAtual;
    }

    /** Resgata todo o saldo somente na data de vencimento ou depois dela. */
    public BigDecimal resgatar() {
        return resgatar(LocalDate.now());
    }

    /** Sobrecarga que permite informar a data da operação, útil para simulações. */
    public BigDecimal resgatar(LocalDate dataResgate) {
        Objects.requireNonNull(dataResgate, "A data do resgate é obrigatória.");
        if (dataResgate.isBefore(dataVencimento)) {
            throw new IllegalStateException("Resgate permitido somente a partir do vencimento: " + dataVencimento);
        }
        BigDecimal valorResgatado = saldo;
        saldo = BigDecimal.ZERO.setScale(2);
        return valorResgatado;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public String getTitular() {
        return titular;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public BigDecimal getTaxaRendimentoMensal() {
        return taxaRendimentoMensal;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }
}
