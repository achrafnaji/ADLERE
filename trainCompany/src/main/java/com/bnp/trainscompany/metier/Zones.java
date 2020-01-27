package com.bnp.trainscompany.metier;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.bnp.trainscompany.data.Zone1;

public class Zones {
    private static  String[] ZONE_1  = {"A","B"};
    private static  String[] ZONE_2  = {"C","D","E"};
    private static  String[] ZONE_3  = {"C","E","F"};
    private static  String[] ZONE_4  = {"F","G","H","I"};
    public  static  List<Rules> rules;


    public static ZoneToFrom getZoneWithCost(String stationFrom, String stationTo){
        
    	List<Integer> zonesFrom = getZones(stationFrom);
        List<Integer> zonesTo = getZones(stationTo);

        List<ZoneToFrom> zoneToFroms = new ArrayList<>();
        for (int i = 0; i < zonesFrom.size(); i++) {
            for (int j = 0; j < zonesTo.size(); j++) {
                ZoneToFrom zoneToFrom = new ZoneToFrom();
                zoneToFrom.setZoneFrom(zonesFrom.get(i));
                zoneToFrom.setZoneTo(zonesTo.get(j));

                List<Rules> rulesZones = rules.stream()
                		.filter(z -> {
                    return (z.from == zoneToFrom.getZoneFrom() && z.to == zoneToFrom.getZoneTo());
                })
                		.collect( Collectors.toList() );
                zoneToFrom.setCost(Long.valueOf(rulesZones.get(0).cost));
                zoneToFroms.add(zoneToFrom);
            }
        }

        List<ZoneToFrom> finalZones = zoneToFroms.stream()
                .sorted(Comparator.comparingDouble(ZoneToFrom::getCost))
                .collect(Collectors.toList());

        return finalZones.get(0);
    }

    private static List<Integer> getZones(String station){
        List<Integer> zones = new ArrayList<>();
        if(Arrays.asList(ZONE_1).contains(station)) zones.add(1);
        if(Arrays.asList(ZONE_2).contains(station)) zones.add(2);
        if(Arrays.asList(ZONE_3).contains(station)) zones.add(3);
        if(Arrays.asList(ZONE_4).contains(station)) zones.add(4);
        return zones;
    }

    public static void initRules(){
        rules = new ArrayList<>();
        rules.add(new Rules(1,1 , Long.valueOf("240")));
        rules.add(new Rules(1,2 , Long.valueOf("240")));
        rules.add(new Rules(2,1 , Long.valueOf("240")));
        rules.add(new Rules(2,2 , Long.valueOf("240")));

        rules.add(new Rules(3,3 , Long.valueOf("200")));
        rules.add(new Rules(3,4 , Long.valueOf("200")));
        rules.add(new Rules(4,3 , Long.valueOf("200")));
        rules.add(new Rules(4,4 , Long.valueOf("200")));

        rules.add(new Rules(3,1 , Long.valueOf("280")));
        rules.add(new Rules(3,2 , Long.valueOf("280")));

        rules.add(new Rules(4,1 , Long.valueOf("300")));
        rules.add(new Rules(4,2 , Long.valueOf("300")));

        rules.add(new Rules(1,3 , Long.valueOf("280")));
        rules.add(new Rules(2,3 , Long.valueOf("280")));

        rules.add(new Rules(1,4 , Long.valueOf("300")));
        rules.add(new Rules(2,4 , Long.valueOf("300")));
    }


           // 7) If there is the possibility of two prices then we must charge the customer the lowest amount and reflect this in the pricing information.
}
