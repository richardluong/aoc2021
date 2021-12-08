val raw = scala.io.Source
  .fromResource("3.txt")
  .getLines()
  .map(s => s.toList.map(_.toString.toInt))
  .toList

val report = raw.transpose

val count = report
  .map(bit => bit.count(_.equals(1)) > bit.count(_.equals(0)))

val gamma = count.map {
  case true  => 1
  case false => 0
}.mkString

val epsion = count.map {
  case true  => 0
  case false => 1
}.mkString

Integer.parseInt(gamma, 2)
Integer.parseInt(epsion, 2)
Integer.parseInt(gamma, 2) * Integer.parseInt(epsion, 2)

def getBit(raw: List[List[Int]], index: Int, reverse: Boolean = false): Int =
  raw.transpose.lift(index).get.foldLeft((0, 0)) {
    case ((zeros, ones), 0) => (zeros + 1, ones)
    case ((zeros, ones), 1) => (zeros, ones + 1)
  } match {
    case (zeros, ones) =>
      if (reverse) {
        if (ones >= zeros) 0 else 1
      } else {
        if (ones >= zeros) 1 else 0
      }
  }

val o = (0 to raw.transpose.length - 1)
  .foldLeft(raw) { case (r, index) =>
    if (r.length > 1) r.filter(_(index).equals(getBit(r, index))) else r
  }(0)
  .mkString

val c = (0 to raw.transpose.length - 1)
  .foldLeft(raw) { case (r, index) =>
    if (r.length > 1) r.filter(_(index).equals(getBit(r, index, true))) else r
  }(0)
  .mkString

Integer.parseInt(o, 2)
Integer.parseInt(c, 2)
Integer.parseInt(o, 2) * Integer.parseInt(c, 2)
