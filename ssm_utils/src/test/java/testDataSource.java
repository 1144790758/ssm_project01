import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.Driver;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @athor:lhl
 * @create:2020-05-12 2:07
 */
public class testDataSource {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.43.11:3306/ssm?characterEncoding=utf8", "root", "123");
        System.out.println(connection);
        String sql="select count(*) from `users`";
        PreparedStatement statement = connection.prepareStatement(sql);
        System.out.println(statement.execute());
    }

}
