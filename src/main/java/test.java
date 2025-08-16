import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class test {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "newpassword"; // Replace with your actual password

        try {
            // Connect to the database
            Connection con = DriverManager.getConnection(url, user, password);

            if (con.isValid(5)) {
                System.out.println("✅ Database is connected!");

                // Query the table
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM courses");

                // Print all rows
                while (rs.next()) {
                    System.out.println(rs.getInt("course_Id") + " " + rs.getString("Name"));
                }

                // Close resources
                rs.close();
                stmt.close();
            } else {
                System.out.println("❌ Failed to connect to database.");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
