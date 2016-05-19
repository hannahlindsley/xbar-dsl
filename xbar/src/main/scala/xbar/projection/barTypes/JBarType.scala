package xbar.projection.barTypes


import xbar.modifier.{Child, Modifier}
import xbar.modifier.Child.{ChildThatIsHead, ChildThatIsBar}
import xbar.modifier.Modifier.{Complement, Adjunct}
import xbar.modifier.abilities.{CanAdjoinJ_, CanComplementJ}
import xbar.modifier.types.AdjunctType.JAdjunct
import xbar.modifier.types.ComplementType.JComplement
import xbar.projection.{NoPhrase, J, J_, Phrase}


sealed trait JBarType {
  val child: Child
  val modifier: Modifier
}

object JBarType {

  final case class LowerJBar[X : Phrase : CanComplementJ](head: J, complement: Option[X]) extends JBarType {
    val child: Child = ChildThatIsHead(head)
    val modifier: Modifier = Complement(JComplement(complement))
  }

  object LowerJBar {
    def apply(j: J): LowerJBar[NoPhrase] = LowerJBar(j, Option.empty[NoPhrase])
  }

  final case class UpperJBar[X : Phrase : CanAdjoinJ_](bar: J_, adjunct: X) extends JBarType {
    val child: Child = ChildThatIsBar(bar)
    val modifier = Adjunct(JAdjunct(adjunct))
  }

}
