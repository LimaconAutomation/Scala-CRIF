import me.limacon.crif.common.Currency
import org.scalatest.funsuite.AnyFunSuite

class CurrencyTest extends AnyFunSuite{
  val validCodes = List("USD", "gbp", "Eur", "jpy", "cNY", "hkd")
  val invalidCodes = List("joe", "人民币", "$")

  test("Currency Code") {
    validCodes.foreach(code => Currency.fromString(code) match {
      case None => assert(false, s"$code is a valid currency code")
      case Some(value) => println(value);
    })

    invalidCodes.foreach(code => Currency.fromString(code) match {
      case Some(value) => assert(false, s"$code is an invalid currency code");
      case _ => ()
    })
  }
}