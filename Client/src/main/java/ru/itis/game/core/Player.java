package ru.itis.game.core;

import ru.itis.game.core.fields.PurchasableField;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final int id;
    private int balance;
    private int character;
    private List<PurchasableField> domain;
    private boolean isArrested;
    private int arrestTurns;
    private int prisonReleases;
    private String nickName;

    public Player(int id) {
        this.id = id;
        balance = 2000;
        domain = new ArrayList<>();
        isArrested = false;
        arrestTurns = 0;
        character = -1;
    }

    public Player(int character, int id, String nickname) {
        this(id);
        this.character = character;
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

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void useRelease(){
        prisonReleases--;
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

    public int getId() {
        return id;
    }

    public void setCharacter(int character) {
        this.character = character;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void increaseArrestedTurns(){
        arrestTurns++;
    }


}
