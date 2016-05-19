package xbar.projection.barTypes


sealed trait BarType

object BarType {

  final case class NBT(v: NBarType) extends BarType

  final case class VBT(v: VBarType) extends BarType

  final case class DBT(v: DBarType) extends BarType

  final case class JBT(v: JBarType) extends BarType

  final case class PBT(v: PBarType) extends BarType

  final case class RBT(v: RBarType) extends BarType

  final case class CBT(v: CBarType) extends BarType

  final case class UBT(v: UBarType) extends BarType

  final case class TBT(v: TBarType) extends BarType

}