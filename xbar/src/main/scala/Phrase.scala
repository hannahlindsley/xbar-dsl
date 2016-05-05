package dsl.xbar

/**
 * Any phrase
 *
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

  implicit def noModifierNComplement: CanComplementN[EmptyPhrase] = new CanComplementN[EmptyPhrase] {
    override val grammaticalCategory: GrammaticalCategory = EmptyCategory
  }

  implicit def noModifierDSpecifier: DSpecifier[EmptyPhrase] = new DSpecifier[EmptyPhrase] {
    override val grammaticalPosition: GrammaticalPosition = Empty
  }

  implicit def noModifierVComplement: VComplement[EmptyPhrase] = new VComplement[EmptyPhrase] {
    val grammaticalPosition = Empty
  }

  implicit def noModifierUSpecifier: USpecifier[EmptyPhrase] = new USpecifier[EmptyPhrase] {}
  implicit def noModifierUComplement: UComplement[EmptyPhrase] = new UComplement[EmptyPhrase] {}
  implicit def noModifierTSpecifier: TSpecifier[EmptyPhrase] = new TSpecifier[EmptyPhrase] {}
  implicit def noModifierTComplement: TComplement[EmptyPhrase] = new TComplement[EmptyPhrase] {}
  implicit def noModifierRSpecifier: RSpecifier[EmptyPhrase] = new RSpecifier[EmptyPhrase] {}
  implicit def noModifierRComplement: RComplement[EmptyPhrase] = new RComplement[EmptyPhrase] {}
  implicit def noModifierJSpecifier: JSpecifier[EmptyPhrase] = new JSpecifier[EmptyPhrase] {}
  implicit def noModifierJComplement: JComplement[EmptyPhrase] = new JComplement[EmptyPhrase] {}
  implicit def noModifierCSpecifier: CSpecifier[EmptyPhrase] = new CSpecifier[EmptyPhrase] {}
  implicit def noModifierCComplement: CComplement[EmptyPhrase] = new CComplement[EmptyPhrase] {}
  implicit def noModifierNSpecifier: NSpecifier[EmptyPhrase] = new NSpecifier[EmptyPhrase] {}
  implicit def noModifierPSpecifier: PSpecifier[EmptyPhrase] = new PSpecifier[EmptyPhrase] {}
  implicit def noModifierPComplement: CanComplementP[EmptyPhrase] = new CanComplementP[EmptyPhrase] {}
  implicit def noModifierDComplement: DComplement[EmptyPhrase] = new DComplement[EmptyPhrase] {}
  implicit def noModifierVSpecifier: VSpecifier[EmptyPhrase] = new VSpecifier[EmptyPhrase] {}
}

/**
 * Noun Phrase
 *
 * @param bar the noun bar projection of the phrase
 * @param spec the specifier of the noun phrase
 * @tparam X type that implements NSpecifier
 */
final case class NP[X <: Phrase : NSpecifier](bar: N_, spec: X) extends Phrase 
object NP {
  def apply(bar: N_): NP[EmptyPhrase] = NP(bar, EmptyPhrase())
  implicit def npDComplement[X <: Phrase : NSpecifier]: DComplement[NP[X]] = new DComplement[NP[X]] {}
}

/**
 * Prepositional Phrase
 *
 * @param bar the prep bar projection of the phrase
 * @param spec the specifier of the prepositional phrase
 * @tparam X type that implements PSpecifier
 */
final case class PP[X <: Phrase : PSpecifier](bar: P_, spec: X) extends Phrase
object PP {
  def apply(bar: P_): PP[EmptyPhrase] = PP(bar, EmptyPhrase())
  implicit def ppNComplement[X <: Phrase : PSpecifier]: CanComplementN[PP[X]] = new CanComplementN[PP[X]] {
    override val grammaticalCategory: GrammaticalCategory = Prepositional
  }
}

final case class VP[X <: Phrase : VSpecifier](bar: V_, spec: X) extends Phrase
object VP {
  def apply(bar: V_): VP[EmptyPhrase] = VP(bar, EmptyPhrase())
}

final case class CP[X <: Phrase : CSpecifier](bar: C_, spec: X) extends Phrase
object CP {
  def apply(bar: C_): CP[EmptyPhrase] = CP(bar, EmptyPhrase())
}

final case class TP[X <: Phrase : TSpecifier](bar: T_, spec: X) extends Phrase
object TP {
  def apply(bar: T_): TP[EmptyPhrase] = TP(bar, EmptyPhrase())
}

final case class JP[X <: Phrase : JSpecifier](bar: J_, spec: X) extends Phrase
object JP {
  def apply(bar: J_): JP[EmptyPhrase] = JP(bar, EmptyPhrase())
}

final case class RP[X <: Phrase : RSpecifier](bar: R_, spec: X) extends Phrase
object RP {
  def apply(bar: R_): RP[EmptyPhrase] = RP(bar, EmptyPhrase())
}

final case class DP[X <: Phrase : DSpecifier](bar: D_, spec: X) extends Phrase
object DP {

  def apply(bar: D_): DP[EmptyPhrase] = DP(bar, EmptyPhrase())
  def apply[X <: Phrase : DSpecifier](spec: X, bar: D_): DP[X] = DP(bar, spec)

  implicit def dpPComplement[X <: Phrase : DSpecifier]: CanComplementP[DP[X]] = new CanComplementP[DP[X]] {}

  // DPs are the only phrases that can specify another DP, and only then in the case of genitive markings.
  implicit def dpDPSpecifier[X <: Phrase : DSpecifier]: DSpecifier[DP[X]] = new DSpecifier[DP[X]] {
    val grammaticalPosition = GenitivePossessor
  }

  implicit def dpVComplement[X <: Phrase : DSpecifier]: VComplement[DP[X]] = new VComplement[DP[X]] {
    val grammaticalPosition = GrammaticalObject
  }
}

final case class UP[X <: Phrase : USpecifier](bar: U_, spec: X) extends Phrase
object UP {
  def apply(bar: U_): UP[EmptyPhrase] = UP(bar, EmptyPhrase())
}