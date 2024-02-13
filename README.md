## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

Database creation and queries !
create database hospital;

create table patients(
-> id INT AUTO_INCREMENT PRIMARY KEY,
-> name VARCHAR(255) NOT NULL,
-> age INT NOT NULL,
-> gender VARCHAR(12) NOT NULL );

CREATE TABLE doctors(
-> id INT AUTO_INCREMENT PRIMARY KEY,
-> name VARCHAR(255)NOT NULL,
-> specialization VARCHAR(255) NOT NULL);

create table appointments(
-> id INT AUTO_INCREMENT PRIMARY KEY,
-> patient_id INT NOT NULL,
-> doctor_id INT NOT NULL,
-> appointment_date DATE NOT NULL,
-> FOREIGN KEY (patient_id) REFERENCES patients(id),
-> FOREIGN KEY (doctor_id) REFERENCES doctors(id));

INSERT INTO doctors(name , specialization) VALUES ("Swathi" ,"Neuro Surgeon"),("Vijay" , "General Surgeon");

now creating a patient class
now we use constructors to create patients
add patient()
view patient()
check patient()

in add patients = >
we first use scanner to take input parameters from user and then use it to send prepared statement query with placeholder ?
