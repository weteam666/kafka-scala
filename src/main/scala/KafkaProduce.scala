import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

object KafkaProduce {
  def main(args: Array[String]): Unit = {

    val props = new Properties()

    props.put("bootstrap.servers","192.168.244.10:9092")

    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String,String](props)

    val topic = "kafkatest"
    val str = """{"data":[{"out_trade_no":"2","create_time":"2019-03-20 19:45:51","pay_time":"2019-03-20 19:45:56",""" +
      """"total_fee":"10","user_id":"wangwu","transaction_id":"2356","trade_state":"1","order_list":"12456","pay_type":null}],""" +
      """"database":"canal_test","es":1553082368000,"id":1,"isDdl":false,"mysqlType":{"out_trade_no":"varchar(64)","""" +
      """create_time":"datetime","pay_time":"datetime","total_fee":"bigint(20)","user_id":"varchar(50)","transaction_id":"varchar(64)",""" +
      """"trade_state":"varchar(1)","order_list":"varchar(200)","pay_type":"varchar(1)"},"old":null,"pkNames":["out_trade_no"],""" +
      """"sql":"","sqlType":{"out_trade_no":12,"create_time":93,"pay_time":93,"total_fee":-5,"user_id":12,"transaction_id":12,""" +
      """"trade_state":12,"order_list":12,"pay_type":12},"table":"tb_pay_log","ts":1553082368937,"type":"INSERT"}"""


      val record = new ProducerRecord(topic, "key", str)
      producer.send(record)

/*
    val record = new ProducerRecord(topic, "key", "the end "+new java.util.Date)
    producer.send(record)*/

    producer.close()


  }
}