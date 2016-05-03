package dsl.xbar

sealed trait Phrase[A, B] {
  val bar: Bar[A]
  val spec: B
}

final case class NoModifier()
object NoModifier {
  implicit def noModifierNSpecifier: NSpecifier[NoModifier] = new NSpecifier[NoModifier] {}
  implicit def noModifierNComplement: NComplement[NoModifier] = new NComplement[NoModifier] {}
  implicit def noModifierPSpecifier: PSpecifier[NoModifier] = new PSpecifier[NoModifier] {}
  implicit def noModifierPComplement: PComplement[NoModifier] = new PComplement[NoModifier] {}
  implicit def noModifierDSpecifier: DSpecifier[NoModifier] = new DSpecifier[NoModifier] {}
  implicit def noModifierDComplement: DComplement[NoModifier] = new DComplement[NoModifier] {}
  implicit def noModifierVSpecifier: VSpecifier[NoModifier] = new VSpecifier[NoModifier] {}
  implicit def noModifierVComplement: VComplement[NoModifier] = new VComplement[NoModifier] {}
  implicit def noModifierUSpecifier: USpecifier[NoModifier] = new USpecifier[NoModifier] {}
  implicit def noModifierUComplement: UComplement[NoModifier] = new UComplement[NoModifier] {}
  implicit def noModifierTSpecifier: TSpecifier[NoModifier] = new TSpecifier[NoModifier] {}
  implicit def noModifierTComplement: TComplement[NoModifier] = new TComplement[NoModifier] {}
  implicit def noModifierRSpecifier: RSpecifier[NoModifier] = new RSpecifier[NoModifier] {}
  implicit def noModifierRComplement: RComplement[NoModifier] = new RComplement[NoModifier] {}
  implicit def noModifierJSpecifier: JSpecifier[NoModifier] = new JSpecifier[NoModifier] {}
  implicit def noModifierJComplement: JComplement[NoModifier] = new JComplement[NoModifier] {}
  implicit def noModifierCSpecifier: CSpecifier[NoModifier] = new CSpecifier[NoModifier] {}
  implicit def noModifierCComplement: CComplement[NoModifier] = new CComplement[NoModifier] {}
}

final case class NP[X : NSpecifier](bar: N_, spec: X) extends Phrase[N, X]
object NP {
  def apply(bar: N_): NP[NoModifier] = NP(bar, NoModifier())
  implicit def npDComplement[X : NSpecifier]: DComplement[NP[X]] = new DComplement[NP[X]] {}
}

final case class PP[X : PSpecifier](bar: P_, spec: X) extends Phrase[P, X]
object PP {
  def apply(bar: P_): PP[NoModifier] = PP(bar, NoModifier())
  implicit def ppNComplement[X : PSpecifier]: NComplement[PP[X]] = new NComplement[PP[X]] {}
}

final case class VP[X : VSpecifier](bar: V_, spec: X) extends Phrase[V, X]
object VP {
  def apply(bar: V_): VP[NoModifier] = VP(bar, NoModifier())
}

final case class CP[X : CSpecifier](bar: C_, spec: X) extends Phrase[C, X]
object CP {
  def apply(bar: C_): CP[NoModifier] = CP(bar, NoModifier())
}

final case class TP[X : TSpecifier](bar: T_, spec: X) extends Phrase[T, X]
object TP {
  def apply(bar: T_): TP[NoModifier] = TP(bar, NoModifier())
}

final case class JP[X : JSpecifier](bar: J_, spec: X) extends Phrase[J, X]
object JP {
  def apply(bar: J_): JP[NoModifier] = JP(bar, NoModifier())
}

final case class RP[X : RSpecifier](bar: R_, spec: X) extends Phrase[R, X]
object RP {
  def apply(bar: R_): RP[NoModifier] = RP(bar, NoModifier())
}

final case class DP[X : DSpecifier](bar: D_, spec: X) extends Phrase[D, X]
object DP {
  def apply(bar: D_): DP[NoModifier] = DP(bar, NoModifier())
  def apply[X : DSpecifier](spec: X, bar: D_): DP[X] = DP(bar, spec)
  implicit def dpPComplement[X : DSpecifier]: PComplement[DP[X]] = new PComplement[DP[X]] {}

  // DPs are the only phrases that can specify another DP, and only then in the case of genitive markings.
  implicit def dpDPSpecifier[X : DSpecifier]: DSpecifier[DP[X]] = new DSpecifier[DP[X]] {}
}

final case class UP[X : USpecifier](bar: U_, spec: X) extends Phrase[U, X]
object UP {
  def apply(bar: U_): UP[NoModifier] = UP(bar, NoModifier())
}

final case class Possessor[X : DSpecifier](dp: DP[X])