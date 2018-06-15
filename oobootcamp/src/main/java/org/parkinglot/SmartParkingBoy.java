package org.parkinglot;

public class SmartParkingBoy extends ParkingBoy
{
    public SmartParkingBoy(int parkingLot) {
        super(parkingLot);
    }

    @Override
    boolean checkParkingAvailbleSlot(ParkingLot parkingLot) {
        boolean result = false;

        for(ParkingLot pkgLot : parkingLots){
            if(pkgLot != parkingLot){
                result = pkgLot.getParkingLot().size() > parkingLot.getParkingLot().size();
            }
        }
        return result;
    }
}
