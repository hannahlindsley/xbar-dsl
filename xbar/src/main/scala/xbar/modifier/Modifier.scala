package xbar.modifier

import xbar.modifier.types.{SpecifierType, ComplementType, AdjunctType}


sealed trait Modifier

object Modifier {

  final case class Complement(x: ComplementType) extends Modifier

  final case class Specifier(x: SpecifierType) extends Modifier

  final case class Adjunct(x: AdjunctType) extends Modifier

}