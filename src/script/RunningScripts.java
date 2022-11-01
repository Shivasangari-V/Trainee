//$Id$
package script;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.ibatis.jdbc.ScriptRunner;
public class RunningScripts {
   public static void main(String args[]) throws Exception {
      //Registering the Driver
      DriverManager.registerDriver(new com.mysql.jdbc.Driver());
      //Getting the connection
      String mysqlUrl = "jdbc:mysql://localhost/school";
      Connection con = DriverManager.getConnection(mysqlUrl, "root", "spyder26");
      System.out.println("Connection established......");
      //Initialize the script runner
      ScriptRunner sr = new ScriptRunner(con);
      //Creating a reader object
      Reader reader = new BufferedReader(new FileReader("/Users/shiva-pt6041/Downloads/Eclipse.app/Contents/MacOS/ZIDE/strutpro/Demo.sql"));
//      //Running the script
//      sr.runScript(reader);
   }
}