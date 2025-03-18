import scala.io.Source


object Main {

  /* Lab Description
  Lab 5.
  Write a Scala program that reads the data from Lab 1
  and prints the total sales (in dollars) for every store.
  Print the result ordered by state.
  That is, start with stores in the state of AL.
  Do not use Spark.

  Example output:
  AL, 123, 34453.34 //store 123 in AL, total sales 34453.34
  ...

  Use TreeSets, HashSets, TreeMaps, HashMaps and appropriate data structures to make your program as efficient as possible.
  You can assume that all the data fits into main memory.
   */

  // General Task: Print the total sales in every store
  // Sort Task: Print the result ordered by state
  // Output Task: State , Store Id, Total of sales

  // Line Item Task
  // - Get the store Id and State (Files Used: lineItem, sales, store)
  //    - lineItem.salesID (JOIN ON) sales.ID => sales.storeID
  //    - At this point we will have the sales.storeID (JOIN ON) store.ID => store.state
  // - Calculate the total amount (Files Used: lineItem, product)
  //    - lineItem.quantity * PRODUCT_PRICE
  //      - PRODUCT_PRICE = lineItem.productID (JOIN ON) product.ID => product.price

  // lineItem: ID, salesID, productID, quantity
  // product: ID, description, price
  // sales: ID, date, time, storeID, customerID
  // store:  ID, storeName, address, city, ZIP, state, phoneNumber

  var inputPath_1 = "/Users/wintongee/Desktop/School/Winter 2024/CSC369/Repos/lab5/src/main/scala/lineItem"
  var inputPath_2 = "/Users/wintongee/Desktop/School/Winter 2024/CSC369/Repos/lab5/src/main/scala/product"
  var inputPath_3 = "/Users/wintongee/Desktop/School/Winter 2024/CSC369/Repos/lab5/src/main/scala/sales"
  var inputPath_4 = "/Users/wintongee/Desktop/School/Winter 2024/CSC369/Repos/lab5/src/main/scala/store"

  def main(args: Array[String]): Unit = {
    val lineItemData = Source.fromFile(inputPath_1).getLines().map(_.split(","))
    val productData = Source.fromFile(inputPath_2).getLines().map(_.split(","))
    val salesData = Source.fromFile(inputPath_3).getLines().map(_.split(","))
    val storeData = Source.fromFile(inputPath_4).getLines().map(_.split(","))

    // HashMap: KEY:productID, VALUE:price
    val priceMap = productData
      .map(array => (array(0), array(2))) // (product.ID, product.price)
      .toMap

    // HashMap: KEY:store.ID, VALUE: store.state
    val storeMap = storeData
      .map(array => (array(0).toInt, array(5))) // (store.ID, store.state)
      .toMap

    // HashMap: KEY:sales.ID, VALUE: sales.storeID
    val salesMap = salesData
      .map(array => (array(0), array(3))) // (sales.ID, sales.storeID)
      .toMap

    val data_1 = lineItemData
      // lineItem.salesID -> 1
      // lineItem.productID -> 2
      // lineItem.quantity -> 3
      .map(array =>
        (
          salesMap(array(1)).toInt, // StoreID
          priceMap(array(2)).toDouble * array(3).toInt // Total Sales
        )
      )
      .toList

    val summedData = data_1.groupBy(_._1) // Groups by the StoreID
      .view.mapValues { salesList =>
      salesList.map(_._2).sum // Sum total sales based on each StoreID
    }.toMap.toList

    val finalData = summedData.map {
      case (key, value) => (storeMap(key), key, value) // State, Store ID, Total Sales
    }.sortBy(_._1) // Sorts the store based on state

    finalData.foreach(println)
  }


}
