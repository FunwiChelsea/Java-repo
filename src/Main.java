import java.util.*;

class Player {
    // class containing player attributes
    String name;
    String id;
    int score;
    int level;

    //constructor initializes the values when a player is created.
    public Player(String name, String id, int score, int level) {
        this.name = name;
        this.id = id;
        this.score = score;
        this.level = level;
    }

    //method that prints out player details
    public void displayInfo() {
        System.out.println("Name: " + name + ", ID: " + id + ", Score: " + score + ", Level: " + level);
    }
}

class GuessingGame {
    static Scanner scanner = new Scanner(System.in);    // Used to read user input.
    static List<Player> players = new ArrayList<>();    // A list that holds all created players.
    static Random random = new Random();                // Generates random numbers for the game

    private static Player createNewPlayer() {   //this method will return the player class
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        int score = 0;
        int level = 1;

        Player player = new Player(name, id, score, level);
        players.add(player);
        return player;
    }

    private static Player chooseExistingPlayer() {
        System.out.println("Available Players:");
        for (int i = 0; i < players.size(); i++) {   //counting the number of elements in the players array
            System.out.print((i + 1) + ". ");
            players.get(i).displayInfo(); //returns the element at the specified position in the array list
        }

        System.out.print("Select player by number: ");
        int index = scanner.nextInt();
        scanner.nextLine();


        // checking if the number chosen to use for an already existing player is less than or greater than the numbeer
        //of players in the list
        if (index < 1 || index > players.size()) {
            System.out.println("Invalid choice. Returning to main menu.");
            return null;
        }

        return players.get(index - 1); //returning players according to their index in the list
    }

    private static void playGame(Player player) {   // datatype and player variable
        while (player.level <= 3) {
            int maxNumber = 0;
            int maxTrials = 0;

            switch (player.level) {
                case 1:
                    maxNumber = 10;
                    maxTrials = 3;
                    break;
                case 2:
                    maxNumber = 20;
                    maxTrials = 4;
                    break;
                case 3:
                    maxNumber = 100;
                    maxTrials = 6;
                    break;
            }

            int numberToGuess = random.nextInt(maxNumber + 1); //generate random number
            boolean guessedCorrectly = false;

            System.out.println("\nLevel " + player.level + ": Guess a number between 0 and " + maxNumber);
            for (int i = 0; i < maxTrials; i++) {
                System.out.print("Trial " + (i + 1) + ": ");
                int guess = scanner.nextInt(); // taking guessed number from user
                scanner.nextLine();

                if (guess == numberToGuess) {
                    System.out.println("Correct! You passed Level " + player.level);
                    player.score += 10 * player.level;  // the scores according to the levels will be 10,30 and 60
                    player.level++;
                    guessedCorrectly = true;
                    break;
                } else if (guess < numberToGuess) {
                    System.out.println("Too low.");
                } else {
                    System.out.println("Too high.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Game Over! The correct number was " + numberToGuess);
                break;
            }
        }

        System.out.println("Final Score for " + player.name + ": " + player.score);
        System.out.println("Current Level: " + player.level);
    }

    public static void main(String[] args) {

        while (true) {
            System.out.println("Welcome to the Guessing Game!");
            System.out.println("1. Create New Player");
            System.out.println("2. Choose Existing Player");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); //

            if (choice == 1) {
                Player player = createNewPlayer(); //type - variable = method,what the method returns will be stored in the
                playGame(player);                  // variable player
            } else if (choice == 2) {
                if (players.isEmpty()) {
                    System.out.println("No players available. Create a new one first.");
                } else {
                    Player player = chooseExistingPlayer();
                    playGame(player);  //
                }
            } else if (choice == 3) {
                System.out.println("Thanks for playing!");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }


}
