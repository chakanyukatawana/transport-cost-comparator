package com.example.transportcostcomparator;

public class TransportCalculator {

    public double calculateDailyCost(double distancePerDay,
                                     double costPerKilometre) {

        return distancePerDay * costPerKilometre;
    }

    public double calculateMonthlyCost(double dailyCost,
                                       int travelDays) {

        return dailyCost * travelDays;
    }

    public double calculateMonthlyDistance(double distancePerDay,
                                           int travelDays) {

        return distancePerDay * travelDays;
    }
}