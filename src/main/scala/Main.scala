object Main extends App {
  var player1 = new Player(" x ", true, false)
  var player2 = new Player(" O ", false, false)

  val game = new TicTacToe(false)
  var board = game.newBoard

  do {
    if (player1.turn){
      game.isGameOver = game.makeMove(player1, board)
      player1.turn = false
      player2.turn = true
    } else if (player2.turn){
      game.isGameOver = game.makeMove(player2, board)
      player1.turn = true
      player2.turn = false
    } else println("ERROR")
  } while (!game.isGameOver);
  println("Koniec gry! ")
}
