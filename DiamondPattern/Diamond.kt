// Simple commandline script for animating any 2D array to move in the shape of a diamond into Windows command line

fun main(){
    // Define the figure you want to animate here as a 2D array. Example figures below.

    // Index 0 of the nested arrays represent the space between row and terminal wall and
    // is used to render the location on the X axis multiplied by current locationX parameter.
    val ball = arrayOf(
        arrayOf("   "," ███████"),
        arrayOf("   ","█████████"),
        arrayOf("   ","█████████"),
        arrayOf("   "," ███████")
    )
    val heart = arrayOf(
        arrayOf("   ","  _   _  "),
        arrayOf("   ","/   V   \\"),
        arrayOf("   ","|       |"),
        arrayOf("   "," \\     / "),
        arrayOf("   ","   \\ /   "),
        arrayOf("   ","    *    ")
    )
    val witch = arrayOf(
        arrayOf("   ","                                              "),
        arrayOf("   ","                          ██                  "),
        arrayOf("   ","                          ████                "),
        arrayOf("   ","                          ██████              "),
        arrayOf("   ","                          ████████████        "),
        arrayOf("   ","                      ████████████            "),
        arrayOf("   ","                        ██████████            "),
        arrayOf("   ","              ████████████████████            "),
        arrayOf("   ","            ██████████████████                "),
        arrayOf("   ","          ██████████████████████████████      "),
        arrayOf("   ","          ██████████  ██████████████████████  "),
        arrayOf("   ","          ████████  ██████████  ██████        "),
        arrayOf("   ","            ██████  ████████████  ██          "),
        arrayOf("   ","                      ████████████            "),
        arrayOf("   ","                  ████████████████            "),
        arrayOf("   ","        ████████████████████████              "),
        arrayOf("   ","      ██████████  ████████████                "),
        arrayOf("   ","          ████      ████████                  "),
        arrayOf("   ","        ████          ██                      "),
        arrayOf("   ","                      ████                    "),
        arrayOf("   ","                                              "),
        arrayOf("   ","                                              "),
    )

    // Animate the selected figure with animateDiamond() function. See more info about params below.
    animateDiamond(ball,10,55)
    //animateDiamond(heart, 10, 55)
    //animateDiamond(witch, 10, 55)

}

    // Function for animating the diamond pattern. Takes figure, animation diameter and frame render speed as props.
    // Animation diameter should be dividable by 2.
fun animateDiamond(figure:Array<Array<String>>,animationDiameter:Int, refreshSpeedMs:Long){

    // Initialize looping variables
    var locationY:Int = animationDiameter/2 // Will change between values 0 to animationDiameter
    var locationX:Int = 0  // --''--
    // Boolean values for the direction of movement of the figure
    var reverseY:Boolean = true
    var reverseX:Boolean = false

    // Creating the loop for rendering the figure and animating diamond movement
    while(true){
        // Loop that sets the Y position
        for (i in 0..locationY){
            println()  // Print empty line to add space
        }
        // Loop for rendering 2D array and setting X position
        figure.forEach { it ->
            it.forEachIndexed{ index, it ->
                if (index == 0){
                    for(i in 0..locationX){  // Loop to print the space between figure and terminal wall times locationX
                        print(it)
                    }
                }
                print(it)  // Print the current row of the ascii figure
            }
            println() // Change line after one row has been printed
        }

        Thread.sleep(refreshSpeedMs) // Wait for defined amount of milliseconds before every animation cycle
        ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor() // Clear terminal

        // Handling the location changes for locationX and locationY
        locationX = checkLocation(reverseX, locationX)
        locationY = checkLocation(reverseY, locationY)

        // Checking the movement direction for X and Y axis
        reverseX = checkDirection(reverseX, locationX)
        reverseY = checkDirection(reverseY, locationY)
    }
}

// Function for defining figure axis location. Returns the changed location value as Int.
fun checkLocation(isReversed:Boolean,location:Int): Int {
    var newLocation:Int = location
    if(isReversed){
        newLocation--
    } else{
        newLocation++
    }
    return newLocation
}

// Function for changing the movement direction once max or min distance has been reached
fun checkDirection(isReversed:Boolean, location:Int): Boolean {
    var reverse:Boolean = isReversed
    if(location == 10){
        reverse = true
    } else if(location == 0){
        reverse = false
    }
    return reverse
}