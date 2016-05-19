package xbar.modifier.types

import xbar.modifier.abilities._
import xbar.projection.Phrase


sealed trait ComplementType

object ComplementType {

  final case class NComplement[X <: Phrase : CanComplementN](x: X) extends ComplementType

  final case class VComplement[X <: Phrase : CanComplementV](x: X) extends ComplementType

  final case class UComplement[X <: Phrase : CanComplementU](x: X) extends ComplementType

  final case class PComplement[X <: Phrase : CanComplementP](x: X) extends ComplementType

  final case class RComplement[X <: Phrase : CanComplementR](x: X) extends ComplementType

  final case class TComplement[X <: Phrase : CanComplementT](x: X) extends ComplementType

  final case class JComplement[X <: Phrase : CanComplementJ](x: X) extends ComplementType

  final case class CComplement[X <: Phrase : CanComplementC](x: X) extends ComplementType

  final case class DComplement[X <: Phrase : CanComplementD](x: X) extends ComplementType

}