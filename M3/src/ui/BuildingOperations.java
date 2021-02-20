package ui;

import delegates.BuildingOperationsDelegate;
import model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BuildingOperations {
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";
    private static final int INVALID_INPUT = Integer.MIN_VALUE;
    private static final int EMPTY_INPUT = 0;

    private BufferedReader bufferedReader = null;
    private BuildingOperationsDelegate delegate = null;

    public BuildingOperations() {
    }

    public void setupDatabase(BuildingOperationsDelegate delegate) {
        this.delegate = delegate;

        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        int choice = INVALID_INPUT;
        // For testing purpose, Dataset will be reset.
        delegate.databaseSetup();
//        while(choice != 1 && choice != 2) {
//            System.out.println("You are about to reset all tables.\nIf you want to proceed, enter 1; if you want to quit, enter 2.");
//
//            choice = readInteger(false);
//
//            if (choice != INVALID_INPUT) {
//                switch (choice) {
//                    case 1:
//                        delegate.databaseSetup();
//                        break;
//                    case 2:
//                        handleQuitOption();
//                        break;
//                    default:
//                        System.out.println(WARNING_TAG + " The number that you entered was not a valid option.\n");
//                        break;
//                }
//            }
//        }
    }

    private int readInteger(boolean allowEmpty) {
        String line = null;
        int input = INVALID_INPUT;
        try {
            line = bufferedReader.readLine();
            input = Integer.parseInt(line);
        } catch (IOException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        } catch (NumberFormatException e) {
            if (allowEmpty && line.length() == 0) {
                input = EMPTY_INPUT;
            } else {
                System.out.println(WARNING_TAG + " Your input was not an integer");
            }
        }
        return input;
    }

    private void handleQuitOption() {
        System.out.println("Good Bye!");

        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                System.out.println("IOException!");
            }
        }

        delegate.buildingOperationsFinished();
    }

    public void showMainMenu(BuildingOperationsDelegate delegate) {
        this.delegate = delegate;

        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int choice = INVALID_INPUT;

        while (choice != 5) {
            System.out.println();
            System.out.println("1. Insert into a table");
            System.out.println("2. Delete a table");
            System.out.println("3. Update a table");
            System.out.println("4. Show table");
            System.out.println("5. Quit");
            System.out.print("Please choose one of the above 5 options: ");

            choice = readInteger(false);

            System.out.println(" ");

            if (choice != INVALID_INPUT) {
                switch (choice) {
                    case 1:
                        int insertChoice = INVALID_INPUT;
                        System.out.println();
                        System.out.println("1. Building table");
                        System.out.println("2. DOBandAge table");
                        System.out.println("3. Garbage Room table");
                        System.out.println("4. Landlord table");
                        System.out.println("5. Manage table");
                        System.out.println("6. Manager table");
                        System.out.println("7. Parking Spot table");
                        System.out.println("8. Postman table");
                        System.out.println("9. Resident table");
                        System.out.println("10. Room table");
                        System.out.println("11. Serve table");
                        System.out.println("12. SINandName table");
                        System.out.println("13. Tenant table");
                        System.out.println("14. Video Serveillance table");
                        System.out.println("15. Go back to main menu");
                        System.out.println("Please choose one of the above 15 options: ");

                        insertChoice = readInteger(false);

                        System.out.println(" ");
                        if (insertChoice != INVALID_INPUT) {
                            switch (insertChoice) {
                                case 1:
                                    handleInsertOptionBuilding();
                                    break;
                                case 2:
                                    handleInsertOptionDOBandAge();
                                    break;
                                case 3:
                                    handleInsertOptionGarbageRoom();
                                    break;
                                case 4:
                                    handleInsertOptionLandlord();
                                    break;
                                case 5:
                                    handleInsertOptionManage();
                                    break;
                                case 6:
                                    handleInsertOptionManager();
                                    break;
                                case 7:
                                    handleInsertOptionParkingSpot();
                                    break;
                                case 8:
                                    handleInsertOptionPostman();
                                    break;
                                case 9:
                                    handleInsertOptionResident();
                                    break;
                                case 10:
                                    handleInsertOptionRoom();
                                    break;
                                case 11:
                                    handleInsertOptionServe();
                                    break;
                                case 12:
                                    handleInsertOptionSINandName();
                                    break;
                                case 13:
                                    handleInsertOptionTenant();
                                    break;
                                case 14:
                                    handleInsertOptionVideoServeillance();
                                    break;
                                case 15:
                                    break;
                            }
                            break;
                        }
                        break;
                    case 2:
                        int deleteChoice = INVALID_INPUT;
                        System.out.println();
                        System.out.println("1. Building table");
                        System.out.println("2. DOBandAge table");
                        System.out.println("3. Garbage Room table");
                        System.out.println("4. Landlord table");
                        System.out.println("5. Manage table");
                        System.out.println("6. Manager table");
                        System.out.println("7. Parking Spot table");
                        System.out.println("8. Postman table");
                        System.out.println("9. Resident table");
                        System.out.println("10. Room table");
                        System.out.println("11. Serve table");
                        System.out.println("12. SINandName table");
                        System.out.println("13. Tenant table");
                        System.out.println("14. Video Serveillance table");
                        System.out.println("15. Go back to main menu");
                        System.out.println("Please choose one of the above 15 options: ");

                        deleteChoice = readInteger(false);

                        System.out.println(" ");
                        if (deleteChoice != INVALID_INPUT) {
                            switch (deleteChoice) {
                                case 1:
                                    handleDeleteOptionBuilding();
                                    break;
                                case 2:
                                    handleDeleteOptionDOBandAge();
                                    break;
                                case 3:
                                    handleDeleteOptionGarbageRoom();
                                    break;
                                case 4:
                                    handleDeleteOptionLandlord();
                                    break;
                                case 5:
                                    handleDeleteOptionManage();
                                    break;
                                case 6:
                                    handleDeleteOptionManager();
                                    break;
                                case 7:
                                    handleDeleteOptionParkingSpot();
                                    break;
                                case 8:
                                    handleDeleteOptionPostman();
                                    break;
                                case 9:
                                    handleDeleteOptionResident();
                                    break;
                                case 10:
                                    handleDeleteOptionRoom();
                                    break;
                                case 11:
                                    handleDeleteOptionServe();
                                    break;
                                case 12:
                                    handleDeleteOptionSINandName();
                                    break;
                                case 13:
                                    handleDeleteOptionTenant();
                                    break;
                                case 14:
                                    handleDeleteOptionVideoServeillance();
                                    break;
                                case 15:
                                    break;
                            }
                            break;
                        }
                        break;
                    case 3:
                        int updateChoice = INVALID_INPUT;
                        System.out.println();
                        System.out.println("1. Building table");
                        System.out.println("2. Garbage Room table");
                        System.out.println("3. Manager table");
                        System.out.println("4. Parking Spot table");
                        System.out.println("5. Postman table");
                        System.out.println("6. Resident table");
                        System.out.println("7. Room table");
                        System.out.println("8. Tenant table");
                        System.out.println("9. Video Serveillance table");
                        System.out.println("10. Go back to main menu");
                        System.out.println("Please choose one of the above 10 options: ");

                        updateChoice = readInteger(false);

                        System.out.println(" ");
                        if (updateChoice != INVALID_INPUT) {
                            switch (updateChoice) {
                                case 1:
                                    handleUpdateOptionBuilding();
                                    break;
                                case 2:
                                    handleUpdateOptionGarbageRoom();
                                    break;
                                case 3:
                                    handleUpdateOptionManager();
                                    break;
                                case 4:
                                    handleUpdateOptionParkingSpot();
                                    break;
                                case 5:
                                    handleUpdateOptionPostman();
                                    break;
                                case 6:
                                    handleUpdateOptionResident();
                                    break;
                                case 7:
                                    handleUpdateOptionRoom();
                                    break;
                                case 8:
                                    handleUpdateOptionTenant();
                                    break;
                                case 9:
                                    handleUpdateOptionVideoServeillance();
                                    break;
                                case 10:
                                    break;
                            }
                            break;
                        }
                        break;
                    case 4:
                        int showChoice = INVALID_INPUT;
                        System.out.println();
                        System.out.println("1. Building table");
                        System.out.println("2. DOBandAge table");
                        System.out.println("3. Garbage Room table");
                        System.out.println("4. Landlord table");
                        System.out.println("5. Manage table");
                        System.out.println("6. Manager table");
                        System.out.println("7. Parking Spot table");
                        System.out.println("8. Postman table");
                        System.out.println("9. Resident table");
                        System.out.println("10. Room table");
                        System.out.println("11. Serve table");
                        System.out.println("12. SINandName table");
                        System.out.println("13. Tenant table");
                        System.out.println("14. Video Serveillance table");
                        System.out.println("15. Go back to main menu");
                        System.out.println("Please choose one of the above 15 options: ");

                        showChoice = readInteger(false);

                        System.out.println(" ");
                        if (showChoice != INVALID_INPUT) {
                            switch (showChoice) {
                                case 1:
                                    delegate.showBuilding();
                                    break;
                                case 2:
                                    delegate.showDOBandAge();
                                    break;
                                case 3:
                                    delegate.showGarbageRoom();
                                    break;
                                case 4:
                                    delegate.showLandlord();
                                    break;
                                case 5:
                                    delegate.showManage();
                                    break;
                                case 6:
                                    delegate.showManager();
                                    break;
                                case 7:
                                    delegate.showParkingSpot();
                                    break;
                                case 8:
                                    delegate.showPostman();
                                    break;
                                case 9:
                                    delegate.showResident();
                                    break;
                                case 10:
                                    delegate.showRoom();
                                    break;
                                case 11:
                                    delegate.showServe();
                                    break;
                                case 12:
                                    delegate.showSINandName();
                                    break;
                                case 13:
                                    delegate.showTenant();
                                    break;
                                case 14:
                                    delegate.showVideoServeillance();
                                    break;
                                case 15:
                                    break;
                            }
                            break;
                        }
                        break;
                    case 5:
                        handleQuitOption();
                        break;
                    default:
                        System.out.println(WARNING_TAG + " The number that you entered was not a valid option.");
                        break;
                }
            }
        }
    }

    private String readLine() {
        String result = null;
        try {
            result = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result;
    }

    private void handleInsertOptionBuilding() {
        String address = null;
        while (address == null || address.length() <= 0) {
            System.out.print("Please enter the building address you wish to insert: ");
            address = readLine().trim();
        }

        String garbageRoomID = null;
        while (garbageRoomID == null || garbageRoomID.length() <= 0) {
            System.out.println("Please enter the garbage room ID you wish to insert: ");
            garbageRoomID = readLine().trim();
        }

        Building model = new Building(address, garbageRoomID);
        delegate.insertBuilding(model);
    }

    private void handleInsertOptionDOBandAge() {
        String birthday = null;
        while (birthday == null || birthday.length() <= 0) {
            System.out.print("Please enter the birthday you wish to insert: ");
            birthday = readLine().trim();
        }

        int age = INVALID_INPUT;
        while (age == INVALID_INPUT) {
            System.out.print("Please enter the age you wish to insert: ");
            age = readInteger(false);
        }

        DOBandAge model = new DOBandAge(birthday, age);
        delegate.insertDOBandAge(model);
    }

    private void handleInsertOptionGarbageRoom() {
        String cleardate = null;
        while (cleardate == null || (cleardate != "Monday" && cleardate != "Tuesday" && cleardate != "Wednesday" && cleardate != "Thursday" && cleardate != "Friday" && cleardate != "Saturday" && cleardate != "Sunday")) {
            System.out.print("Please enter the cleardate you wish to insert: ");
            cleardate = readLine().trim();
        }

        String id = null;
        while (id == null || id.length() <= 0) {
            System.out.print("Please enter the id you wish to insert: ");
            id = readLine().trim();
        }

        String address = null;
        while (address == null || address.length() <= 0) {
            System.out.print("Please enter the address you wish to insert: ");
            address = readLine().trim();
        }

        GarbageRoom model = new GarbageRoom(cleardate, id, address);
        delegate.insertGarbageRoom(model);
    }

    private void handleInsertOptionLandlord() {
        String sin = null;
        while (sin == null || sin.length() <= 0) {
            System.out.print("Please enter the SIN you wish to insert: ");
            sin = readLine().trim();
        }

        Landlord model = new Landlord(sin);
        delegate.insertLandlord(model);
    }

    private void handleInsertOptionManage() {
        String managerID = null;
        while (managerID == null || managerID.length() <= 0) {
            System.out.print("Please enter the managerID you wish to insert: ");
            managerID = readLine().trim();
        }

        String address = null;
        while (address == null || address.length() <= 0) {
            System.out.print("Please enter the address you wish to insert: ");
            address = readLine().trim();
        }

        Manage model = new Manage(managerID, address);
        delegate.insertManage(model);
    }

    private void handleInsertOptionManager() {
        String name = null;
        while (name == null || name.length() <= 0) {
            System.out.print("Please enter the name you wish to insert: ");
            name = readLine().trim();
        }

        String id = null;
        while (id == null || id.length() <= 0) {
            System.out.print("Please enter the id you wish to insert: ");
            id = readLine().trim();
        }

        String contactNum = null;
        while (contactNum == null || contactNum.length() <= 0) {
            System.out.print("Please enter the contactNum you wish to insert: ");
            contactNum = readLine().trim();
        }

        Manager model = new Manager(name, id, contactNum);
        delegate.insertManager(model);
    }

    private void handleInsertOptionParkingSpot() {
        String availability = null;
        while (availability == null || (availability != "true" && availability != "false")) {
            System.out.print("Please enter the availability you wish to insert: ");
            availability = readLine().trim();
        }

        String id = null;
        while (id == null || id.length() <= 0) {
            System.out.print("Please enter the id you wish to insert: ");
            id = readLine().trim();
        }

        String ownerName = null;
        while (ownerName == null || ownerName.length() <= 0) {
            System.out.print("Please enter the ownerName you wish to insert: ");
            ownerName = readLine().trim();
        }

        String address = null;
        while (address == null || address.length() <= 0) {
            System.out.print("Please enter the address you wish to insert: ");
            address = readLine().trim();
        }

        int unitNum = INVALID_INPUT;
        while (unitNum == INVALID_INPUT) {
            System.out.print("Please enter the unitNum you wish to insert: ");
            unitNum = readInteger(false);
        }

        String sin = null;
        while (sin == null || sin.length() <= 0) {
            System.out.print("Please enter the sin you wish to insert: ");
            sin = readLine().trim();
        }

        ParkingSpot model = new ParkingSpot(availability, id, ownerName, address, unitNum, sin);
        delegate.insertParkingSpot(model);
    }

    private void handleInsertOptionPostman() {
        String company = null;
        while (company == null || company.length() <= 0) {
            System.out.print("Please enter the company you wish to insert: ");
            company = readLine().trim();
        }

        String id = null;
        while (id == null || id.length() <= 0) {
            System.out.print("Please enter the id you wish to insert: ");
            id = readLine().trim();
        }

        String name = null;
        while (name == null || name.length() <= 0) {
            System.out.print("Please enter the name you wish to insert: ");
            name = readLine().trim();
        }

        Postman model = new Postman(company, id, name);
        delegate.insertPostman(model);
    }

    private void handleInsertOptionResident() {
        String sin = null;
        while (sin == null || sin.length() <= 0) {
            System.out.print("Please enter the sin you wish to insert: ");
            sin = readLine().trim();
        }

        String name = null;
        while (name == null || name.length() <= 0) {
            System.out.print("Please enter the name you wish to insert: ");
            name = readLine().trim();
        }

        String birthday = null;
        while (birthday == null || birthday.length() <= 0) {
            System.out.print("Please enter the birthday you wish to insert: ");
            birthday = readLine().trim();
        }

        int unitNum = INVALID_INPUT;
        while (unitNum == INVALID_INPUT) {
            System.out.print("Please enter the unitNum you wish to insert: ");
            unitNum = readInteger(false);
        }

        Resident model = new Resident(sin, name, birthday, unitNum);
        delegate.insertResident(model);
    }

    private void handleInsertOptionRoom() {
        int unitNum = INVALID_INPUT;
        while (unitNum == INVALID_INPUT) {
            System.out.print("Please enter the unitNum you wish to insert: ");
            unitNum = readInteger(false);
        }

        String address = null;
        while (address == null || address.length() <= 0) {
            System.out.print("Please enter the address you wish to insert: ");
            address = readLine().trim();
        }

        String parkingID = null;
        while (parkingID == null || parkingID.length() <= 0) {
            System.out.println("Please enter the parking ID you wish to insert: ");
            parkingID = readLine().trim();
        }

        Room model = new Room(unitNum, address, parkingID);
        delegate.insertRoom(model);
    }

    private void handleInsertOptionServe() {
        String company = null;
        while (company == null || company.length() <= 0) {
            System.out.print("Please enter the company you wish to insert: ");
            company = readLine().trim();
        }

        String postmanID = null;
        while (postmanID == null || postmanID.length() <= 0) {
            System.out.print("Please enter the postmanID you wish to insert: ");
            postmanID = readLine().trim();
        }

        String address = null;
        while (address == null || address.length() <= 0) {
            System.out.print("Please enter the address you wish to insert: ");
            address = readLine().trim();
        }

        Serve model = new Serve(company, postmanID, address);
        delegate.insertServe(model);
    }

    private void handleInsertOptionSINandName() {
        String sin = null;
        while (sin == null || sin.length() <= 0) {
            System.out.print("Please enter the sin you wish to insert: ");
            sin = readLine().trim();
        }

        String ownerName = null;
        while (ownerName == null || ownerName.length() <= 0) {
            System.out.print("Please enter the ownerName you wish to insert: ");
            ownerName = readLine().trim();
        }

        SINandName model = new SINandName(sin, ownerName);
        delegate.insertSINandName(model);
    }

    private void handleInsertOptionTenant() {
        String sin = null;
        while (sin == null || sin.length() <= 0) {
            System.out.print("Please enter the sin you wish to insert: ");
            sin = readLine().trim();
        }

        int monthlyRent = INVALID_INPUT;
        while (monthlyRent == INVALID_INPUT) {
            System.out.print("Please enter the monthlyRent you wish to insert: ");
            monthlyRent = readInteger(false);
        }

        Tenant model = new Tenant(sin, monthlyRent);
        delegate.insertTenant(model);
    }

    private void handleInsertOptionVideoServeillance() {
        String id = null;
        while (id == null || id.length() <= 0) {
            System.out.print("Please enter the id you wish to insert: ");
            id = readLine().trim();
        }

        int floor = INVALID_INPUT;
        while (floor == INVALID_INPUT) {
            System.out.print("Please enter the floor you wish to insert: ");
            floor = readInteger(false);
        }

        String address = null;
        while (address == null || address.length() <= 0) {
            System.out.print("Please enter the address you wish to insert: ");
            address = readLine().trim();
        }

        VideoServeillance model = new VideoServeillance(id, floor, address);
        delegate.insertVideoServeillance(model);
    }

    private void handleDeleteOptionBuilding() {
        String address = null;
        while (address == null || address.length() <= 0) {
            System.out.print("Please enter the address you wish to delete: ");
            address = readLine().trim();
        }
        delegate.deleteBuilding(address);
    }

    private void handleDeleteOptionDOBandAge() {
        String birthday = null;
        while (birthday == null || birthday.length() <= 0) {
            System.out.print("Please enter the birthday you wish to delete: ");
            birthday = readLine().trim();
        }
        int age = INVALID_INPUT;
        while (age == INVALID_INPUT) {
            System.out.print("Please enter the age you wish to delete: ");
            age = readInteger(false);
            if (age != INVALID_INPUT) {
                delegate.deleteDOBandAge(birthday, age);
            }
        }
    }

    private void handleDeleteOptionGarbageRoom() {
        String id = null;
        while (id == null || id.length() <= 0) {
            System.out.print("Please enter the id you wish to delete: ");
            id = readLine().trim();
        }
        delegate.deleteGarbageRoom(id);
    }

    private void handleDeleteOptionLandlord() {
        String sin = null;
        while (sin == null || sin.length() <= 0) {
            System.out.print("Please enter the sin you wish to delete: ");
            sin = readLine().trim();
        }
        delegate.deleteLandlord(sin);
    }

    private void handleDeleteOptionManage() {
        String managerID = null;
        while (managerID == null || managerID.length() <= 0) {
            System.out.print("Please enter the manager ID you wish to delete: ");
            managerID = readLine().trim();
        }
        String address = null;
        while (address == null || address.length() <= 0) {
            System.out.println("Please enter the address you with to delete: ");
            address = readLine().trim();
        }
        delegate.deleteManage(managerID, address);
    }

    private void handleDeleteOptionManager() {
        String id = null;
        while (id == null || id.length() <= 0) {
            System.out.print("Please enter the id you wish to delete: ");
            id = readLine().trim();
        }
        delegate.deleteManager(id);
    }

    private void handleDeleteOptionParkingSpot() {
        String id = null;
        while (id == null || id.length() <= 0) {
            System.out.print("Please enter the id you wish to delete: ");
            id = readLine().trim();
        }
        delegate.deleteParkingSpot(id);
    }

    private void handleDeleteOptionPostman() {
        String company = null;
        while (company == null || company.length() <= 0) {
            System.out.print("Please enter the company you wish to delete: ");
            company = readLine().trim();
        }

        String id = null;
        while (id == null || id.length() <= 0) {
            System.out.print("Please enter the id you wish to delete: ");
            id = readLine().trim();
        }
        delegate.deletePostman(company, id);
    }

    private void handleDeleteOptionResident() {
        String sin = null;
        while (sin == null || sin.length() <= 0) {
            System.out.print("Please enter the sin you wish to delete: ");
            sin = readLine().trim();
        }
        delegate.deleteResident(sin);
    }

    private void handleDeleteOptionRoom() {
        String address = null;
        while (address == null || address.length() <= 0) {
            System.out.print("Please enter the address you wish to delete: ");
            address = readLine().trim();
        }

        int unitNum = INVALID_INPUT;
        while (unitNum == INVALID_INPUT) {
            System.out.print("Please enter the unit number you wish to delete: ");
            unitNum = readInteger(false);
            if (unitNum != INVALID_INPUT) {
                delegate.deleteRoom(unitNum, address);
            }
        }
    }

    private void handleDeleteOptionServe() {
        String company = null;
        while (company == null || company.length() <= 0) {
            System.out.print("Please enter the company you wish to delete: ");
            company = readLine().trim();
        }

        String postmanID = null;
        while (postmanID == null || postmanID.length() <= 0) {
            System.out.print("Please enter the postmanID you wish to delete: ");
            postmanID = readLine().trim();
        }

        String address = null;
        while (address == null || address.length() <= 0) {
            System.out.print("Please enter the address you wish to delete: ");
            address = readLine().trim();
        }
        delegate.deleteServe(company, postmanID, address);
    }

    private void handleDeleteOptionSINandName() {
        String sin = null;
        while (sin == null || sin.length() <= 0) {
            System.out.print("Please enter the sin you wish to delete: ");
            sin = readLine().trim();
        }

        String ownerName = null;
        while (ownerName == null || ownerName.length() <= 0) {
            System.out.print("Please enter the ownerName you wish to delete: ");
            ownerName = readLine().trim();
        }
        delegate.deleteSINandName(sin, ownerName);
    }

    private void handleDeleteOptionTenant() {
        String sin = null;
        while (sin == null || sin.length() <= 0) {
            System.out.print("Please enter the sin you wish to delete: ");
            sin = readLine().trim();
        }
        delegate.deleteTenant(sin);
    }

    private void handleDeleteOptionVideoServeillance() {
        String id = null;
        while (id == null || id.length() <= 0) {
            System.out.print("Please enter the id you wish to delete: ");
            id = readLine().trim();
        }
        delegate.deleteVideoServeillance(id);
    }

    private void handleUpdateOptionBuilding() {
        String garbageRoomID = null;
        while (garbageRoomID == null || garbageRoomID.length() <= 0) {
            System.out.print("Please enter the garbage room ID you wish to update: ");
            garbageRoomID = readLine().trim();
        }

        String address = null;
        while (address == null || address.length() <= 0) {
            System.out.print("Please enter the address you wish to update: ");
            address = readLine().trim();
        }

        delegate.updateBuilding(garbageRoomID, address);
    }

    private void handleUpdateOptionGarbageRoom() {
        String cleardate = null;
        while (cleardate == null || (cleardate != "Monday" && cleardate != "Tuesday" && cleardate != "Wednesday" && cleardate != "Thursday" && cleardate != "Friday" && cleardate != "Saturday" && cleardate != "Sunday")) {
            System.out.print("Please enter the cleardate you wish to update: ");
            cleardate = readLine().trim();
        }

        String id = null;
        while (id == null || id.length() <= 0) {
            System.out.print("Please enter the id you wish to update: ");
            id = readLine().trim();
        }

        String address = null;
        while (address == null || address.length() <= 0) {
            System.out.print("Please enter the address you wish to update: ");
            address = readLine().trim();
        }

        delegate.updateGarbageRoom(id, cleardate, address);
    }

    private void handleUpdateOptionManager() {
        String name = null;
        while (name == null || name.length() <= 0) {
            System.out.print("Please enter the name you wish to update: ");
            name = readLine().trim();
        }

        String id = null;
        while (id == null || id.length() <= 0) {
            System.out.print("Please enter the id you wish to update: ");
            id = readLine().trim();
        }

        String contactNum = null;
        while (contactNum == null || contactNum.length() <= 0) {
            System.out.print("Please enter the contactNum you wish to update: ");
            contactNum = readLine().trim();
        }

        delegate.updateManager(id, name, contactNum);
    }

    private void handleUpdateOptionParkingSpot() {
        String availability = null;
        while (availability == null || (availability != "true" && availability != "false")) {
            System.out.print("Please enter the availability you wish to update: ");
            availability = readLine().trim();
        }

        String id = null;
        while (id == null || id.length() <= 0) {
            System.out.print("Please enter the id you wish to update: ");
            id = readLine().trim();
        }

        String ownerName = null;
        while (ownerName == null || ownerName.length() <= 0) {
            System.out.print("Please enter the ownerName you wish to update: ");
            ownerName = readLine().trim();
        }

        String address = null;
        while (address == null || address.length() <= 0) {
            System.out.print("Please enter the address you wish to update: ");
            address = readLine().trim();
        }

        int unitNum = INVALID_INPUT;
        while (unitNum == INVALID_INPUT) {
            System.out.print("Please enter the unitNum you wish to update: ");
            unitNum = readInteger(false);
        }

        String sin = null;
        while (sin == null || sin.length() <= 0) {
            System.out.print("Please enter the sin you wish to update: ");
            sin = readLine().trim();
        }

        delegate.updateParkingSpot(id, availability, ownerName, address, unitNum, sin);
    }

    private void handleUpdateOptionPostman() {
        String company = null;
        while (company == null || company.length() <= 0) {
            System.out.print("Please enter the company you wish to update: ");
            company = readLine().trim();
        }

        String id = null;
        while (id == null || id.length() <= 0) {
            System.out.print("Please enter the id you wish to update: ");
            id = readLine().trim();
        }

        String name = null;
        while (name == null || name.length() <= 0) {
            System.out.print("Please enter the name you wish to update: ");
            name = readLine().trim();
        }

        delegate.updatePostman(id, company, name);
    }

    private void handleUpdateOptionResident() {
        String sin = null;
        while (sin == null || sin.length() <= 0) {
            System.out.print("Please enter the sin you wish to update: ");
            sin = readLine().trim();
        }

        String name = null;
        while (name == null || name.length() <= 0) {
            System.out.print("Please enter the name you wish to update: ");
            name = readLine().trim();
        }

        String birthday = null;
        while (birthday == null || birthday.length() <= 0) {
            System.out.print("Please enter the birthday you wish to update: ");
            birthday = readLine().trim();
        }

        int unitNum = INVALID_INPUT;
        while (unitNum == INVALID_INPUT) {
            System.out.print("Please enter the unitNum you wish to update: ");
            unitNum = readInteger(false);
        }

        delegate.updateResident(sin, name, birthday, unitNum);
    }

    private void handleUpdateOptionRoom() {
        int unitNum = INVALID_INPUT;
        while (unitNum == INVALID_INPUT) {
            System.out.print("Please enter the unitNum you wish to update: ");
            unitNum = readInteger(false);
        }

        String address = null;
        while (address == null || address.length() <= 0) {
            System.out.print("Please enter the address you wish to update: ");
            address = readLine().trim();
        }

        String parkingID = null;
        while (parkingID == null || parkingID.length() <= 0) {
            System.out.println("Please enter the parking ID you wish to update: ");
            parkingID = readLine().trim();
        }

        delegate.updateRoom(unitNum, address, parkingID);
    }

    private void handleUpdateOptionTenant() {
        String sin = null;
        while (sin == null || sin.length() <= 0) {
            System.out.print("Please enter the sin you wish to update: ");
            sin = readLine().trim();
        }

        int monthlyRent = INVALID_INPUT;
        while (monthlyRent == INVALID_INPUT) {
            System.out.print("Please enter the monthlyRent you wish to update: ");
            monthlyRent = readInteger(false);
        }

        delegate.updateTenant(sin, monthlyRent);
    }

    private void handleUpdateOptionVideoServeillance() {
        String id = null;
        while (id == null || id.length() <= 0) {
            System.out.print("Please enter the id you wish to update: ");
            id = readLine().trim();
        }

        int floor = INVALID_INPUT;
        while (floor == INVALID_INPUT) {
            System.out.print("Please enter the floor you wish to update: ");
            floor = readInteger(false);
        }

        String address = null;
        while (address == null || address.length() <= 0) {
            System.out.print("Please enter the address you wish to update: ");
            address = readLine().trim();
        }

        delegate.updateVideoServeillance(id, floor, address);
    }
}
