package ru.itis.game.core;

import ru.itis.game.core.fields.PurchasableField;

import java.util.List;

public class Offer {
    private Player from;
    private Player to;
    private int moneyFrom;
    private int moneyTo;
    private List<PurchasableField> fieldsFrom;
    private List<PurchasableField> fieldsTo;
    private int prisonReleasesFrom;
    private int prisonReleasesTo;

    public Offer(Player from, Player to,
                 int moneyFrom, int moneyTo,
                 List<PurchasableField> fieldsFrom, List<PurchasableField> fieldsTo,
                 int prisonReleasesFrom, int prisonReleasesTo) {
        this.from = from;
        this.to = to;
        this.moneyFrom = moneyFrom;
        this.moneyTo = moneyTo;
        this.fieldsFrom = fieldsFrom;
        this.fieldsTo = fieldsTo;
        this.prisonReleasesFrom = prisonReleasesFrom;
        this.prisonReleasesTo = prisonReleasesTo;
    }

    public Player getFrom() {
        return from;
    }

    public Player getTo() {
        return to;
    }

    public int getMoneyFrom() {
        return moneyFrom;
    }

    public int getMoneyTo() {
        return moneyTo;
    }

    public List<PurchasableField> getFieldsFrom() {
        return fieldsFrom;
    }

    public List<PurchasableField> getFieldsTo() {
        return fieldsTo;
    }

    public int getPrisonReleasesFrom() {
        return prisonReleasesFrom;
    }

    public int getPrisonReleasesTo() {
        return prisonReleasesTo;
    }
}
