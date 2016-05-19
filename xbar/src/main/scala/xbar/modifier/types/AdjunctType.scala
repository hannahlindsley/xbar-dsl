package xbar.modifier.types

import xbar.modifier.abilities._
import xbar.projection.Phrase


sealed trait AdjunctType

object AdjunctType {

  final case class NAdjunct[X <: Phrase : CanAdjoinN_](x: X) extends AdjunctType

  final case class VAdjunct[X <: Phrase : CanAdjoinV_](x: X) extends AdjunctType

  final case class UAdjunct[X <: Phrase : CanAdjoinU_](x: X) extends AdjunctType

  final case class PAdjunct[X <: Phrase : CanAdjoinP_](x: X) extends AdjunctType

  final case class RAdjunct[X <: Phrase : CanAdjoinR_](x: X) extends AdjunctType

  final case class TAdjunct[X <: Phrase : CanAdjoinT_](x: X) extends AdjunctType

  final case class JAdjunct[X <: Phrase : CanAdjoinJ_](x: X) extends AdjunctType

  final case class CAdjunct[X <: Phrase : CanAdjoinC_](x: X) extends AdjunctType

  final case class DAdjunct[X <: Phrase : CanAdjoinD_](x: X) extends AdjunctType

}