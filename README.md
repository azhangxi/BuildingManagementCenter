# CPSC304 Project Milestone 3

# Proposal:

[Project Descirption](https://docs.google.com/document/d/1MhQmnG1k4P7-hEiT-2Q_1LzQxzp2RAr5KlFQ6Mz-4fU/edit#)

# SQL Script:

create tables: (see createtable.sql)
```
create table - see createtable.sql 
CREATE TABLE Building (
id varchar2(8), 
address varchar2(50) PRIMARY KEY);

CREATE TABLE GarbageRoom (
id varchar2(8) PRIMARY KEY, 
clearDate varchar2(20) not null, 
address varchar2(50) UNIQUE);

CREATE TABLE Landlord (
sin varchar2(9) PRIMARY KEY);

CREATE TABLE Manage 
(id varchar2(8), 
address varchar2(50),
 PRIMARY KEY (id, Address));

CREATE TABLE Manager (
name varchar2(20) not null, 
id varchar2(8) PRIMARY KEY, 
contactNum INTEGER UNIQUE);

CREATE TABLE ParkingSpot (
availability varchar2(5) not null, 
id varchar2(8) PRIMARY KEY, 
ownerName varchar2(20), 
address varchar2(50), 
unitNum INTEGER UNIQUE, 
sin varchar2(9));

CREATE TABLE Postman (
company varchar2(50), 
id varchar2(8), 
name varchar2(20) not null, 
PRIMARY KEY (id, company);

CREATE TABLE Resident (
sin varchar2(9) PRIMARY KEY, 
name varchar2(20) not null, 
birthday varchar2(10), 
unitNum INTEGER);

CREATE TABLE Room (
unitNum INTEGER, 
address varchar2(50),
 id varchar2(8) not null, 
PRIMARY KEY (unitNum, address),
 UNIQUE (id));

CREATE TABLE Serve (
company varchar2(50), 
id varchar2(8), 
address varchar2(50), 
PRIMARY KEY(company, id, address));

CREATE TABLE Tenant (
sin varchar2(9), 
monthlyRent INTEGER not null, 
PRIMARY KEY (sin));

CREATE TABLE VideoServeillance (
id varchar2(8), 
floor INTEGER not null,
 address varchar2(50) not null, 
PRIMARY KEY (id));

CREATE TABLE DOBandAge (
birthday varchar2(10), 
age INTEGER, 
PRIMARY KEY (birthday));

CREATE TABLE SINandName (
sin varchar2(9), 
ownerName varchar2(20) not null, 
PRIMARY KEY (sin));
```

populate tables: (see populatetable.sql)

# SQL Query:
see queries.sql
```

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
WHERE r.parkingID = ps.id and ownerName = ?

// query 4: select the manager id and the number of buildings it manages

SELECT mg.id, COUNT(b.address) count
FROM Manager mg, Manage m, Building b
WHERE m.address = b.address AND mg.id = m.managerID
GROUP BY mg.id 
ORDER BY COUNT(b.address)

// query 5: select building address and number of postmen where it has more than one postmen serving
SELECT b.address, COUNT(pm.id) count
FROM Postman pm, Serve s, Building b
WHERE b.address = s.address AND s.id = pm.id
GROUP BY b.address 
HAVING COUNT(pm.id) > 1
```

