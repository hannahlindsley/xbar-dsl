package xbar.projection

import xbar.modifier.Modifier.Specifier
import xbar.modifier.abilities._

/**
 * Any phrase
 */
trait Phrase[X] {
  type S
  type B
  def make(bar: B, spec: Option[S]): X
}

object Phrase {

  implicit def emptyCanComplementV[X]: CanComplementV[Phrase[X]] = new CanComplementV[Phrase[X]] {}
  implicit def emptyCanComplementN[X]: CanComplementN[Phrase[X]] = new CanComplementN[Phrase[X]] {}
  implicit def emptyCanComplementJ[X]: CanComplementJ[Phrase[X]] = new CanComplementJ[Phrase[X]] {}
  implicit def emptyCanComplementR[X]: CanComplementR[Phrase[X]] = new CanComplementR[Phrase[X]] {}
  implicit def emptyCanSpecifyV[X]: CanSpecifyV[Phrase[X]] = new CanSpecifyV[Phrase[X]] {}
  implicit def emptyCanSpecifyN[X]: CanSpecifyN[Phrase[X]] = new CanSpecifyN[Phrase[X]] {}
  implicit def emptyCanSpecifyD[X]: CanSpecifyD[Phrase[X]] = new CanSpecifyD[Phrase[X]] {}
  implicit def emptyCanSpecifyP[X]: CanSpecifyP[Phrase[X]] = new CanSpecifyP[Phrase[X]] {}
  implicit def emptyCanSpecifyU[X]: CanSpecifyU[Phrase[X]] = new CanSpecifyU[Phrase[X]] {}
  implicit def emptyCanSpecifyR[X]: CanSpecifyR[Phrase[X]] = new CanSpecifyR[Phrase[X]] {}
  implicit def emptyCanSpecifyJ[X]: CanSpecifyJ[Phrase[X]] = new CanSpecifyJ[Phrase[X]] {}
  implicit def emptyCanSpecifyT[X]: CanSpecifyT[Phrase[X]] = new CanSpecifyT[Phrase[X]] {}
  implicit def emptyCanSpecifyC[X]: CanSpecifyC[Phrase[X]] = new CanSpecifyC[Phrase[X]] {}
}

trait NoPhrase
object NoPhrase {

  implicit def phraseNoPhrase: Phrase[NoPhrase] = new Phrase[NoPhrase] {

    override type S = NoPhrase
    override type B = NoPhrase

    override def make(bar: B, spec: Option[S]) = new NoPhrase {}
  }

  implicit def noModifierNComplement: CanComplementN[NoPhrase] = new CanComplementN[NoPhrase] {}
  implicit def noModifierDSpecifier: CanSpecifyD[NoPhrase] = new CanSpecifyD[NoPhrase] {}
  implicit def noModifierVComplement: CanComplementV[NoPhrase] = new CanComplementV[NoPhrase] {}
  implicit def noModifierUSpecifier: CanSpecifyU[NoPhrase] = new CanSpecifyU[NoPhrase] {}
  implicit def noModifierUComplement: CanComplementU[NoPhrase] = new CanComplementU[NoPhrase] {}
  implicit def noModifierTSpecifier: CanSpecifyT[NoPhrase] = new CanSpecifyT[NoPhrase] {}
  implicit def noModifierTComplement: CanComplementT[NoPhrase] = new CanComplementT[NoPhrase] {}
  implicit def noModifierRSpecifier: CanSpecifyR[NoPhrase] = new CanSpecifyR[NoPhrase] {}
  implicit def noModifierRComplement: CanComplementR[NoPhrase] = new CanComplementR[NoPhrase] {}
  implicit def noModifierJSpecifier: CanSpecifyJ[NoPhrase] = new CanSpecifyJ[NoPhrase] {}
  implicit def noModifierJComplement: CanComplementJ[NoPhrase] = new CanComplementJ[NoPhrase] {}
  implicit def noModifierCSpecifier: CanSpecifyC[NoPhrase] = new CanSpecifyC[NoPhrase] {}
  implicit def noModifierCComplement: CanComplementC[NoPhrase] = new CanComplementC[NoPhrase] {}
  implicit def noModifierCanSpecifyN: CanSpecifyN[NoPhrase] = new CanSpecifyN[NoPhrase] {}
  implicit def noModifierPSpecifier: CanSpecifyP[NoPhrase] = new CanSpecifyP[NoPhrase] {}
  implicit def noModifierPComplement: CanComplementP[NoPhrase] = new CanComplementP[NoPhrase] {}
  implicit def noModifierDComplement: CanComplementD[NoPhrase] = new CanComplementD[NoPhrase] {}
  implicit def noModifierVSpecifier: CanSpecifyV[NoPhrase] = new CanSpecifyV[NoPhrase] {}

}

/**
 * Noun Phrase
 *
 * @param bar the noun bar projection of the phrase
 * @param spec the specifier of the noun phrase
 * @tparam X type that implements CanSpecifyN
 */
final case class NP[X : Phrase : CanSpecifyN](bar: N_, spec: Option[X])

object NP {

  def apply(bar: N_): NP[NoPhrase] = NP(bar, Option.empty[NoPhrase])

  implicit def npDComplement[X : Phrase : CanSpecifyN]: CanComplementD[NP[X]] = new CanComplementD[NP[X]] {}

  implicit def phraseNP[X : Phrase : CanSpecifyN]: Phrase[NP[X]] = new Phrase[NP[X]] {
    override type S = X
    override type B = N_
    override def make(bar: N_, spec: Option[X]): NP[X] = NP(bar, spec)
  }

}

/**
 * Prepositional Phrase
 *
 * @param bar the prep bar projection of the phrase
 * @param spec the specifier of the prepositional phrase
 * @tparam X type that implements PSpecifier
 */
final case class PP[X : Phrase : CanSpecifyP](bar: P_, spec: Option[X])

object PP {

  def apply(bar: P_): PP[NoPhrase] = PP(bar, Option.empty[NoPhrase])

  implicit def phrasePP[X : Phrase : CanSpecifyP]: Phrase[PP[X]] = new Phrase[PP[X]] {

    override type S = X

    override type B = P_

    override def make(bar: P_, spec: Option[X]) = PP(bar, spec)

  }

  implicit def ppNComplement[X : Phrase : CanSpecifyP]: CanComplementN[PP[X]] = new CanComplementN[PP[X]] {}

}

final case class VP[X : Phrase : CanSpecifyV](bar: V_, spec: Option[X])

object VP {

  def apply(bar: V_): VP[NoPhrase] = VP(bar, Option.empty[NoPhrase])

  def apply[X : Phrase : CanSpecifyV](spec: X, bar: V_): VP[X] = VP(bar, Some(spec))

  implicit def phraseVP[X : Phrase : CanSpecifyV]: Phrase[VP[X]] = new Phrase[VP[X]] {

    override type S = X

    override type B = V_

    override def make(bar: V_, spec: Option[X]) = VP(bar, spec)

  }

  implicit def vpCanComplementT[X : Phrase : CanSpecifyV]: CanComplementT[VP[X]] = new CanComplementT[VP[X]] {}

  implicit def vpCanComplementJ[X : Phrase : CanSpecifyV]: CanComplementJ[VP[X]] = new CanComplementJ[VP[X]] {}

}

final case class CP[X : Phrase : CanSpecifyC](bar: C_, spec: Option[X])

object CP {

  def apply(bar: C_): CP[NoPhrase] = CP(bar, Option.empty[NoPhrase])

  implicit def phraseCP[X : Phrase : CanSpecifyC]: Phrase[CP[X]] = new Phrase[CP[X]] {

    override type S = X

    override type B = C_

    override def make(bar: C_, spec: Option[X]) = CP(bar, spec)

  }

  implicit def cpCanComplementJ[X : Phrase : CanSpecifyC]: CanComplementJ[CP[X]] = new CanComplementJ[CP[X]] {}

}

final case class TP[X : Phrase : CanSpecifyT](bar: T_, spec: Option[X])

object TP {

  def apply(bar: T_): TP[NoPhrase] = TP(bar, Option.empty[NoPhrase])

  implicit def phraseTP[X : Phrase : CanSpecifyT]: Phrase[TP[X]] = new Phrase[TP[X]] {

    override type S = X

    override type B = T_

    override def make(bar: T_, spec: Option[X]) = TP(bar, spec)

  }

  implicit def tpCanComplementC[X : Phrase : CanSpecifyT]: CanComplementC[TP[X]] = new CanComplementC[TP[X]] {}

}

final case class JP(bar: J_)

object JP {

  implicit def phraseJP: Phrase[JP] = new Phrase[JP] {

    override type S = NoPhrase

    override type B = J_

    override def make(bar: J_, spec: Option[NoPhrase] = None) = JP(bar)

  }

  implicit def jpCanComplementV: CanComplementV[JP] = new CanComplementV[JP] {}

}

final case class RP[X : Phrase : CanSpecifyR](bar: R_, spec: Option[X])

object RP {

  def apply(bar: R_): RP[NoPhrase] = RP(bar, Option.empty[NoPhrase])

  implicit def phraseRP[X : Phrase : CanSpecifyR]: Phrase[RP[X]] = new Phrase[RP[X]] {

    override type S = X

    override type B = R_

    override def make(bar: R_, spec: Option[X]) = RP(bar, spec)

  }

  implicit def rpCanAdjoinJP[X : Phrase : CanSpecifyR]: CanAdjoinJ_[RP[X]] = new CanAdjoinJ_[RP[X]] {}

  implicit def rpCanAdjoinVP[X : Phrase : CanSpecifyR]: CanAdjoinV_[RP[X]] = new CanAdjoinV_[RP[X]] {}

}

final case class DP[X : Phrase : CanSpecifyD](bar: D_, spec: Option[X])

object DP {

  def apply(bar: D_): DP[NoPhrase] = DP(bar, Option.empty[NoPhrase])

  def apply[X : Phrase : CanSpecifyD](spec: X, bar: D_): DP[X] = DP(bar, Some(spec))
  
  implicit def phraseDP[X : Phrase : CanSpecifyD]: Phrase[DP[X]] = new Phrase[DP[X]] {

    override type S = X
    
    override type B = D_
    
    override def make(bar: D_, spec: Option[X]) = DP(bar, spec)
    
  }

  implicit def dpPComplement[X : Phrase : CanSpecifyD]: CanComplementP[DP[X]] = new CanComplementP[DP[X]] {}

  // DPs are the only phrases that can specify another DP, and only then in the case of genitive markings.
  implicit def dpDPSpecifier[X : Phrase : CanSpecifyD]: CanSpecifyD[DP[X]] = new CanSpecifyD[DP[X]] {}

  implicit def dpVComplement[X : Phrase : CanSpecifyD]: CanComplementV[DP[X]] = new CanComplementV[DP[X]] {}

  implicit def dpCanSpecifyV[X : Phrase : CanSpecifyD]: CanSpecifyV[DP[X]] = new CanSpecifyV[DP[X]] {}

}

final case class UP[X : Phrase : CanSpecifyU](bar: U_, spec: Option[X])

object UP {

  def apply(bar: U_): UP[NoPhrase] = UP(bar, Option.empty[NoPhrase])

  implicit def phraseUP[X : Phrase : CanSpecifyU]: Phrase[UP[X]] = new Phrase[UP[X]] {

    override type S = X

    override type B = U_

    override def make(bar: U_, spec: Option[X]) = UP(bar, spec)

  }

}