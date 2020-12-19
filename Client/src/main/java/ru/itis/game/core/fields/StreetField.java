package ru.itis.game.core.fields;

import ru.itis.game.client.Connection;
import ru.itis.game.core.Player;
import java.util.List;

public class StreetField extends PurchasableField{

    private final Street street;

    private int level;

    private int buildCost;

    public StreetField(Street street) {
        this.street = street;
        level = 0;
    }

    public void build(){
        level++;
    }

    public void remove(){
        level--;
    }

    @Override
    public int getCost() {
        return street.getCost();
    }

    @Override
    public int getMortgageCost() {
        return street.getMortgageCost();
    }

    @Override
    public int getUnmortgageCost() {
        return (int)(street.getMortgageCost() * 1.1);
    }

    public int getColor(){
        return street.getColor();
    }

    public Street getStreet() {
        return street;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getBuildCost() {
        return buildCost;
    }

    private static final int BROWN = 0;
    private static final int YELLOW = 1;
    private static final int GREEN = 2;
    private static final int GRAY = 3;
    private static final int RED = 4;
    private static final int PINK = 5;
    private static final int BLUE = 6;
    private static final int CYAN = 7;

    public enum Street {
        RESIDENTIAL_STREET("Жилая улица", 60, BROWN,
                new int[]{2, 10, 30, 90, 160, 250}, 30, 50),
        NAGATINSKAYA_STREET("Нагатинская улица", 60, BROWN,
                new int[]{4, 20, 60, 180, 320, 450}, 30, 50),
        WARSAW_HIGHWAY("Варшавское шоссе", 100, YELLOW,
                new int[]{6, 30, 90, 270, 400, 550}, 50, 50),
        OGAREVA_STREET("Улица Огарева", 100, YELLOW,
                new int[]{6, 30, 90, 270, 400, 550}, 50, 50),
        THE_FIRST_PARK_STREET("Первая парковая улица", 120, YELLOW,
                new int[]{8, 40, 100, 300, 450, 600}, 60, 50),
        POLYANKA_STREET("Улица Полянка", 140, GREEN,
                new int[]{10, 50, 150, 450, 625, 750}, 70, 100),
        SRETENKA_STREET("Улица Сретенка", 140, GREEN,
                new int[]{10, 50, 150, 450, 625, 750}, 70, 100),
        ROSTOV_EMBANKMENT("Ростовская набережная", 160, GREEN,
                new int[]{12, 60, 180, 500, 700, 900}, 80, 100),
        RYAZANSKY_PROSPECT("Рязанский проспект", 180, GRAY,
                new int[]{14, 70, 200, 550, 750, 950}, 90, 100),
        VAVILOV_STREET("Улица Вавилова", 180, GRAY,
                new int[]{14, 70, 200, 550, 750, 950}, 90, 100),
        RUBLEVSKOE_HIGHWAY("Рублевское шоссе", 200, GRAY,
                new int[]{16, 80, 220, 600, 800, 100}, 100, 100),
        TVERSKAYA_STREET("Тверская улица", 220, RED,
                new int[]{18, 90, 250, 700, 875, 1050}, 110, 150),
        PUSHKIN_STREET("Пушкинская улица", 220, RED,
                new int[]{18, 90, 250, 700, 875, 1050}, 110, 150),
        MAYAKOVSKY_SQUARE("Площадь Маяковского", 240, RED,
                new int[]{20, 100, 300, 750,925, 1100}, 120, 150),
        GRUZINSKY_VAL_STREET("Улица Грузинский вал", 260, PINK,
                new int[]{22, 110, 330, 800, 975, 1150}, 130, 150),
        TCHAIKOVSKY_STREET("Улица Чайковского", 260, PINK,
                new int[]{22, 110, 330, 800, 975, 1150}, 130, 150),
        SMOLENSKAYA_STREET("Смоленская площадь", 280, PINK,
                new int[]{24, 120, 360, 850, 1025, 1200}, 140, 150),
        SHCHUSEV_STREET("Улица Щусева", 300, BLUE,
                new int[]{26, 130, 390, 900, 1100, 1275}, 150, 200),
        GOGOLEVSKY_BULVAR("Гоголевский бульвар", 300, BLUE,
                new int[]{26, 130, 390, 900, 1100, 1275}, 150, 200),
        KUTUZOVSKY_PROSPECT("Кутузовский проспект", 320, BLUE,
                new int[]{28, 150, 450, 1000, 1200, 1400}, 160, 200),
        MALAYA_BRONNAYA_STREET("Улица Малая Бронная", 350, CYAN,
                new int[]{35, 175, 500, 1100, 1300, 1500}, 175, 200),
        ARBAT_STREET("Улица Арбат", 400, CYAN,
                new int[]{50, 200, 600, 1400, 1700, 2000}, 200, 200)
        ;



        private final String name;
        private final int cost;
        private final int color;
        private final int[] rent;
        private final int mortgageCost;
        private final int buildCost;

        Street(String name, int cost, int color,
               int[] rent,
               int mortgageCost, int buildCost) {
            this.name = name;
            this.cost = cost;
            this.color = color;
            this.rent = rent;
            this.mortgageCost = mortgageCost;
            this.buildCost = buildCost;
        }

        public int getCost() {
            return cost;
        }

        public int getMortgageCost() {
            return mortgageCost;
        }

        public String getName() {
            return name;
        }

        public int[] getRent() {
            return rent;
        }

        public int getColor() {
            return color;
        }

        public int getBuildCost() {
            return buildCost;
        }
    }
}
