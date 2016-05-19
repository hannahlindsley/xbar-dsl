package xbar.projection

import barTypes.BarType
import xbar.modifier.abilities._
import xbar.projection.barTypes.BarType._
import xbar.projection.barTypes.DBarType._
import xbar.projection.barTypes.JBarType.{LowerJBar, UpperJBar}
import xbar.projection.barTypes.VBarType._
import xbar.projection.barTypes.PBarType._
import xbar.projection.barTypes.NBarType._

/**
 * Bar Projection
 */
sealed trait Bar {
  val v: BarType
}

/**
 * Empty Bar. Used for type conformity.
 */
case object Empty_ extends Bar {
  val v: BarType = EmptyBarType
}

/**
 * Tense Bar
 * @param v the type of tense bar, upper or lower
 */
final case class T_(v: TBT) extends Bar

/**
 * Complementizer Bar
 * @param v the type of complementizer bar, upper or lower
 */
final case class C_(v: CBT) extends Bar

/**
 * Adjective Bar
 * @param v the type of adjective bar, upper or lower
 */
final case class J_(v: JBT) extends Bar
object J_ {
  def apply(head: J): J_ = J_(JBT(LowerJBar(head)))
  def apply[X <: Phrase : CanComplementJ](head: J, complement: X): J_ = J_(JBT(LowerJBar(head, complement)))
  def apply[X <: Phrase : CanAdjoinJ_](main: J_, adjunct: X): J_ = J_(JBT(UpperJBar(main, adjunct)))
}

/**
 * Adverb Bar
 * @param v the type of adverb bar, upper or lower
 */
final case class R_(v: RBT) extends Bar
/**
 * Determiner Bar
 * @param v the type of determiner bar, upper or lower
 */
final case class D_(v: DBT) extends Bar
object D_ {
  def apply[X <: Phrase : CanComplementD](head: D, complement: X): D_ = D_(DBT(LowerDBar(head, complement)))
  def apply[X <: Phrase : CanAdjoinD_](main: D_, adjunct: X): D_ = D_(DBT(UpperDBar(main, adjunct)))
}

/**
 * Union Bar
 * @param v the type of union bar, upper or lower
 */
final case class U_(v: UBT) extends Bar

/**
 * Verb Bar
 * @param v the bype of verb bar, upper or lower
 */
final case class V_(v: VBT) extends Bar
object V_ {
  def apply [X <: Phrase : CanComplementV](head: V, complement: X): V_ = V_(VBT(LowerVBar(head, complement)))
  def apply(head: V): V_ = V_(VBT(LowerVBar(head, EmptyPhrase())))
  def apply[X <: Phrase : CanAdjoinV_](main: V_, adjunct: X): V_ = V_(VBT(UpperVBar(main, adjunct)))
}

/**
 * Preposition Bar
 * @param v the type of preposition bar, upper or lower
 */
final case class P_(v: PBT) extends Bar
object P_ {
  def apply[X <: Phrase : CanComplementP](head: P, complement: X): P_ = P_(PBT(LowerPBar(head, complement)))
  def apply(head: P): P_ = P_(PBT(LowerPBar(head, EmptyPhrase())))
  def apply[X <: Phrase : CanAdjoinP_](main: P_, adjunct: X): P_ = P_(PBT(UpperPBar(main, adjunct)))
}

/**
 * Noun Bar
 * @param v the type of noun bar, upper or lower
 */
final case class N_(v: NBT) extends Bar
object N_ {
  def apply[X <: Phrase : CanComplementN](head: N, complement: X): N_ = N_(NBT(LowerNBar(head, complement)))
  def apply(head: N): N_ = N_(NBT(LowerNBar(head, EmptyPhrase())))
  def apply[X <: Phrase : CanAdjoinN_](main: N_, adjunct: X): N_ = N_(NBT(UpperNBar(main, adjunct)))
}

