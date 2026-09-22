package com.example.transportcostcomparator;

public class Transport {

    private String modeOfTransport;
    private String transportType;
    private double distancePerDay;
    private double costPerKilometre;
    private int travelDays;

    // Default constructor
    public Transport() {
    }

    // Parameterized constructor
    public Transport(String modeOfTransport,
                     String transportType,
                     double distancePerDay,
                     double costPerKilometre,
                     int travelDays) {

        this.modeOfTransport = modeOfTransport;
        this.transportType = transportType;
        this.distancePerDay = distancePerDay;
        this.costPerKilometre = costPerKilometre;
        this.travelDays = travelDays;
    }

    public String getModeOfTransport() {
        return modeOfTransport;
    }

    public void setModeOfTransport(String modeOfTransport) {
        this.modeOfTransport = modeOfTransport;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public double getDistancePerDay() {
        return distancePerDay;
    }

    public void setDistancePerDay(double distancePerDay) {
        this.distancePerDay = distancePerDay;
    }

    public double getCostPerKilometre() {
        return costPerKilometre;
    }

    public void setCostPerKilometre(double costPerKilometre) {
        this.costPerKilometre = costPerKilometre;
    }

    public int getTravelDays() {
        return travelDays;
    }

    public void setTravelDays(int travelDays) {
        this.travelDays = travelDays;
    }
}
