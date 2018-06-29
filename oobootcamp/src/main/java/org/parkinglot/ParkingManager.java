package org.parkinglot;

import java.util.ArrayList;
import java.util.List;

class ParkingManager extends ParkingBoy {
    private static final String PARKING_BOY = "B";
    private static final String MANAGER = "M";
    private static final String PARKING_LOT = "P";
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


    void generateReport(List<Report> reports) {
        Report managerReport = new Report();
        managerReport.setUnit(MANAGER);
        reports.add(managerReport);
        for (ParkingLot lot : getParkingLots()) {
            Report managerParkingLotReport = new Report();
            reports.add(managerParkingLotReport);
            mapReport(managerReport, lot, managerParkingLotReport);
        }
        for (ParkingBoy parkingBoy : parkingBoyList) {
            Report parkingBoyReport = new Report();
            parkingBoyReport.setUnit(PARKING_BOY);
            reports.add(parkingBoyReport);
            for (ParkingLot lot : parkingBoy.getParkingLots()) {
                Report parkingBoyParkingLotReport = new Report();
                reports.add(parkingBoyParkingLotReport);
                mapReport(parkingBoyReport, lot, parkingBoyParkingLotReport);
                incrementManagerReport(managerReport, parkingBoyParkingLotReport);
            }
        }
    }


    String generateReportString() {
        StringBuilder reportBuilder = new StringBuilder();
        int managerMaxLimit= 0;
        int managerCar= 0;



        int containerLotLimit = 0;
        int containerCar = 0;


        for (int i = parkingBoyList.size()-1; i >=0 ; i--) {
            int parkingBoyLotLimit = 0;
            int parkingBoyCar=0;

            for (ParkingLot lot : parkingBoyList.get(i).getParkingLots()) {
                containerLotLimit = lot.getParkingLimit();
                containerCar = lot.getCars().size();

                parkingBoyLotLimit += lot.getParkingLimit();
                parkingBoyCar += lot.getCars().size();

                reportBuilder.append(PARKING_LOT).append(" ").append(containerCar).append(" ").append(containerLotLimit).append("\n");
                managerMaxLimit+=containerLotLimit;
                managerCar+=containerCar;
            }

            reportBuilder.append(PARKING_BOY).append(" ").append(parkingBoyCar).append(" ").append(parkingBoyLotLimit).append("\n");
        }


        for (ParkingLot lot : getParkingLots()) {
            containerLotLimit = lot.getParkingLimit();
            containerCar = lot.getCars().size();
            reportBuilder.append(PARKING_LOT).append(" ").append(containerCar).append(" ").append(containerLotLimit).append("\n");
            managerMaxLimit+=containerLotLimit;
            managerCar+=containerCar;
        }

        reportBuilder.append(MANAGER).append(" ").append(managerCar).append(" ").append(managerMaxLimit).append("\n");
        System.out.println(reportBuilder.toString());
        return reportBuilder.toString();

    }

    private void mapReport(Report parkingBoyReport, ParkingLot lot, Report parkingBoyParkingLotReport) {
        parkingBoyParkingLotReport.setMaximumLimit(String.valueOf(lot.getParkingLimit()));
        parkingBoyParkingLotReport.setParkedCars(String.valueOf(lot.getCars().size()));
        parkingBoyParkingLotReport.setUnit(PARKING_LOT);
        incrementManagerReport(parkingBoyReport, parkingBoyParkingLotReport);
    }
    private void incrementManagerReport(Report managerReport, Report parkingBoyParkingLotReport) {
        managerReport.setParkedCars(String.valueOf(Integer.parseInt(managerReport.getParkedCars()) + Integer.parseInt(parkingBoyParkingLotReport.getParkedCars())));
        managerReport.setMaximumLimit(String.valueOf(Integer.parseInt(managerReport.getMaximumLimit()) + Integer.parseInt(parkingBoyParkingLotReport.getMaximumLimit())));
    }


}
