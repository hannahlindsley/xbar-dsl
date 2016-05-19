package xbar.projection.barTypes

import xbar.modifier.{Modifier, Child}
import xbar.modifier.Modifier.{Complement, Adjunct}
import xbar.modifier.Child.{ChildThatIsBar, ChildThatIsHead}
import xbar.modifier.abilities.{CanAdjoinU_, CanComplementU}
import xbar.modifier.types.AdjunctType.UAdjunct
import xbar.modifier.types.ComplementType.UComplement
import xbar.projection.{Phrase, U_, U}


sealed trait UBarType {
  val child: Child
  val modifier: Modifier
}

object UBarType {

  final case class LowerUBar[X : Phrase : CanComplementU](head: U, complement: X) extends UBarType {
    val child: Child = ChildThatIsHead(head)
    val modifier: Modifier = Complement(UComplement(complement))
  }

  final case class UpperUBar[X : Phrase : CanAdjoinU_](bar: U_, adjunct: X) extends UBarType {
    val child: Child = ChildThatIsBar(bar)
    val modifier: Modifier = Adjunct(UAdjunct(adjunct))
  }

}