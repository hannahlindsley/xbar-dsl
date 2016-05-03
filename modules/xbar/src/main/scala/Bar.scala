package dsl.xbar

sealed trait Bar[A]

final case class T_(v: TBarType) extends Bar[T]

final case class C_(v: CBarType) extends Bar[C]

final case class J_(v: JBarType) extends Bar[J]

final case class R_(v: RBarType) extends Bar[R]

final case class D_(v: DBarType) extends Bar[D]
object D_ {
  def apply[X : DComplement](head: D, complement: X): D_ = D_(LowerDBar(head, complement))
  def apply(head: D): D_ = D_(LowerDBar(head, NoModifier()))
  def apply[X : DAdjunct](main: D_, adjunct: X): D_ = D_(UpperDBar(main, adjunct))
}

final case class U_(v: UBarType) extends Bar[U]

final case class V_(v: VBarType) extends Bar[V]

final case class P_(v: PBarType) extends Bar[P]
object P_ {
  def apply[X : PComplement](head: P, complement: X): P_ = P_(LowerPBar(head, complement))
  def apply(head: P): P_ = P_(LowerPBar(head, NoModifier()))
  def apply[X : PAdjunct](main: P_, adjunct: X): P_ = P_(UpperPBar(main, adjunct))
}

final case class N_(v: NBarType) extends Bar[N]
object N_ {
  def apply[X : NComplement](head: N, complement: X): N_ = N_(LowerNBar(head, complement))
  def apply(head: N): N_ = N_(LowerNBar(head, NoModifier()))
  def apply[X : NAdjunct](main: N_, adjunct: X): N_ = N_(UpperNBar(main, adjunct))
}

sealed trait NBarType
final case class LowerNBar[X : NComplement](head: N, complement: X) extends NBarType
final case class UpperNBar[X : NAdjunct](main: N_, adjunct: X) extends NBarType

sealed trait PBarType
final case class LowerPBar[X : PComplement](head: P, complement: X) extends PBarType
final case class UpperPBar[X : PAdjunct](bar: P_, adjunct: X) extends PBarType

sealed trait VBarType
final case class LowerVBar[X : VComplement](head: V, complement: X) extends VBarType
object LowerVBar {
  def apply(head: V): LowerVBar[NoModifier] = LowerVBar(head, NoModifier())
}
final case class UpperVBar[X : VAdjunct](bar: V_, adjunct: X) extends VBarType

sealed trait UBarType
final case class LowerUBar[X : UComplement](head: U, complement: X) extends UBarType
final case class UpperUBar[X : UAdjunct](bar: U_, adjunct: X) extends UBarType

sealed trait DBarType
final case class LowerDBar[X : DComplement](head : D, complement: X) extends DBarType
final case class UpperDBar[X : DAdjunct](bar: D_, adjunct: X) extends DBarType

sealed trait RBarType
final case class LowerRBar[X : RComplement](head: R, complement: X) extends RBarType
final case class UpperRBar[X : RAdjunct](bar: R_, adjunct: X) extends RBarType

sealed trait JBarType
final case class LowerJBar[X : JComplement](head: J, complement: X) extends JBarType
final case class UpperJBar[X : JAdjunct](bar: J_, adjunct: X) extends JBarType

sealed trait CBarType
final case class LowerCBar[X : CComplement](head: C, complement: X) extends CBarType
final case class UpperCBar[X : CAdjunct](bar: C_, adjunct: X) extends CBarType

sealed trait TBarType
final case class LowerTBar[X : TComplement](head: T, complement: X) extends TBarType
final case class UpperTBar[X : TAdjunct](bar: T_, adjunct: X) extends TBarType