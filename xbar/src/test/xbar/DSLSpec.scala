package xbar

import xbar.projection._

import org.scalatest.{FunSpec, Matchers}

/**
 * There is no way to "run" this test suite; compilation of the code
 * (and compilation errors on the commented out code) signifies that
 * the tests have passed.
 */
class DSLSpec extends FunSpec with Matchers {

  describe("A verb phrase") {

    it ("should be complementable by DP and JP") {
      VP(V_(V("eat"),  DP(D_(D("the"), NP(N_(N("mouse")))))))
      VP(V_(V("be"), JP(J_(J("happy")))))
    }
  }

  describe ("A determiner phrase") {

    it ("can be complemented by an NP") {
      DP(D_(D("the"), NP(N_(N("tree")))))
    }

    it ("should not (ever) be uncomplemented") {
//      DP(D_(D("the"))))
    }

    it ("should be specifiable only by a determiner phrase") {
      DP(DP(D_(D(""), NP(N_(N("Hannah"))))), D_(D("'s"), NP(N_(N("cat")))))
    }
  }

  describe ("a noun phrase") {}

  describe ("an adjective phrase") {}

  describe ("an adverb phrase") {}

  describe ("a prepositional phrase") {}

  describe ("a union phrase") {}

  describe ("a complementizer phrase") {}

  describe ("a tense phrase") {}

}
