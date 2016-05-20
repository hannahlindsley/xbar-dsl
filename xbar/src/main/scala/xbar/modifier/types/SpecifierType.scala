package xbar.modifier.types

import xbar.modifier.abilities._
import xbar.projection.Phrase


sealed trait SpecifierType

object SpecifierType {

  final case class NSpecifier[X : Phrase : CanSpecifyN](x: X) extends SpecifierType

  final case class VSpecifier[X : Phrase : CanSpecifyV](x: X) extends SpecifierType

  final case class USpecifier[X : Phrase : CanSpecifyU](x: X) extends SpecifierType

  final case class PSpecifier[X : Phrase : CanSpecifyP](x: X) extends SpecifierType

  final case class RSpecifier[X : Phrase : CanSpecifyR](x: X) extends SpecifierType

  final case class TSpecifier[X : Phrase : CanSpecifyT](x: X) extends SpecifierType

  final case class JSpecifier[X : Phrase : CanSpecifyJ](x: X) extends SpecifierType

  final case class CSpecifier[X : Phrase : CanSpecifyC](x: X) extends SpecifierType

  final case class DSpecifier[X : Phrase : CanSpecifyGenitive](x: X) extends SpecifierType

}