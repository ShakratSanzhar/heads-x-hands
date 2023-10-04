import java.util.List;

public class Player extends Creature implements Runnable {

    private List<Monster> monsters;
    private final int healingValue;
    private volatile int healingAmount;
    private static final double HEALING_PERCENT = 0.3;

    public Player(int health, String name) {
        super(health, name);
        this.healingValue = (int) (health * HEALING_PERCENT);
        this.healingAmount = RandomUtil.getAmountOfHealing();
    }

    @Override
    public void run() {
        try {
            synchronized (this) {
                while (this.getHealth() > 0 && !monsters.isEmpty()) {
                    Monster monster = monsters.get(RandomUtil.getIndexOfMonster(monsters.size()));
                    if (assault(monster)) {
                        successfulAttack(monster);
                        System.out.println(this.getName() + "   бьет монстра   " + monster.getName());
                        if (monster.getHealth() <= 0) {
                            monsters.remove(monster);
                            monster.setActive(false);
                            System.out.println(this.getName() + "   убивает монстра   " + monster.getName());
                            continue;
                        }
                        this.notifyAll();
                    }
                    this.notifyAll();
                    this.wait();
                }
                if (this.getHealth() <= 0) {
                    System.out.println(this.getName() + " игрок погибает");
                } else {
                    System.out.println(this.getName() + "   игрок победил");
                }
                this.notifyAll();
            }
        } catch (InterruptedException e) {
            e.getStackTrace();
        }
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public int getHealingValue() {
        return healingValue;
    }

    public int getHealingAmount() {
        return healingAmount;
    }

    public void setHealingAmount(int healingAmount) {
        this.healingAmount = healingAmount;
    }
}
