import org.scalatest.funsuite.AnyFunSuite
import me.limacon.crif.isda_simm.ProductClass;

class ProductClassTest extends AnyFunSuite{
  val validProductClasses = List("RatesFX", "Credit", "Equity", "FX", "Commodity", "ratesfx", "interest Rates and Foreign Exchange", "rates")
  val invalidProductClasses = List("fx","Equities", "Gold", "CDS", "CreditQ", "InterestRates")

  test("Product Class") {
    validProductClasses.foreach(productClassName => println(ProductClass(productClassName)))
    invalidProductClasses.foreach(productClassName =>
        intercept[IllegalArgumentException](ProductClass(productClassName))
      )
  }
}