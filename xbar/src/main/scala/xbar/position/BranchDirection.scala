package xbar.position


/**
 * Currently not used; will be used to identify constraints on where modifiers can
 * show up in a projection.
 */
sealed trait BranchDirection

object BranchDirection {

  case object Left extends BranchDirection

  case object Right extends BranchDirection

}