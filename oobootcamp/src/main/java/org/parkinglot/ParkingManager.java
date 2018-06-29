package org.parkinglot;

import java.util.ArrayList;
import java.util.List;

class ParkingManager extends ParkingBoy {
    private List<ParkingBoy> parkingBoyList = new ArrayList<>();

    ParkingManager(SearchBehavior searchBehavior) {
        super(searchBehavior);
    }

    void addParkingBoy(ParkingBoy parkingBoy) {
        parkingBoyList.add(parkingBoy);
    }

    @Override
    Car pickUpCar(Ticket ticket) {
        for (ParkingBoy parkingboy : parkingBoyList) {
            Car car = parkingboy.pickUpCar(ticket);
            if (car != null) {
                return car;
            }
        }
        return super.pickUpCar(ticket);
    }

    @Override
    Ticket parkCar(Car car) {
        for (ParkingBoy parkingboy : parkingBoyList) {
            if (parkingboy.retrieveAvailableVacantLot() != null) {
                return parkingboy.parkCar(car);
            }
        }
        return super.parkCar(car);
    }

}
