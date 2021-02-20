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
