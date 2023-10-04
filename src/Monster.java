public class Monster extends Creature implements Runnable {

    private final Player player;
    private volatile boolean isActive;

    public Monster(int health, String name, Player player) {
        super(health, name);
        this.player = player;
        isActive = true;
    }

    @Override
    public void run() {
        try {
            synchronized (player) {
                while (!player.getMonsters().isEmpty() && player.getHealth() > 0 && isActive) {
                    if (assault(player)) {
                        successfulAttack(player);
                        System.out.println(this.getName() + "   бьет игрока   " + player.getName());
                        if (player.getHealingAmount() > 0 && player.getHealth() <= 0) {
                            player.setHealth(player.getHealingValue());
                            System.out.println(player.getName() + "   исцелился");
                            player.setHealingAmount(player.getHealingAmount() - 1);
                        }
                        player.notifyAll();
                    } else {
                        player.notifyAll();
                        player.wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.getStackTrace();
        }
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
