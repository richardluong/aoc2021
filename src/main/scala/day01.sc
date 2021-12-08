val numbers =
  scala.io.Source.fromResource("1.txt").getLines().map(_.toInt).toList

numbers.sliding(2).count(w => w.head < w.last)

numbers
  .sliding(3)
  .map(_.sum)
  .sliding(2)
  .count(w => w.head < w.last)
