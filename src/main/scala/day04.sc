import scala.collection.mutable.ListBuffer

val raw = scala.io.Source
  .fromResource("4.txt")
  .getLines()

val ints = raw.next().split(",").map(_.toInt)
val numbers = ints.iterator
val numbers2 = ints.iterator

case class Board(
    var rows: List[List[(Int, Boolean)]]
) {
  def isFinished: Boolean = {
    rows.foreach(row => {
      if (row.forall(_._2)) return true
    })
    rows.transpose.foreach(row => {
      if (row.forall(_._2)) return true
    })
    false
  }

  def pick(i: Int) = {
    rows = rows.map(row => row.map(num => if (num._1 == i) (i, true) else num))
  }

  override def toString() = {
    rows.map(_.filterNot(_._2).map(_._1)).mkString(" | ")
  }

  def winningScore(i: Int): Int =
    rows.map(_.filterNot(_._2).map(_._1).sum).sum * i
}

val boards = new ListBuffer[Board]()
val boards2 = new ListBuffer[Board]()

while (raw.hasNext) {
  println(raw.next())
  val rows = List(raw.next(), raw.next(), raw.next(), raw.next(), raw.next())
    .map(_.trim.split("\\s+").map(s => (s.toInt, false)).toList)
  boards.append(Board(rows))
  boards2.append(Board(rows))
}
var b = boards.toList
var b2 = boards2.toList

var number = 0
while (b.count(_.isFinished) == 0) {
  number = numbers.next()
  println("pick", number)
  b.foreach(bo => {
    bo.pick(number)
    println(bo.isFinished)
    println(bo)
  })
}

print(number)
print(b.filter(_.isFinished).map(_.winningScore(number)))

number = 0
while (b2.length > 1) {
  println("l", b2.length)
  number = numbers2.next()
  println("pick", number)
  b2.foreach(bo => {
    bo.pick(number)
    println(bo.isFinished)
    println(bo)
  })
  b2 = b2.filterNot(_.isFinished)
}

b2(0)
while (b2(0).isFinished == false) {
  number = numbers2.next()
  b2(0).pick(number)
}

print(number)
print(b2(0).winningScore(number))
