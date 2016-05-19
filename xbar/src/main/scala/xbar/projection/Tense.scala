package xbar.projection


sealed trait Tense

object Tense {

  case object Untensed extends Tense

  case object Present extends Tense

  case object Past extends Tense

}