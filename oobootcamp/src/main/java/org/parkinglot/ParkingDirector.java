package org.parkinglot;

import java.util.ArrayList;
import java.util.List;

class ParkingDirector {
    private List<Report> reports = new ArrayList<>();

    List<Report> getReports() {
        return reports;
    }

    private void setManagers(List<ParkingManager> managers) {
        this.managers = managers;
    }

    private List<ParkingManager> managers = new ArrayList<>();

    void hireManager(List<ParkingManager> parkingManagers) {
        setManagers(parkingManagers);
    }

    void viewReport() {
        for (ParkingManager parkingManager : managers) {
            parkingManager.generateReport(reports);
        }
    }


}
