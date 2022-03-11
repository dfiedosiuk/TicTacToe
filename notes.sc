val board = Array.fill(4, 4)(" _ ")
val legendHor = Array("   "," a ", " b ", " c ")
val legendVer = Array("   "," 1 ", " 2 ", " 3 ")
for(a <- 0 to 3){
  board(0)(a)=legendHor(a)
  board(a)(0)=legendVer(a)
}
board foreach { row => row foreach print; println }