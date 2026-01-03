package model;

import lombok.Getter;

@Getter
public enum FormeMedicament {
    comprime("Comprimé"),
    gelule("Gélule"),
    sirop("Sirop / Solution buvable"),
    injection("Injection / Injectable"),
    pommade("Pommade / Crème"),
    collyre("Collyre (Yeux)"),
    spray("Spray / Aérosol"),
    poudre("Poudre");

    private final String label;

    FormeMedicament(String label) {
        this.label = label;
    }
}
