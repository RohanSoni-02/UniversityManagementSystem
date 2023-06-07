package secure;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author rohansoni
 */
public class TeacherDB {

    // Database parameters for connection - url, username, password
    static String url;
    static String username;
    static String password;

    static final String DB_TABLE = "UMS_Teacher";
    static final String DB_PK_CONSTRAINT = "PK_" + DB_TABLE;

    /**
     * constructor using default url, username and password
     */
    public TeacherDB() {
        // set default parameters for Derby and JavaDB
        url = "jdbc:derby://localhost/sun-appserv-samples;create=true";
        username = "APP";
        password = "APP";
    }

    /**
     * getConnecion()
     *
     * @aim Get a connection to the database using the specified info
     */
    public static Connection getConnection()
            throws SQLException, IOException {
        // first, need to set the driver for connection
        // for Derby
        System.setProperty("jdbc.drivers",
                "org.apache.derby.jdbc.ClientDriver");

        // next is to get the connection
        return DriverManager.getConnection(url, username, password);
    }

    /*
     * createDBTable
     *
     * @aim Use SQL commands to create the database table
     */
    public void createDBTable() {
        Connection cnnct = null;    // declare a connection object
        Statement stmnt = null;     // declare a statement object

        try {
            // get connection
            cnnct = getConnection();
            // get statement
            stmnt = cnnct.createStatement();

            /**
             * execute query to create a data table with the required fields:
             * EmpId, Name, Tel, Address, Bank_Account (for payroll), Salary,
             * Active (currently employed in the company)
             */
            stmnt.execute("CREATE TABLE " + DB_TABLE
                    + " (TchId CHAR(5) CONSTRAINT " + DB_PK_CONSTRAINT + " PRIMARY KEY,"
                    + " UsrId CHAR(5), "
                    + " Name VARCHAR(25), "
                    + " date_of_birth VARCHAR(25), "
                    + " address VARCHAR(255), "
                    + " email VARCHAR(255), "
                    + " qualification VARCHAR(255), "
                    + " gender CHAR(10), "
                    + " course_assigned VARCHAR(50), "
                    + " FOREIGN KEY (UsrId) REFERENCES EMS_EMPLOYEE(EmpId))");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            // do nothing
        } finally {
            // close Statement object
            if (stmnt != null) {
                try {
                    stmnt.close();
                } catch (SQLException e) {
                    // do nothing
                }
            }

            // close Connection object
            if (cnnct != null) {
                try {
                    /**
                     * cnnct.close() throws a SQLException, but we cannot
                     * recover at this point
                     */
                    cnnct.close();
                } catch (SQLException sqlEx) {
                    // do nothing
                }
            }
        }
    }

    /**
     * destroyDBTable()
     *
     * @aim Remove the database table
     */
    public void destroyDBTable() {
        Connection cnnct = null;
        Statement stmnt = null;

        try {
            // get connection
            cnnct = getConnection();
            // get statement
            stmnt = cnnct.createStatement();

            // execute action query to destroy a data table
            stmnt.execute("DROP TABLE " + DB_TABLE);
        } catch (SQLException ex) {
            // do nothing
        } catch (IOException ex) {
            // do nothing
        } finally {
            // close Statement object
            if (stmnt != null) {
                try {
                    stmnt.close();
                } catch (SQLException e) {
                    // do nothing
                }
            }

            // close Connection object
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                    // do nothing
                }
            }
        }
    }

    /**
     * addRecord()
     *
     * @aim Add a record into the database table
     */
    public void addRecords(ArrayList<Teacher> tchList) {

        Connection cnnct = null;

        // create a PreparedStatement object
        PreparedStatement pStmnt = null;

        try {
            // get connection
            cnnct = getConnection();

            // precompiled query statement
            String preQueryStatement = "INSERT INTO " + DB_TABLE
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";

            for (Teacher tch : tchList) {

                pStmnt = cnnct.prepareStatement(preQueryStatement);

                pStmnt.setString(1, tch.getTchId());
                pStmnt.setString(2, tch.getUsrId());
                pStmnt.setString(3, tch.getName());
                pStmnt.setString(4, tch.getDateOfBirth());
                pStmnt.setString(5, tch.getAddress());
                pStmnt.setString(6, tch.getEmail());
                pStmnt.setString(7, tch.getQualification());
                pStmnt.setString(8, tch.getGender());
                pStmnt.setString(9, tch.getCourseAssigned());

                /*
                 * execute update query to add record into the data table
                 * with four fields: CustId, Name, Tel, Age
                 *
                 * will return number of records added
                 */
                int rowCount = pStmnt.executeUpdate();

                /*
                 * rowCount should be 1 because 1 record is added
                 *
                 * throws exception if not
                 */
                if (rowCount == 0) {
                    throw new SQLException("Cannot insert records!");
                }
            }
        } catch (SQLException ex) {
            // do nothing
        } catch (IOException ex) {
            // do nothing
        } finally {
            // close Prepared Statement object
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                    // do nothing
                }
            }

            // close Connection object
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                    // do nothing
                }
            }
        }
    }
}
