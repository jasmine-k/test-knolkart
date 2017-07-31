name := "knolkart"

version := "1.0"

scalaVersion := "2.12.3"

libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.3" % "test"

/*
lazy val common = Seq(
  version := "1.0",
  scalaVersion := "2.12.2"
)

lazy val inventory = project.
  settings(common.settings: _*)

lazy val inventorySdk = project.
  dependsOn(inventory).
  settings(common.settings: _*)

lazy val notificationService = project.
  settings(common.settings: _*)

lazy val checkoutService = project.
  settings(common.settings: _*)

lazy val root = (project in file(".")).
  aggregate(inventory, inventorySdk, notificationService, checkoutService)
*/
