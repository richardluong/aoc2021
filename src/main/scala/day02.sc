var horisontalPosition = 0
var depth = 0

val instructions = scala.io.Source
  .fromResource("2.txt")
  .getLines()
  .map(_.split(" "))
  .map(arr => (arr.head, arr.last.toInt))
  .toList

//instructions
//  .map(inst =>
//    inst match {
//      case ("forward", step) => horisontalPosition += step
//      case ("down", step)    => depth += step
//      case ("up", step)      => depth -= step
//    }
//  )
//  .toList

instructions
  .foldLeft((0, 0)) {
    case ((p, d), ("forward", s)) => (p + s, d)
    case ((p, d), ("down", s))    => (p, d + s)
    case ((p, d), ("up", s))      => (p, d - s)
  } match {
  case (pos, depth) => pos * depth
}

//horisontalPosition
//depth
//horisontalPosition * depth

//var horisontalPosition = 0
//var depth = 0
//var aim = 0
//
//val instructions2 = scala.io.Source
//  .fromResource("2.txt")
//  .getLines()
//  .map(_.split(" "))
//  .map(arr => (arr.head, arr.last.toInt))
//  .map(inst =>
//    inst match {
//      case ("forward", step) =>
//        horisontalPosition += step
//        depth += aim * step
//      case ("down", step) => aim += step
//      case ("up", step)   => aim -= step
//    }
//  )
//  .toList
//
//horisontalPosition
//depth
//horisontalPosition * depth

instructions
  .foldLeft((0, 0, 0)) {
    case ((p, d, a), ("forward", s)) => (p + s, d + a * s, a)
    case ((p, d, a), ("down", s))    => (p, d, a + s)
    case ((p, d, a), ("up", s))      => (p, d, a - s)
  } match {
  case (pos, depth, _) => pos * depth
}
