## Hospital Management System

### Database Schema

The Hospital Management System uses a MySQL database to store information about patients, doctors, and appointments. Here's the database schema:

#### Tables:

1. **patients**: Stores information about patients, including their ID, name, age, and gender.

   ```sql
   CREATE TABLE patients (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       age INT NOT NULL,
       gender VARCHAR(12) NOT NULL
   );
   ```

2. **doctors**: Stores information about doctors, including their ID, name, and specialization.

   ```sql
   CREATE TABLE doctors (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       specialization VARCHAR(255) NOT NULL
   );
   ```

3. **appointments**: Stores information about appointments, including their ID, patient ID, doctor ID, and appointment date.
   ```sql
   CREATE TABLE appointments (
       id INT AUTO_INCREMENT PRIMARY KEY,
       patient_id INT NOT NULL,
       doctor_id INT NOT NULL,
       appointment_date DATE NOT NULL,
       FOREIGN KEY (patient_id) REFERENCES patients(id),
       FOREIGN KEY (doctor_id) REFERENCES doctors(id)
   );
   ```

#### Initial Data:

- Inserted initial data into the **doctors** table:
  ```sql
  INSERT INTO doctors (name, specialization) VALUES
  ("Swathi", "Neuro Surgeon"),
  ("Vijay", "General Surgeon");
  ```

### Java Classes

The Hospital Management System is implemented in Java and utilizes JDBC for database connectivity. Here are the Java classes used in the system:

1. **Patient**: Represents operations related to patients, including adding patients, viewing patients, and checking patient information.
2. **Doctor**: Represents operations related to doctors, including viewing doctors and checking doctor information.

3. **Appointment**: Represents operations related to appointments, including booking appointments and checking doctor availability on a specific date.

4. **App**: Main class that provides a command-line interface for interacting with the Hospital Management System. It allows users to add/view patients, view doctors, book appointments, and view appointments.

# outputs

![output1](./src/assets/Screenshot%20(136).png)
![output1](./src/assets/Screenshot%20(137).png)
![output1](./src/assets/Screenshot%20(138).png)
![output1](./src/assets/Screenshot%20(139).png)
