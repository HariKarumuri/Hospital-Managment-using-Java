import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class App {
    private static final String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String username = "root";
    private static final String password = "H@ri1086";
    public static void main(String[] args)  {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Patient patient = new Patient(connection,sc);
            Doctors doctor = new Doctors(connection);
            while(true){
                System.out.println("Hosipital Management System");
                System.out.println("1.ADD Patient");
                System.out.println("2.View All Patient");
                System.out.println("3.View All Doctors");
                System.out.println("4.Book Appointment");
                System.out.println("5.View Appointments");
                System.out.println("6. Exit");
                System.out.println("ENter your choice ");

                int choice = sc.nextInt();

                switch(choice){
                    case 1:
                        // Add Patient
                        patient.addPatient();
                        System.out.println();
                        break;
                    case 2:
                        // View Patient
                        patient.viewPatients();
                        System.out.println();
                        break;
                    case 3:
                        // View Doctors
                        doctor.viewDoctors();
                        System.out.println();
                        break;
                    case 4:
                        // Book Appointment
                        bookAppointment(patient, doctor, connection, sc);
                        System.out.println();
                        break;

                    case 5:
                        viewBookings(connection,sc);
                        System.out.println();
                        break;    
                    case 6:
                        System.out.println("THANK YOU! FOR USING HOSPITAL MANAGEMENT SYSTEM!!");
                        return;
                    default:
                        System.out.println("Enter valid choice!!!");
                        break;
                }
            }



        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void bookAppointment(Patient patient, Doctors doctor, Connection connection, Scanner scanner){
        System.out.print("Enter Patient Id: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Doctor Id: ");
        int doctorId = scanner.nextInt();
        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String appointmentDate = scanner.next();
        if(patient.getPatientById(patientId) && doctor.getDoctorById(doctorId)){
            if(checkDoctorAvailability(doctorId, appointmentDate, connection)){
                String appointmentQuery = "INSERT INTO appointments(patient_id, doctor_id, appointment_date) VALUES(?, ?, ?)";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
                    preparedStatement.setInt(1, patientId);
                    preparedStatement.setInt(2, doctorId);
                    preparedStatement.setString(3, appointmentDate);
                    int rowsAffected = preparedStatement.executeUpdate();
                    if(rowsAffected>0){
                        System.out.println("Appointment Booked!");
                    }else{
                        System.out.println("Failed to Book Appointment!");
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }else{
                System.out.println("Doctor not available on this date!!");
            }
        }else{
            System.out.println("Either doctor or patient doesn't exist!!!");
        }
    }

    public static boolean checkDoctorAvailability(int doctorId, String appointmentDate, Connection connection){
        String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_date = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, doctorId);
            preparedStatement.setString(2, appointmentDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int count = resultSet.getInt(1);
                if(count==0){
                    return true;
                }else{
                    return false;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static void viewBookings(Connection connection , Scanner scanner){
        String query = "SELECT * FROM appointments";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Appointments:");
            while(resultSet.next()){
                int id =  resultSet.getInt("id");
                int patient_id = resultSet.getInt("patient_id");
                int doctor_id = resultSet.getInt("doctor_id");
                Date date = resultSet.getDate("appointment_date");
                System.out.printf("| %-10s | %-18s | %-8s | %-10s |\n", id, patient_id, doctor_id, date);
                System.out.println("+------------+--------------------+----------+------------+");


            }

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
