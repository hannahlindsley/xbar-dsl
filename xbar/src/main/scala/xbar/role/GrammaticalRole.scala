package xbar.role

import scalaz.Show


sealed trait GrammaticalRole

object GrammaticalRole {

  case object GrammaticalObject extends GrammaticalRole

  case object GrammaticalSubject extends GrammaticalRole

  case object NestedVerb extends GrammaticalRole

  case object Empty extends GrammaticalRole

}