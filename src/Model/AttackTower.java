package src.Model;

public abstract class AttackTower extends ATower implements IShootable{
    protected int damage;
    protected int fireSpeed;

   public AttackTower(int x, int y) {
       super(x, y);
   }

   public int getDamage() {
       return damage;
   }
   public void setDamage(int damage) {
       this.damage = damage;
   }

   public int getFireSpeed() {
       return fireSpeed;
   }
   public void setFireSpeed(int fireSpeed) {
       this.fireSpeed = fireSpeed;
   }
}
