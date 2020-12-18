package ru.itis.game.core;

public enum ChanceEvent {
    GO_TO_GUEST_COMPLEX(1, "Отправляйтесь в гостиничный комплекс", ""),
    GO_TO_START(2, "Пройдите на старт.", ""),
    PRISON_RELEASE(3, "Освобождение из тюрьмы", ""),
    STREET_REPAIR(4, "Сбор на ремонт улицы", "40$ за каждый дом, 115$ за каждый отель"),
    GO_TO_RESTAURANT(5, "Отправляйтесь в ресторан", "если вы проходите “старт”, получите 200$"),
    SPEED_FINE(6, "Штраф за превышение скорости", "заплатите 15$"),
    DEPT_REPAYMENT(7, "Возврат займа", "получите 150$"),
    ARREST(8, "Вас арестовали", "отправляйтесь в тюрьму"),
    CAPITAL_REPAIR(9, "Капитальный ремонт", "25$ за каждый дом, 100$ за каждый отель"),
    GO_TO_NORTH_S_P(10, "Отправляйтесь в северный морской порт", "если вы проходите “старт” получите 200$"),
    DIVIDENTS(11, "Банковские дивиденды", "получите 50$"),
    DRUNK_DRIVE(12, "Вождение в нетрезвом виде", "штраф 20$"),
    DRIVE_COURSES(13, "Оплата курсов водителей", "заплатите 150$"),
    CHECKMATES(14, "Вы выиграли чемпионат по шахматам", "получите 100$"),
    GO_TO_AQUAPARK(15, "Отправляйтесь в аквапарк", "если вы проходите “старт”, получите 200$"),
    MOVE_BACK(16, "Вернитесь на три квадрата назад", "");

    private int chanceId;
    private String header;
    private String text;

    ChanceEvent(int chanceId, String header, String text) {
        this.chanceId = chanceId;
        this.header = header;
        this.text = text;
    }

    public String getHeader() {
        return header;
    }

    public String getText() {
        return text;
    }
}
