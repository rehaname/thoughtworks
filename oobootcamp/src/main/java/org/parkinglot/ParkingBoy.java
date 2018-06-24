package org.parkinglot;

import java.util.ArrayList;
import java.util.List;

class ParkingBoy {
    private int parkingLot = 0;
    private int parkingNumber = 0;
    private List<ParkingLot> parkingLots = new ArrayList<>();
    private SearchBehavior searchBehavior;



    ParkingBoy(SearchBehavior searchBehavior){
        this.searchBehavior = searchBehavior;
    }

    Ticket parkCar(Car car) {
        Ticket ticket = null;

        ParkingLot selectedParkingLot = retrieveAvailableVacantLot();
        selectedParkingLot.getCars().add(car);
        if (containsCar(car)) {
            ticket = new Ticket(parkingNumber++);
            car.setTicket(ticket);
        }

        return ticket;
    }

    public ParkingLot retrieveAvailableVacantLot() {
        return searchBehavior.search(parkingLots);
    }

    boolean containsCar(Car car) {
        for (ParkingLot pkgLot : parkingLots) {
            if (pkgLot.getCars().contains(car)) {
                return true;
            }
        }
        return false;
    }

    Car pickUpCar(Ticket ticket) {
        for (ParkingLot pkngLot : parkingLots) {
            for (Car car1 : pkngLot.getCars()) {
                if (car1.checkAssignedTicket(ticket)) {
                    return car1;
                }
            }
        }
        return null;
    }

    List<ParkingLot> getParkingLots() {
        return parkingLots;
    }
    void addParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

}
