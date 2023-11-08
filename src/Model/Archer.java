package src.Model;

import java.awt.*;

public class Archer extends AttackTower{

   public Archer(int x, int y, int cost, int range, Image model, int damage, int fireSpeed) {
       super(x, y, cost, range, model, damage, fireSpeed);
   }

   @Override
   public void shoot(AEnemy target) {
       //target.getHP -= getDamage();
   }

   @Override
   public void place(int x, int y) {
       setX(x);
       setY(y);
   }
}
