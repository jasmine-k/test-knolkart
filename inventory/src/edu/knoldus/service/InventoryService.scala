package edu.knoldus.service

import edu.knoldus.models.{Inventory,Item, Price, Vendor}

import scala.concurrent.Future


trait InventoryService {

  def sortByPrice(inventory: Inventory, priceSelection: Option[String]): Inventory ={
    priceSelection match {
      case Some("low") => Inventory(inventory.listOfItem.sortBy(_.price.amount))
      case Some("high") => Inventory(inventory.listOfItem.sortBy(_.price.amount).reverse)
      case _ => throw new Exception
    }
  }

  def searchItem(inventory: Inventory, itemCategory: String, itemName: String,
                 priceSelection: Option[String]): Future[List[Item]] = {
    Future {
      val itemsRequired:List[Item] = inventory.listOfItem.filter(_.category == itemCategory).filter(_.name == itemName)

      if(itemsRequired.isEmpty) {
        throw new NoSuchElementException
      }
      else {
          priceSelection match {
            case Some(`priceSelection`) => sortByPrice(Inventory(itemsRequired),priceSelection).listOfItem
            case None => itemsRequired
          }
        }
      }
  }

  def accessPriceInfo (inventory: Inventory, itemId: Long): Future[Price] ={
    Future {
      inventory.listOfItem.filter(_.id == itemId)(0).price
    }
  }

  def updateItemCount(inventory: Inventory, itemId: Long, itemCount: Int)(f: (Int,Int)=>Int): Future[Option[Item]] ={
    Future{
      val itemBeforeUpdate : Item= inventory.listOfItem.filter(_.id == itemId)(0)
      if(itemCount != 0 ) {
        Some(itemBeforeUpdate.copy(count = f(itemBeforeUpdate.count, itemCount)))
      }
      else {
        None
      }
    }

  }


  def addItems(inventory: Inventory, itemToBeAdded: Item) : Future[[Inventory, Long]] ={
    Future{
      val updatedInventory: Inventory = itemToBeAdded :: inventory
      [updatedInventory]
    }
  }
}
