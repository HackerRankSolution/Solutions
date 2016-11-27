package com.martin.hr.dynamicprogramming

/**
  * Created by Martin on 11/25/2016.
  *
  *
  *
  * Christy is interning at HackerRank. One day she has to distribute some chocolates to her colleagues. She is biased towards her friends and may have distributed the chocolates unequally. One of the program managers gets to know this and orders Christy to make sure everyone gets equal number of chocolates.

But to make things difficult for the intern, she is ordered to equalize the number of chocolates for every colleague in the following manner,

For every operation, she can choose one of her colleagues and can do one of the three things.

She can give one chocolate to every colleague other than chosen one.
She can give two chocolates to every colleague other than chosen one.
She can give five chocolates to every colleague other than chosen one.
Calculate minimum number of such operations needed to ensure that every colleague has the same number of chocolates.
  */
class ChristieChoclate {


}


object ChristieChoclate {


  def main(args: Array[String]): Unit = {
    var noOfTestCases = readInt()
    val in = Array(5, 2, 1)

    while (noOfTestCases > 0) {
      readInt()
      val stri = readLine().split(" ")
      val chocCount: Array[Int] = stri.map(_.toInt)

      println(distribute(chocCount, in))
      noOfTestCases -= 1
    }
  }


  def distribute(chocCount: Array[Int], inp: Array[Int]): Int = {


    val min = chocCount.min
    var minCount = Int.MaxValue

    var index = 0
    var count = 0

    while (index < chocCount.length) {
      if (chocCount(index) != min)
        count += reduce(chocCount(index), min, inp)
      index += 1
    }
    minCount = math.min(minCount, count)
    var tempMin = min
    for (arg <- inp) {
      tempMin = min - arg

      if (tempMin >= 0) {
        count = 1
        index=0
        while (index < chocCount.length) {
          if (chocCount(index) != min)
            count += reduce(chocCount(index), tempMin, inp)
          index += 1
        }

        minCount = math.min(minCount, count)
      }


    }
    minCount
  }


  def reduce(count: Int, min: Int, arr: Array[Int]): Int = {
    var counter = 0
    var newCount = count - min

    for (i <- 0 to arr.length) {
      if (newCount > 0) {
        counter += newCount / arr(i)
        newCount = newCount % arr(i)
      }
    }
    counter
  }
}
