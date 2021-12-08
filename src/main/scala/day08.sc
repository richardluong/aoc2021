val raw = scala.io.Source
  .fromResource("8.txt")
  .getLines()
  .toList
  .map(
    _.split(" \\| ")
      .map(_.split(" ").map(_.sorted))
  )

def getDict(s: List[String]) = {
  val one = s.filter(_.length == 2).head
  val four = s.filter(_.length == 4).head
  val seven = s.filter(_.length == 3).head
  val eight = s.filter(_.length == 7).head

  val (zero, six, nine) = {
    val nine = s
      .filter(_.length == 6)
      .filter(st => s"[$four]".r.findAllMatchIn(st).length == 4)

    val zero = s
      .filter(_.length == 6)
      .filterNot(_.equals(nine.head))
      .filter(st => {
        s"[$seven]".r.findAllMatchIn(st).length == 3
      })
    val six = s
      .filter(_.length == 6)
      .filterNot(_.equals(nine.head))
      .filterNot(_.equals(zero.head))
    (zero.head, six.head, nine.head)
  }

  val (two, three, five) = {
    val three = s
      .filter(_.length == 5)
      .filter(st => s"[$seven]".r.findAllMatchIn(st).length == 3)
    val two = s
      .filter(_.length == 5)
      .filterNot(_.equals(three.head))
      .filter(st => s"[$four]".r.findAllMatchIn(st).length == 2)
    val five = s
      .filter(_.length == 5)
      .filterNot(_.equals(three.head))
      .filter(st => s"[$four]".r.findAllMatchIn(st).length == 3)

    (two.head, three.head, five.head)
  }

  val dict2 = Map(
    zero -> 0,
    one -> 1,
    two -> 2,
    three -> 3,
    four -> 4,
    five -> 5,
    six -> 6,
    seven -> 7,
    eight -> 8,
    nine -> 9
  )
  dict2
}

raw
  .map(s => {
    val d = getDict(s.head.toList)
    s.last.flatMap(d.get).mkString.toInt
  })
  .sum

//val dict = Map(
//  2 -> 1,
//  4 -> 4,
//  3 -> 7,
//  7 -> 8
//)
//
//raw
//  .map(s =>
//    s.last
//      .map(_.length)
//      .map(i => dict.getOrElse(i, 0))
//      .count(_ > 0)
//    //      .split(" ")
//  )
//  .sum
//
//"abcd"
