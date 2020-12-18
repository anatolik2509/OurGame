package ru.itis.game.core;

import ru.itis.game.client.Connection;
import ru.itis.game.core.fields.*;

import java.util.*;

public class GameMap {
    private final List<MapField> mapFields;
    private final Map<Player, Integer> playersPositions;
    private final Connection connection;

    public GameMap(Player[] players, Connection connection){
        this.connection = connection;
        mapFields = new ArrayList<>();
        playersPositions = new HashMap<>();
        for(Player p : players){
            if(p != null) {
                playersPositions.put(p, 0);
            }
        }
        mapFields.add(new StartField());
        mapFields.add(new StreetField(StreetField.Street.RESIDENTIAL_STREET));
        mapFields.add(new RandomEventsField(RandomEventsField.COMMUNITY_CHEST));
        mapFields.add(new StreetField(StreetField.Street.NAGATINSKAYA_STREET));
        mapFields.add(new TaxField(TaxField.INCOME));
        mapFields.add(new StationField("Западный морской порт"));
        mapFields.add(new StreetField(StreetField.Street.WARSAW_HIGHWAY));
        mapFields.add(new RandomEventsField(RandomEventsField.CHANCE));
        mapFields.add(new StreetField(StreetField.Street.OGAREVA_STREET));
        mapFields.add(new StreetField(StreetField.Street.THE_FIRST_PARK_STREET));
        mapFields.add(new PrisonField());
        mapFields.add(new StreetField(StreetField.Street.POLYANKA_STREET));
        mapFields.add(new UtilityField("Электрическая компания"));
        mapFields.add(new StreetField(StreetField.Street.SRETENKA_STREET));
        mapFields.add(new StreetField(StreetField.Street.ROSTOV_EMBANKMENT));
        mapFields.add(new StationField("Северный морской порт"));
        mapFields.add(new StreetField(StreetField.Street.RYAZANSKY_PROSPECT));
        mapFields.add(new RandomEventsField(RandomEventsField.COMMUNITY_CHEST));
        mapFields.add(new StreetField(StreetField.Street.VAVILOV_STREET));
        mapFields.add(new StreetField(StreetField.Street.RUBLEVSKOE_HIGHWAY));
        mapFields.add(new FreeParkingField());
        mapFields.add(new StreetField(StreetField.Street.TVERSKAYA_STREET));
        mapFields.add(new RandomEventsField(RandomEventsField.CHANCE));
        mapFields.add(new StreetField(StreetField.Street.PUSHKIN_STREET));
        mapFields.add(new StreetField(StreetField.Street.MAYAKOVSKY_SQUARE));
        mapFields.add(new StationField("Восточный морской порт"));
        mapFields.add(new StreetField(StreetField.Street.GRUZINSKY_VAL_STREET));
        mapFields.add(new StreetField(StreetField.Street.TCHAIKOVSKY_STREET));
        mapFields.add(new UtilityField("Водопроводная компания"));
        mapFields.add(new StreetField(StreetField.Street.SMOLENSKAYA_STREET));
        mapFields.add(new ArrestField());
        mapFields.add(new StreetField(StreetField.Street.SHCHUSEV_STREET));
        mapFields.add(new StreetField(StreetField.Street.GOGOLEVSKY_BULVAR));
        mapFields.add(new RandomEventsField(RandomEventsField.COMMUNITY_CHEST));
        mapFields.add(new StreetField(StreetField.Street.KUTUZOVSKY_PROSPECT));
        mapFields.add(new StationField("Южный морской порт"));
        mapFields.add(new RandomEventsField(RandomEventsField.CHANCE));
        mapFields.add(new StreetField(StreetField.Street.MALAYA_BRONNAYA_STREET));
        mapFields.add(new TaxField(TaxField.LUXURY));
        mapFields.add(new StreetField(StreetField.Street.ARBAT_STREET));
    }

    public void movePlayer(Player p, int steps){

        }

    public void moveDirectlyPlayer(Player p, int position){

    }

    public void arrestPlayer(Player p){

    }

    public int getPrisonPosition(){
        MapField f;
        for(int i = 0; i < mapFields.size(); i++){
            f = mapFields.get(i);
            if(f instanceof PrisonField){
                return i;
            }
        }
        return 0;
    }

    public int getMapSize(){
        return mapFields.size();
    }

    public int getPlayerPosition(Player p){
        return playersPositions.get(p);
    }

    public List<MapField> getMapFields() {
        return mapFields;
    }

    public MapField field(int i){
        return mapFields.get(i);
    }

    public List<StreetField> streetsByColor(int color){
        List<StreetField> r = new ArrayList<>();
        StreetField streetField;
        for(MapField f : getMapFields()){
            if(f instanceof StreetField){
                streetField = (StreetField) f;
                r.add(streetField);
            }
        }
        return r;
    }

    public List<StationField> stations(){
        List<StationField> r = new ArrayList<>();
        StationField stationField;
        for(MapField f : getMapFields()){
            if(f instanceof StationField){
                stationField = (StationField) f;
                r.add(stationField);
            }
        }
        return r;
    }

    public List<UtilityField> utilities(){
        List<UtilityField> r = new ArrayList<>();
        UtilityField utilityField;
        for(MapField f : getMapFields()){
            if(f instanceof UtilityField){
                utilityField = (UtilityField) f;
                r.add(utilityField);
            }
        }
        return r;
    }

    public void removePlayer(Player p) {
        playersPositions.remove(p);
        for(MapField f : mapFields){
            if(f instanceof PurchasableField){
                ((PurchasableField) f).setOwner(null);
                if(f instanceof StreetField){
                    ((StreetField) f).setLevel(0);
                }
            }
        }
    }

    public int fieldIndex(MapField field){
        for(int i = 0; i < mapFields.size(); i++){
            if(mapFields.get(i) == field){
                return i;
            }
        }
        return -1;
    }

    public void purchaseField(Player p){
        MapField field = field(playersPositions.get(p));
        if(field instanceof PurchasableField){
            if(((PurchasableField) field).getOwner() == null){
                ((PurchasableField) field).purchase(p);
            }
        }
    }
}
