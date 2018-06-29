package org.parkinglot;

import java.util.List;

public class SmartSearch implements SearchBehavior {
    @Override
    public ParkingLot search(List<ParkingLot> parkingLotList) {
        ParkingLot result = null;

        for (ParkingLot pkgLot : parkingLotList) {
            if (result == null) {
                if (pkgLot.getAvailableSlot() > 0) {
                    result = pkgLot;
                }
            } else {
                if (pkgLot.getAvailableSlot() > result.getAvailableSlot()) {
                    result = pkgLot;
                }
            }
        }
        return result;
    }
}
