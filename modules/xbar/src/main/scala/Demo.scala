package dsl.xbar

object Demo {
  val tree: N = N("tree")
  val treeNP = NP(N_(tree))
  val pp = PP(P_(P("of"), DP(D_(D(), NP(N_(N("life")))))))
  val npRightSpec = NP(N_(tree, pp))
  val possessive = DP(DP(D_(D("the"), treeNP)), D_(D("'s"), treeNP))
}