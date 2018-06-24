package org.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingManager extends ParkingBoy{
    List<ParkingBoy> parkingBoyList = new ArrayList<>();
    ParkingManager(SearchBehavior searchBehavior) {
        super(searchBehavior);
    }

    public void addParkingBoy(ParkingBoy parkingBoy) {
        parkingBoyList.add(parkingBoy);
    }

//    @Override
//    Car pickUpCar(Ticket ticket){
//        Car car = null;
//
//        parkingBoyList.stream().map
//
//        return car;
//    }

    @Override
    Ticket parkCar(Car car) {
     return   getAvailableParkingBoy().parkCar(car);
    }

    ParkingBoy getAvailableParkingBoy(){
        for(ParkingBoy parkingboy : parkingBoyList){
            if (parkingboy.retrieveAvailableVacantLot() != null) {
                return parkingboy;
            }
        }
        return this;
    }
}
