package org.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private int parkingLimit = 10;

    public int getParkingLimit() {
        return parkingLimit;
    }

    private List<Car> parkingLot = new ArrayList();

    public List<Car> getParkingLot() {
        return parkingLot;
    }
}
