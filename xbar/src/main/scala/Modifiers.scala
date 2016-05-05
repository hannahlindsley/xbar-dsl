package dsl.xbar

import scala.language.higherKinds

sealed trait Position
case object Left extends Position
case object Right extends Position

sealed trait Child
final case class HeadChild(head: Head) extends Child
final case class BarChild(bar: Bar) extends Child

sealed trait Modifier
final case class Complement(x: Complementive) extends Modifier
final case class Specifier(x: Specifying) extends Modifier
final case class Adjunct(x: Adjunctive) extends Modifier

sealed trait GrammaticalCategory
case object EmptyCategory extends GrammaticalCategory
case object Prepositional extends GrammaticalCategory

case class DirectObject[X <: Phrase : DSpecifier](obj: DP[X])
case class Subject[X <: Phrase : TSpecifier](subj: X)
case class EmptyObjectOfIntransitiveVerb(empty: VComplement[EmptyPhrase])
case class Possessor[X <: Phrase : DSpecifier](poss: DSpecifier[DP[X]])

sealed trait Adjunctive
final case class NAdjunctive[X <: Phrase : CanAdjoinN_](x: X) extends Adjunctive
final case class VAdjunctive[X <: Phrase : VAdjunct](x: X) extends Adjunctive
final case class UAdjunctive[X <: Phrase : UAdjunct](x: X) extends Adjunctive
final case class PAdjunctive[X <: Phrase : CanAdjoinP_](x: X) extends Adjunctive
final case class RAdjunctive[X <: Phrase : RAdjunct](x: X) extends Adjunctive
final case class TAdjunctive[X <: Phrase : TAdjunct](x: X) extends Adjunctive
final case class JAdjunctive[X <: Phrase : JAdjunct](x: X) extends Adjunctive
final case class CAdjunctive[X <: Phrase : CAdjunct](x: X) extends Adjunctive
final case class DAdjunctive[X <: Phrase : DAdjunct](x: X) extends Adjunctive

sealed trait Complementive
final case class NComplementive[X <: Phrase : CanComplementN](x: X) extends Complementive
final case class VComplementive[X <: Phrase : VComplement](x: X) extends Complementive
final case class UComplementive[X <: Phrase : UComplement](x: X) extends Complementive
final case class PComplementive[X <: Phrase : CanComplementP](x: X) extends Complementive
final case class RComplementive[X <: Phrase : RComplement](x: X) extends Complementive
final case class TComplementive[X <: Phrase : TComplement](x: X) extends Complementive
final case class JComplementive[X <: Phrase : JComplement](x: X) extends Complementive
final case class CComplementive[X <: Phrase : CComplement](x: X) extends Complementive
final case class DComplementive[X <: Phrase : DComplement](x: X) extends Complementive

sealed trait Specifying
final case class NSpecifying[X <: Phrase : NSpecifier](x: X) extends Specifying
final case class VSpecifying[X <: Phrase : VSpecifier](x: X) extends Specifying
final case class USpecifying[X <: Phrase : USpecifier](x: X) extends Specifying
final case class PSpecifying[X <: Phrase : PSpecifier](x: X) extends Specifying
final case class RSpecifying[X <: Phrase : RSpecifier](x: X) extends Specifying
final case class TSpecifying[X <: Phrase : TSpecifier](x: X) extends Specifying
final case class JSpecifying[X <: Phrase : JSpecifier](x: X) extends Specifying
final case class CSpecifying[X <: Phrase : CSpecifier](x: X) extends Specifying
final case class DSpecifying[X <: Phrase : DSpecifier](x: X) extends Specifying

trait CanComplementN[X] {
  val position = Right
  val grammaticalCategory: GrammaticalCategory
}

trait CanAdjoinN_[X]
object CanAdjoinN_
trait NSpecifier[X]
object NSpecifier

trait VComplement[A] {
  val grammaticalPosition: GrammaticalPosition
}
object VComplement
trait VAdjunct[X]
trait VSpecifier[X]

trait UComplement[X]
trait UAdjunct[X]
trait USpecifier[X]

trait RComplement[X]
trait RAdjunct[X]
trait RSpecifier[X]

trait JComplement[X]
trait JAdjunct[X]
trait JSpecifier[X]

trait DComplement[X]
trait DAdjunct[X]
trait DSpecifier[X] {
  val grammaticalPosition: GrammaticalPosition
}

trait CanComplementP[X]
trait CanAdjoinP_[X] {
  def mod(x: X) = identity(x)
}
trait PSpecifier[X]

trait TComplement[X]
trait TAdjunct[X]
trait TSpecifier[X]

trait CComplement[X] {
  val position = Right
}

trait CAdjunct[X]
trait CSpecifier[X]