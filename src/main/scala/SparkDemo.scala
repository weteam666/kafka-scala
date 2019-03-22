import java.util.Date

import org.apache.spark.api.java.JavaRDD
import org.apache.spark.streaming.{Seconds, State, StateSpec, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

import scala.actors.threadpool.Arrays
import scala.collection.mutable
object SparkDemo {

  def main(args: Array[String]): Unit = {
    var str = """{"data":[{"out_trade_no":"2","create_time":"2019-03-20 19:45:51","pay_time":"2019-03-20 19:45:56",""" +
      """"total_fee":"2224","user_id":"wangwu","transaction_id":"2356","trade_state":"1","order_list":"12456","pay_type":null}],""" +
      """"database":"canal_test","es":1553082368000,"id":1,"isDdl":false,"mysqlType":{"out_trade_no":"varchar(64)","""" +
      """create_time":"datetime","pay_time":"datetime","total_fee":"bigint(20)","user_id":"varchar(50)","transaction_id":"varchar(64)",""" +
      """"trade_state":"varchar(1)","order_list":"varchar(200)","pay_type":"varchar(1)"},"old":null,"pkNames":["out_trade_no"],""" +
      """"sql":"","sqlType":{"out_trade_no":12,"create_time":93,"pay_time":93,"total_fee":-5,"user_id":12,"transaction_id":12,""" +
      """"trade_state":12,"order_list":12,"pay_type":12},"table":"tb_pay_log","ts":1553082368937,"type":"INSERT"}"""

    println(RegJson regJson(str,90))



  }

}
