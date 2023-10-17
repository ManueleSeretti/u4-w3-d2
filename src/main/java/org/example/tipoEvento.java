package org.example;

import java.util.Random;

public enum tipoEvento {
    PUBBLICO, PRIVATO;

    private static final Random PRNG = new Random();

    public static tipoEvento randomTipoEvento() {
        tipoEvento[] eventi = values();
        return eventi[PRNG.nextInt(eventi.length)];
    }
}
