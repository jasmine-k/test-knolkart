package edu.knoldus.models

case class Item(id: Long, name: String, category: String,count: Int,
                photos : List[Photo], vendorInformation: Vendor, price: Price ) {

}
