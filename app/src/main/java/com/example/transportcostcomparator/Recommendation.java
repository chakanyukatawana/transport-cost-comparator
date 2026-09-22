package com.example.transportcostcomparator;

public class Recommendation {

    public String getCostCategory(double monthlyCost) {

        if (monthlyCost < 1000) {

            return "LOW COST";

        } else if (monthlyCost < 2000) {

            return "MODERATE";

        } else if (monthlyCost < 3000) {

            return "HIGH";

        } else if (monthlyCost < 5000) {

            return "VERY HIGH";

        } else {

            return "EXCESSIVE";
        }
    }

    public String getRecommendation(double monthlyCost) {

        if (monthlyCost < 1000) {

            return "Continue using your current transport method.";

        } else if (monthlyCost < 2000) {

            return "Consider carpooling where possible.";

        } else if (monthlyCost < 3000) {

            return "Reduce unnecessary trips and combine errands.";

        } else if (monthlyCost < 5000) {

            return "Use public transport where practical.";

        } else {

            return "Immediate action is recommended to reduce transport costs.";
        }
    }
}