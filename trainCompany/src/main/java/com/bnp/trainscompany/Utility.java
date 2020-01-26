package com.bnp.trainscompany;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.bnp.trainscompany.infra.io.Utils;
import com.bnp.trainscompany.infra.io.read.Tap;
import com.bnp.trainscompany.infra.io.read.Taps;
import com.bnp.trainscompany.infra.io.write.CustomerSummaries;
import com.bnp.trainscompany.infra.io.write.Summaries;
import com.bnp.trainscompany.infra.io.write.Trip;
import com.bnp.trainscompany.metier.ZoneToFrom;
import com.bnp.trainscompany.metier.Zones;

public class Utility {

public static void Running (String INPUT_FILE,String OUTPUT_FILE){
      	
    	Zones.initRules();
    	
        Taps taps = Utils.fromJsonFile(INPUT_FILE);
      
        List<Integer> CustomersIds = taps.getTaps().stream()
                .filter( distinctByKey(p -> p.getCustomerId()) )
                .map(p -> p.getCustomerId())
                .collect( Collectors.toList() );
        Summaries summaries = new Summaries();  
        summaries.setCustomerSummaries(new ArrayList<>());

        for (Integer id: CustomersIds) {
            CustomerSummaries customerSummaries = new CustomerSummaries();
            customerSummaries.setCustomerId(id);
            customerSummaries.setTrips(new ArrayList<>());
            List<Tap> tapsCustomer = taps.getTaps().stream()
                    .filter( tap -> tap.getCustomerId() == id )
                    .sorted(Comparator.comparingLong(Tap::getUnixTimestamp))
                    .collect( Collectors.toList() );

            Long totalPrice = 0L;
            for (int i = 0; i < tapsCustomer.size(); i++) {
            	
                Trip trip = new Trip();
                trip.setStartedJourneyAt(tapsCustomer.get(i).getUnixTimestamp());
                trip.setStationStart(tapsCustomer.get(i).getStation());
                trip.setStationEnd(tapsCustomer.get(i+1).getStation());

                ZoneToFrom zoneToFrom = Zones.getZoneWithCost(trip.getStationStart(),trip.getStationEnd());

                trip.setZoneFrom(zoneToFrom.getZoneFrom());
                trip.setZoneTo(zoneToFrom.getZoneTo());
                trip.setCostInCents(zoneToFrom.getPrice());
                customerSummaries.getTrips().add(trip);
                totalPrice+= zoneToFrom.getPrice();
                i++;
            }
            customerSummaries.setTotalCostInCents(totalPrice);
            summaries.getCustomerSummaries().add(customerSummaries);

        }
        Utils.toJsonFile(OUTPUT_FILE , summaries);
    }
    
   	//  distinct by object property directly
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

 

}
