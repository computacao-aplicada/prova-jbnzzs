import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorTest {

    @Test
    @DisplayName("Deve validar CPF válido — com e sem máscara")
    void deveValidarCPFValido() {
        assertTrue(Validador.validarCPF("529.982.247-25"));
        assertTrue(Validador.validarCPF("52998224725"));
    }

    @Test
    @DisplayName("Deve ignorar espaços externos")
    void deveIgnorarEspacosExternos() {
        assertTrue(Validador.validarCPF("   529.982.247-25   "));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   \t\n"})
    @DisplayName("Null/Vazio/Branco → false")
    void deveRejeitarNullOuVazio(String entrada) {
        assertFalse(Validador.validarCPF(entrada));
    }

    @Test
    @DisplayName("Tamanho incorreto → false")
    void deveRejeitarTamanhosIncorretos() {
        assertFalse(Validador.validarCPF("935.411.347-8"));
        assertFalse(Validador.validarCPF("935.411.347-800"));
    }

    @Test
    @DisplayName("Caracteres inválidos → false")
    void deveRejeitarCaracteresInvalidos() {
        assertFalse(Validador.validarCPF("529.982.247-2X"));
    }

    @Test
    @DisplayName("Sequência repetida → false")
    void deveRejeitarSequenciaRepetida() {
        assertFalse(Validador.validarCPF("00000000000"));
        assertFalse(Validador.validarCPF("11111111111"));
    }

    @Test
    @DisplayName("DV incorreto → false")
    void deveRejeitarDVIncorreto() {
        assertFalse(Validador.validarCPF("529.982.247-24"));
        assertFalse(Validador.validarCPF("123.456.789-00"));
    }
}
