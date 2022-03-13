class TicTacToe(var isGameOver: Boolean) {

  def newBoard: Array[Array[String]] = {
    val board = Array.fill(4, 4)(" _ ")
    val legendHor = Array("   ", " a ", " b ", " c ")
    val legendVer = Array("   ", " 1 ", " 2 ", " 3 ")
    for (n <- 0 to 3) {
      board(0)(n) = legendHor(n)
      board(n)(0) = legendVer(n)
    }
    printBoard(board)
    board
  }

  def printBoard(board: Array[Array[String]]): Unit = {
    board foreach { row => row foreach print; println }
  }

  def makeMove(p1: Player, p2: Player, board: Array[Array[String]]): Boolean = {
    val playerDoMove: Player => Boolean = p => {
      var coordinates = p.takeCoordinates()
      while (board(coordinates.head)(coordinates(1)) != " _ ") {
        println("Pole zajete! Jeszcze raz! ")
        coordinates = p.takeCoordinates()
      }
      board(coordinates.head)(coordinates(1)) = p.sign
      checkPlayerWin(p, board)
      printBoard(board)
      isFinished(p, board)
    }
    if (p1.turn) {
      p1.turn = false
      p2.turn = true
      playerDoMove(p1)
    }
    else if (p2.turn) {
      p1.turn = true
      p2.turn = false
      playerDoMove(p2)
    }
    else true
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
      println(s"Wygral gracz ${p.sign}! Gratulacje! ")
      true
    }
    else if (count == 0) {
      println("Remis ! ")
      true
    }
    else false
  }
}
