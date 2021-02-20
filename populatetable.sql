INSERT INTO Resident
VALUES ("239944031", "Jai Parrish", "1999/02/23", 105);
INSERT INTO Resident
VALUES ("102471900", "Sorcha Bean", "1999/02/23", 708);
INSERT INTO DOBandAGE
VALUES ("1999/02/23", 21);
INSERT INTO DOBandAGE
VALUES ("1998/02/25", 22);
INSERT INTO Tenant
VALUES ("239944031", 5745);
INSERT INTO Tenant
VALUES ("102471900", 7163);
INSERT INTO Landlord
VALUES ("239944031");
INSERT INTO Landlord
VALUES ("102471900");
INSERT INTO ParkingSpot
VALUES ("true", "45678786", "Jai Parrish", null, 105, "239944031");
INSERT INTO Building
VALUES (null, "3588 Crowley Drive, Vancouver, BC, V5R 6H3");
INSERT INTO GarbageRoom
VALUES("Monday", "63388699", "3588 Crowley Drive, Vancouver, BC, V5R 6H3");
INSERT INTO ParkingSpot
VALUES ("true", "67542579", "Sorcha Bean", null, 708, "102471900");
INSERT INTO Building
VALUES (null, "3760 Alexander Rd, Richmond, BC, V6X 1C6");
INSERT INTO GarbageRoom
VALUES ("Sunday", "87875675", "3760 Alexander Rd, Richmond, BC, V6X 1C6");
INSERT INTO Postman
VALUES ("Canada Post", "24679383", "Jackson Wang");
INSERT INTO Postman
VALUES ("Canada Post", "24568987", "Nick Harrington");
INSERT INTO Manager
VALUES ("Jeremy Gill", "76864674", "77887791505");
INSERT INTO Manager
VALUES ("Sirena Harrop", "86656889", "7788792253");
INSERT INTO Manage
VALUES ("76864674", "3588 Crowley Drive, Vancouver, BC, V5R 6H3");
INSERT INTO Manage
VALUES ("86656889", "3760 Alexander Rd, Richmond, BC, V6X 1C6");
INSERT INTO VideoServeillance
VALUES ("12476877", -1, "3760 Alexander Rd, Richmond, BC, V6X 1C6");
INSERT INTO VideoServeillance
VALUES ("68689543", -2, "3588 Crowley Drive, Vancouver, BC, V5R 6H3");
INSERT INTO Room
VALUES (105, "3588 Crowley Drive, Vancouver, BC, V5R 6H3", "45678786");
INSERT INTO Room
VALUES (708, "3760 Alexander Rd, Richmond, BC, V6X 1C6", "67542579");
INSERT INTO Serve
VALUES ("Canada Post", "24679383", "3588 Crowley Drive, Vancouver, BC, V5R 6H3");
INSERT INTO Serve
VALUES ("Canada Post", "24568987", "3760 Alexander Rd, Richmond, BC, V6X 1C6");
UPDATE Building 
SET id = "63388699" 
WHERE address = "3588 Crowley Drive, Vancouver, BC, V5R 6H3";
UPDATE ParkingSpot 
SET availability = "true", ownerName = "Jai Parrish", 
        address = "3588 Crowley Drive, Vancouver, BC, V5R 6H3", unitNum = 105,  sin = "239944031" 
WHERE id =  "45678786";
UPDATE Building 
SET id = "87875675" 
WHERE address = "3760 Alexander Rd, Richmond, BC, V6X 1C6";
UPDATE ParkingSpot 
SET availability = "true", ownerName = "Sorcha Bean", 
         address = "3760 Alexander Rd, Richmond, BC, V6X 1C6", unitNum = 708, sin = "102471900” 
WHERE id = "67542579";
