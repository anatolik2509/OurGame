package ru.itis.game.core;

import ru.itis.game.core.fields.PurchasableField;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int id;
    private int balance;
    private int character;
    private List<PurchasableField> domain;
    private GameSession session;
    private boolean isArrested;
    private int arrestTurns;
    private int prisonReleases;
    private String nickName;

    public Player(int character) {
        this.character = character;
        balance = 2000;
        domain = new ArrayList<>();
        isArrested = false;
        arrestTurns = 0;
    }

    public Player(int character, GameSession session, String nickname) {
        this(character);
        this.session = session;
        this.nickName = nickname;
    }

    public int getBalance() {
        return balance;
    }

    public int getCharacter() {
        return character;
    }

    public boolean isArrested() {
        return isArrested;
    }

    public int getArrestTurns() {
        return arrestTurns;
    }

    public int getPrisonReleases() {
        return prisonReleases;
    }

    public void setArrested(boolean arrested) {
        isArrested = arrested;
    }

    public void setArrestTurns(int arrestTurns) {
        this.arrestTurns = arrestTurns;
    }

    public void addRelease() {
        prisonReleases++;
    }

    public void useRelease() {
        if (prisonReleases > 0) {
            prisonReleases--;
        }
    }

    public void receive(int money) {
        balance += money;
    }

    public boolean pay(int money) {
        if (balance < money) {
            return false;
        } else {
            balance -= money;
            return true;
        }
    }

    public void takeAway(int money) {
        balance -= money;
    }

    public void addField(PurchasableField field) {
        field.setOwner(this);
        domain.add(field);
    }

    public boolean removeField(PurchasableField field) {
        if (domain.remove(field)) {
            field.setOwner(null);
            return true;
        }
        return false;
    }

    public boolean isOwner(PurchasableField field) {
        return domain.contains(field);
    }

    public List<PurchasableField> getDomain() {
        return domain;
    }

    public String getNickName() {
        return nickName;
    }
}
