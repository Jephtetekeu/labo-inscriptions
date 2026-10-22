package cm.laboacademy.inscriptions;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class FraisInscriptionTest {

    private final FraisInscription frais = new FraisInscription();
    private final LocalDate limite = LocalDate.of(2026, 11, 30);

    @Test
    void licenceNonBoursier() {
        assertEquals(50000, frais.calculer("L2", false));
    }

    @Test
    void masterNonBoursier() {
        assertEquals(75000, frais.calculer("M1", false));
    }

    @Test
    void boursierPayeLaMoitie() {
        assertEquals(25000, frais.calculer("L1", true));
    }

    @Test
    void niveauInconnuLeveUneException() {
        assertThrows(IllegalArgumentException.class,
                () -> frais.calculer("X9", false));
    }

    @Test
    void reductionDixPourCentAvantLaDateLimite() {
        assertEquals(45000, frais.calculer("L2", false, LocalDate.of(2026, 11, 15), limite));
    }

    @Test
    void reductionValableLeJourMemeDeLaDateLimite() {
        assertEquals(45000, frais.calculer("L2", false, limite, limite));
    }

    @Test
    void pasDeReductionApresLaDateLimite() {
        assertEquals(50000, frais.calculer("L2", false, LocalDate.of(2026, 12, 1), limite));
    }

    @Test
    void reductionCumuleeAvecLaBourse() {
        // 75000 / 2 = 37500, puis -10 % = 33750
        assertEquals(33750, frais.calculer("M1", true, LocalDate.of(2026, 11, 1), limite));
    }
}