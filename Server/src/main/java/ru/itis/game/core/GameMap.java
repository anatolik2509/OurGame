package ru.itis.game.core;

import ru.itis.game.core.fields.*;

import java.util.*;

public class GameMap {
    private final List<MapField> mapFields;
    private final Map<Player, Integer> playersPositions;
    private final GameSession session;

    private final Queue<ChanceEvents> chanceQueue;
    private final Queue<CommunityChestEvents> chestQueue;


    public GameMap(List<Player> players, GameSession session){
        this.session = session;
        mapFields = new ArrayList<>();
        playersPositions = new HashMap<>();
        for(Player p : players){
            playersPositions.put(p, 0);
        }
        mapFields.add(new StartField(session));
        mapFields.add(new StreetField(StreetField.Street.OLD_ROAD, session));
        mapFields.add(new RandomEventsField(RandomEventsField.COMMUNITY_CHEST, session));
        mapFields.add(new StreetField(StreetField.Street.MAIN_HIGHWAY, session));
        mapFields.add(new TaxField(TaxField.INCOME, session));
        mapFields.add(new StationField("Западный морской порт", session));
        mapFields.add(new StreetField(StreetField.Street.AQUAPARK, session));
        mapFields.add(new RandomEventsField(RandomEventsField.CHANCE, session));
        mapFields.add(new StreetField(StreetField.Street.CITY_PARK, session));
        mapFields.add(new StreetField(StreetField.Street.SKI_RESORT, session));
        mapFields.add(new PrisonField(session));
        mapFields.add(new StreetField(StreetField.Street.DORMITORY_AREA, session));
        mapFields.add(new UtilityField("Электрическая компания", session));
        mapFields.add(new StreetField(StreetField.Street.BUSINESS_DISTRICT, session));
        mapFields.add(new StreetField(StreetField.Street.TRADING_AREA, session));
        mapFields.add(new StationField("Северный морской порт", session));
        mapFields.add(new StreetField(StreetField.Street.PUSHKIN_ST, session));
        mapFields.add(new RandomEventsField(RandomEventsField.COMMUNITY_CHEST, session));
        mapFields.add(new StreetField(StreetField.Street.MIR_AVE, session));
        mapFields.add(new StreetField(StreetField.Street.POBEDA_AVE, session));
        mapFields.add(new FreeParkingField(session));
        mapFields.add(new StreetField(StreetField.Street.BAR, session));
        mapFields.add(new RandomEventsField(RandomEventsField.CHANCE, session));
        mapFields.add(new StreetField(StreetField.Street.NIGHT_CLUB, session));
        mapFields.add(new StreetField(StreetField.Street.RESTAURANT, session));
        mapFields.add(new StationField("Восточный морской порт", session));
        mapFields.add(new StreetField(StreetField.Street.COMPUTERS, session));
        mapFields.add(new StreetField(StreetField.Street.INTERNET, session));
        mapFields.add(new UtilityField("Водопроводная компания", session));
        mapFields.add(new StreetField(StreetField.Street.MOBILE_CONNECTION, session));
        mapFields.add(new ArrestField(session));
        mapFields.add(new StreetField(StreetField.Street.SHIPPING, session));
        mapFields.add(new StreetField(StreetField.Street.RAIL_ROAD, session));
        mapFields.add(new RandomEventsField(RandomEventsField.COMMUNITY_CHEST, session));
        mapFields.add(new StreetField(StreetField.Street.AVIACOMPANY, session));
        mapFields.add(new StationField("Южный морской порт", session));
        mapFields.add(new RandomEventsField(RandomEventsField.CHANCE, session));
        mapFields.add(new StreetField(StreetField.Street.RESORT_ZONES, session));
        mapFields.add(new TaxField(TaxField.LUXURY, session));
        mapFields.add(new StreetField(StreetField.Street.GUEST_COMPLEX, session));

        chanceQueue = new LinkedList<>();
        List<ChanceEvents> chanceEvents = Arrays.asList(ChanceEvents.values());
        Collections.shuffle(chanceEvents);
        chanceQueue.addAll(chanceEvents);

        chestQueue = new LinkedList<>();
        List<CommunityChestEvents> chestEvents = Arrays.asList(CommunityChestEvents.values());
        Collections.shuffle(chanceEvents);
        chestQueue.addAll(chestEvents);
    }

    public void movePlayer(Player p, int steps){
        //todo fields
    }

    public void moveDirectlyPlayer(Player p, int position, boolean startBonus){
        //todo fields
    }

    public void arrestPlayer(Player p){

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
        for(MapField f : session.getGameMap().getMapFields()){
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
        for(MapField f : session.getGameMap().getMapFields()){
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
        for(MapField f : session.getGameMap().getMapFields()){
            if(f instanceof UtilityField){
                utilityField = (UtilityField) f;
                r.add(utilityField);
            }
        }
        return r;
    }

    public void chance(Player target){

    }

    public void communityChest(Player target){

    }
}
