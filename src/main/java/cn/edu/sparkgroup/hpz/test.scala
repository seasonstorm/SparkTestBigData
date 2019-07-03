package cn.edu.sparkgroup.hpz

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.types._
import org.apache.spark.sql.Row
import org.apache.spark.sql.SparkSession
import java.util.Properties
import scala.collection.mutable.ArrayBuffer

object test {
  def getstatics():Array[Array[String]] ={

    //spark://192.168.1.201:7077

    val spark = SparkSession
      .builder()
      .appName("memoryout")
      .config("spark.network.timeout","10000000")
      .config("spark.driver.memory", "8G")
      .config("spark.executor.cores",2)
      .config("spark.sql.shuffle.partitions",6)
      .config("spark.executor.memory","8G")
      .config("spark.yarn.executor.memoryOverhead","8G")
      .master("local[2]")
      .getOrCreate()
    val sc=spark.sparkContext
    sc.setLogLevel("ERROR")


    val totalarr=new  Array[Array[String]](3);



    val moviesDF=spark.read.format("jdbc").
      option("url","jdbc:mysql://192.168.31.124:3306/sparkdatabase").
      option("driver","com.mysql.jdbc.Driver").
      option("dbtable","movies").
      option("user","root").
      option("password","rootadmin").
      load()
    moviesDF.createOrReplaceTempView("movies")

    var typeArr = ArrayBuffer[String]()

    var typeMoviesArr = ArrayBuffer[(String,String)]()
    val moviesArr=spark.sql("select * from movies").rdd.filter(array=>array(1)!=null).
      map(array=>(array(0),array(1).toString().split('|'))).collect
    for(index <- moviesArr){
      for(i <- index._2){
        typeArr+=i
        var item=(index._1.toString(),i)
        typeMoviesArr+=item
      }
    }

    val typearr=sc.parallelize(typeArr).distinct().map(line=>(line,1)).sortByKey().map(item=>item._1).collect()

    totalarr(0)=typearr

    val typemoviesrdd=sc.parallelize(typeMoviesArr)
    val typemoviesDF=spark.createDataFrame(typemoviesrdd)
    typemoviesDF.createOrReplaceTempView("moviesdeal")


    val btempDF=spark.read.format("jdbc").
      option("url","jdbc:mysql://192.168.31.124:3306/sparkdatabase").
      option("driver","com.mysql.jdbc.Driver").
      option("dbtable","btemp").
      option("user","root").
      option("password","rootadmin").
      load()
    btempDF.createOrReplaceTempView("btemp")

    val gtempDF=spark.read.format("jdbc").
      option("url","jdbc:mysql://192.168.31.124:3306/sparkdatabase").
      option("driver","com.mysql.jdbc.Driver").
      option("dbtable","gtemp").
      option("user","root").
      option("password","rootadmin").
      load()
    gtempDF.createOrReplaceTempView("gtemp")


    //   统计男平均分.saveAsTextFile("file:///result/boys")
    val resultbDF=spark.sql("select moviesdeal._2,btemp.rating from moviesdeal,btemp where moviesdeal._1=btemp.movieId")
    type MVType = (Int, Double)
    val bDF= resultbDF.rdd.map(line=>(line.getString(0),line.getString(1).toDouble)).combineByKey(
      score => (1, score),
      (c1: MVType, newScore) => (c1._1 + 1, c1._2 + newScore),
      (c1: MVType, c2: MVType) => (c1._1 + c2._1, c1._2 + c2._2))
      .map { case (name, (num, score)) => (name, (score / num).formatted("%.2f")) }.sortByKey().map(line=>line._2).collect()
    totalarr(1)=bDF

    //   统计女平均分          .saveAsTextFile("file:///result/grils")
    val resultgDF=spark.sql("select moviesdeal._2,gtemp.rating from moviesdeal,gtemp where moviesdeal._1=gtemp.movieId")
    val gDF=resultgDF.rdd.map(line=>(line.getString(0),line.getString(1).toDouble)).combineByKey(
      score => (1, score),
      (c1: MVType, newScore) => (c1._1 + 1, c1._2 + newScore),
      (c1: MVType, c2: MVType) => (c1._1 + c2._1, c1._2 + c2._2))
      .map { case (name, (num, score)) => (name, (score / num).formatted("%.2f")) }.sortByKey().map(line=>line._2).collect()

    totalarr(2)=gDF
    spark.close()

    return totalarr

  }
}