package edu.knoldus.service

import edu.knoldus.models._

trait BackendService {

  def addItem(inventory: Inventory,item: Item):(Inventory,Long)={

    val itemList=inventory.listOfItem
    val newList=item::itemList
    (Inventory(newList),item.id)
  }
}
