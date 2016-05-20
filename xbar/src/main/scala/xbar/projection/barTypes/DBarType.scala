package xbar.projection.barTypes

import xbar.modifier.{Child, Modifier}
import xbar.modifier.Child.{ChildThatIsHead, ChildThatIsBar}
import xbar.modifier.Modifier.{Complement, Adjunct}
import xbar.modifier.abilities.{CanAdjoinD_, CanComplementD}
import xbar.modifier.types.AdjunctType.DAdjunct
import xbar.modifier.types.ComplementType.DComplement
import xbar.projection._


sealed trait DBarType {
  val child: Child
  val modifier: Modifier
}

object DBarType {

  final case class LowerDBar[X : Phrase : CanComplementD](head: D, complement: X) extends DBarType {
    val child: Child = ChildThatIsHead(head)
    val modifier: Modifier = Complement(DComplement(complement))
  }

  final case class UpperDBar[X : Phrase : CanAdjoinD_](bar: D_, adjunct: X) extends DBarType {
    val child: Child = ChildThatIsBar(bar)
    val modifier: Modifier = Adjunct(DAdjunct(adjunct))
  }
}


