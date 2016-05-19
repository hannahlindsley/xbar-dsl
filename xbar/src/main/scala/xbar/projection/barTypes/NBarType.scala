package xbar.projection.barTypes

import xbar.modifier.{Child, Modifier}
import xbar.modifier.Child.{ChildThatIsHead, ChildThatIsBar}
import xbar.modifier.Modifier.{Complement, Adjunct}
import xbar.modifier.abilities.{CanAdjoinN_, CanComplementN}
import xbar.modifier.types.AdjunctType.NAdjunct
import xbar.modifier.types.ComplementType.NComplement
import xbar.projection.{Phrase, N, N_}


sealed trait NBarType {
  val child: Child
  val modifier: Modifier
}

object NBarType {

  final case class LowerNBar[X : Phrase : CanComplementN](head: N, complement: X) extends NBarType {
    val child: Child = ChildThatIsHead(head)
    val modifier: Modifier = Complement(NComplement(complement))
  }

  final case class UpperNBar[X : Phrase : CanAdjoinN_](bar: N_, adjunct: X) extends NBarType {
    val child: Child = ChildThatIsBar(bar)
    val modifier: Modifier = Adjunct(NAdjunct(adjunct))
  }

}