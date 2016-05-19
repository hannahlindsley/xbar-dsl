package xbar.projection

import xbar.modifier.abilities._


/**
 * Implementing the Phrase type class allows you to define a projection and specifier of a particular class
 * @tparam X a type that can be phrased with a projection and optional specifier
 */
trait Phrase[X] {
  
  type S
  type B
  
  def phrase(bar: B, spec: Option[S]): X
}

object Phrase {

//  implicit def emptyCanComplementV[X : Phrase]: CanComplementV[Phrase[X]] = new CanComplementV[Phrase[X]] {}
//  implicit def emptyCanComplementN[X : Phrase]: CanComplementN[Phrase[X]] = new CanComplementN[Phrase[X]] {}
//  implicit def emptyCanComplementJ[X : Phrase]: CanComplementJ[Phrase[X]] = new CanComplementJ[Phrase[X]] {}
//  implicit def emptyCanComplementR[X : Phrase]: CanComplementR[Phrase[X]] = new CanComplementR[Phrase[X]] {}
//  implicit def emptyCanSpecifyV[X : Phrase]: CanSpecifyV[Phrase[X]] = new CanSpecifyV[Phrase[X]] {}
//  implicit def emptyCanSpecifyN[X : Phrase]: CanSpecifyN[Phrase[X]] = new CanSpecifyN[Phrase[X]] {}
//  implicit def emptyCanSpecifyD[X : Phrase]: CanSpecifyD[Phrase[X]] = new CanSpecifyD[Phrase[X]] {}
//  implicit def emptyCanSpecifyP[X : Phrase]: CanSpecifyP[Phrase[X]] = new CanSpecifyP[Phrase[X]] {}
//  implicit def emptyCanSpecifyU[X]: CanSpecifyU[Phrase[X]] = new CanSpecifyU[Phrase[X]] {}
//  implicit def emptyCanSpecifyR[X]: CanSpecifyR[Phrase[X]] = new CanSpecifyR[Phrase[X]] {}
//  implicit def emptyCanSpecifyJ[X]: CanSpecifyJ[Phrase[X]] = new CanSpecifyJ[Phrase[X]] {}
//  implicit def emptyCanSpecifyT[X]: CanSpecifyT[Phrase[X]] = new CanSpecifyT[Phrase[X]] {}
//  implicit def emptyCanSpecifyC[X]: CanSpecifyC[Phrase[X]] = new CanSpecifyC[Phrase[X]] {}
}

/**
 * Used for type class implementation to identify which modifiers can be empty.
 * If a particular modifier cannot be empty (Complement of Determiner, for example), there will
 * be no implicit object for it for EmptyPhrase.
 */
trait EmptyPhrase

object EmptyPhrase {

  def apply(): EmptyPhrase = new EmptyPhrase {}

  implicit def phraseNoPhrase: Phrase[EmptyPhrase] = new Phrase[EmptyPhrase] {

    override type S = EmptyPhrase
    override type B = EmptyPhrase

    override def phrase(bar: B, spec: Option[S]) = new EmptyPhrase {}
  }

  implicit def noModifierNComplement: CanComplementN[EmptyPhrase] = new CanComplementN[EmptyPhrase] {}
  implicit def noModifierDSpecifier: CanSpecifyD[EmptyPhrase] = new CanSpecifyD[EmptyPhrase] {}
  implicit def noModifierVComplement: CanComplementV[EmptyPhrase] = new CanComplementV[EmptyPhrase] {}
  implicit def noModifierUSpecifier: CanSpecifyU[EmptyPhrase] = new CanSpecifyU[EmptyPhrase] {}
  implicit def noModifierUComplement: CanComplementU[EmptyPhrase] = new CanComplementU[EmptyPhrase] {}
  implicit def noModifierTSpecifier: CanSpecifyT[EmptyPhrase] = new CanSpecifyT[EmptyPhrase] {}
  implicit def noModifierTComplement: CanComplementT[EmptyPhrase] = new CanComplementT[EmptyPhrase] {}
  implicit def noModifierRSpecifier: CanSpecifyR[EmptyPhrase] = new CanSpecifyR[EmptyPhrase] {}
  implicit def noModifierRComplement: CanComplementR[EmptyPhrase] = new CanComplementR[EmptyPhrase] {}
  implicit def noModifierJSpecifier: CanSpecifyJ[EmptyPhrase] = new CanSpecifyJ[EmptyPhrase] {}
  implicit def noModifierJComplement: CanComplementJ[EmptyPhrase] = new CanComplementJ[EmptyPhrase] {}
  implicit def noModifierCSpecifier: CanSpecifyC[EmptyPhrase] = new CanSpecifyC[EmptyPhrase] {}
  implicit def noModifierCComplement: CanComplementC[EmptyPhrase] = new CanComplementC[EmptyPhrase] {}
  implicit def noModifierCanSpecifyN: CanSpecifyN[EmptyPhrase] = new CanSpecifyN[EmptyPhrase] {}
  implicit def noModifierPSpecifier: CanSpecifyP[EmptyPhrase] = new CanSpecifyP[EmptyPhrase] {}
  implicit def noModifierPComplement: CanComplementP[EmptyPhrase] = new CanComplementP[EmptyPhrase] {}
  implicit def noModifierDComplement: CanComplementD[EmptyPhrase] = new CanComplementD[EmptyPhrase] {}
  implicit def noModifierVSpecifier: CanSpecifyV[EmptyPhrase] = new CanSpecifyV[EmptyPhrase] {}

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

  def apply(bar: N_): NP[EmptyPhrase] = NP(bar, Option.empty[EmptyPhrase])

  implicit def npDComplement[X : Phrase : CanSpecifyN]: CanComplementD[NP[X]] = new CanComplementD[NP[X]] {}

  implicit def phraseNP[X : Phrase : CanSpecifyN]: Phrase[NP[X]] = new Phrase[NP[X]] {
    override type S = X
    override type B = N_
    override def phrase(bar: N_, spec: Option[X]): NP[X] = NP(bar, spec)
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

  def apply(bar: P_): PP[EmptyPhrase] = PP(bar, Option.empty[EmptyPhrase])

  implicit def phrasePP[X : Phrase : CanSpecifyP]: Phrase[PP[X]] = new Phrase[PP[X]] {

    override type S = X

    override type B = P_

    override def phrase(bar: P_, spec: Option[X]) = PP(bar, spec)

  }

  implicit def ppNComplement[X : Phrase : CanSpecifyP]: CanComplementN[PP[X]] = new CanComplementN[PP[X]] {}

}

final case class VP[X : Phrase : CanSpecifyV](bar: V_, spec: Option[X])

object VP {

  def apply(bar: V_): VP[EmptyPhrase] = VP(bar, Option.empty[EmptyPhrase])

  def apply[X : Phrase : CanSpecifyV](spec: X, bar: V_): VP[X] = VP(bar, Some(spec))

  implicit def phraseVP[X : Phrase : CanSpecifyV]: Phrase[VP[X]] = new Phrase[VP[X]] {

    override type S = X

    override type B = V_

    override def phrase(bar: V_, spec: Option[X]) = VP(bar, spec)

  }

  implicit def vpCanComplementT[X : Phrase : CanSpecifyV]: CanComplementT[VP[X]] = new CanComplementT[VP[X]] {}

}

final case class CP[X : Phrase : CanSpecifyC](bar: C_, spec: Option[X])

object CP {

  def apply(bar: C_): CP[EmptyPhrase] = CP(bar, Option.empty[EmptyPhrase])

  implicit def phraseCP[X : Phrase : CanSpecifyC]: Phrase[CP[X]] = new Phrase[CP[X]] {

    override type S = X

    override type B = C_

    override def phrase(bar: C_, spec: Option[X]) = CP(bar, spec)

  }

  implicit def cpCanComplementJ[X : Phrase : CanSpecifyC]: CanComplementJ[CP[X]] = new CanComplementJ[CP[X]] {}

}

final case class TP[X : Phrase : CanSpecifyT](bar: T_, spec: Option[X])

object TP {

  def apply(bar: T_): TP[EmptyPhrase] = TP(bar, Option.empty[EmptyPhrase])

  implicit def phraseTP[X : Phrase : CanSpecifyT]: Phrase[TP[X]] = new Phrase[TP[X]] {

    override type S = X

    override type B = T_

    override def phrase(bar: T_, spec: Option[X]) = TP(bar, spec)

  }

  implicit def tpCanComplementC[X : Phrase : CanSpecifyT]: CanComplementC[TP[X]] = new CanComplementC[TP[X]] {}

}

final case class JP(bar: J_)

object JP {

  implicit def phraseJP: Phrase[JP] = new Phrase[JP] {

    override type S = EmptyPhrase

    override type B = J_

    override def phrase(bar: J_, spec: Option[EmptyPhrase]) = JP(bar)

  }

  implicit def jpCanComplementV: CanComplementV[JP] = new CanComplementV[JP] {}

}

final case class RP[X : Phrase : CanSpecifyR](bar: R_, spec: Option[X])

object RP {

  def apply(bar: R_): RP[EmptyPhrase] = RP(bar, Option.empty[EmptyPhrase])

  implicit def phraseRP[X : Phrase : CanSpecifyR]: Phrase[RP[X]] = new Phrase[RP[X]] {

    override type S = X

    override type B = R_

    override def phrase(bar: R_, spec: Option[X]) = RP(bar, spec)

  }

  implicit def rpCanAdjoinJP[X : Phrase : CanSpecifyR]: CanAdjoinJ_[RP[X]] = new CanAdjoinJ_[RP[X]] {}

  implicit def rpCanAdjoinVP[X : Phrase : CanSpecifyR]: CanAdjoinV_[RP[X]] = new CanAdjoinV_[RP[X]] {}

}

final case class DP[X : Phrase : CanSpecifyD](bar: D_, spec: Option[X])

object DP {

  def apply(bar: D_): DP[EmptyPhrase] = DP(bar, Option.empty[EmptyPhrase])

  def apply[X : Phrase : CanSpecifyD](spec: X, bar: D_): DP[X] = DP(bar, Some(spec))
  
  implicit def phraseDP[X : Phrase : CanSpecifyD]: Phrase[DP[X]] = new Phrase[DP[X]] {

    override type S = X
    
    override type B = D_
    
    override def phrase(bar: D_, spec: Option[X]) = DP(bar, spec)
    
  }

  implicit def dpPComplement[X : Phrase : CanSpecifyD]: CanComplementP[DP[X]] = new CanComplementP[DP[X]] {}

  // DPs are the only phrases that can specify another DP, and only then in the case of genitive markings.
  implicit def dpDPSpecifier[X : Phrase : CanSpecifyD]: CanSpecifyD[DP[X]] = new CanSpecifyD[DP[X]] {}

  implicit def dpVComplement[X : Phrase : CanSpecifyD]: CanComplementV[DP[X]] = new CanComplementV[DP[X]] {}

  implicit def dpCanSpecifyV[X : Phrase : CanSpecifyD]: CanSpecifyV[DP[X]] = new CanSpecifyV[DP[X]] {}

}

final case class UP[X : Phrase : CanSpecifyU](bar: U_, spec: Option[X])

object UP {

  def apply(bar: U_): UP[EmptyPhrase] = UP(bar, Option.empty[EmptyPhrase])

  implicit def phraseUP[X : Phrase : CanSpecifyU]: Phrase[UP[X]] = new Phrase[UP[X]] {

    override type S = X

    override type B = U_

    override def phrase(bar: U_, spec: Option[X]) = UP(bar, spec)

  }

}