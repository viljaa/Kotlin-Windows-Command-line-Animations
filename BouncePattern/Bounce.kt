// Simple commandline script for animating any 2D array to move in a bounce pattern into Windows command line

fun main(){
    // Define the figure you want to animate here as a 2D array. Example figures below.
    val bunny = arrayOf(
        arrayOf("   ", "         ((`\\"),
        arrayOf("   ", "      ___ \\\\ '--._"),
        arrayOf("   ", "   .'`   `'    o  )"),
        arrayOf("   ", "  /    \\   '. __.'"),
        arrayOf("   ", " _|    /_  \\ \\_\\_"),
        arrayOf("   ", "{_\\______\\-'\\__\\_\\")
    )
    val ball = arrayOf(
        arrayOf("   "," ███████"),
        arrayOf("   ","█████████"),
        arrayOf("   ","█████████"),
        arrayOf("   "," ███████")
    )
    // Call the animate function. Takes figure and amount of bounces as params.
    animateBounce(bunny, 6)
}
// Initialize singleton object to save coordinates of the figure
object Location {
    var y:Int = 3
    var x:Int = 0
}

fun animateBounce(figure:Array<Array<String>>, bounces: Int) {
    // Boolean values for the direction of movement of the figure
    var reverseY: Boolean = true
    var reverseX: Boolean = false

    // Create loop for rendering the figure
    while (true) {
        for (i in 1..bounces) {
            // Do one bounce iteration
            for (i in 0..5) {
                for (i in 0..Location.y) {
                    println() // Print empty line for spacing
                }

                // Loop through 2D array
                figure.forEach { it ->
                    it.forEachIndexed { index, it ->
                        if (index == 0) {
                            for (i in 0..Location.x) { // Loop to print the space between figure and terminal wall times locationX
                                print(it)
                            }
                        }
                        print(it)  // Print current row of the ascii figure
                    }
                    println()  // Change line to start printing next row of the figure
                }

                Thread.sleep(55) // Wait for defined amount of milliseconds before every animation cycle
                ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor() // Clear terminal

                // Handling the location changes for locationX and locationY
                Location.x = checkLocationX(reverseX)
                Location.y = checkLocationY(reverseY)
                // Checking the movement direction for Y axis
                reverseY = checkDirectionY(reverseY)
            }
        }
        // Change X axis direction after all bounces have been completed
        reverseX = checkDirectionX(reverseX)
    }
}

// Function for defining figure Y axis location. Returns the changed location value as Int.
fun checkLocationY(isReversed: Boolean): Int {
    var newLocationY:Int = Location.y
    if(isReversed){
        newLocationY--
    } else{
        newLocationY++
    }
    return newLocationY
}

// Function for defining figure X axis location. Returns the changed location value as Int.
fun checkLocationX(isReversed: Boolean): Int{
    var newLocationX:Int = Location.x
    if(isReversed && Location.y == 0){
        newLocationX--
    } else if(Location.y == 0){
        newLocationX++
    }
    return newLocationX
}

// Function for defining figure Y axis direction. Returns the direction value as Boolean.
fun checkDirectionY(isReversed: Boolean): Boolean {
    var reverse: Boolean = isReversed

    if(Location.y == 3){
        reverse = true
    } else if( Location.y == 0){
        reverse = false
    }
    return reverse
}

// Function for defining figure X axis direction. Returns the direction value as Boolean.
fun checkDirectionX(isReversed: Boolean): Boolean {
    return !isReversed
}