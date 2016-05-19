package xbar.position

sealed trait BranchDirection

object BranchDirection {

  case object Left extends BranchDirection

  case object Right extends BranchDirection

}