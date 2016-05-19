package xbar.modifier

import xbar.projection.{Bar, Head}


sealed trait Child

object Child {

  final case class ChildThatIsHead(head: Head) extends Child

  final case class ChildThatIsBar(bar: Bar) extends Child

}