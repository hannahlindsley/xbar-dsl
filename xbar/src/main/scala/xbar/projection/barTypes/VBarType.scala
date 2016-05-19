package xbar.projection.barTypes

import xbar.modifier.{Child, Modifier}
import xbar.modifier.Child.{ChildThatIsHead, ChildThatIsBar}
import xbar.modifier.Modifier.{Complement, Adjunct}
import xbar.modifier.abilities.{CanAdjoinV_, CanComplementV}
import xbar.modifier.types.AdjunctType.VAdjunct
import xbar.modifier.types.ComplementType.VComplement
import xbar.projection._


sealed trait VBarType {
  val child: Child
  val modifier: Modifier
}

object VBarType {

  final case class LowerVBar[X : Phrase : CanComplementV](head: V, complement: X) extends VBarType {
    val vComp = implicitly[CanComplementV[X]]
    val child: Child = ChildThatIsHead(head)
    val modifier: Modifier = Complement(VComplement(complement))
  }

  object LowerVBar {
    def apply(head: V): LowerVBar[EmptyPhrase] = LowerVBar(head, EmptyPhrase())
  }

  final case class UpperVBar[X : Phrase : CanAdjoinV_](bar: V_, adjunct: X) extends VBarType {
    val child: Child = ChildThatIsBar(bar)
    val modifier: Modifier = Adjunct(VAdjunct(adjunct))
  }
}