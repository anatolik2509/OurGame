package ru.itis.game.core.fields;

import ru.itis.game.core.Player;

public class StreetField extends PurchasableField{

    private final Street street;

    private int level;

    public StreetField(Street street) {
        this.street = street;
        level = 0;
    }

    public void build(Player p){
        //todo build
    }

    @Override
    public void stop(Player p) {
        //todo rent
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public int getMortgageCost() {
        return 0;
    }

    @Override
    public int getUnmortgageCost() {
        return 0;
    }

    public Street getStreet() {
        return street;
    }

    public int getLevel() {
        return level;
    }

    public enum Street {
        //todo streets
        ;

        //todo colors
        private final String name;
        private final int cost;
        private final int color;
        private final int rent;
        private final int rent1;
        private final int rent2;
        private final int rent3;
        private final int rent4;
        private final int rent5;
        private final int mortgageCost;
        private final int unmortgageCost;

        Street(String name, int cost, int color,
               int rent, int rent1, int rent2, int rent3, int rent4, int rent5,
               int mortgageCost, int unmortgageCost) {
            this.name = name;
            this.cost = cost;
            this.color = color;
            this.rent = rent;
            this.rent1 = rent1;
            this.rent2 = rent2;
            this.rent3 = rent3;
            this.rent4 = rent4;
            this.rent5 = rent5;
            this.mortgageCost = mortgageCost;
            this.unmortgageCost = unmortgageCost;
        }

        public int getCost() {
            return cost;
        }

        public int getRent1() {
            return rent1;
        }

        public int getRent2() {
            return rent2;
        }

        public int getRent3() {
            return rent3;
        }

        public int getRent4() {
            return rent4;
        }

        public int getRent5() {
            return rent5;
        }

        public int getMortgageCost() {
            return mortgageCost;
        }

        public int getUnmortgageCost() {
            return unmortgageCost;
        }

        public String getName() {
            return name;
        }

        public int getRent() {
            return rent;
        }

        public int getColor() {
            return color;
        }
    }
}
