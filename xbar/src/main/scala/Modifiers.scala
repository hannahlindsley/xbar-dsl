package dsl.xbar

sealed trait Position
case object Left extends Position
case object Right extends Position

trait NComplement[X] {
  val position = Right
}
trait NAdjunct[X]
trait NSpecifier[X]

trait VComplement[X]
trait VAdjunct[X]
trait VSpecifier[X]

trait UComplement[X]
trait UAdjunct[X]
trait USpecifier[X]

trait RComplement[X]
trait RAdjunct[X]
trait RSpecifier[X]

trait JComplement[X]
trait JAdjunct[X]
trait JSpecifier[X]

trait DComplement[X]
trait DAdjunct[X]
trait DSpecifier[X]

trait PComplement[X]
trait PAdjunct[X]
trait PSpecifier[X]

trait TComplement[X]
trait TAdjunct[X]
trait TSpecifier[X]

trait CComplement[X] {
  val position = Right
}

trait CAdjunct[X]
trait CSpecifier[X]
