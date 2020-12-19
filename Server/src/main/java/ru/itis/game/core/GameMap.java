package ru.itis.game.core;

import ru.itis.game.core.fields.*;
import ru.itis.game.core.util.Event;
import ru.itis.game.protocol.Protocol;

import java.util.*;

public class GameMap {
    private final List<MapField> mapFields;
    private final Map<Player, Integer> playersPositions;
    private final GameSession session;

    private final Queue<ChanceEvent> chanceQueue;
    private final Queue<CommunityChestEvent> chestQueue;


    public GameMap(Player[] players, GameSession session){
        this.session = session;
        mapFields = new ArrayList<>();
        playersPositions = new HashMap<>();
        for(Player p : players){
            if(p != null) {
                playersPositions.put(p, 0);
            }
        }
        mapFields.add(new StartField(session));
        mapFields.add(new StreetField(StreetField.Street.RESIDENTIAL_STREET, session));
        mapFields.add(new RandomEventsField(RandomEventsField.COMMUNITY_CHEST, session));
        mapFields.add(new StreetField(StreetField.Street.NAGATINSKAYA_STREET, session));
        mapFields.add(new TaxField(TaxField.INCOME, session));
        mapFields.add(new StationField("Западный морской порт", session));
        mapFields.add(new StreetField(StreetField.Street.WARSAW_HIGHWAY, session));
        mapFields.add(new RandomEventsField(RandomEventsField.CHANCE, session));
        mapFields.add(new StreetField(StreetField.Street.OGAREVA_STREET, session));
        mapFields.add(new StreetField(StreetField.Street.THE_FIRST_PARK_STREET, session));
        mapFields.add(new PrisonField(session));
        mapFields.add(new StreetField(StreetField.Street.POLYANKA_STREET, session));
        mapFields.add(new UtilityField("Электрическая компания", session));
        mapFields.add(new StreetField(StreetField.Street.SRETENKA_STREET, session));
        mapFields.add(new StreetField(StreetField.Street.ROSTOV_EMBANKMENT, session));
        mapFields.add(new StationField("Северный морской порт", session));
        mapFields.add(new StreetField(StreetField.Street.RYAZANSKY_PROSPECT, session));
        mapFields.add(new RandomEventsField(RandomEventsField.COMMUNITY_CHEST, session));
        mapFields.add(new StreetField(StreetField.Street.VAVILOV_STREET, session));
        mapFields.add(new StreetField(StreetField.Street.RUBLEVSKOE_HIGHWAY, session));
        mapFields.add(new FreeParkingField(session));
        mapFields.add(new StreetField(StreetField.Street.TVERSKAYA_STREET, session));
        mapFields.add(new RandomEventsField(RandomEventsField.CHANCE, session));
        mapFields.add(new StreetField(StreetField.Street.PUSHKIN_STREET, session));
        mapFields.add(new StreetField(StreetField.Street.MAYAKOVSKY_SQUARE, session));
        mapFields.add(new StationField("Восточный морской порт", session));
        mapFields.add(new StreetField(StreetField.Street.GRUZINSKY_VAL_STREET, session));
        mapFields.add(new StreetField(StreetField.Street.TCHAIKOVSKY_STREET, session));
        mapFields.add(new UtilityField("Водопроводная компания", session));
        mapFields.add(new StreetField(StreetField.Street.SMOLENSKAYA_STREET, session));
        mapFields.add(new ArrestField(session));
        mapFields.add(new StreetField(StreetField.Street.SHCHUSEV_STREET, session));
        mapFields.add(new StreetField(StreetField.Street.GOGOLEVSKY_BULVAR, session));
        mapFields.add(new RandomEventsField(RandomEventsField.COMMUNITY_CHEST, session));
        mapFields.add(new StreetField(StreetField.Street.KUTUZOVSKY_PROSPECT, session));
        mapFields.add(new StationField("Южный морской порт", session));
        mapFields.add(new RandomEventsField(RandomEventsField.CHANCE, session));
        mapFields.add(new StreetField(StreetField.Street.MALAYA_BRONNAYA_STREET, session));
        mapFields.add(new TaxField(TaxField.LUXURY, session));
        mapFields.add(new StreetField(StreetField.Street.ARBAT_STREET, session));

        chanceQueue = new LinkedList<>();
        List<ChanceEvent> chanceEvents = Arrays.asList(ChanceEvent.values());
        Collections.shuffle(chanceEvents);
        chanceQueue.addAll(chanceEvents);

        chestQueue = new LinkedList<>();
        List<CommunityChestEvent> chestEvents = Arrays.asList(CommunityChestEvent.values());
        Collections.shuffle(chanceEvents);
        chestQueue.addAll(chestEvents);
    }

    public void movePlayer(Player p, int steps){
        int position = playersPositions.get(p);
        position += steps;
        if(position < 0){
            position += getMapSize();
        }
        if(position >= getMapSize()){
            position %= getMapSize();
            startBonus(p);
        }
        playersPositions.put(p, position);
        field(position).stop(p);
        session.initEvent(new Event(p, Protocol.MOVE, steps));
    }

    public void moveDirectlyPlayer(Player p, int position, boolean startBonus){
        int lastPosition = playersPositions.get(p);
        if(position < lastPosition && startBonus){
            startBonus(p);
        }
        playersPositions.put(p, position);
        field(position).stop(p);
        session.initEvent(new Event(p, Protocol.GO_TO, position));
    }

    public void arrestPlayer(Player p){
        moveDirectlyPlayer(p, getPrisonPosition(), false);
        p.setArrested(true);
        session.initEvent(new Event(p, Protocol.GO_TO_PRISON));
        session.setCanRoll(false);
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

    public void startBonus(Player p){
        p.receive(200);
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
        ChanceEvent event = chanceQueue.poll();
        chanceQueue.add(event);
        if (event != null) {
            event.getEvent().invoke(session, target);
        }
    }

    public void communityChest(Player target){
        CommunityChestEvent event = chestQueue.poll();
        chestQueue.add(event);
        if (event != null) {
            event.getEvent().invoke(session, target);
        }
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
