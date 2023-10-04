import java.util.ArrayList;
import java.util.List;

public class GameRunner {

    public static void main(String[] args) throws InterruptedException {
        Player player = new Player(130, "Бильбо Бэггинс");
        Monster monster1 = new Monster(30, "Саурон", player);
        Monster monster2 = new Monster(35, "Азок-Осквернитель", player);
        List<Monster> monsters = new ArrayList<>();
        monsters.add(monster1);
        monsters.add(monster2);
        player.setMonsters(monsters);

        Thread thread1 = new Thread(player);
        Thread thread2 = new Thread(monster1);
        Thread thread3 = new Thread(monster2);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
    }
}
