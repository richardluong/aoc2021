val raw = scala.io.Source
  .fromResource("7.txt")
  .getLines()
  .next()
  .split(",")
  .map(_.toInt)
  .toList

var fuelSpent = Array.ofDim[Int](raw.max)

fuelSpent.indices
  .foldLeft(fuelSpent) { case (fuel, i) =>
    fuel(i) += raw
      .map(p => {
        val n = (p - i).abs
        (n * (n + 1)) / 2
      })
      .sum
    fuel
  }
  .min
