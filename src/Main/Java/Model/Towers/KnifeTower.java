package Model.Towers;

public class KnifeTower extends AttackTower{

    public KnifeTower(int x, int y) {
        super(x, y, 1, 1, 100, TowerType.knife, 1);
    }

   /*
   @Override
   public void shoot() {
        AEnemy target = findFirstTarget(null);
        // target.health -= getDamage();
   }
   */

   @Override
   public void place(int x, int y) {
       this.x = x;
       this.y = y;
   }
}
