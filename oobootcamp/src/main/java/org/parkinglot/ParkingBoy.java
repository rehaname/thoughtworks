package org.parkinglot;

import java.util.ArrayList;
import java.util.List;

abstract class ParkingBoy {
    private int parkingLot = 0;
    private int parkingNumber = 0;
    List<ParkingLot> parkingLots = new ArrayList<>();


    abstract boolean checkParkingAvailbleSlot(ParkingLot parkingLot);

    public ParkingBoy(int parkingLot) {
        this.parkingLot = parkingLot;
        initParkingLots();
    }

    private void initParkingLots() {
        for (int x = 0; x < this.parkingLot; x++) {
            parkingLots.add(new ParkingLot());
        }
    }

    public Ticket parkCar(Car car) {
        Ticket ticket = null;

        for (ParkingLot pkngLot : parkingLots) {
            if (checkParkingAvailbleSlot(pkngLot)) {
                boolean hasBeenParked = pkngLot.getParkingLot().add(car);
                if (hasBeenParked) {
                    ticket = new Ticket(parkingNumber++);
                    car.setTicket(ticket);
                }
                break;
            }
        }


        return ticket;
    }


    public boolean containsCar(Car car) {
        boolean result = false;
        for (ParkingLot pkngLot : parkingLots) {
            result = pkngLot.getParkingLot().contains(car);
            if (result) {
                return result;
            }
        }
        return result;
    }

    public Car pickUpCar(Ticket ticket) {
        Car car = null;
        for (ParkingLot pkngLot : parkingLots) {
            for (Car car1:pkngLot.getParkingLot()) {
                car = car1.checkAssignedTicket(ticket) ? car1 : null;
            }
        }
        return car;
    }

}
