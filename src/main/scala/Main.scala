import scala.io.StdIn.readLine

object Main extends App {
  var player1 = new Player(" x ", true, false)
  var player2 = new Player(" O ", false, false)

  do {
    val game = new TicTacToe(false)
    var board = game.newBoard

    do {
      game.isGameOver = game.makeMove(player1, player2, board)
    } while (!game.isGameOver);
    println("Koniec gry! ")
  } while (readLine("Czy chcesz zagrac ponownie?: (y/n) ") == "y");
}
