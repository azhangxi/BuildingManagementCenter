package database;

import com.sun.org.apache.xpath.internal.operations.Div;
import model.*;
import oracle.jdbc.proxy.annotation.Post;
import ui.MainMenu;

import java.security.acl.Group;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class DatabaseConnectionHandler {
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    private Connection connection = null;

    public DatabaseConnectionHandler() {
        try {
            // Load the Oracle JDBC driver
            // Note that the path could change for new drivers
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public boolean login(String username, String password) {
        try {
            if (connection != null) {
                connection.close();
            }

            connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            System.out.println("\nConnected to Oracle!");
            return true;
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }

    public void databaseSetup() {
        dropAllTableIfExists();

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE Building (id varchar2(8), address varchar2(50) PRIMARY KEY)");
            stmt.executeUpdate("CREATE TABLE GarbageRoom (id varchar2(8) PRIMARY KEY, clearDate varchar2(20) not null, address varchar2(50) UNIQUE)");
            stmt.executeUpdate("CREATE TABLE Landlord (sin varchar2(9) PRIMARY KEY)");
            stmt.executeUpdate("CREATE TABLE Manage (id varchar2(8), address varchar2(50), PRIMARY KEY (id, Address))");
            stmt.executeUpdate("CREATE TABLE Manager (name varchar2(20) not null, id varchar2(8) PRIMARY KEY, contactNum INTEGER UNIQUE)");
            stmt.executeUpdate("CREATE TABLE ParkingSpot (availability varchar2(5) not null, id varchar2(8) PRIMARY KEY, ownerName varchar2(20), address varchar2(50), unitNum INTEGER UNIQUE, sin varchar2(9))");
            stmt.executeUpdate("CREATE TABLE Postman (company varchar2(50), id varchar2(8), name varchar2(20) not null, PRIMARY KEY (id, company))");
            stmt.executeUpdate("CREATE TABLE Resident (sin varchar2(9) PRIMARY KEY, name varchar2(20) not null, birthday varchar2(10), unitNum INTEGER)");
            stmt.executeUpdate("CREATE TABLE Room (unitNum INTEGER, address varchar2(50), id varchar2(8) not null, PRIMARY KEY (unitNum, address), UNIQUE (id))");
            stmt.executeUpdate("CREATE TABLE Serve (company varchar2(50), id varchar2(8), address varchar2(50), PRIMARY KEY(company, id, address))");
            stmt.executeUpdate("CREATE TABLE Tenant (sin varchar2(9), monthlyRent INTEGER not null, PRIMARY KEY (sin))");
            stmt.executeUpdate("CREATE TABLE VideoServeillance (id varchar2(8), floor INTEGER not null, address varchar2(50) not null, PRIMARY KEY (id))");
            stmt.executeUpdate("CREATE TABLE DOBandAge (birthday varchar2(10), age INTEGER, PRIMARY KEY (birthday))");
            stmt.executeUpdate("CREATE TABLE SINandName (sin varchar2(9), ownerName varchar2(20) not null, PRIMARY KEY (sin))");
            stmt.executeUpdate("ALTER TABLE Building ADD CONSTRAINT Building_fk FOREIGN KEY (id) REFERENCES GarbageRoom ON DELETE CASCADE");
            stmt.executeUpdate("ALTER TABLE GarbageRoom ADD CONSTRAINT GarbageRoom_fk FOREIGN KEY (address) REFERENCES Building ON DELETE CASCADE");
            stmt.executeUpdate("ALTER TABLE Landlord ADD CONSTRAINT Landlord_fk FOREIGN KEY (sin) REFERENCES Resident ON DELETE CASCADE");
            stmt.executeUpdate("ALTER TABLE Manage ADD CONSTRAINT Manage_fk1 FOREIGN KEY (id) REFERENCES Manager ON DELETE CASCADE");
            stmt.executeUpdate("ALTER TABLE Manage ADD CONSTRAINT Manage_fk2 FOREIGN KEY (address) REFERENCES Building ON DELETE CASCADE");
            stmt.executeUpdate("ALTER TABLE ParkingSpot ADD CONSTRAINT ParkingSpot_fk1 FOREIGN KEY (address) REFERENCES Building ON DELETE CASCADE");
            stmt.executeUpdate("ALTER TABLE ParkingSpot ADD CONSTRAINT ParkingSpot_fk2 FOREIGN KEY (sin) REFERENCES Resident ON DELETE CASCADE");
            stmt.executeUpdate("ALTER TABLE Room ADD CONSTRAINT Room_fk FOREIGN KEY (id) REFERENCES ParkingSpot ON DELETE CASCADE");
            stmt.executeUpdate("ALTER TABLE Serve ADD CONSTRAINT Serve_fk1 FOREIGN KEY (id, company) REFERENCES Postman ON DELETE CASCADE");
            stmt.executeUpdate("ALTER TABLE Serve ADD CONSTRAINT Serve_fk2 FOREIGN KEY (address) REFERENCES Building ON DELETE CASCADE");
            stmt.executeUpdate("ALTER TABLE Tenant ADD CONSTRAINT Tenant_fk FOREIGN KEY (sin) REFERENCES Resident ON DELETE CASCADE");
            stmt.executeUpdate("ALTER TABLE VideoServeillance ADD CONSTRAINT VideoServeillance_fk FOREIGN KEY (address) REFERENCES Building ON DELETE CASCADE");
            stmt.close();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        Resident resident1 = new Resident("239944031", "Jai Parrish", "1999/02/23", 105);
        insertResident(resident1);

        Resident resident2 = new Resident("102471900", "Sorcha Bean", "1999/02/23", 708);
        insertResident(resident2);

        DOBandAge dobandage1 = new DOBandAge("1999/02/23", 21);
        insertDOBandAge(dobandage1);

        DOBandAge dobandage2 = new DOBandAge("1998/02/25", 22);
        insertDOBandAge(dobandage2);

        Tenant tenant1 = new Tenant("239944031", 5745);
        insertTenant(tenant1);

        Tenant tenant2 = new Tenant("102471900", 7163);
        insertTenant(tenant2);

        Landlord landlord1 = new Landlord("239944031");
        insertLandlord(landlord1);

        Landlord landlord2 = new Landlord("102471900");
        insertLandlord(landlord2);

        GarbageRoom garbageroom1 = new GarbageRoom("Monday", "63388699", null);
        insertGarbageRoom(garbageroom1);

        Building building1 = new Building("63388699", "3588 Crowley Drive, Vancouver, BC, V5R 6H3");
        insertBuilding(building1);

        ParkingSpot parkingspot1 = new ParkingSpot("true", "45678786", "Jai Parrish", "3588 Crowley Drive, Vancouver, BC, V5R 6H3", 105, "239944031");
        insertParkingSpot(parkingspot1);

        GarbageRoom garbageroom2 = new GarbageRoom("Sunday", "87875675", null);
        insertGarbageRoom(garbageroom2);

        Building building2 = new Building("87875675", "3760 Alexander Rd, Richmond, BC, V6X 1C6");
        insertBuilding(building2);

        ParkingSpot parkingspot2 = new ParkingSpot("true", "67542579", "Sorcha Bean", "3760 Alexander Rd, Richmond, BC, V6X 1C6", 708, "102471900");
        insertParkingSpot(parkingspot2);

        Postman postman1 = new Postman("Canada Post", "24679383", "Jackson Wang");
        insertPostman(postman1);

        Postman postman2 = new Postman("Canada Post", "24568987", "Nick Harrington");
        insertPostman(postman2);

        Manager manager1 = new Manager("Jeremy Gill", "76864674", "77887791505");
        insertManager(manager1);

        Manager manager2 = new Manager("Sirena Harrop", "86656889", "7788792253");
        insertManager(manager2);

        Manage manage1 = new Manage("76864674", "3588 Crowley Drive, Vancouver, BC, V5R 6H3");
        insertManage(manage1);

        Manage manage2 = new Manage("86656889", "3760 Alexander Rd, Richmond, BC, V6X 1C6");
        insertManage(manage2);

        VideoServeillance videoserveillance1 = new VideoServeillance("12476877", -1, "3760 Alexander Rd, Richmond, BC, V6X 1C6");
        insertVideoServeillance(videoserveillance1);

        VideoServeillance videoserveillance2 = new VideoServeillance("68689543", -2, "3588 Crowley Drive, Vancouver, BC, V5R 6H3");
        insertVideoServeillance(videoserveillance2);

        Room room1 = new Room(105, "3588 Crowley Drive, Vancouver, BC, V5R 6H3", "45678786");
        insertRoom(room1);

        Room room2 = new Room(708, "3760 Alexander Rd, Richmond, BC, V6X 1C6", "67542579");
        insertRoom(room2);

        Serve serve1 = new Serve("Canada Post", "24679383", "3588 Crowley Drive, Vancouver, BC, V5R 6H3");
        insertServe(serve1);

        Serve serve2 = new Serve("Canada Post", "24568987", "3760 Alexander Rd, Richmond, BC, V6X 1C6");
        insertServe(serve2);

        updateGarbageRoom( "Monday", "63388699",  "3588 Crowley Drive, Vancouver, BC, V5R 6H3");

        updateGarbageRoom("Sunday","87875675", "3760 Alexander Rd, Richmond, BC, V6X 1C6");
    }

    private void dropAllTableIfExists() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while(rs.next()) {
                Statement stmt2 = connection.createStatement();
                if (rs.getString(1).toLowerCase().equals("building")) {
                    stmt2.execute("DROP TABLE Building CASCADE CONSTRAINTS");
                } else if (rs.getString(1).toLowerCase().equals("garbageroom")) {
                    stmt2.execute("DROP TABLE GarbageRoom CASCADE CONSTRAINTS");
                } else if (rs.getString(1).toLowerCase().equals("landlord")) {
                    stmt2.execute("DROP TABLE Landlord CASCADE CONSTRAINTS");
                } else if (rs.getString(1).toLowerCase().equals("manage")) {
                    stmt2.execute("DROP TABLE Manage CASCADE CONSTRAINTS");
                } else if (rs.getString(1).toLowerCase().equals("manager")) {
                    stmt2.execute("DROP TABLE Manager CASCADE CONSTRAINTS");
                } else if (rs.getString(1).toLowerCase().equals("parkingspot")) {
                    stmt2.execute("DROP TABLE ParkingSpot CASCADE CONSTRAINTS");
                } else if (rs.getString(1).toLowerCase().equals("postman")) {
                    stmt2.execute("DROP TABLE Postman CASCADE CONSTRAINTS");
                } else if (rs.getString(1).toLowerCase().equals("resident")) {
                    stmt2.execute("DROP TABLE Resident CASCADE CONSTRAINTS");
                } else if (rs.getString(1).toLowerCase().equals("room")) {
                    stmt2.execute("DROP TABLE Room CASCADE CONSTRAINTS");
                } else if (rs.getString(1).toLowerCase().equals("serve")) {
                    stmt2.execute("DROP TABLE Serve CASCADE CONSTRAINTS");
                } else if (rs.getString(1).toLowerCase().equals("tenant")) {
                    stmt2.execute("DROP TABLE Tenant CASCADE CONSTRAINTS");
                } else if (rs.getString(1).toLowerCase().equals("videoserveillance")) {
                    stmt2.execute("DROP TABLE VideoServeillance CASCADE CONSTRAINTS");
                } else if (rs.getString(1).toLowerCase().equals("dobandage")) {
                    stmt2.execute("DROP TABLE DOBandAge CASCADE CONSTRAINTS");
                } else if (rs.getString(1).toLowerCase().equals("sinandname")) {
                    stmt2.execute("DROP TABLE SINandName CASCADE CONSTRAINTS");
                }
                stmt2.close();
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    private void rollbackConnection() {
        try  {
            connection.rollback();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void insertResident(Resident model) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Resident VALUES (?,?,?,?)");
            ps.setString(1, model.getSin());
            ps.setString(2, model.getName());
            ps.setString(3, model.getBirthday());
            ps.setInt(4, model.getUnitNum());

            ps.executeUpdate();
            connection.commit();

            ps.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void deleteResident(String sin) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Resident WHERE sin = ?");
            ps.setString(1, sin);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                MainMenu.makeWarningDialog(WARNING_TAG + " Resident " + sin + " does not exist!");
                System.out.println(WARNING_TAG + " Resident " + sin + " does not exist!");
            }

            connection.commit();

            ps.close();
            MainMenu.playDeleteClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void updateResident(String sin, String name, String birthday, int roomNum) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Resident SET name = ?, birthday = ?, roomNum = ? WHERE sin = ?");
            ps.setString(1, name);
            ps.setString(2, birthday);
            ps.setInt(3, roomNum);
            ps.setString(4, sin);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                MainMenu.makeWarningDialog(WARNING_TAG + " Resident " + sin + " does not exist!");
                System.out.println(WARNING_TAG + " Resident " + sin + " does not exist!");
            }

            connection.commit();

            ps.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public Resident[] getResidentInfo() {
        ArrayList<Resident> result = new ArrayList<Resident>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Resident");

            while(rs.next()) {
                Resident model = new Resident(rs.getString("sin"),
                        rs.getString("name"),
                        rs.getString("birthday"),
                        rs.getInt("unitNum"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new Resident[result.size()]);
    }

    public void insertDOBandAge(DOBandAge model) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO DOBandAge VALUES (?,?)");
            ps.setString(1, model.getBirthday());
            ps.setInt(2, model.getAge());

            ps.executeUpdate();
            connection.commit();

            ps.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void deleteDOBandAge(String birthday, int age) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM DOBandAge WHERE birthday = ? AND age = ?");
            ps.setString(1, birthday);
            ps.setInt(2, age);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                MainMenu.makeWarningDialog(WARNING_TAG + " DOBandAge " + birthday + age + " does not exist!");
                System.out.println(WARNING_TAG + " DOBandAge " + birthday + age + " does not exist!");
            }

            connection.commit();

            ps.close();
            MainMenu.playDeleteClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public DOBandAge[] getDOBandAgeInfo() {
        ArrayList<DOBandAge> result = new ArrayList<DOBandAge>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM DOBandAge");

            while(rs.next()) {
                DOBandAge model = new DOBandAge(
                        rs.getString("birthday"),
                        rs.getInt("age"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new DOBandAge[result.size()]);
    }

    public void insertTenant(Tenant model) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Tenant VALUES (?,?)");
            ps.setString(1, model.getSin());
            ps.setInt(2, model.getMonthlyRent());

            ps.executeUpdate();
            connection.commit();

            ps.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void deleteTenant(String sin) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Tenant WHERE sin = ?");
            ps.setString(1, sin);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                MainMenu.makeWarningDialog(WARNING_TAG + " Tenant " + sin + " does not exist!");
                System.out.println(WARNING_TAG + " Tenant " + sin + " does not exist!");
            }

            connection.commit();

            ps.close();
            MainMenu.playDeleteClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void updateTenant(String sin, int monthlyRent) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Tenant SET monthlyRent = ? WHERE sin = ?");
            ps.setInt(1, monthlyRent);
            ps.setString(2, sin);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                MainMenu.makeWarningDialog(WARNING_TAG + " Tenant " + sin + " does not exist!");
                System.out.println(WARNING_TAG + " Tenant " + sin + " does not exist!");
            }

            connection.commit();

            ps.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public Tenant[] getTenantInfo() {
        ArrayList<Tenant> result = new ArrayList<Tenant>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Tenant");

            while(rs.next()) {
                Tenant model = new Tenant(rs.getString("sin"),
                        rs.getInt("monthlyRent"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new Tenant[result.size()]);
    }

    public void insertLandlord(Landlord model) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Landlord VALUES (?)");
            ps.setString(1, model.getSin());

            ps.executeUpdate();
            connection.commit();

            ps.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void deleteLandlord(String sin) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Landlord WHERE sin = ?");
            ps.setString(1, sin);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                MainMenu.makeWarningDialog(WARNING_TAG + " Landlord " + sin + " does not exist!");
                System.out.println(WARNING_TAG + " Landlord " + sin + " does not exist!");
            }

            connection.commit();

            ps.close();
            MainMenu.playDeleteClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public Landlord[] getLandlordInfo() {
        ArrayList<Landlord> result = new ArrayList<Landlord>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Landlord");

            while(rs.next()) {
                Landlord model = new Landlord(rs.getString("sin"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new Landlord[result.size()]);
    }

    public void insertParkingSpot(ParkingSpot model) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO ParkingSpot VALUES (?,?,?,?,?,?)");
            ps.setString(1, model.isAvailability());
            ps.setString(2, model.getId());
            ps.setString(3, model.getOwnerName());
            ps.setString(4, model.getAddress());
            ps.setInt(5, model.getUnitNum());
            ps.setString(6, model.getSin());


            ps.executeUpdate();
            connection.commit();

            ps.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void deleteParkingSpot(String id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM ParkingSpot WHERE id = ?");
            ps.setString(1, id);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                MainMenu.makeWarningDialog(WARNING_TAG + " ParkingSpot " + id + " does not exist!");
                System.out.println(WARNING_TAG + " ParkingSpot " + id + " does not exist!");
            }

            connection.commit();

            ps.close();
            MainMenu.playDeleteClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void updateParkingSpot(String availability, String id, String ownerName, String address, int unitNum, String sin) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE ParkingSpot SET availability = ?, ownerName = ?, address = ?, unitNum = ?, sin = ? WHERE id = ?");
            ps.setString(1, availability);
            ps.setString(2, ownerName);
            ps.setString(3, address);
            ps.setInt(4, unitNum);
            ps.setString(5, sin);
            ps.setString(6, id);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                MainMenu.makeWarningDialog(WARNING_TAG + " ParkingSpot " + id + " does not exist!");
                System.out.println(WARNING_TAG + " ParkingSpot " + id + " does not exist!");
            }

            connection.commit();

            ps.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public ParkingSpot[] getParkingSpotInfo() {
        ArrayList<ParkingSpot> result = new ArrayList<ParkingSpot>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ParkingSpot");

            while(rs.next()) {
                ParkingSpot model = new ParkingSpot(rs.getString("availability"),
                        rs.getString("id"),
                        rs.getString("ownerName"),
                        rs.getString("address"),
                        rs.getInt("unitNum"),
                        rs.getString("sin"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new ParkingSpot[result.size()]);
    }

    public void insertServe(Serve model) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Serve VALUES (?,?,?)");
            ps.setString(1, model.getCompany());
            ps.setString(2, model.getPostmanID());
            ps.setString(3, model.getAddress());


            ps.executeUpdate();
            connection.commit();

            ps.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void deleteServe(String company, String postmanID, String address) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Serve WHERE company = ? AND id = ? AND address = ?");
            ps.setString(1, company);
            ps.setString(2, postmanID);
            ps.setString(3, address);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                MainMenu.makeWarningDialog(WARNING_TAG + " Serve " + company + postmanID + address + " does not exist!");
                System.out.println(WARNING_TAG + " Serve " + company + postmanID + address + " does not exist!");
            }

            connection.commit();

            ps.close();
            MainMenu.playDeleteClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public Serve[] getServeInfo() {
        ArrayList<Serve> result = new ArrayList<Serve>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Serve");

            while(rs.next()) {
                Serve model = new Serve(rs.getString("company"),
                        rs.getString("id"),
                        rs.getString("address"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new Serve[result.size()]);
    }

    public void insertManage(Manage model) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Manage VALUES (?,?)");
            ps.setString(1, model.getManagerID());
            ps.setString(2, model.getAddress());


            ps.executeUpdate();
            connection.commit();

            ps.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void deleteManage(String managerID, String address) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Manage WHERE managerID = ? AND address = ?");
            ps.setString(1, managerID);
            ps.setString(2, address);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                MainMenu.makeWarningDialog(WARNING_TAG + " Manage " + managerID + address + " does not exist!");
                System.out.println(WARNING_TAG + " Manage " + managerID + address + " does not exist!");
            }

            connection.commit();

            ps.close();
            MainMenu.playDeleteClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public Manage[] getManageInfo() {
        ArrayList<Manage> result = new ArrayList<Manage>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Manage");

            while(rs.next()) {
                Manage model = new Manage(rs.getString("id"),
                        rs.getString("address"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new Manage[result.size()]);
    }

    public void insertPostman(Postman model) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Postman VALUES (?,?,?)");
            ps.setString(1, model.getCompany());
            ps.setString(2, model.getId());
            ps.setString(3, model.getName());


            ps.executeUpdate();
            connection.commit();

            ps.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void deletePostman(String company, String id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Postman WHERE company = ? AND id = ?");
            ps.setString(1, company);
            ps.setString(2, id);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                MainMenu.makeWarningDialog(WARNING_TAG + " Postman " + company + id + " does not exist!");
                System.out.println(WARNING_TAG + " Postman " + company + id + " does not exist!");
            }

            connection.commit();

            ps.close();
            MainMenu.playDeleteClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void updatePostman(String company, String id, String name) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Postman SET name = ? WHERE company = ? AND id = ?");
            ps.setString(1, name);
            ps.setString(2, company);
            ps.setString(3, id);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                MainMenu.makeWarningDialog(WARNING_TAG + " Postman " + company + id + " does not exist!");
                System.out.println(WARNING_TAG + " Postman " + company + id + " does not exist!");
            }

            connection.commit();

            ps.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public Postman[] getPostmanInfo() {
        ArrayList<Postman> result = new ArrayList<Postman>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Postman");

            while(rs.next()) {
                Postman model = new Postman(rs.getString("company"),
                        rs.getString("id"),
                        rs.getString("name"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new Postman[result.size()]);
    }

    public void insertVideoServeillance(VideoServeillance model) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO VideoServeillance VALUES (?,?,?)");
            ps.setString(1, model.getId());
            ps.setInt(2, model.getFloor());
            ps.setString(3, model.getAddress());


            ps.executeUpdate();
            connection.commit();

            ps.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void deleteVideoServeillance(String id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM VideoServeillance WHERE id = ?");
            ps.setString(1, id);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                MainMenu.makeWarningDialog(WARNING_TAG + " VideoServeillance " + id + " does not exist!");
                System.out.println(WARNING_TAG + " VideoServeillance " + id + " does not exist!");
            }

            connection.commit();

            ps.close();
            MainMenu.playDeleteClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void updateVideoServeillance(String id, int floor, String address) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE VideoServeillance SET floor = ?, address = ? WHERE id = ?");
            ps.setInt(1, floor);
            ps.setString(2, address);
            ps.setString(3, id);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                MainMenu.makeWarningDialog(WARNING_TAG + " VideoServeillance " + id + " does not exist!");
                System.out.println(WARNING_TAG + " VideoServeillance " + id + " does not exist!");
            }

            connection.commit();

            ps.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public VideoServeillance[] getVideoServeillanceInfo() {
        ArrayList<VideoServeillance> result = new ArrayList<VideoServeillance>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM VideoServeillance");

            while(rs.next()) {
                VideoServeillance model = new VideoServeillance(rs.getString("id"),
                        rs.getInt("floor"),
                        rs.getString("address"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new VideoServeillance[result.size()]);
    }

    public void insertGarbageRoom(GarbageRoom model) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO GarbageRoom VALUES (?,?,?)");
            ps.setString(1, model.getId());
            ps.setString(2, model.getClearDate());
            ps.setString(3, model.getAddress());


            ps.executeUpdate();
            connection.commit();

            ps.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void deleteGarbageRoom(String id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM GarbageRoom WHERE id = ?");
            ps.setString(1, id);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                MainMenu.makeWarningDialog(WARNING_TAG + " GarbageRoom " + id + " does not exist!");
                System.out.println(WARNING_TAG + " GarbageRoom " + id + " does not exist!");
            }

            connection.commit();

            ps.close();
            MainMenu.playDeleteClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void updateGarbageRoom(String cleardate, String id, String address) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE GarbageRoom SET cleardate = ?, address = ? WHERE id = ?");
            ps.setString(1, cleardate);
            ps.setString(2, address);
            ps.setString(3, id);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                MainMenu.makeWarningDialog(WARNING_TAG + " GarbageRoom " + id + " does not exist!");
                System.out.println(WARNING_TAG + " GarbageRoom " + id + " does not exist!");
            }

            connection.commit();

            ps.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public GarbageRoom[] getGarbageRoomInfo() {
        ArrayList<GarbageRoom> result = new ArrayList<GarbageRoom>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM GarbageRoom");

            while(rs.next()) {
                GarbageRoom model = new GarbageRoom(rs.getString("cleardate"),
                        rs.getString("id"),
                        rs.getString("address"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new GarbageRoom[result.size()]);
    }

    public void insertManager(Manager model) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Manager VALUES (?,?,?)");
            ps.setString(1, model.getName());
            ps.setString(2, model.getId());
            ps.setString(3, model.getContactNum());


            ps.executeUpdate();
            connection.commit();

            ps.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void deleteManager(String id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Manager WHERE id = ?");
            ps.setString(1, id);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                MainMenu.makeWarningDialog(WARNING_TAG + " Manager " + id + " does not exist!");
                System.out.println(WARNING_TAG + " Manager " + id + " does not exist!");
            }

            connection.commit();

            ps.close();
            MainMenu.playDeleteClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void updateManager(String name, String id, String contactNum) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Manager SET name = ?, contactNum = ? WHERE id = ?");
            ps.setString(1, name);
            ps.setString(2, contactNum);
            ps.setString(3, id);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                MainMenu.makeWarningDialog(WARNING_TAG + " Manager " + id + " does not exist!");
                System.out.println(WARNING_TAG + " Manager " + id + " does not exist!");
            }

            connection.commit();

            ps.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public Manager[] getManagerInfo() {
        ArrayList<Manager> result = new ArrayList<Manager>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Manager");

            while(rs.next()) {
                Manager model = new Manager(rs.getString("name"),
                        rs.getString("id"),
                        rs.getString("contactNum"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new Manager[result.size()]);
    }

    public void insertRoom(Room model) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Room VALUES (?,?,?)");
            ps.setInt(1, model.getUnitNum());
            ps.setString(2, model.getAddress());
            ps.setString(3, model.getParkingID());


            ps.executeUpdate();
            connection.commit();

            ps.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void deleteRoom(int unitNum, String address) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Room WHERE unitNum = ? AND address = ?");
            ps.setInt(1, unitNum);
            ps.setString(2, address);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                MainMenu.makeWarningDialog(WARNING_TAG + " Room " + unitNum + address + " does not exist!");
                System.out.println(WARNING_TAG + " Room " + unitNum + address + " does not exist!");
            }

            connection.commit();

            ps.close();
            MainMenu.playDeleteClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void updateRoom(int unitNum, String address, String parkingID) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Room SET parkingID = ? WHERE unitNum = ? AND address = ?");
            ps.setString(1, parkingID);
            ps.setInt(2, unitNum);
            ps.setString(3, address);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                MainMenu.makeWarningDialog(WARNING_TAG + " Room " + unitNum + address + " does not exist!");
                System.out.println(WARNING_TAG + " Room " + unitNum + address + " does not exist!");
            }

            connection.commit();

            ps.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public Room[] getRoomInfo() {
        ArrayList<Room> result = new ArrayList<Room>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Room");

            while(rs.next()) {
                Room model = new Room(rs.getInt("unitNum"),
                        rs.getString("address"),
                        rs.getString("id"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new Room[result.size()]);
    }

    public void insertBuilding(Building model) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Building VALUES (?,?)");
            ps.setString(1, model.getGarbageRoomID());
            ps.setString(2, model.getAddress());


            ps.executeUpdate();
            connection.commit();

            ps.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void deleteBuilding(String address) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Building WHERE address = ?");
            ps.setString(1, address);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                MainMenu.makeWarningDialog(WARNING_TAG + " Building " + address + " does not exist!");
                System.out.println(WARNING_TAG + " Building " + address + " does not exist!");
            }

            connection.commit();

            ps.close();
            MainMenu.playDeleteClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void updateBuilding(String address, String garbageRoomID) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Building SET id = ? WHERE address = ?");
            ps.setString(1, garbageRoomID);
            ps.setString(2, address);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                MainMenu.makeWarningDialog(WARNING_TAG + " Building " + address + " does not exist!");
                System.out.println(WARNING_TAG + " Building " + address + " does not exist!");
            }

            connection.commit();

            ps.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public Building[] getBuildingInfo() {
        ArrayList<Building> result = new ArrayList<Building>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Building");

            while(rs.next()) {
                Building model = new Building(rs.getString("id"),
                        rs.getString("address"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new Building[result.size()]);
    }

    public void insertSINandName(SINandName model) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO SINandName VALUES (?,?)");
            ps.setString(1, model.getSin());
            ps.setString(2, model.getOwnerName());


            ps.executeUpdate();
            connection.commit();

            ps.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void deleteSINandName(String sin, String ownerName) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM SINandName WHERE sin = ? AND ownerName = ?");
            ps.setString(1, sin);
            ps.setString(2, ownerName);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                MainMenu.makeWarningDialog(WARNING_TAG + " SINandName " + sin + ownerName + " does not exist!");
                System.out.println(WARNING_TAG + " SINandName " + sin + ownerName + " does not exist!");
            }

            connection.commit();

            ps.close();
            MainMenu.playDeleteClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public SINandName[] getSINandNameInfo() {
        ArrayList<SINandName> result = new ArrayList<SINandName>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SINandName");

            while(rs.next()) {
                SINandName model = new SINandName(rs.getString("sin"),
                        rs.getString("ownerName"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new SINandName[result.size()]);
    }

    public ParkingSpot[] projection() {
        ArrayList<ParkingSpot> result = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT sin, ownerName, unitNum FROM ParkingSpot");

            while(rs.next()) {
                ParkingSpot model = new ParkingSpot(null,
                        null,
                        rs.getString("ownerName"),
                        null,
                        rs.getInt("unitNum"),
                        rs.getString("sin"));
                result.add(model);
            }

            rs.close();
            stmt.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

        return result.toArray(new ParkingSpot[result.size()]);
    }

    public Tenant[] selection(int monthRent) {
        ArrayList<Tenant> result = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM Tenant WHERE monthlyRent > " + monthRent);

            while(rs.next()) {
                Tenant model = new Tenant(rs.getString("sin"),
                        rs.getInt("monthlyRent"));
                result.add(model);
            }

            rs.close();
            stmt.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

        return result.toArray(new Tenant[result.size()]);
    }

    // join room and parking spot
    public List<String[]> join(String name) {
        List<String[]> result = new ArrayList<>();

        try {
            result.add(new String[]{"Unit Number", "Address", "Id", "Availability", "Owner Name", "SIN"});
            Statement stmt = connection.createStatement();
            name = "\'" + name + "\'";
            String s = "SELECT * FROM Room r, ParkingSpot ps WHERE r.id = ps.id and ownerName = " + name;
            System.out.println("==" + s + "==");
            ResultSet rs = stmt.executeQuery(s);
            while (rs.next()) {
                result.add(new String[]{String.valueOf(rs.getInt("unitNum")),
                        rs.getString("address"),
                        rs.getString("id"),
                        rs.getString("availability"),
                        rs.getString("ownerName"),
                        rs.getString("sin")});
            }

            rs.close();
            stmt.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

        return result;
    }

    // select the manager id and the number of buildings it manages
    public List<String[]> groupBy() {
        List<String[]> result = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, COUNT(address) AS building_num FROM Manage m GROUP BY id");
            result.add(new String[]{"Id", "Building Number"});
            while (rs.next()) {
                result.add(new String[]{rs.getString("id"),
                        String.valueOf(rs.getInt("building_num"))});
            }

            rs.close();
            stmt.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

        return result;
    }

    // building address and number of postmen where it has more than one postmen serving
    public List<String[]> groupByHaving() {
        List<String[]>  result = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT address, COUNT(id) AS postmen_num FROM Serve s GROUP BY address HAVING COUNT(id) > 1");
            result.add(new String[]{"Address", "Postmen Number"});
            while (rs.next()) {
                result.add(new String[]{rs.getString("address"),
                        String.valueOf(rs.getInt("postmen_num"))});
            }

            rs.close();
            stmt.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

        return result;
    }

    public List<String[]> nestedAggregationWithGroupBy() {
        List<String[]> result = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT address, COUNT(id) AS video_num FROM VideoServeillance GROUP BY address HAVING COUNT(id) >= all (SELECT COUNT(id) FROM VideoServeillance GROUP BY address)");
            result.add(new String[]{"Address", "Video Surveillance Number"});
            while (rs.next()) {
                result.add(new String[]{rs.getString("address"),
                        String.valueOf(rs.getInt("video_num"))});
            }

            rs.close();
            stmt.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

        return result;
    }

    public List<String[]> division() {
        List<String[]> result = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM Postman p WHERE NOT EXISTS (SELECT b.address FROM Building b MINUS SELECT s.address FROM Serve s WHERE s.id = p.id)");
            result.add(new String[]{"Name"});
            while (rs.next()) {
                result.add(new String[]{rs.getString("name")});
            }

            rs.close();
            stmt.close();
            MainMenu.playSuccessClip();
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

        return result;
    }


    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            MainMenu.makeWarningDialog(EXCEPTION_TAG + " " + e.getMessage());
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }
}
