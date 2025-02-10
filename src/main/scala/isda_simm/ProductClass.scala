package me.limacon.crif.isda_simm;

/**
 * Represents a product class used in the ISDA SIMM 2.7 model.
 *
 * The `ProductClass` class serves as an abstraction for categorizing financial products
 * into different classes such as RatesFX, Credit, Equity, or Commodity.
 * Each subclass or instance corresponds to a specific product class type.
 *
 * @param productClass A string representation of the product class.
 */
sealed abstract class ProductClass(val productClass: String){
  override def toString: String = s"Product class: $productClass"
}

/**
 * Represents a set of predefined product classes used in financial systems according to ISDA SIMM 2.7.
 * Each product class corresponds to a specific financial product domain such as RatesFX, Credit, Equity, or Commodity.
 *
 * This object provides functionality to retrieve a `ProductClass` instance based on a string input.
 * Case-insensitive matching is performed on the input with predefined aliases to determine the appropriate product class.
 *
 * Throws an `IllegalArgumentException` if an unknown or unsupported product class is provided.
 */
object ProductClass {
  case object RatesFX extends ProductClass("RatesFX")
  case object Credit extends ProductClass("Credit")
  case object Equity extends ProductClass("Equity")
  case object Commodity extends ProductClass("Commodity")

  def apply(s: String): ProductClass = s.toLowerCase match{
    case "ratesfx" => RatesFX
    case "fx" => RatesFX
    case "rates" => RatesFX
    case "interest rates and foreign exchange" => RatesFX
    case "credit" => Credit
    case "credits" => Credit
    case "equity" => Equity
    case "commodity" => Commodity
    case _ => throw new IllegalArgumentException(s"Invalid product class: $s")
  }
}