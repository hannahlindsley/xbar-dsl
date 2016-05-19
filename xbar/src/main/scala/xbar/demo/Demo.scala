package xbar.demo

import xbar.projection._


/**
 * A space to play around with compilation of sentences.
 */
object Demo {

  // "tree"
  private val tree: N = N("tree")

  // "tree"
  private val treeNP = NP(N_(tree))

  // "the tree"
  private val theTree = DP(D_(D("the"), treeNP))

  // "of life"
  private val pp = PP(P_(P("of"), DP(D_(D(), NP(N_(N("life")))))))

  // "tree of life"
  private val npRightSpec = NP(N_(tree, pp))

  // "the tree of life
  private val possessive = DP(theTree, D_(D("'s"), treeNP))

  // "hug the tree"
  private val complementedVP = VP(V_(V("hug"), theTree))
}
