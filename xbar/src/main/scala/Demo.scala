package dsl.xbar

import scala.annotation.tailrec

object Demo {
  val tree: N = N("tree")
  val treeNP = NP(N_(tree))
  val pp = PP(P_(P("of"), DP(D_(D(), NP(N_(N("life")))))))
  val npRightSpec = NP(N_(tree, pp))
  private val theTree = DP(D_(D("the"), treeNP))
  val possessive = DP(theTree, D_(D("'s"), treeNP))
  val vp = VP(V_(V("die")))
  val vp2 = VP(V_(V("help"), theTree))
}

object Demo2 {

  @tailrec
  def findDirectObject(vbar: V_): Option[DirectObject[Phrase]] = vbar.v.v match {
    case UpperVBar(bar, adjunct) => findDirectObject(bar)
    case LowerVBar(head, complement) => complement match {
      case dp@DP(bar, spec) => Some(DirectObject(dp))
      case _ => None
    }
  }
}