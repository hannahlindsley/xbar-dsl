package dsl.xbar

/**
 * Created by hannahlindsley on 5/4/16.
 */
sealed trait GrammaticalPosition
case object GrammaticalObject extends GrammaticalPosition
case object GrammaticalSubject extends GrammaticalPosition
case object SubVerb extends GrammaticalPosition
case object Empty extends GrammaticalPosition
case object GenitivePossessor extends GrammaticalPosition



// transitive/intransitive/ditransitive verb
// appositive
// list
// passive/active structures
// possessive