import java.text.SimpleDateFormat
import java.util.Date

import org.apache.spark.rdd.RDD
import org.apache.spark.{Partition, SparkConf, SparkContext, TaskContext}

import scala.util.parsing.json._
object RegJson {
  var sc:SparkContext = null
  def sparkInit():SparkContext = {
    if (sc==null){
      val conf = new SparkConf().setAppName("json").setMaster("local")
      sc = new SparkContext(conf)
    }

    return sc
  }


  def regJson(str : String,money:Int): Int = {
/*
    {"data":[{"username":"ablse","password":"abadsfk"}]}
*/

    val result = str.substring(str.lastIndexOf('[',10),str.lastIndexOf(']',300)).replace("[","").replace("]","")

    val jsons = regJson(JSON.parseFull(result))
    val total_fee = jsons.get("total_fee")

    val total_f = Integer.valueOf(total_fee.get.toString)

    //println(pay_time.get)

    val seq : Seq[Int] = Seq(money,total_f)
    val c : RDD[Int] = sparkInit().parallelize(seq)
    //结果55

    return c.reduce((x, y) => x + y)
/*
    val date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis())
*/

    /*println(date + "\t" + total)*/

/*    val jsonS = JSON.parseFull(str)

    val first = regJson(jsonS)

    val dev = first.get("data")

    println(dev)*/

     /* val result = dev.toString.replace("Some(List(Map(","").replace(")","")

      println(result)

      val list:Array[String] = result.split(", ")

      println(list(0))
      println(list(1))
*/
  }

  def regJson(option: Option[Any]) = option match {
    case Some(map: Map[String,Any]) => map
  }
}