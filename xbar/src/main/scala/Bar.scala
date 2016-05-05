package dsl.xbar

/**
 * Bar Projection
 */
sealed trait Bar {
  val v: BarType
}

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
  def apply[X <: Phrase : DComplement](head: D, complement: X): D_ = D_(DBT(LowerDBar(head, complement)))
  def apply(head: D): D_ = D_(DBT(LowerDBar(head, EmptyPhrase())))
  def apply[X <: Phrase : DAdjunct](main: D_, adjunct: X): D_ = D_(DBT(UpperDBar(main, adjunct)))
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
  def apply [X <: Phrase : VComplement](head: V, complement: X): V_ = V_(VBT(LowerVBar(head, complement)))
  def apply(head: V): V_ = V_(VBT(LowerVBar(head, EmptyPhrase())))
  def apply[X <: Phrase : VAdjunct](main: V_, adjunct: X): V_ = V_(VBT(UpperVBar(main, adjunct)))
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


sealed trait BarType
final case class NBT(v: NBarType) extends BarType
final case class VBT(v: VBarType) extends BarType
final case class DBT(v: DBarType) extends BarType
final case class JBT(v: JBarType) extends BarType
final case class PBT(v: PBarType) extends BarType
final case class RBT(v: RBarType) extends BarType
final case class CBT(v: CBarType) extends BarType
final case class UBT(v: UBarType) extends BarType
final case class TBT(v: TBarType) extends BarType
case object EmptyBarType extends BarType


sealed trait NBarType {
  val child: Child
  val modifier: Modifier
}
final case class LowerNBar[X <: Phrase : CanComplementN](head: N, complement: X) extends NBarType {
  override val child: Child = HeadChild(head)
  override val modifier: Modifier = Complement(NComplementive(complement))
}
final case class UpperNBar[X <: Phrase : CanAdjoinN_](bar: N_, adjunct: X) extends NBarType {
  override val child: Child = BarChild(bar)
  override val modifier: Modifier = Adjunct(NAdjunctive(adjunct))
}


sealed trait PBarType {
  val child: Child
  val modifier: Modifier
}
final case class LowerPBar[X <: Phrase : CanComplementP](head: P, complement: X) extends PBarType {
  override val child: Child = HeadChild(head)
  override val modifier: Modifier = Complement(PComplementive(complement))
}
final case class UpperPBar[X <: Phrase : CanAdjoinP_](bar: P_, adjunct: X) extends PBarType {
  override val child: Child = BarChild(bar)
  override val modifier: Modifier = Adjunct(PAdjunctive(adjunct))
}


sealed trait VBarType {
  val child: Child
  val modifier: Modifier
}
final case class LowerVBar[X <: Phrase : VComplement](head: V, complement: X) extends VBarType {
  val vComp = implicitly[VComplement[X]]
  override val child: Child = HeadChild(head)
  override val modifier: Modifier = Complement(VComplementive(complement))
}
object LowerVBar {
  def apply(head: V): LowerVBar[EmptyPhrase] = LowerVBar(head, EmptyPhrase())
}
final case class UpperVBar[X <: Phrase : VAdjunct](bar: V_, adjunct: X) extends VBarType {
  override val child: Child = BarChild(bar)
  override val modifier: Modifier = Adjunct(VAdjunctive(adjunct))
}


sealed trait UBarType {
  val child: Child
  val modifier: Modifier
}
final case class LowerUBar[X <: Phrase : UComplement](head: U, complement: X) extends UBarType {
  override val child: Child = HeadChild(head)
  override val modifier: Modifier = Complement(UComplementive(complement))
}
final case class UpperUBar[X <: Phrase : UAdjunct](bar: U_, adjunct: X) extends UBarType {
  override val child: Child = BarChild(bar)
  override val modifier: Modifier = Adjunct(UAdjunctive(adjunct))
}


sealed trait DBarType {
  val child: Child
  val modifier: Modifier
}
final case class LowerDBar[X <: Phrase : DComplement](head : D, complement: X) extends DBarType {
  override val child: Child = HeadChild(head)
  override val modifier: Modifier = Complement(DComplementive(complement))
}
final case class UpperDBar[X <: Phrase : DAdjunct](bar: D_, adjunct: X) extends DBarType {
  override val child: Child = BarChild(bar)
  override val modifier: Modifier = Adjunct(DAdjunctive(adjunct))
}


sealed trait RBarType {
  val child: Child
  val modifier: Modifier
}
final case class LowerRBar[X <: Phrase : RComplement](head: R, complement: X) extends RBarType {
  override val child: Child = HeadChild(head)
  override val modifier: Modifier = Complement(RComplementive(complement))
}
final case class UpperRBar[X <: Phrase : RAdjunct](bar: R_, adjunct: X) extends RBarType {
  override val child: Child = BarChild(bar)
  override val modifier: Modifier = Adjunct(RAdjunctive(adjunct))
}


sealed trait JBarType {
  val child: Child
  val modifier: Modifier
}
final case class LowerJBar[X <: Phrase : JComplement](head: J, complement: X) extends JBarType {
  override val child: Child = HeadChild(head)
  override val modifier: Modifier = Complement(JComplementive(complement))
}
final case class UpperJBar[X <: Phrase : JAdjunct](bar: J_, adjunct: X) extends JBarType {
  override val child: Child = BarChild(bar)
  override val modifier: Modifier = Adjunct(JAdjunctive(adjunct))
}


sealed trait CBarType {
  val child: Child
  val modifier: Modifier
}
final case class LowerCBar[X <: Phrase : CComplement](head: C, complement: X) extends CBarType {
  override val child: Child = HeadChild(head)
  override val modifier: Modifier = Complement(CComplementive(complement))
}
final case class UpperCBar[X <: Phrase : CAdjunct](bar: C_, adjunct: X) extends CBarType {
  override val child: Child = BarChild(bar)
  override val modifier: Modifier = Adjunct(CAdjunctive(adjunct))
}


sealed trait TBarType {
  val child: Child
  val modifier: Modifier
}
final case class LowerTBar[X <: Phrase : TComplement](head: T, complement: X) extends TBarType {
  override val child: Child = HeadChild(head)
  override val modifier: Modifier = Complement(TComplementive(complement))
}
final case class UpperTBar[X <: Phrase : TAdjunct](bar: T_, adjunct: X) extends TBarType {
  override val child: Child = BarChild(bar)
  override val modifier: Modifier = Adjunct(TAdjunctive(adjunct))
}