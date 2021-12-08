val raw = scala.io.Source
  .fromResource("5.txt")
  .getLines()
  .toList

val inputs = raw
  .map(_.split(" -> "))
  .map(s => (s.head.split(","), s.last.split(",")))
  .map(s =>
    ((s._1.head.toInt, s._1.last.toInt), (s._2.head.toInt, s._2.last.toInt))
  )

var board = Array.ofDim[Int](1000, 1000)

//val output = inputs
//  .filter({ case (start, end) =>
//    start.head == end.head | start.last == end.last
//  })
//  .foldLeft(board) { case (board, row) =>
//    println(row._1.mkString, row._2.mkString)
//    if (row._1.head == row._2.head) {
//      if (row._1.last < row._2.last) {
//        (row._1.last to row._2.last).foreach(i => board(row._1.head)(i) += 1)
//      } else {
//        (row._2.last to row._1.last).foreach(i => board(row._1.head)(i) += 1)
//      }
//    } else if (row._1.last == row._2.last) {
//      if (row._1.head < row._2.head) {
//        (row._1.head to row._2.head).foreach(i => board(i)(row._1.last) += 1)
//      } else {
//        (row._2.head to row._1.head).foreach(i => board(i)(row._1.last) += 1)
//      }
//    }
//    board
//  }

inputs.length

val output = inputs
  .map({
    case (start, end) => {
      //      println("s", start, "e", end)
      if (start._1 > end._1) {
        println("s1 > s2")
        (end, start)
      } else {
        if (start._1 equals end._1) {
          println("s1 == s2")
          println("s1 == s2")
          if (start._2 > end._2) {
            println("e1 > e2")
            (end, start)
          } else {
            (start, end)
          }
        } else {
          (start, end)
        }
      }
    }
  })
  .foldLeft(board) { case (board, row) =>
    row match {
      case (start, end) =>
        {
          println("s", start, "e", end)
          if (start._1 == end._1) {
            (start._2 to end._2).foreach(i => board(start._1)(i) += 1)
          } else if (start._2 == end._2) {
            (start._1 to end._1).foreach(i => board(i)(start._2) += 1)
          } else {
            println("# s", start, "e", end)
            if (start._2 > end._2)
              (0 to (start._2 - end._2).abs).foreach(i => {
                println((start._1 + i, start._2 - i))
                board(start._1 + i)(start._2 - i) += 1
              })
            else
              (0 to (start._2 - end._2).abs).foreach(i => {
                println((start._1 + i, start._2 + i))
                board(start._1 + i)(start._2 + i) += 1
              })
          }
        }
        board
    }
  }

print(inputs)
output.foreach(s => {
  println(s.mkString)
})

output.map(_.count(_ > 1)).sum
