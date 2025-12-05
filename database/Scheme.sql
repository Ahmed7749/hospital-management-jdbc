CREATE DATABASE IF NOT EXISTS hospital_db;
USE hospital_db;
CREATE TABLE IF NOT EXISTS patients(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    gender ENUM('FEMALE','MALE') NOT NULL,
    birth_date DATE NOT NULL,
    middle_name VARCHAR(255),
    last_name VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS doctors(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    major ENUM('SURGEON', 'GENERAL') NOT NULL,
    gender ENUM('FEMALE','MALE') NOT NULL
);
CREATE TABLE IF NOT EXISTS appointments(
    appointment_id INT PRIMARY KEY AUTO_INCREMENT,
    appointment_date DATE NOT NULL,
    appointment_time TIME NOT NULL,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES patients(id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctors(id) ON DELETE CASCADE
);