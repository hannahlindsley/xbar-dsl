package xbar.projection

import xbar.modifier.abilities._

/**
 * Any phrase
 */
sealed trait Phrase {
  val bar: Bar
  val spec: Phrase
}

/**
 * I made NoModifier a case class rather than a case object in order to give it type class instances
 * as needed for modifier types. This is clearly not the right approach.
 *
 * NoModifier is used when a phrase is unmodified.
 */
final case class EmptyPhrase() extends Phrase {
  override val bar: Bar = Empty_
  override val spec: Phrase = EmptyPhrase()
}
object EmptyPhrase {

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
final case class NP[X <: Phrase : CanSpecifyN](bar: N_, spec: X) extends Phrase 
object NP {
  def apply(bar: N_): NP[EmptyPhrase] = NP(bar, EmptyPhrase())
  implicit def npDComplement[X <: Phrase : CanSpecifyN]: CanComplementD[NP[X]] = new CanComplementD[NP[X]] {}
}

/**
 * Prepositional Phrase
 *
 * @param bar the prep bar projection of the phrase
 * @param spec the specifier of the prepositional phrase
 * @tparam X type that implements PSpecifier
 */
final case class PP[X <: Phrase : CanSpecifyP](bar: P_, spec: X) extends Phrase
object PP {
  def apply(bar: P_): PP[EmptyPhrase] = PP(bar, EmptyPhrase())
  implicit def ppNComplement[X <: Phrase : CanSpecifyP]: CanComplementN[PP[X]] = new CanComplementN[PP[X]] {}
}

final case class VP[X <: Phrase : CanSpecifyV](bar: V_, spec: X) extends Phrase
object VP {
  def apply(bar: V_): VP[EmptyPhrase] = VP(bar, EmptyPhrase())
}

final case class CP[X <: Phrase : CanSpecifyC](bar: C_, spec: X) extends Phrase
object CP {
  def apply(bar: C_): CP[EmptyPhrase] = CP(bar, EmptyPhrase())
}

final case class TP[X <: Phrase : CanSpecifyT](bar: T_, spec: X) extends Phrase
object TP {
  def apply(bar: T_): TP[EmptyPhrase] = TP(bar, EmptyPhrase())
}

final case class JP[X <: Phrase : CanSpecifyJ](bar: J_, spec: X) extends Phrase
object JP {
  def apply(bar: J_): JP[EmptyPhrase] = JP(bar, EmptyPhrase())

  implicit def jpCanComplementV[X <: Phrase : CanSpecifyJ]: CanComplementV[JP[X]] = new CanComplementV[JP[X]] {}
}

final case class RP[X <: Phrase : CanSpecifyR](bar: R_, spec: X) extends Phrase
object RP {
  def apply(bar: R_): RP[EmptyPhrase] = RP(bar, EmptyPhrase())
}

final case class DP[X <: Phrase : CanSpecifyD](bar: D_, spec: X) extends Phrase
object DP {

  def apply(bar: D_): DP[EmptyPhrase] = DP(bar, EmptyPhrase())
  def apply[X <: Phrase : CanSpecifyD](spec: X, bar: D_): DP[X] = DP(bar, spec)

  implicit def dpPComplement[X <: Phrase : CanSpecifyD]: CanComplementP[DP[X]] = new CanComplementP[DP[X]] {}

  // DPs are the only phrases that can specify another DP, and only then in the case of genitive markings.
  implicit def dpDPSpecifier[X <: Phrase : CanSpecifyD]: CanSpecifyD[DP[X]] = new CanSpecifyD[DP[X]] {}

  implicit def dpVComplement[X <: Phrase : CanSpecifyD]: CanComplementV[DP[X]] = new CanComplementV[DP[X]] {}
}

final case class UP[X <: Phrase : CanSpecifyU](bar: U_, spec: X) extends Phrase
object UP {
  def apply(bar: U_): UP[EmptyPhrase] = UP(bar, EmptyPhrase())
}