package xbar

import projection._
import org.scalatest.{FunSpec, Matchers}

/**
 * Note that the import of the projection directory is necessary for these tests to be run.
 */
class DSLSpec extends FunSpec with Matchers {

  describe("A verb phrase") {

    it ("should be complementable by DP and JP") {
      "VP(V_(V(\"eat\"), DP(D_(D(\"the\"), NP(N_(N(\"mouse\")))))))" should compile
      "VP(V_(V(\"be\"), JP(J_(J(\"happy\")))))" should compile
    }
  }

  describe ("A determiner phrase") {

    it ("can be complemented by an NP") {
      "DP(D_(D(\"the\"), NP(N_(N(\"tree\")))))" should compile
    }

    it ("should not (ever) be uncomplemented") {
      "DP(D_(D(\"the\"))))" shouldNot compile
    }

    it ("should be specifiable only by a determiner phrase") {
      "DP(DP(D_(D(\"\"), NP(N_(N(\"Hannah\"))))), D_(D(\"'s\"), NP(N_(N(\"cat\")))))" should compile
    }
  }

  describe ("a noun phrase") {

    it ("can be complemented by a PP") {

    }

    it ("can be specified by nothing") {

    }

    it ("can be adjoined by a UP") {

    }
  }

  describe ("an adjective phrase") {

  }

  describe ("an adverb phrase") {}

  describe ("a prepositional phrase") {

    it ("must be complemented") {

    }

    it ("can be complemented by DP") {

    }
  }

  describe ("a union phrase") {

  }

  describe ("a complementizer phrase") {

  }

  describe ("a tense phrase") {

    it ("must be complemented") {}

    it ("can only be complemented by a verb phrase") {}
  }

}
