// Simple commandline script for animating a driving car into Windows command line

fun main(){
    // Defining multidimensional array for the car and spacing, index 0 of each array represents the space that the car
    // is from the left side of the command line.
    var car = arrayOf(
        arrayOf("   ", "      .--------."),
        arrayOf("   ", " ____/_____|___ \\___"),
        arrayOf("   ", "O    _   - |   _   ,*"),
        arrayOf("   ", " '--(_)-------(_)--'")
    )

    // Initialize looping variables
    var location:Int = 0  // Defines the space between car and terminal wall
    var reversing:Boolean = false // Defines if the car is going forward or reversing

    // Create loop for rendering the car
    while (true){
        // Print the current state of the car array
        car.forEach { it ->
            it.forEachIndexed() { index, it ->
                if(index===0){
                    for (i in 0..location) {
                        print(it) // Print the space between car and left border of terminal
                    }
                }
                print(it) // Print one row of the ascii car
            }
            println() // Change line after one row of the car is printed
        }

        Thread.sleep(100) // Wait for 0.1 seconds before every animation cycle
        ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor() // Clear terminal

        // Increment or decrease location
        if(reversing){
            location--
        }
        else location++

        // Conditional reversing once car has reached max distance
        if(location === 5){
            reversing = true
        } else if( location === 0){
            reversing = false
        }
    }
}