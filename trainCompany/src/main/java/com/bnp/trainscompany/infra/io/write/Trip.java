package com.bnp.trainscompany.infra.io.write;

public class Trip {

    private String stationStart;
    private String stationEnd;
    private Long startedJourneyAt;
    private Long costInCents;
    private int zoneFrom;
    private int zoneTo;

    public String getStationStart() {
        return stationStart;
    }

    public void setStationStart(String stationStart) {
        this.stationStart = stationStart;
    }

    public String getStationEnd() {
        return stationEnd;
    }

    public void setStationEnd(String stationEnd) {
        this.stationEnd = stationEnd;
    }

    public Long getStartedJourneyAt() {
        return startedJourneyAt;
    }

    public void setStartedJourneyAt(Long startedJourneyAt) {
        this.startedJourneyAt = startedJourneyAt;
    }

    public Long getCostInCents() {
        return costInCents;
    }

    public void setCostInCents(Long costInCents) {
        this.costInCents = costInCents;
    }

    public int getZoneFrom() {
        return zoneFrom;
    }

    public void setZoneFrom(int zoneFrom) {
        this.zoneFrom = zoneFrom;
    }

    public int getZoneTo() {
        return zoneTo;
    }

    public void setZoneTo(int zoneTo) {
        this.zoneTo = zoneTo;
    }
}
