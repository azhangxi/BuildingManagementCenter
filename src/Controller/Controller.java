package Controller;

import database.DatabaseConnectionHandler;
import delegates.BuildingOperationsDelegate;
import delegates.LoginWindowDelegate;
import model.*;
import ui.BuildingOperations;
import ui.LoginWindow;
import ui.Main;
import ui.MainMenu;

import java.util.ArrayList;
import java.util.List;

public class Controller implements LoginWindowDelegate, BuildingOperationsDelegate {

    private DatabaseConnectionHandler dbHandler = null;
    private LoginWindow loginWindow = null;

    public Controller() {
        dbHandler = new DatabaseConnectionHandler();
    }

    public void start() {
        loginWindow = new LoginWindow();
        loginWindow.showFrame(this);
    }

    public void login(String username, String password) {
        boolean didConnect = dbHandler.login(username, password);

        if (didConnect) {
            // Once connected, remove login window and start text transaction flow
            loginWindow.dispose();
            BuildingOperations operation = new BuildingOperations();
            operation.setupDatabase(this);
            Main.MAIN_WINDOW = new MainMenu("BuildingServiceCenter", this);
//            operation.showMainMenu(this);
        } else {
            loginWindow.handleLoginFailed();

            if (loginWindow.hasReachedMaxLoginAttempts()) {
                loginWindow.dispose();
                System.out.println("You have exceeded your number of allowed attempts");
                System.exit(-1);
            }
        }
    }

    public void databaseSetup() {
        dbHandler.databaseSetup();;
    }

    public void insertBuilding(Building model) {
        dbHandler.insertBuilding(model);
    }

    public void insertDOBandAge(DOBandAge model) {
        dbHandler.insertDOBandAge(model);
    }

    public void insertGarbageRoom(GarbageRoom model) {
        dbHandler.insertGarbageRoom(model);
    }

    public void insertLandlord(Landlord model) {
        dbHandler.insertLandlord(model);
    }

    public void insertManage(Manage model) {
        dbHandler.insertManage(model);
    }

    public void insertManager(Manager model) {
        dbHandler.insertManager(model);
    }

    public void insertParkingSpot(ParkingSpot model) {
        dbHandler.insertParkingSpot(model);
    }

    public void insertPostman(Postman model) {
        dbHandler.insertPostman(model);
    }

    public void insertResident(Resident model) {
        dbHandler.insertResident(model);
    }

    public void insertRoom(Room model) {
        dbHandler.insertRoom(model);
    }

    public void insertServe(Serve model) {
        dbHandler.insertServe(model);
    }

    public void insertSINandName(SINandName model) {
        dbHandler.insertSINandName(model);
    }

    public void insertTenant(Tenant model) {
        dbHandler.insertTenant(model);
    }

    public void insertVideoServeillance(VideoServeillance model) {
        dbHandler.insertVideoServeillance(model);
    }

    public void deleteResident(String sin) {
        dbHandler.deleteResident(sin);
    }

    public void deleteDOBandAge(String birthday, int age) {
        dbHandler.deleteDOBandAge(birthday, age);
    }

    public void deleteTenant(String sin) {
        dbHandler.deleteTenant(sin);
    }

    public void deleteLandlord(String sin) {
        dbHandler.deleteLandlord(sin);
    }

    public void deleteParkingSpot(String id) {
        dbHandler.deleteParkingSpot(id);
    }

    public void deleteServe(String company, String postmanID, String address) {
        dbHandler.deleteServe(company, postmanID, address);
    }

    public void deleteManage(String managerID, String address) {
        dbHandler.deleteManage(managerID, address);
    }

    public void deletePostman(String company, String id) {
        dbHandler.deletePostman(company, id);
    }

    public void deleteVideoServeillance(String id) {
        dbHandler.deleteVideoServeillance(id);
    }

    public void deleteGarbageRoom(String id) {
        dbHandler.deleteGarbageRoom(id);
    }

    public void deleteManager(String id) {
        dbHandler.deleteManager(id);
    }

    public void deleteRoom(int unitNum, String address) {
        dbHandler.deleteRoom(unitNum, address);
    }

    public void deleteBuilding(String garbageRoomID) {
        dbHandler.deleteBuilding(garbageRoomID);
    }

    public void deleteSINandName(String sin, String ownerName) {
        dbHandler.deleteSINandName(sin, ownerName);
    }

    public void updateResident(String sin, String name, String birthday, int roomNum) {
        dbHandler.updateResident(sin, name, birthday, roomNum);
    }

    public void updateTenant(String sin, int monthlyRent) {
        dbHandler.updateTenant(sin, monthlyRent);
    }

    public void updateParkingSpot(String id, String availability, String ownerName, String address, int unitNum, String sin) {
        dbHandler.updateParkingSpot(id, availability, ownerName, address, unitNum, sin);
    }

    public void updatePostman(String company, String id, String name) {
        dbHandler.updatePostman(company, id, name);
    }

    public void updateVideoServeillance(String id, int floor, String address) {
        dbHandler.updateVideoServeillance(id, floor, address);
    }

    public void updateGarbageRoom(String id, String cleardate, String address) {
        dbHandler.updateGarbageRoom(id, cleardate, address);
    }

    public void updateManager(String id, String name, String contactNum) {
        dbHandler.updateManager(id, name, contactNum);
    }

    public void updateRoom(int unitNum, String address, String parkingID) {
        dbHandler.updateRoom(unitNum, address, parkingID);
    }

    public void updateBuilding(String garbageRoomID, String address) {
        dbHandler.updateBuilding(garbageRoomID, address);
    }

    public List<String[]> showBuilding() {
        Building[] models = dbHandler.getBuildingInfo();
        List<String[]> rtn = new ArrayList<>();

        String str1 = fillOutTheArray("Address", 50);
        String str2 = fillOutTheArray("GarbageRoomID", 20);
        rtn.add(new String[]{str1, str2});

        for (int i = 0; i < models.length; i++) {
            Building model = models[i];

            String str11 = fillOutTheArray(model.getAddress(), 50);
            String str22 = fillOutTheArray(model.getGarbageRoomID(), 20);
            rtn.add(new String[]{str11, str22});

            System.out.printf("%-50.50s", model.getAddress());
            System.out.printf("%-20.20s", model.getGarbageRoomID());
            System.out.println();
        }
        return rtn;
    }

    public List<String[]> showDOBandAge() {
        DOBandAge[] models = dbHandler.getDOBandAgeInfo();
        List<String[]> rtn = new ArrayList<>();

        String str1 = fillOutTheArray("Birthday", 20);
        String str2 = fillOutTheArray("Age", 20);
        rtn.add(new String[]{str1, str2});

        for (int i = 0; i < models.length; i++) {
            DOBandAge model = models[i];
            String str11 = fillOutTheArray(model.getBirthday(), 20);
            String str22 = fillOutTheArray(model.getAge(), 20);
            rtn.add(new String[]{str11, str22});
            System.out.printf("%-20.20s", model.getBirthday());
            System.out.printf("%-20.20s", model.getAge());

            System.out.println();
        }
        return rtn;
    }

    public List<String[]> showGarbageRoom() {
        GarbageRoom[] models = dbHandler.getGarbageRoomInfo();
        List<String[]> rtn = new ArrayList<>();

        String title1 = fillOutTheArray("ClearDate", 20);
        String title2 = fillOutTheArray("Id", 20);
        String title3 = fillOutTheArray("Address", 50);
        rtn.add(new String[]{title1, title2, title3});

        for (int i = 0; i < models.length; i++) {
            GarbageRoom model = models[i];

            String str1 = fillOutTheArray(model.getClearDate(), 20);
            String str2 = fillOutTheArray(model.getId(), 20);
            String str3 = fillOutTheArray(model.getAddress(), 50);

            rtn.add(new String[]{str1, str2, str3});

            System.out.printf("%-20.20s", model.getClearDate());
            System.out.printf("%-20.20s", model.getId());
            System.out.printf("%-50.50s", model.getAddress());

            System.out.println();
        }
        return rtn;
    }

    public List<String[]> showLandlord() {
        Landlord[] models = dbHandler.getLandlordInfo();
        List<String[]> rtn = new ArrayList<>();

        String title1 = fillOutTheArray("SIN", 20);

        rtn.add(new String[]{title1});

        for (int i = 0; i < models.length; i++) {
            Landlord model = models[i];
            String str1 = fillOutTheArray(model.getSin(), 20);

            rtn.add(new String[]{str1});

            System.out.printf("%-20.20s", model.getSin());

            System.out.println();
        }
        return rtn;
    }

    public List<String[]> showManage() {
        Manage[] models = dbHandler.getManageInfo();
        List<String[]> rtn = new ArrayList<>();

        String title1 = fillOutTheArray("ManagerID", 20);
        String title2 = fillOutTheArray("Address", 50);
        rtn.add(new String[]{title1, title2});

        for (int i = 0; i < models.length; i++) {
            Manage model = models[i];
            String str1 = fillOutTheArray(model.getManagerID(), 20);
            String str2 = fillOutTheArray(model.getAddress(), 50);

            rtn.add(new String[]{str1, str2});
            System.out.printf("%-20.20s", model.getManagerID());
            System.out.printf("%-50.50s", model.getAddress());

            System.out.println();
        }
        return rtn;
    }

    public List<String[]> showManager() {
        Manager[] models = dbHandler.getManagerInfo();
        List<String[]> rtn = new ArrayList<>();

        String title1 = fillOutTheArray("Name", 20);
        String title2 = fillOutTheArray("Id", 20);
        String title3 = fillOutTheArray("ContactNum", 20);
        rtn.add(new String[]{title1, title2, title3});

        for (int i = 0; i < models.length; i++) {
            Manager model = models[i];

            String str1 = fillOutTheArray(model.getName(), 20);
            String str2 = fillOutTheArray(model.getId(), 20);
            String str3 = fillOutTheArray(model.getContactNum(), 20);

            rtn.add(new String[]{str1, str2, str3});

            System.out.printf("%-20.20s", model.getName());
            System.out.printf("%-20.20s", model.getId());
            System.out.printf("%-20.20s", model.getContactNum());

            System.out.println();
        }
        return rtn;
    }

    public List<String[]> showParkingSpot() {
        ParkingSpot[] models = dbHandler.getParkingSpotInfo();
        List<String[]> rtn = new ArrayList<>();

        String title1 = fillOutTheArray("Availability", 20);
        String title2 = fillOutTheArray("Id", 20);
        String title3 = fillOutTheArray("OwnerName", 20);
        String title4 = fillOutTheArray("Address", 20);
        String title5 = fillOutTheArray("UnitNum", 20);
        String title6 = fillOutTheArray("SIN", 20);

        rtn.add(new String[]{title1, title2, title3, title4, title5, title6});
        for (int i = 0; i < models.length; i++) {
            ParkingSpot model = models[i];

            String str1 = fillOutTheArray(model.isAvailability(), 20);
            String str2 = fillOutTheArray(model.getId(), 20);
            String str3 = fillOutTheArray(model.getOwnerName(), 20);
            String str4 = fillOutTheArray(model.getAddress(), 50);
            String str5 = fillOutTheArray(model.getUnitNum(), 20);
            String str6 = fillOutTheArray(model.getSin(), 20);

            rtn.add(new String[]{str1, str2, str3, str4, str5, str6});

            System.out.printf("%-20.20s", model.isAvailability());
            System.out.printf("%-20.20s", model.getId());
            System.out.printf("%-20.20s", model.getOwnerName());
            System.out.printf("%-50.50s", model.getAddress());
            System.out.printf("%-20.20s", model.getUnitNum());
            System.out.printf("%-20.20s", model.getSin());

            System.out.println();
        }
        return rtn;
    }

    public List<String[]> showPostman() {
        Postman[] models = dbHandler.getPostmanInfo();
        List<String[]> rtn = new ArrayList<>();

        String title1 = fillOutTheArray("Company", 20);
        String title2 = fillOutTheArray("Id", 20);
        String title3 = fillOutTheArray("Name", 20);
        rtn.add(new String[]{title1, title2, title3});

        for (int i = 0; i < models.length; i++) {
            Postman model = models[i];

            String str1 = fillOutTheArray(model.getCompany(), 20);
            String str2 = fillOutTheArray(model.getId(), 20);
            String str3 = fillOutTheArray(model.getName(), 20);

            rtn.add(new String[]{str1, str2, str3});

            System.out.printf("%-20.20s", model.getCompany());
            System.out.printf("%-20.20s", model.getId());
            System.out.printf("%-20.20s", model.getName());

            System.out.println();
        }
        return rtn;
    }

    public List<String[]> showResident() {
        Resident[] models = dbHandler.getResidentInfo();
        List<String[]> rtn = new ArrayList<>();

        String title1 = fillOutTheArray("SIN", 20);
        String title2 = fillOutTheArray("Name", 20);
        String title3 = fillOutTheArray("Birthday", 20);
        String title4 = fillOutTheArray("UnitNum", 20);
        rtn.add(new String[]{title1, title2, title3, title4});

        for (int i = 0; i < models.length; i++) {
            Resident model = models[i];

            String str1 = fillOutTheArray(model.getSin(), 20);
            String str2 = fillOutTheArray(model.getName(), 20);
            String str3 = fillOutTheArray(model.getBirthday(), 20);
            String str4 = fillOutTheArray(model.getUnitNum(), 20);

            rtn.add(new String[]{str1, str2, str3, str4});
            System.out.printf("%-20.20s", model.getSin());
            System.out.printf("%-20.20s", model.getName());
            System.out.printf("%-20.20s", model.getBirthday());
            System.out.printf("%-20.20s", model.getUnitNum());

            System.out.println();
        }
        return rtn;
    }

    public List<String[]> showRoom() {
        Room[] models = dbHandler.getRoomInfo();
        List<String[]> rtn = new ArrayList<>();

        String title1 = fillOutTheArray("UnitNum", 20);
        String title2 = fillOutTheArray("Address", 50);
        String title3 = fillOutTheArray("ParkingID", 20);
        rtn.add(new String[]{title1, title2, title3});

        for (int i = 0; i < models.length; i++) {
            Room model = models[i];

            String str1 = fillOutTheArray(model.getUnitNum(), 20);
            String str2 = fillOutTheArray(model.getAddress(), 50);
            String str3 = fillOutTheArray(model.getParkingID(), 20);

            rtn.add(new String[]{str1, str2, str3});


            System.out.printf("%-20.20s", model.getUnitNum());
            System.out.printf("%-50.50s", model.getAddress());
            System.out.printf("%-20.20s", model.getParkingID());

            System.out.println();
        }
        return rtn;
    }

    public List<String[]> showServe() {
        Serve[] models = dbHandler.getServeInfo();
        List<String[]> rtn = new ArrayList<>();

        String title1 = fillOutTheArray("Company", 20);
        String title2 = fillOutTheArray("PostmanID", 20);
        String title3 = fillOutTheArray("Address", 50);
        rtn.add(new String[]{title1, title2, title3});

        for (int i = 0; i < models.length; i++) {
            Serve model = models[i];

            String str1 = fillOutTheArray(model.getCompany(), 20);
            String str2 = fillOutTheArray(model.getPostmanID(), 20);
            String str3 = fillOutTheArray(model.getAddress(), 50);

            rtn.add(new String[]{str1, str2, str3});

            System.out.printf("%-20.20s", model.getCompany());
            System.out.printf("%-20.20s", model.getPostmanID());
            System.out.printf("%-50.50s", model.getAddress());

            System.out.println();
        }
        return rtn;
    }

    public List<String[]> showSINandName() {
        SINandName[] models = dbHandler.getSINandNameInfo();
        List<String[]> rtn = new ArrayList<>();

        String title1 = fillOutTheArray("SIN", 20);
        String title2 = fillOutTheArray("OwnerName", 20);
        rtn.add(new String[]{title1, title2});

        for (int i = 0; i < models.length; i++) {
            SINandName model = models[i];

            String str1 = fillOutTheArray(model.getSin(), 20);
            String str2 = fillOutTheArray(model.getOwnerName(), 20);

            rtn.add(new String[]{str1, str2});

            System.out.printf("%-20.20s", model.getSin());
            System.out.printf("%-20.20s", model.getOwnerName());

            System.out.println();
        }
        return rtn;
    }

    public List<String[]> showTenant() {
        Tenant[] models = dbHandler.getTenantInfo();
        List<String[]> rtn = new ArrayList<>();

        String title1 = fillOutTheArray("SIN", 20);
        String title2 = fillOutTheArray("MonthlyRent", 20);
        rtn.add(new String[]{title1, title2});

        for (int i = 0; i < models.length; i++) {
            Tenant model = models[i];

            String str1 = fillOutTheArray(model.getSin(), 20);
            String str2 = fillOutTheArray(model.getMonthlyRent(), 20);

            rtn.add(new String[]{str1, str2});

            System.out.printf("%-20.20s", model.getSin());
            System.out.printf("%-20.20s", model.getMonthlyRent());

            System.out.println();
        }
        return rtn;
    }

    public List<String[]> showVideoServeillance() {
        VideoServeillance[] models = dbHandler.getVideoServeillanceInfo();
        List<String[]> rtn = new ArrayList<>();

        String title1 = fillOutTheArray("Id", 20);
        String title2 = fillOutTheArray("Floor", 20);
        String title3 = fillOutTheArray("Address", 50);
        rtn.add(new String[]{title1, title2, title3});

        for (int i = 0; i < models.length; i++) {
            VideoServeillance model = models[i];

            String str1 = fillOutTheArray(model.getId(), 20);
            String str2 = fillOutTheArray(model.getFloor(), 20);
            String str3 = fillOutTheArray(model.getAddress(), 50);

            rtn.add(new String[]{str1, str2, str3});

            System.out.printf("%-20.20s", model.getId());
            System.out.printf("%-20.20s", model.getFloor());
            System.out.printf("%-50.50s", model.getAddress());

            System.out.println();
        }
        return rtn;
    }

    public List<String[]> projection() {
        ParkingSpot[] models = dbHandler.projection();
        List<String[]> rtn = new ArrayList<>();

        String title3 = fillOutTheArray("OwnerName", 20);
        String title5 = fillOutTheArray("UnitNum", 20);
        String title6 = fillOutTheArray("SIN", 20);

        rtn.add(new String[]{title3, title5, title6});
        for (int i = 0; i < models.length; i++) {
            ParkingSpot model = models[i];

            String str3 = fillOutTheArray(model.getOwnerName(), 20);
            String str5 = fillOutTheArray(model.getUnitNum(), 20);
            String str6 = fillOutTheArray(model.getSin(), 20);

            rtn.add(new String[]{str3, str5, str6});

        }
        return rtn;
    }

    public List<String[]> selection(int monthlyRent) {
        Tenant[] models = dbHandler.selection(monthlyRent);

        List<String[]> rtn = new ArrayList<>();

        String title1 = fillOutTheArray("SIN", 20);
        String title2 = fillOutTheArray("MonthlyRent", 20);
        rtn.add(new String[]{title1, title2});

        for (int i = 0; i < models.length; i++) {
            Tenant model = models[i];

            String str1 = fillOutTheArray(model.getSin(), 20);
            String str2 = fillOutTheArray(model.getMonthlyRent(), 20);

            rtn.add(new String[]{str1, str2});

            System.out.println();
        }
        return rtn;
    }

    public List<String[]> join(String ownerName) {
        return dbHandler.join(ownerName);
    }

    public List<String[]> groupBy() {
        return dbHandler.groupBy();
    }

    public List<String[]> groupByHaving() {
        return dbHandler.groupByHaving();
    }

    public List<String[]> nestedAggregationWithGroupBy() {
        return dbHandler.nestedAggregationWithGroupBy();
    }

    public List<String[]> division() {
        return dbHandler.division();
    }

    public void buildingOperationsFinished() {
        dbHandler.close();
        dbHandler = null;

        System.exit(0);
    }

    private String fillOutTheArray(String str, int length) {
        String.format("%-" + length + "s", str);
        return str;
    }

    private String fillOutTheArray(int inp, int length) {
        String str = String.valueOf(inp);
        String.format("%-" + length + "s", str);
        return str;
    }
}
