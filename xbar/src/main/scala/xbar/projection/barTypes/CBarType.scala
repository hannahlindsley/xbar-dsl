package xbar.projection.barTypes

import xbar.modifier.{Child, Modifier}
import xbar.modifier.Child.{ChildThatIsHead, ChildThatIsBar}
import xbar.modifier.Modifier.{Complement, Adjunct}
import xbar.modifier.abilities.{CanAdjoinC_, CanComplementC}
import xbar.modifier.types.ComplementType.CComplement
import xbar.modifier.types.AdjunctType.CAdjunct
import xbar.projection.{Phrase, C, C_}


sealed trait CBarType {
  val child: Child
  val modifier: Modifier
}

object CBarType {

  final case class LowerCBar[X <: Phrase : CanComplementC](head: C, complement: X) extends CBarType {
    val child: Child = ChildThatIsHead(head)
    val modifier: Modifier = Complement(CComplement(complement))
  }

  final case class UpperCBar[X <: Phrase : CanAdjoinC_](bar: C_, adjunct: X) extends CBarType {
    val child: Child = ChildThatIsBar(bar)
    val modifier: Modifier = Adjunct(CAdjunct(adjunct))
  }

}