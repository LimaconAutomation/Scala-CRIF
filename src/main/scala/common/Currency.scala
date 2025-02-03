package me.limacon.crif.common


import java.util.logging.{Logger, Level};

sealed abstract class Currency(val code: String, val symbol: String, val fullName: String) {
  def getCode(): String = code
  def getSymbol(): String = symbol
  def getName(): String = fullName

  override def toString(): String = s"$code: $fullName ($symbol)"
}
object Currency {
  case object USD extends Currency("USD", "$", "US Dollar")
  case object EUR extends Currency("EUR", "€", "Euro")
  case object GBP extends Currency("GBP", "£", "British Pound")
  case object JPY extends Currency("JPY", "¥", "Japanese Yen")
  case object CHF extends Currency("CHF", "Fr.", "Swiss Franc")
  case object CAD extends Currency("CAD", "$", "Canadian Dollar")
  case object AUD extends Currency("AUD", "$", "Australian Dollar")
  case object NZD extends Currency("NZD", "$", "New Zealand Dollar")
  case object CNY extends Currency("CNY", "¥", "Chinese Yuan")
  case object HKD extends Currency("HKD", "$", "Hong Kong Dollar")
  case object SGD extends Currency("SGD", "$", "Singapore Dollar")
  case object KRW extends Currency("KRW", "₩", "South Korean Won")
  case object INR extends Currency("INR", "₹", "Indian Rupee")
  case object BRL extends Currency("BRL", "R$", "Brazilian Real")
  case object RUB extends Currency("RUB", "₽", "Russian Ruble")
  case object ZAR extends Currency("ZAR", "R", "South African Rand")
  case object MXN extends Currency("MXN", "$", "Mexican Peso")
  case object SEK extends Currency("SEK", "kr", "Swedish Krona")
  case object NOK extends Currency("NOK", "kr", "Norwegian Krone")
  case object DKK extends Currency("DKK", "kr", "Danish Krone")
  case object THB extends Currency("THB", "฿", "Thai Baht")
  case object IDR extends Currency("IDR", "Rp", "Indonesian Rupiah")
  case object TRY extends Currency("TRY", "₺", "Turkish Lira")
  case object SAR extends Currency("SAR", "﷼", "Saudi Riyal")
  case object AED extends Currency("AED", "د.إ", "UAE Dirham")

  val values: List[Currency] = List(USD, EUR, GBP, JPY, CHF, CAD, AUD, NZD, CNY, HKD, SGD, KRW, INR, BRL, RUB, ZAR, MXN, SEK, NOK, DKK, THB, IDR, TRY, SAR, AED)

  def fromString(code: String): Option[Currency] = {
    val currency = values.find(_.code.equalsIgnoreCase(code))
    if (currency.isEmpty && !code.trim.isEmpty()) {
      val logger = Logger.getAnonymousLogger()
      logger.log(Level.WARNING, s"$code is an invalid ISO4317 currency code. See https://www.iso.org/iso-4217-currency-codes.html")
    }
    return currency
  }
}

