package xbar.projection.barTypes

import xbar.modifier.{Child, Modifier}
import xbar.modifier.Child.{ChildThatIsHead, ChildThatIsBar}
import xbar.modifier.Modifier.{Complement, Adjunct}
import xbar.modifier.abilities.{CanAdjoinP_, CanComplementP}
import xbar.modifier.types.AdjunctType.PAdjunct
import xbar.modifier.types.ComplementType.PComplement
import xbar.projection.{Phrase, P_, P}


sealed trait PBarType {
  val child: Child
  val modifier: Modifier
}

object PBarType {

  final case class LowerPBar[X : Phrase : CanComplementP](head: P, complement: X) extends PBarType {
    val child: Child = ChildThatIsHead(head)
    val modifier: Modifier = Complement(PComplement(complement))
  }

  final case class UpperPBar[X : Phrase : CanAdjoinP_](bar: P_, adjunct: X) extends PBarType {
    val child: Child = ChildThatIsBar(bar)
    val modifier: Modifier = Adjunct(PAdjunct(adjunct))
  }

}