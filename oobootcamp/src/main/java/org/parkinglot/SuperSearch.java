package org.parkinglot;

import java.util.List;

public class SuperSearch implements SearchBehavior {
    @Override
    public ParkingLot search(List<ParkingLot> parkingLotList) {
        ParkingLot result = null;

        for (ParkingLot pkgLot : parkingLotList) {
            if (result == null) {
                result = pkgLot;
            } else {
                if (pkgLot.getParkingRatio() > result.getParkingRatio()) {
                    result = pkgLot;
                }
            }
        }
        return result;
    }
}
