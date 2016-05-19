package xbar

import projection._
import org.scalatest.{FunSpec, Matchers}
import xbar.projection.Tense.Infinitive

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

    it ("can only ever be complemented by an NP") {

      "DP(D_(D(\"the\"), NP(N_(N(\"tree\")))))" should compile
      "DP(D_(D(\"the\"))))" shouldNot compile
    }

    it ("should be specifiable only by a genitive determiner phrase") {

      //TODO: generate random other phrases and ensure that they do not compile
      "DP(DP(D_(D(\"\"), NP(N_(N(\"Hannah\"))))), D_(D(\"'s\"), NP(N_(N(\"cat\")))))" should compile
      "DP(DP(D_(D(\"the\"), NP(N_(N(\"cat\"))))), D_(D(\"the\"), NP(N_(N(\"dog\")))))" shouldNot compile
    }
  }

  describe ("a noun phrase") {

    it ("can be complemented by a PP") {
      "NP(N_(N(\"hat\"), PP(P_(P(\"with\"), DP(D_(D(\"\"), NP(N_(N(\"feathers\")))))))))" should compile
    }

    it ("cannot have a specifier") {
      // TODO generate random phrases of differing types to test in here
      "NP(NP(N_(N(\"spec\"))), N_(N(\"this\")))" shouldNot compile
    }

    it ("can be adjoined by a UP") {

    }
  }

  describe ("an adjective phrase") {

    it ("can be adjoined on the lefthand side by an adverb phrase, but not the right") {

      "JP(J_(RP(R_(R(\"very\"))), J_(J(\"happy\"))))" should compile
      "JP(J_(J_(J(\"happy\"))), RP(R_(R(\"very\"))))" shouldNot compile
    }

    it ("can be complemented by a complementizer phrase") {

      // example: "I'm happy for her to do it"
      "JP(J_(J(\"happy\"), CP(C_(C(\"for\"), TP(T_(T(Infinitive), VP(DP(D_(D(\"\"), " +
        "NP(N_(N(\"her\"))))), V_(V(\"to do\"), DP(D_(D(\"\"), NP(N_(N(\"it\")))))))))))))" should compile
    }

    it ("cannot be complemented by a verb phrase") {

      "JP(J_(J(\"happy\"), VP(V_(V(\"to go\")))))" shouldNot compile
    }
  }

  describe ("an adverb phrase") {

  }

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

    it ("must be complemented, and only be a VP") {
      "TP(T_(T(Infinitive), VP(V_(V(\"kick\")))))" should compile
    }

  }

}
