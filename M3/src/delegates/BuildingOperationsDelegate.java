package delegates;

import model.*;

import java.util.List;

public interface BuildingOperationsDelegate {
    public void databaseSetup();

    public void deleteBuilding(String address);
    public void insertBuilding(Building model);
    public List<String[]> showBuilding();
    public void updateBuilding(String address, String garbageRoomID);

    public void deleteGarbageRoom(String id);
    public void insertGarbageRoom(GarbageRoom model);
    public List<String[]> showGarbageRoom();
    public void updateGarbageRoom(String id, String clearDate, String address);

    public void deleteLandlord(String sin);
    public void insertLandlord(Landlord model);
    public List<String[]> showLandlord();

    public void deleteManage(String managerID, String address);
    public void insertManage(Manage model);
    public List<String[]> showManage();

    public void deleteManager(String id);
    public void insertManager(Manager model);
    public List<String[]> showManager();
    public void updateManager(String id, String name, String contactNum);

    public void deleteParkingSpot(String id);
    public void insertParkingSpot(ParkingSpot model);
    public List<String[]> showParkingSpot();
    public void updateParkingSpot(String id, String availability, String ownerName, String address, int unitNum, String sin);

    public void deletePostman(String id, String company);
    public void insertPostman(Postman model);
    public List<String[]> showPostman();
    public void updatePostman(String id, String company, String name);

    public void deleteResident(String sin);
    public void insertResident(Resident model);
    public List<String[]> showResident();
    public void updateResident(String sin, String name, String birthday, int unitNum);

    public void deleteRoom(int unitNum, String address);
    public void insertRoom(Room model);
    public List<String[]> showRoom();
    public void updateRoom(int unitNum, String address, String parkingID);

    public void deleteServe(String company, String postmanID, String address);
    public void insertServe(Serve model);
    public List<String[]> showServe();

    public void deleteTenant(String sin);
    public void insertTenant(Tenant model);
    public List<String[]> showTenant();
    public void updateTenant(String sin, int monthlyRent);

    public void deleteVideoServeillance(String id);
    public void insertVideoServeillance(VideoServeillance model);
    public List<String[]> showVideoServeillance();
    public void updateVideoServeillance(String id, int floor, String address);

    public void deleteDOBandAge(String birthday, int age);
    public void insertDOBandAge(DOBandAge model);
    public List<String[]> showDOBandAge();

    public void deleteSINandName(String sin, String ownerName);
    public void insertSINandName(SINandName model);
    public List<String[]> showSINandName();

    public void buildingOperationsFinished();
}
