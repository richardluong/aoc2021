import scala.collection.mutable.ListBuffer

val raw = scala.io.Source
  .fromResource("6.txt")
  .getLines()
  .next()
  .split(",")
  .map(_.toInt)
  .toList

val counter = raw
  .foldLeft(Array.ofDim[Int](9)) { case (c, raw) =>
    {
      c(raw) += 1
    }
    c
  }
  .map(BigInt.int2bigInt)

val fishes = (1 to 256).foldLeft(counter) { case (counter, day) =>
  {
    //    println(counter.mkString)
    val nNewFishes = counter(0)
    (0 to 7).foreach(i => {
      counter(i) = counter(i + 1)
    })
    counter(8) = nNewFishes
    counter(6) += nNewFishes
  }
  println(counter.mkString(" "))
  counter
}

fishes.sum

//val totalFishes = (1 to 256).foldLeft(raw) { case (fishes, day) =>
//  val newFishes = ListBuffer[Int]()
//  val updatedFishes = fishes
//    .map(_ - 1)
//    .map(timer =>
//      if (timer < 0) {
//        newFishes.addOne(8)
//        6
//      } else {
//        timer
//      }
//    )
//  val fish = updatedFishes ++ newFishes.toList
//  println(s"After $day day: ${fish.length}")
//  fish
//}
//
//totalFishes.length
