package cn.edu.sparkgroup.hhy

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

    System.setProperty("HADOOP_USER_NAME","hdfs")
    val conf = new SparkConf().setMaster("local[*]").setAppName(scala.util.Random.nextInt(100).toString)
    //    conf.set("spark.driver.host", "localhost");
    //    conf.set("spark.driver.host", "192.168.125.130")
    //    conf.set("spark.driver.port", "4040")
    val sc = new SparkContext(conf)
    var data = sc.textFile("C:\\Users\\Administrator\\Desktop\\Spark\\db\\ml-latest\\movies.csv")
    val header=data.first()
    val pattern = new Regex(".*"+args(0)+".*")
    val res=new ArrayBuffer[String]()
    val movies=data.filter( row=>row != header).filter(row =>(pattern findAllIn row).mkString(" ")!="").collect()
    sc.stop()
    movies
  }
}