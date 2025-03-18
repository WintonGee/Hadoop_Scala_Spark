import scala.io.Source
import org.apache.spark.SparkContext._
import scala.io._
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd._
import org.apache.log4j.Logger
import org.apache.log4j.Level
import scala.collection._

object Main {

  // lineItem: ID, salesID, productID, quantity
  // product: ID, description, price
  // sales: ID, date, time, storeID, customerID
  // store:  ID, storeName, address, city, ZIP, state, phoneNumber

  var inputPath_1 = "/Users/wintongee/Desktop/School/Winter 2024/CSC369/Repos/Lab6/src/main/scala/lineItem"
  var inputPath_2 = "/Users/wintongee/Desktop/School/Winter 2024/CSC369/Repos/Lab6/src/main/scala/product"
  var inputPath_3 = "/Users/wintongee/Desktop/School/Winter 2024/CSC369/Repos/Lab6/src/main/scala/sales"
  var inputPath_4 = "/Users/wintongee/Desktop/School/Winter 2024/CSC369/Repos/Lab6/src/main/scala/store"

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    val conf = new SparkConf().setAppName("AppName").setMaster("local")
    val sc = new SparkContext(conf)

    // Load data into RDDs from text file
    val lineItemRDD = sc.textFile(inputPath_1).map(_.split(","))
    val productRDD = sc.textFile(inputPath_2).map(_.split(","))
    val salesRDD = sc.textFile(inputPath_3).map(_.split(","))
    val storeRDD = sc.textFile(inputPath_4).map(_.split(","))

    lineItemRDD
      .cartesian(productRDD)
      .filter {
        case (lineItem, product) =>
          lineItem(2).equals(product(0)) // Same Product Id
      }
      .cartesian(salesRDD)
      .filter {
        case ((lineItem, product), sales) =>
          lineItem(1).equals(sales(0)) // Same Sales Id
      }
      .map {
        // store.state = store(5)
        // sales.storeID = sales(3)
        // lineItem.quantity = lineItem(3)
        // product.price = product(2)
        case ((lineItem, product), sales) =>
          val salesAmount = lineItem(3).toInt * product(2).toDouble
          // Store Id, Sales Amount
          (sales(3).toInt, salesAmount)
      }
      // Group by the store ID, then sum sales
      .groupByKey()
      .mapValues { salesList =>
        salesList.sum // Sum total sales based on each Store ID
      }
      // Add in the State of the store
      .cartesian(storeRDD)
      .filter {
        case (storeSales, store) =>
          storeSales._1 == store(0).toInt // Same Stored Id
      }
      // Sort by the state, then store ID
      // store.state = store(5)
      // store.ID = store(0)
      .sortBy {
        case (storeSales, store) =>
          (store(5), store(0))
      }
      // Print the output
      .foreach {
        case (storeSales, store) =>
          println(store(5) + " " + store(0) + " " + storeSales._2)
      }

  }


}
