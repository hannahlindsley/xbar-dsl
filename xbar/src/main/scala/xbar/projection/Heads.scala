package xbar.projection

/**
 * Part of speech ADT
 */
sealed trait Head

/**
 * Noun class
 * @param v the word
 */
final case class N(v: String) extends Head

/**
 * Verb class
 * @param v the word
 */
final case class V(v: String) extends Head

/**
 * Tense class
 *
 * Note: This needs to be refactored, with consideration given to phrase/head movement
 * @param v tense, such as Untensed, Present, or Future
 */
final case class T(v: Tense) extends Head

/**
 * Adjective class
 * @param v the word
 */
final case class J(v: String) extends Head

/**
 * Adverb class
 * @param v the word
 */
final case class R(v: String) extends Head

/**
 * Preposition class
 * @param v the word
 */
final case class P(v: String) extends Head

/**
 * Complementizer class
 *
 * Complementizers can be empty, as in "I know * she is nice," where the star can be a
 * complementizer like "that" or empty.
 * @param v the value of the complementizer, either None or some string
 */
final case class C(v: Option[String]) extends Head
object C {
  def apply(): C = C(None)
  def apply(v: String): C = C(Some(v))
}

/**
 * Determiner class
 *
 * Determiners can be null, as in "* Dogs with sweaters," where the star can either be empty or filled by a
 * determiner like "the" or "those".
 * @param v the value of the determiner, either None or some string
 */
final case class D(v: Option[String]) extends Head
object D {
  def apply(): D = D(None)
  def apply(v: String): D = D(Some(v))
}

/**
 * Union class
 *
 * Unions can be null, as in "Jake, * Bob, please come this way," where the star can either be empty or filled
 * by an operator like "and".
 * @param v the value of the Union, either None or some string
 */
final case class U(v: Option[String]) extends Head
object U {
  def apply(): U = U(None)
  def apply(v: String): U = U(Some(v))
}