package org.parkinglot;

import java.util.ArrayList;
import java.util.List;

class ParkingLot {
    private int parkingLimit = 10;

    ParkingLot(int parkingLimit) {
        this.parkingLimit = parkingLimit;
    }

    private int getParkingLimit() {
        return parkingLimit;
    }

    private List<Car> cars = new ArrayList<>();

    List<Car> getCars() {
        return cars;
    }

    int getAvailableSlot() {
        return parkingLimit - cars.size();
    }

    double getParkingRatio() {
        return (double) getAvailableSlot() / (double) parkingLimit;
    }

    boolean isAvailable() {
        return getCars().size() < getParkingLimit();
    }


    void park(Car car){
        cars.add(car);
    }
}
