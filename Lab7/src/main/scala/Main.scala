import scala.io.Source
import org.apache.spark.SparkContext._

import scala.io._
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd._
import org.apache.log4j.Logger
import org.apache.log4j.Level

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import scala.collection._

object Main {

  // lineItem: ID, salesID, productID, quantity
  // product: ID, description, price
  // sales: ID, date, time, storeID, customerID
  // store:  ID, storeName, address, city, ZIP, state, phoneNumber

  // TOOD Information needed for lab7: month, storeName, storeCity, total sales

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
        // sales.date = sales(1)
        // sales.storeID = sales(3)
        // lineItem.quantity = lineItem(3)
        // product.price = product(2)
        case ((lineItem, product), sales) =>
          val salesDate = sales(1).split("/").take(2).mkString("/") // Remove the Day
          val salesAmount = lineItem(3).toInt * product(2).toDouble
          // Sales Date, Store Id, Sales Amount
          (salesDate, sales(3).toInt, salesAmount)
      }
      // Group by the salesMonth and storeID
      .groupBy {
        case (salesMonth, storeId, _) => (salesMonth, storeId)
      }
      // Sum total sales based on each Sales Month and Store ID
      .mapValues { salesList =>
        salesList.map(_._3).sum
      }
      // Add in the Name and City of the store
      .cartesian(storeRDD)
      .filter {
        case (((salesMonth, storeId), totalSales), store) =>
          storeId == store(0).toInt // Same Stored Id
      }
      .map { // Clean up the object structure
        case (((salesMonth, storeId), totalSales), store) =>
          (salesMonth, storeId, totalSales, store)
      }
      .groupBy { // Group by Sales Month
        case (salesMonth, storeId, totalSales, store) => salesMonth
      }
      .mapValues { salesList =>
        salesList
          .toList
          .take(10)
          .sortBy { // Sort by the Total Sales
            case (salesMonth, storeId, totalSales, store) =>
              totalSales * -1 // Remember: -1 to make it desc order
          }
          .map { // Match the output on Lab Description
            case (salesMonth, storeId, totalSales, store) =>
              (store(1), store(3), totalSales)
          }

      }
      .sortBy { // Sort by the months
        case (salesMonth, topStores) =>
          val Array(year, month) = salesMonth.split("/")
          (year.toInt * -1, month.toInt * -1)
      }
      .foreach {
        case (salesMonth, topStores) =>
          println(s"$salesMonth: ${topStores.mkString(", ")}")
      }
  }


}
