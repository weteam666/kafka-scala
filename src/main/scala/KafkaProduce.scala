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

    for(i<- 1 to 50){
      val record = new ProducerRecord(topic, "key", s"hello $i")
      producer.send(record)
    }

    val record = new ProducerRecord(topic, "key", "the end "+new java.util.Date)
    producer.send(record)

    producer.close()


  }
}