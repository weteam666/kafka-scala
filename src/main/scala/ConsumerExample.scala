import java.text.SimpleDateFormat
import java.util

import org.apache.kafka.clients.consumer.KafkaConsumer

import scala.collection.JavaConverters._
import java.util.{Date, Properties}
object ConsumerExample extends App {
    val TOPIC="kafkatest"

    val  props = new Properties()
    props.put("bootstrap.servers", "192.168.244.10:9092")

    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("group.id", "test-consumer")

    val consumer = new KafkaConsumer[String, String](props)

    consumer.subscribe(util.Collections.singletonList(TOPIC))

    var sum = 10000

    while(true){
      val records=consumer.poll(1000)
      for (record<-records.asScala){

        sum = RegJson.regJson(record.value(),sum)

        println(sum)
      }
      var date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date)
      ToMysql.add(date,sum.toString)
    }


/*
{"data":[{"out_trade_no":"2","create_time":"2019-03-20 19:45:51","pay_time":"2019-03-20 19:45:56","total_fee":"4","user_id":"wangwu","transaction_id":"2356","trade_state":"1","order_list":"12456","pay_type":null}],"database":"canal_test","es":1553082368000,"id":1,"isDdl":false,"mysqlType":{"out_trade_no":"varchar(64)","create_time":"datetime","pay_time":"datetime","total_fee":"bigint(20)","user_id":"varchar(50)","transaction_id":"varchar(64)","trade_state":"varchar(1)","order_list":"varchar(200)","pay_type":"varchar(1)"},"old":null,"pkNames":["out_trade_no"],"sql":"","sqlType":{"out_trade_no":12,"create_time":93,"pay_time":93,"total_fee":-5,"user_id":12,"transaction_id":12,"trade_state":12,"order_list":12,"pay_type":12},"table":"tb_pay_log","ts":1553082368937,"type":"INSERT"}
*/

}