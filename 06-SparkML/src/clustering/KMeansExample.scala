package clustering

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.clustering.KMeans

object KMeansExample {
  def main(args:Array[String]): Unit = {
    val spark = SparkSession
        .builder
        .master("local[*]")
        .appName("KMeansExample").getOrCreate()
    // loads data
    val dataset = spark.read.format("libsvm").load("D:/dev/GitHub/BEAD2020June/06-SparkML/data/clustering/KMeansExample.txt")
    // train a k-means model
    val kmeans = new KMeans().setK(2).setSeed(1L)
    val model = kmeans.fit(dataset)
    // evaluate clustering by computing within set sum of squared errors
    val WSSSE = model.computeCost(dataset)
    println("Cluster centers: ")
    model.clusterCenters.foreach(println)
    println("WSSSE:", WSSSE)
  }
}