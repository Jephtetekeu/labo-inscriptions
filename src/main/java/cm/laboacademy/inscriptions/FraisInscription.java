package cm.laboacademy.inscriptions;
import java.time.LocalDate;

public class FraisInscription {

    public int calculer(String niveau, boolean boursier) {
        int base = switch (niveau) {
            case "L1", "L2", "L3" -> 50000;
            case "M1", "M2" -> 75000;
            default -> throw new IllegalArgumentException("Niveau inconnu : " + niveau);
        };
        return boursier ? base / 2 : base;
    }

    public int calculer(String niveau, boolean boursier,
                        LocalDate dateInscription, LocalDate dateLimite) {
        int montant = calculer(niveau, boursier);
        if (!dateInscription.isAfter(dateLimite)) {
            montant = montant * 90 / 100;
        }
        return montant;
    }
}