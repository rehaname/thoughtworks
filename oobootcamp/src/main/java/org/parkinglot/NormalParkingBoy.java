package org.parkinglot;

public class NormalParkingBoy extends ParkingBoy {

    public NormalParkingBoy(int parkingLot) {
        super(parkingLot);
    }

    @Override
    boolean checkParkingAvailbleSlot(ParkingLot parkingLot) {
        return parkingLot.getParkingLot().size() < parkingLot.getParkingLimit() ;
    }
}
