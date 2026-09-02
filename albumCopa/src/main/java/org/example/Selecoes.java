package org.example;

public enum Selecoes {
    // Grupo A
    VAZIO(""),
    MEXICO("México"),
    AFRICA_DO_SUL("África do Sul"),
    COREIA_DO_SUL("Coreia do Sul"),
    REPUBLICA_TCHECA("República Tcheca"),

    // Grupo B
    CANADA("Canadá"),
    BOSNIA_HERZEGOVINA("Bósnia e Herzegovina"),
    QATAR("Catar"),
    SUICA("Suíça"),

    // Grupo C
    BRASIL("Brasil"),
    MARROCOS("Marrocos"),
    HAITI("Haiti"),
    ESCOCIA("Escócia"),

    // Grupo D
    ESTADOS_UNIDOS("Estados Unidos"),
    AUSTRALIA("Austrália"),
    PARAGUAI("Paraguai"),
    TURQUIA("Turquia"),

    // Grupo E
    ALEMANHA("Alemanha"),
    CURACAO("Curaçao"),
    COSTA_DO_MARFIM("Costa do Marfim"),
    EQUADOR("Equador"),

    // Grupo F
    HOLANDA("Holanda"),
    JAPAO("Japão"),
    SUECIA("Suécia"),
    TUNISIA("Tunísia"),

    // Grupo G
    BELGICA("Bélgica"),
    EGITO("Egito"),
    IRA("Irã"),
    NOVA_ZELANDIA("Nova Zelândia"),

    // Grupo H
    ESPANHA("Espanha"),
    CABO_VERDE("Cabo Verde"),
    ARABIA_SAUDITA("Arábia Saudita"),
    URUGUAI("Uruguai"),

    // Grupo I
    FRANCA("França"),
    SENEGAL("Senegal"),
    IRAQUE("Iraque"),
    NORUEGA("Noruega"),

    // Grupo J
    ARGENTINA("Argentina"),
    AUSTRIA("Áustria"),
    ARGELIA("Argélia"),
    JORDANIA("Jordânia"),

    // Grupo K
    COLOMBIA("Colômbia"),
    PORTUGAL("Portugal"),
    RD_CONGO("República Democrática do Congo"),
    UZBEQUISTAO("Uzbequistão"),

    // Grupo L
    INGLATERRA("Inglaterra"),
    CROACIA("Croácia"),
    GANA("Gana"),
    PANAMA("Panamá"),
    OUTRO("Outro");


    private final String nomeFormatado;

    Selecoes(String nomeFormatado) {
        this.nomeFormatado = nomeFormatado;
    }

    public String getNomeFormatado() {
        return nomeFormatado;
    }

    @Override
    public String toString() {
        return this.nomeFormatado;
    }


    public static Selecoes daString(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return VAZIO;
        }

        for (Selecoes s : Selecoes.values()) {
            if (s.getNomeFormatado().equalsIgnoreCase(texto.trim())) {
                return s;
            }
        }
        return OUTRO;
    }
}