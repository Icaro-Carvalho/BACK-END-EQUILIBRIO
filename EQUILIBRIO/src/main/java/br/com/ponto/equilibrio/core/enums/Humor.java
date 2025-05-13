package br.com.ponto.equilibrio.core.enums;

import lombok.Getter;

public enum Humor {
    ESTRESSADO(-2),
    DESANIMADO(-1),
    NEUTRO(0),
    FELIZ(1),
    MUITO_FELIZ(2);

    @Getter
    private final int score;

    Humor(int score) {
        this.score = score;
    }
}
