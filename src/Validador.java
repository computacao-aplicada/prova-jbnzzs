public final class Validador{

    private Validador() {}

    public static boolean validarCPF(String cpf) {
        if (cpf == null) return false;

        String limpo = sanitizar(cpf);
        if (!formatoBasico(limpo)) return false;
        if (todosIguais(limpo)) return false;

        return checarDigitos(limpo);
    }

    static String sanitizar(String s) {
        String trimmed = s.trim();
        return trimmed.replace(".", "").replace("-", "");
    }

    static boolean formatoBasico(String limpo) {
        if (limpo.length() != 11) return false;
        for (int i = 0; i < limpo.length(); i++) {
            if (!Character.isDigit(limpo.charAt(i))) return false;
        }
        return true;
    }

    static boolean todosIguais(String limpo) {
        char first = limpo.charAt(0);
        for (int i = 1; i < limpo.length(); i++) {
            if (limpo.charAt(i) != first) return false;
        }
        return true;
    }

    static boolean checarDigitos(String cpf11) {
        int[] d = cpf11.chars().map(c -> c - '0').toArray();

        int dv1 = calcularDV(d, 0, 9, 10);
        if (d[9] != dv1) return false;

        int dv2 = calcularDV(d, 0, 10, 11);
        return d[10] == dv2;
    }

    static int calcularDV(int[] d, int from, int len, int pesoInicial) {
        int soma = 0;
        int peso = pesoInicial;
        for (int i = 0; i < len; i++) {
            soma += d[from + i] * (peso--);
        }
        int r = soma % 11;
        return (r < 2) ? 0 : 11 - r;
    }
}
