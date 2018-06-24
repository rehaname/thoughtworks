package org.parkinglot;

import java.util.List;

public class NormalSearch implements SearchBehavior {
    @Override
    public ParkingLot search(List<ParkingLot> parkingLotList) {
        return parkingLotList.stream()
                .filter(lot -> lot.isAvailable()).findFirst().get();
    }

}
