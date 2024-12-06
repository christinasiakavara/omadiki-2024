CREATE TABLE User (
    UserID INT PRIMARY KEY NOT NULL,
    FirstName VARCHAR(15),
    Surname VARCHAR(30),
    UserName VARCHAR(10) NOT NULL,
    Password VARCHAR(20) NOT NULL,
    Email VARCHAR(30) NOT NULL,
    Phone INT NOT NULL,
    Address VARCHAR(50),
    BirthDate DATE,
    Role INT,
    CONSTRAINT CHK_Role CHECK (Role=1 OR Role=2)
);

CREATE TABLE Psychologist (
    PsychologistID INT PRIMARY KEY NOT NULL ,
    Bio TEXT(5000),
    ratings DECIMAL(3,2),
    FOREIGN KEY (PsychologistID) REFERENCES User(UserID)
);

CREATE TABLE Patient (
    PatientID INT NOT NULL PRIMARY KEY,
    FOREIGN KEY (PatientID) REFERENCES User(UserID)
);

CREATE TABLE Payment (
    PaymentID INT NOT NULL,
    Amount FLOAT NOT NULL,
    PaymentDate DATETIME
)