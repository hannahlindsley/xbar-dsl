package xbar.projection.barTypes

import xbar.modifier.{Child, Modifier}
import xbar.modifier.Child.{ChildThatIsHead, ChildThatIsBar}
import xbar.modifier.Modifier.{Complement, Adjunct}
import xbar.modifier.abilities.{CanAdjoinT_, CanComplementT}
import xbar.modifier.types.AdjunctType.TAdjunct
import xbar.modifier.types.ComplementType.TComplement
import xbar.projection.{Phrase, T_, T}


sealed trait TBarType {
  val child: Child
  val modifier: Modifier
}

object TBarType {

  final case class LowerTBar[X : Phrase : CanComplementT](head: T, complement: X) extends TBarType {
    val child: Child = ChildThatIsHead(head)
    val modifier: Modifier = Complement(TComplement(complement))
  }

  final case class UpperTBar[X : Phrase : CanAdjoinT_](bar: T_, adjunct: X) extends TBarType {
    val child: Child = ChildThatIsBar(bar)
    val modifier: Modifier = Adjunct(TAdjunct(adjunct))
  }

}