package xbar.projection.barTypes

import xbar.modifier.{Child, Modifier}
import xbar.modifier.Child.{ChildThatIsHead, ChildThatIsBar}
import xbar.modifier.Modifier.{Complement, Adjunct}
import xbar.modifier.abilities.{CanAdjoinR_, CanComplementR}
import xbar.modifier.types.AdjunctType.RAdjunct
import xbar.modifier.types.ComplementType.RComplement
import xbar.projection.{Phrase, R_, R}


sealed trait RBarType {
  val child: Child
  val modifier: Modifier
}

object RBarType {

  final case class LowerRBar[X <: Phrase : CanComplementR](head: R, complement: X) extends RBarType {
    val child: Child = ChildThatIsHead(head)
    val modifier: Modifier = Complement(RComplement(complement))
  }

  final case class UpperRBar[X <: Phrase : CanAdjoinR_](bar: R_, adjunct: X) extends RBarType {
    val child: Child = ChildThatIsBar(bar)
    val modifier: Modifier = Adjunct(RAdjunct(adjunct))
  }

}