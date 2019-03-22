import java.sql
import java.sql.{Connection, DriverManager, PreparedStatement}
object ToMysql {

  def add(time : String,money : String): Unit= {

    var conn :Connection = null
    var ps : PreparedStatement = null
    val sql = "REPLACE INTO t_spare(time,money) VALUES(?, ?)"
    try {
      Class.forName("com.mysql.jdbc.Driver")
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/scala_test","root","root")

      val pstm = conn.prepareStatement(sql)
      pstm.setString(1, time)
      pstm.setString(2, money)
      pstm.executeUpdate()
    }
    finally {
      if (ps != null){
        ps.close()
      }
      if (conn!=null){
        conn.close()
      }
    }
  }

}
