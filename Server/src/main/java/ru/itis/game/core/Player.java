package ru.itis.game.core;

import ru.itis.game.core.fields.PurchasableField;
import ru.itis.game.core.util.Event;
import ru.itis.game.protocol.Protocol;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final int id;
    private int balance;
    private int character;
    private List<PurchasableField> domain;
    private GameSession session;
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

    public Player(int character, int id, GameSession session, String nickname) {
        this(id);
        this.character = character;
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

    public boolean useRelease() {
        if (prisonReleases > 0) {
            session.initEvent(new Event(this, Protocol.USE_PRISON_RELEASE));
            arrestTurns = 0;
            isArrested = false;
            prisonReleases--;
            return true;
        }
        return false;
    }

    public void receive(int money) {
        balance += money;
        session.initEvent(new Event(this, Protocol.BALANCE_CHANGE, balance));
    }

    public boolean pay(int money) {
        if (balance < money) {
            return false;
        } else {
            balance -= money;
            session.initEvent(new Event(this, Protocol.BALANCE_CHANGE, balance));
            return true;
        }
    }

    public void takeAway(int money) {
        balance -= money;
        session.initEvent(new Event(this, Protocol.BALANCE_CHANGE, balance));
    }

    public void addField(PurchasableField field) {
        domain.add(field);
    }

    public void removeField(PurchasableField field) {
        domain.remove(field);
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

    public void setSession(GameSession session) {
        this.session = session;
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
