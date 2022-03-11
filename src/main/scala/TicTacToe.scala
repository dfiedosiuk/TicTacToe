import scala.io.StdIn.readLine

class TicTacToe(var isGameOver: Boolean) {

  def newBoard: Array[Array[String]]= {
    val board = Array.fill(4, 4)(" _ ")
    val legendHor = Array("   "," a ", " b ", " c ")
    val legendVer = Array("   "," 1 ", " 2 ", " 3 ")
    for(n <- 0 to 3){
      board(0)(n)=legendHor(n)
      board(n)(0)=legendVer(n)
    }
    printBoard(board)
    board
  }
  def printBoard(board: Array[Array[String]]): Unit = {
    board foreach { row => row foreach print; println }
  }
  def defineRow(p: Player): Int = {
    val x = readLine(s"Gracz ${p.sign} podaje wiersz: ").toInt
    if (x != 1 && x != 2 && x != 3) 0
    else x
  }
  def defineCol(p: Player): Int = {
    val y = readLine(s"Gracz ${p.sign} podaje kolumne: ")
    if (y == "a" || y == "A") 1
    else if (y == "b" || y == "B") 2
    else if (y == "c" || y == "C") 3
    else 0
  }
  def makeMove(p: Player, board: Array[Array[String]]): Boolean = {

    var x = defineRow(p)
    var y = defineCol(p)
    while (x == 0 || y == 0 || board(x)(y) != " _ ") {
      println("Podales zle wspolrzedne! Jeszcze raz! ")
      x = defineRow(p)
      y = defineCol(p)
    }

    if (p.turn) {
      board(x)(y) = p.sign
      checkPlayerWin(p, board)
      printBoard(board)
    }
    isFinished(p, board)
  }
  def drawSign(x: Int, y: Int, board: Array[Array[String]]): Array[Array[String]] = {

    if (board(x)(y) != " _ ") {
      printBoard(board)
      println("Pole zajete! Podaj wspolrzedne jeszcze raz ")
      board
    } else {
      board(x)(y) = " x "
      board foreach { row => row foreach print; println }
      board
    }
  }
  def checkTripleCombo(p: Player, board: Array[Array[String]]): Boolean = {
    var countDiagN = 0
    var countDiagZ = 0
    var countRow = 0
    var countCol = 0
    for (w <- 1 to 3) {
      if (countRow != 3 && countCol != 3) {
        countRow = 0
        countCol = 0
        for (k <- 1 to 3) {
          if (board(w)(k) == p.sign) countRow += 1
          if (board(k)(w) == p.sign) countCol += 1
        }
        if (board(w)(w) == p.sign) countDiagN += 1
        if (board(w)(4 - w) == p.sign) countDiagZ += 1
      }
    }
    if (countRow == 3 || countCol == 3 || countDiagN == 3 || countDiagZ == 3) true
    else false
  }
  def checkPlayerWin(p: Player, board: Array[Array[String]]): Boolean = {
    if (checkTripleCombo(p, board)) {
      !p.winStatus
    } else p.winStatus
  }
  def isFinished(p: Player, board: Array[Array[String]]): Boolean = {
    var count = 0
    for (w <- 1 to 3) {
      for (k <- 1 to 3) {
        if (board(w)(k) == " _ ") count += 1
      }
    }
    if (checkPlayerWin(p, board)) {
      printBoard(board)
      println(s"Wygral gracz ${p.sign}! Gratulacje! ")
      true
    }
    else if (count == 0){
      println("Remis ! ")
      true
    }
    else false
  }

}
