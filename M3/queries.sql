// query 1: select tenants with a montly rent greater than ? (userinput)

SELECT * 
FROM Tenant 
WHERE monthlyRent > ?

// query 2: select only the sin, ownername and unit number of a parkingspot

SELECT sin, ownerName, unitNum 
FROM ParkingSpot

// query 3: find the room and parking spot information for the given owner name (user input)
SELECT * 
FROM Room r, ParkingSpot ps 
WHERE r.id = ps.id and ownerName = ?

// query 4: select the manager id and the number of buildings it manages

SELECT id, COUNT(address) AS building_num 
FROM Manage m 
GROUP BY id

// query 5:list the building address and number of postmen where it has more than one postmen serving
SELECT address, COUNT(id) AS postmen_num 
FROM Serve s 
GROUP BY address 
HAVING COUNT(id) > 1

// query 6: list building address and nunber of video survelliances which the number of video survilliances is the greatest
SELECT address, COUNT(id) AS video_num 
FROM VideoServeillance 
GROUP BY address
HAVING COUNT(id) >= all (SELECT COUNT(id) 
                         FROM VideoServeillance 
                         GROUP BY address)

// query 7: list name of postmen that serve all buildings
SELECT name 
FROM Postman p 
WHERE NOT EXISTS (SELECT b.address 
                  FROM Building b 
                  MINUS 
                  SELECT s.address 
                  FROM Serve s 
                  WHERE s.id = p.id)
