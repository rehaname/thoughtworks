package org.parkinglot;

class Report {
    private String unit ="M";
    private String parkedCars = "0";
    private String maximumLimit = "0";

    String getUnit() {
        return unit;
    }

    void setUnit(String unit) {
        this.unit = unit;
    }

    String getParkedCars() {
        return parkedCars;
    }

    void setParkedCars(String parkedCars) {
        this.parkedCars = parkedCars;
    }

    String getMaximumLimit() {
        return maximumLimit;
    }

    void setMaximumLimit(String maximumLimit) {
        this.maximumLimit = maximumLimit;
    }
}
