package xbar.projection


sealed trait Tense

object Tense {

  case object Infinitive extends Tense

  case object Present extends Tense

  case object Past extends Tense
  
  case object Future extends Tense

}