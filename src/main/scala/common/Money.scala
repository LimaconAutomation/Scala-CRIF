package me.limacon.crif.common

import me.limacon.crif.{Real, SMALL_TOLERANCE}

//final class Money(val amount: Real)(implicit val unit: Currency)
final class Money(val amount: Real)(val currency: Currency) {
  protected def currencyUnit: String = currency.code
  override def toString: String = amount.setScale(2, BigDecimal.RoundingMode.HALF_UP) + " " +currencyUnit

  // Binary Operators
  /**
   * Compares this Money instance with another object for equality.
   * The comparison checks if the provided object is a Money instance,
   * and whether both the amounts are approximately equal (within a
   * small tolerance) and their currency units are the same.
   *
   * @param that the object to compare with this Money instance
   * @return true if the specified object is a Money instance with
   *         the same currency unit and approximately equal amount;
   *         false otherwise
   */
  override def equals(that: Any) : Boolean = {
    that match {
      case that: Money => that.currencyUnit == this.currencyUnit && (that.amount - this.amount).abs < SMALL_TOLERANCE
      case _ => false
    }
  }

  /**
   * Adds the given amount of money to the current instance.
   * Both the current and the given money instances must have the same currency.
   *
   * @param that the Money instance to be added to the current instance
   * @throws UnsupportedOperationException if the currencies of the two Money instances do not match
   * @return a new Money instance representing the sum of the current and the given Money instances
   */
  def plus(that: Money): Money = that.currency match {
      case this.currency => new Money(this.amount + that.amount)(this.currency)
      case _ => throw new UnsupportedOperationException("Cannot subtract money of different currencies")
  }
  def +(that: Money): Money = this.plus(that)
  def +(amount: Real): Money = new Money(this.amount + amount)(this.currency)
  // TODO Impl number + money operator

  /**
   * Subtracts the given amount of money from the current instance.
   * Both the current and the given money instances must have the same currency.
   *
   * @param that the Money instance to be subtracted from the current instance
   *             @ */
  def minus(that: Money): Money = that.currency match {
    case this.currency => new Money(this.amount - that.amount)(this.currency)
    case _ => throw new UnsupportedOperationException("Cannot subtract money of different currencies")
  }
  def -(that: Money): Money = this.minus(that)
  def -(amount: Real): Money = new Money(this.amount - amount)(this.currency)

  /**
   * Multiplies the current Money instance by a specified scale factor.
   *
   * @param scale the BigDecimal scale factor by which the amount of the current Money instance is multiplied
   * @return a new Money instance with the amount scaled by the specified factor, while retaining the same currency
   */
  def mul(scale: Real) = new Money(this.amount * scale)(this.currency)
  def *(scale: Real): Money = this.mul(scale)

  /**
   * Divides the current Money instance's amount by a specified scale factor.
   *
   * @param scale the BigDecimal scale factor by which the amount is divided
   * @return a new Money instance with the amount divided by the scale factor, retaining the same currency
   */
  def div(scale: Real) = new Money(this.amount / scale)(this.currency)
  def /(scale: Real): Money = this.div(scale)

  /**
   * Divides the amount of the current Money instance by the amount of another Money instance.
   * Both Money instances must have the same currency.
   *
   * @param that the Money instance whose amount will be used as the divisor
   * @throws UnsupportedOperationException */
  def div(that: Money): Real = this.amount / that.amount
  def /(that: Money): Real = this.div(that)

  // Comparing operators
  /**
   * Compares the current Money instance with another Money instance.
   * Both instances must have the same currency for comparison to be performed.
   *
   * @param that the Money instance to compare with the current instance
   * @throws UnsupportedOperationException if the currencies of the two Money instances do not match
   * @return an integer indicating the comparison result:
   *         a negative integer if this instance is less than `that`,
   *         zero if they are equal,
   *         and a positive integer if this instance is greater than `that`
   */
  def compare(that: Money): Int = that.currency match {
    case currency => this.amount.compare(that.amount)
    case _ => throw new UnsupportedOperationException("Cannot compare money of different currencies")
  }

  def greaterThan(that: Money): Boolean = this.compare(that) > 0
  def >(that: Money): Boolean = this.greaterThan(that)
  def lessThan(that: Money): Boolean = this.compare(that) < 0
  def <(that: Money): Boolean = this.lessThan(that)
  def greaterThanOrEqual(that: Money): Boolean = this.compare(that) >= 0
  def >=(that: Money): Boolean = this.greaterThanOrEqual(that)
  def lessThanOrEqual(that: Money): Boolean = this.compare(that) <= 0
  def <=(that: Money): Boolean = this.lessThanOrEqual(that)

  // Unary operators
  /**
   * Negates the amount of the current Money instance.
   *
   * This operator creates a new Money instance with the same currency as the original instance
   * but with the amount negated (multiplied by -1).
   *
   * @return a new Money instance with the negated amount, retaining the same currency.
   */
  def unary_- = new Money(-this.amount)(this.currency)

  /**
   * Computes the absolute value of the amount in the current Money instance.
   * This operation creates a new Money instance with a non-negative amount,
   * keeping the same currency unit.
   *
   * @return a new Money instance where the amount is the absolute value
   *         of the current amount, retaining the same currency.
   */
  def abs = new Money(this.amount.abs)(this.currency)

}

final object Money {
  // TODO add ctor w/ context currency
//  def apply(amount: Real): Money = new Money(amount)(Currency.USD)
  def apply(amount: Real, currency: Currency): Money = new Money(amount)(currency)
  def apply(amount: Real, currencyCode: String): Money = new Money(amount)(Currency.fromString(currencyCode))
}
