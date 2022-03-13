import scala.annotation.tailrec
import scala.collection.immutable
import scala.io.StdIn.readLine

class Player(val sign: String, var turn: Boolean, var winStatus: Boolean) {

  def takeCoordinates(): List[Int] = {
    var pass = List(0, 0)
    val columns = List('a', 'b', 'c')
    val rows = List('1', '2', '3')
    val values = List(1, 2, 3)

    do {
      val coordinates = readLine("Podaj wspolrzedne: ")
      val coordinatesList = coordinates.toList.filter(_ != ' ')

      val f: (Char, Char) => List[Int] = (m, n) => {
        if (columns.contains(m) && rows.contains(n)) values(rows.indexOf(n)) :: values(columns.indexOf(m)) :: Nil
        else if (columns.contains(n) && rows.contains(m)) values(rows.indexOf(m)) :: values(columns.indexOf(n)) :: Nil
        else 0 :: 0 :: Nil
      }

      coordinatesList match {
        case m :: n :: Nil => pass = f(m, n)
        case _ => pass = 0 :: 0 :: Nil
      }
      if (pass == 0 :: 0 :: Nil) println("Podales nieprawidlowe wspolrzedne! Jeszcze raz! ")
    } while (pass == 0 :: 0 :: Nil);
    pass
  }
}

