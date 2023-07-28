import java.sql.Connection;
import java.sql.DriveManager;
public class mySQLdb{
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/products";
        String username="root";
        String password = "910604";

        public static void main(String[] args) throws InstantiationException, IllegalAccesException, ClassNotFoundException, SQLException{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection=DriverManager.getConnection(url, username, password);

            int status=ps.executeUpdate();
        }
    }
}