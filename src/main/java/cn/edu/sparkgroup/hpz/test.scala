package cn.edu.sparkgroup.hpz
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.types._
import org.apache.spark.sql.Row
import org.apache.spark.sql.SparkSession
import java.util.Properties
import scala.collection.mutable.ArrayBuffer

object test {
//  ArrayBuffer[ArrayBuffer[String]] =
    def getstatics(){
     val conf=new SparkConf().setAppName("removemore").setMaster("local")
    val sc=new SparkContext(conf)
    val spark = SparkSession
      .builder()
      .appName(this.getClass.getSimpleName).master("local")
      .getOrCreate()
      
    sc.setLogLevel("ERROR")
     

    val usersDF=spark.read.format("jdbc").
    option("url","jdbc:mysql://192.168.31.124:3306/sparkdatabase").
    option("driver","com.mysql.jdbc.Driver").
    option("dbtable","users").
    option("user","root").
    option("password","rootadmin").
    load()
    
    usersDF.createOrReplaceTempView("users")
  


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

     val typearr=sc.parallelize(typeArr).distinct()

      //打印种类排序
      typearr.foreach(println)

     val typemoviesrdd=sc.parallelize(typeMoviesArr)
     val typemoviesDF=spark.createDataFrame(typemoviesrdd)
     typemoviesDF.createOrReplaceTempView("movies")

    val ratingsDF=spark.read.format("jdbc").
    option("url","jdbc:mysql://192.168.31.124:3306/sparkdatabase").
    option("driver","com.mysql.jdbc.Driver").
    option("dbtable","ratings").
    option("user","root").
    option("password","rootadmin").
    load()
    
    ratingsDF.createOrReplaceTempView("ratings")
    
//    typearr.saveAsTextFile("file:///home/types")

      println("************************************")
    val resultbDF=spark.sql("select movies._2,ratings.rating from users,movies,ratings where users.Gender='M' and movies._1=ratings.movieId and users.userId=ratings.userId")
    type MVType = (Int, Double)
    resultbDF.rdd.map(line=>(line.getString(0),line.getDouble(1))).combineByKey(
        score => (1, score),
        (c1: MVType, newScore) => (c1._1 + 1, c1._2 + newScore),
        (c1: MVType, c2: MVType) => (c1._1 + c2._1, c1._2 + c2._2))
        .map { case (name, (num, score)) => (name, score / num) }.sortByKey().foreach(println)

//      .saveAsTextFile("file:///home/boys")   .saveAsTextFile("file:///home/girls")

      println("************************************")
    val resultgDF=spark.sql("select movies._2,ratings.rating from users,movies,ratings where users.Gender='F' and movies._1=ratings.movieId and users.userId=ratings.userId")
    resultgDF.rdd.map(line=>(line.getString(0),line.getDouble(1))).combineByKey(
        score => (1, score),
        (c1: MVType, newScore) => (c1._1 + 1, c1._2 + newScore),
        (c1: MVType, c2: MVType) => (c1._1 + c2._1, c1._2 + c2._2))
        .map { case (name, (num, score)) => (name, score / num) }.sortByKey().foreach(println)
    
    }
}