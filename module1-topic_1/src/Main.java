import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String person = "\uD83E\uDDD9\u200D";
        int personLive = 3;
        /* здесь необходимо рассказать про переполнение и про другие типы данных
            int personLive = 2147483649; // мало ли кто-то захочет сделать ооочень много жизней
         */

        String monster = "\uD83E\uDDDF\u200D";
        int sizeBoard = 3;
        int personX = 1;
        int personY = 3;

        int step = 0;
        String gamingField = "+ —— + —— + —— +\n"
                + "|    |    | \uD83C\uDFE0 |\n"
                + "+ —— + —— + —— +\n"
                + "|    | " + monster + " |    |\n"
                + "+ —— + —— + —— +\n"
                + "| " + person + " |    |    |\n"
                + "+ —— + —— + —— +";

        System.out.println("Привет! Ты готов начать играть в игру? (Напиши: ДА или НЕТ)");

        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        System.out.println("Ваш ответ:\t" + answer);

        System.out.println("Выбери сложность игры(от 1 до 5):");
        int difficultGame = sc.nextInt();
        System.out.println("Выбранная сложность:\t" + difficultGame);
        sc.nextLine();

        System.out.println("\nИгровое поле:");
        System.out.println(gamingField);

        System.out.println("\n" + person + " Ты стоишь на игровом поле. В центре ты видишь монстра " + monster + "!");
        System.out.println("Хочешь сразиться с ним в мини-игре? (ДА или НЕТ)");

        String fightAnswer = sc.nextLine();
        if (fightAnswer.equalsIgnoreCase("ДА") || fightAnswer.equalsIgnoreCase("да")) {
            BattleGame battleGame = new BattleGame(difficultGame);
            battleGame.start();
        } else {
            System.out.println("\n" + person + " Ты решил обойти монстра стороной... В следующий раз повезёт!");
        }

        System.out.println("\nСпасибо за игру! Приходи ещё! 👋");
    }
}
