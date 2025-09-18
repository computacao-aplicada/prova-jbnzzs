import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Validador de CPF ===");
        System.out.print("Digite um CPF (com ou sem máscara): ");
        String entrada = sc.nextLine();

        boolean valido = Validador.validarCPF(entrada);

        if (valido) {
            System.out.println("✅ CPF VÁLIDO!");
        } else {
            System.out.println("❌ CPF INVÁLIDO!");
        }

        sc.close();
    }
}
