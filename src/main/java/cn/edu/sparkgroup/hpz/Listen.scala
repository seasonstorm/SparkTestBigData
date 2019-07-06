package cn.edu.sparkgroup.hpz
import org.apache.spark._
import org.apache.spark.streaming._
import redis.clients.jedis.Jedis


object Listen {
  def getamount(){
    val sprakConf=new SparkConf().setAppName("getListenAmount").setMaster("local[2]")
    val ssc=new StreamingContext(sprakConf,Seconds(2))
    val lines=ssc.textFileStream("D://upload")
    lines.print()
    val jedis = new Jedis("localhost")
    lines.foreachRDD(rdd=>{
      jedis.set("flag",rdd.collect().length.toString())
    })

    ssc.start()
    ssc.awaitTermination()
  }
}
