import me.limacon.crif.common.{Currency, Money}
import org.scalatest.funsuite.AnyFunSuite;
class MoneyTest extends AnyFunSuite{
  test("Constructor"){
    assert(Money(100, "USD").equals(Money(100, "USD")))
    assert(Money(100.0001, "USD").equals(Money(100.0002, "USD")))
    assert(!Money(100.001, "USD").equals(Money(100.002, "USD")))
  }
  test("Arithmetic Operation ") {
    assert(Money(420.69, "USD") + Money(420.69, "USD") == Money(841.38, "USD"),"Money should be equal")
    assert(Money(420.69, "EUR") * 2 == Money(841.38, "EUR"),"Money should be equal")
    assert(Money(420.69, "USD") - Money(420, "USD") == Money(0.69, "USD"))
    assert(Money(420.69, "USD") * 10000000 == Money(4206900000L, "USD"))
    assert(Money(1.25, "USD") + 2 == Money(3.25, "USD"))
    assert(Money(1.25, "USD") - 2 == Money(-0.75, "USD"))
    assert(Money(420.69, "USD") != Money(420.69, "GBP"), "Currency should not be equal")
  }
  test("Unary Operation") {
    assert(Money(420.69, "USD") == -Money(-420.69, "USD"))
    print(-(Money(420.69, "USD")))
  }
  test("Comparison Operation") {
    assert(Money(420.69, "USD") <= Money(420.69, Currency.USD))
    assert(Money(420.69, Currency.USD) < Money(1420.69, "USD"))
    assert(Money(420.69, "USD") >= Money(420.69, "USD"))
    assert(Money(420.69, "USD") > Money(20.69, "USD"))
  }
  test("Cross Currency") {
    val _ = intercept[UnsupportedOperationException](Money(1.25, "USD") + Money(1, "GBP"))
    val _ = intercept[UnsupportedOperationException](Money(1.25, "USD") - Money(1, "GBP"))
    val _ = intercept[ArithmeticException](Money(1.25, "USD") / 0)

    assert(Money(1.25, "USD") / Money(1, "GBP") == 1.25)
  }

}
