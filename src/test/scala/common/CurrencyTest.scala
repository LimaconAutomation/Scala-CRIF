import me.limacon.crif.common.Currency
import org.scalatest.funsuite.AnyFunSuite

class CurrencyTest extends AnyFunSuite{
  val validCodes = List("USD", "gbp", "Eur", "jpy", "cNY", "hkd")
  val invalidCodes = List("joe", "人民币", "$")

  test("Currency Code") {
    validCodes.foreach(code => {
      try{
        val _ = Currency.fromString(code)
      }
    })

    invalidCodes.foreach(code => try {
      var _ = Currency.fromString(code)
    } catch {
      case e: IllegalArgumentException => ()
      case _ => throw new IllegalArgumentException("IllegalArgumentException expected")

    })

  }
  test("Currency Compare") {
    assert(Currency.fromString("usd") == Currency.fromString("USD"))
  }
}