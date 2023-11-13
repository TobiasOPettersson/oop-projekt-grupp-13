package Model;

import java.awt.*;

public abstract class ATower implements IPlacable, IPositionable{
   protected int x;
   protected int y;
   protected int cost;
   protected int range;
   protected Image model;

   public ATower(int x, int y) {
       this.x = x;
       this.y = y;
   }

   public int getX() {
       return x;
   }

   public void setX(int x) {
       this.x = x;
   }

   public int getY() {
       return y;
   }

   public void setY(int y) {
       this.y = y;
   }

   public int getCost() {
       return cost;
   }

   public void setCost(int cost) {
       this.cost = cost;
   }

   public int getRange() {
       return range;
   }

   public void setRange(int range) {
       this.range = range;
   }

   public Image getModel() {
       return model;
   }

   public void setModel(Image model) {
       this.model = model;
   }
}
