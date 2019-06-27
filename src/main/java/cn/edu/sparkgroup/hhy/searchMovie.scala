package sparkbigdata

import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

import scala.util.matching.Regex
import scala.collection.mutable.ArrayBuffer
object q1  {
  def search(args: Array[String]):Array[String] ={
    Logger.getLogger("org").setLevel(Level.WARN)
    //    System.setProperty("user.name", "root");
    //    System.setProperty("SPARK_HOME", "D:\\Spark\\bin");
    //    System.setProperty("spark.driver.host", "192.168.125.130");
    //    System.setProperty("spark.driver.port", "7077");

    System.setProperty("HADOOP_USER_NAME","hdfs");
    val conf = new SparkConf().setMaster("local[*]").setAppName("q1")
    //    conf.set("spark.driver.host", "localhost");
    //    conf.set("spark.driver.host", "192.168.125.130")
    //    conf.set("spark.driver.port", "4040")
    val sc = new SparkContext(conf)
    var data = sc.textFile("hdfs://master:9000/sparkData/movies.csv")
    val header=data.first()
    val pattern = new Regex(".*"+args(0)+".*")
//    var movies=new ArrayBuffer[String]()
    val res=new ArrayBuffer[String]()
    val movies=data.filter( row=>row != header).filter(row =>(pattern findAllIn row).mkString(" ")!="").collect()
    for(i <-  0 to movies.length-1){
      if(i >= ((args(1).toInt)*args(2).toInt) && i<((args(1).toInt+1)*args(2).toInt)){
        res+=movies(i)
      }
    }
//    movies.foreach(println)
//    println("111111")
    sc.stop()
    return res.toArray
  }
}