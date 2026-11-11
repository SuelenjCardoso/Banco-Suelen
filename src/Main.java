public class Main {
    public static void main(String[] args) {
        ContaCorrente cc = new ContaCorrente("Ana", 500.0, 300.0);
        ContaPoupanca cp = new ContaPoupanca("Bruno", 1000.0, 0.05);
        cc.sacar(700.0); // deve funcionar (500 + 300 de limite)
        cc.sacar(200.0); // deve falhar: "Limite excedido!"
        cc.exibirExtrato();
        cp.depositar(500.0);
        cp.render();
        cp.sacar(2000.0); // deve falhar: "Saldo insuficiente!"
        cp.exibirExtrato();
    }
}