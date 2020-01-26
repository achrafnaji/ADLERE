package com.bnp.trainscompany.infra.io.write;

import java.util.List;

public class CustomerSummaries {

    private int customerId;
    private Long totalCostInCents;
    private List<Trip> trips;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Long getTotalCostInCents() {
        return totalCostInCents;
    }

    public void setTotalCostInCents(Long totalCostInCents) {
        this.totalCostInCents = totalCostInCents;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}
