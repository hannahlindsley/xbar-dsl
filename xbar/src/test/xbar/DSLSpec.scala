package xbar

import projection._
import org.scalatest.{FunSpec, Matchers}
import xbar.projection.Tense.Untensed

/**
 * Note that the import of the projection directory is necessary for these tests to be run.
 */
class DSLSpec extends FunSpec with Matchers {

  describe("A verb phrase") {

    it ("should be complementable by DP and JP") {

      val dpComplement = "VP(V_(V(\"eat\"), DP(D_(D(\"the\"), NP(N_(N(\"mouse\")))))))"
      val jpComplement = "VP(V_(V(\"be\"), JP(J_(J(\"happy\")))))"

      dpComplement should compile
      jpComplement should compile
    }
  }

  describe ("A determiner phrase") {

    it ("can only ever be complemented by an NP") {

      val npComplement = "DP(D_(D(\"the\"), NP(N_(N(\"tree\")))))"
      val uncomplemented = "DP(D_(D(\"the\"))))"

      npComplement should compile
      uncomplemented shouldNot compile
    }

    it ("should be specifiable only by a determiner phrase") {

      //TODO: generate random other phrases and ensure that they do not compile
      val dpSpecifier = "DP(DP(D_(D(\"\"), NP(N_(N(\"Hannah\"))))), D_(D(\"'s\"), NP(N_(N(\"cat\")))))"
      dpSpecifier should compile
    }
  }

  describe ("a noun phrase") {

    it ("can be complemented by a PP") {
      val ppComplement = "NP(N_(N(\"hat\"), PP(P_(P(\"with\"), DP(D_(D(\"\"), NP(N_(N(\"feathers\")))))))))"
      ppComplement should compile
    }

    it ("cannot have a specifier") {
      // TODO generate random phrases of differing types to test in here
      val specifiedNP = "NP(NP(N_(N(\"spec\"))), N_(N(\"this\")))"
      specifiedNP shouldNot compile
    }

    it ("can be adjoined by a UP") {

    }
  }

  describe ("an adjective phrase") {

    it ("can be adjoined on the lefthand side by an adverb phrase, but not the right") {

      val leftRPAdjunct = "JP(J_(RP(R_(R(\"very\"))), J_(J(\"happy\"))))"
      val rightRPAdjunct = "JP(J_(J_(J(\"happy\"))), RP(R_(R(\"very\"))))"

      leftRPAdjunct should compile
      rightRPAdjunct shouldNot compile
    }

    it ("can be complemented by a complementizer phrase") {

      // example: "I'm happy for her to do it"
      val cpComplement = "JP(J_(J(\"happy\"), CP(C_(C(\"for\"), TP(T_(T(Untensed), VP(DP(D_(D(\"\"), " +
        "NP(N_(N(\"her\"))))), V_(V(\"to do\"), DP(D_(D(\"\"), NP(N_(N(\"it\")))))))))))))"

      cpComplement should compile
    }

    it ("cannot be complemented by a verb phrase") {

      val vpComplement = "JP(J_(J(\"happy\"), VP(V_(V(\"to go\")))))"

      vpComplement shouldNot compile
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
      "TP(T_(T(Untensed), VP(V_(V(\"kick\")))))" should compile
    }

  }

}
