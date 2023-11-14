package src.Model;

import java.awt.*;

public abstract class ATower implements IPlacable{
   private int x;
   private double y;
   private int cost;
   private int range;
   private Image model;

   public ATower(int x, int y) {
       this.x = x;
       this.y = y;
   }

   public double getX() {
       return x;
   }

   public void setX(int x) {
       this.x = x;
   }

   public double getY() {
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
