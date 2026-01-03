package model;

public enum StatutRendezVous {
    programme("Programmé"),
    confirme("Confirmé"),
    annule("Annulé"),
    termine("Terminé"),
    absent("Absent");

    private final String label;

    StatutRendezVous(String label) {
        this.label = label;
    }

    public String getLibelle() {
        return label;
    }
}
