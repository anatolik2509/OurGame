package ru.itis.game.core;

public enum CommunityChestEvent {
    BANK_ERROR(1, "Банковская ошибка в вашу пользу", "получите 200$"),
    STONKS(2, "Выгодная продажа акций", "получите 25$"),
    STONKS_2(2, "Выгодная продажа акций", "получите 25$"),
    PRISON_RELEASE(3, "Освобождение от тюрьмы", "карта может быть использована позже или продана"),
    BEQUEST(4, "Вы получили наследство", "получите 100$"),
    TAX_CANCEL(5, "Возмещение налога", "получите 25$"),
    ARREST(6, "Вас арестовали", "отправляйтесь в тюрьму"),
    INSURANCE(7, "Оплата страховки", "заплатите 50$"),
    BEAUTY_CONCURS(8, "Вы заняли второе место на конкурсе красоты", "получите 10$"),
    BONDS(9, "Выгодная продажа облигаций", "получите 50$"),
    MEDIC_BILL(10, "Оплата лечения", "оплатите 100$"),
    GO_TO_GUEST_COMPLEX(11, "Вернитесь на старую дорогу", ""),
    RENT(12, "Сбор ренты", "получите 100$"),
    BIRTHDAY(13, " С Днём Рождения", "получите 10$ у каждого игрока"),
    FINE(14, "Заплатите штраф", "10$ или возьмите карточку “шанс”"),
    DOCTOR_BILL(15, "Оплата услуг доктора", "заплатите 50$");

    private int communityChestEventId;
    private String header;
    private String text;

    CommunityChestEvent(int communityChestEventId, String header, String text) {
        this.communityChestEventId = communityChestEventId;
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
