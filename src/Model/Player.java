package src.Model;

public class Player {
    private int money;
    private int health;

    public Player(int money, int health){
        this.money = money;
        this.health = health;
    }

    public int getMoney(){
        return this.money;
    }

    public boolean canBuy(int cost){
        if (this.money >= cost) return true;
        return false;
    }

    public void addMoney(int money){
        this.money += money;
    }

    public void subtractMoney(int money) throws Exception{
        if (this.money - money < 0) throw new Exception("You don't have enough money");
        this.money -= money;
    }

    public int getHealth(){
        return this.health;
    }

    public void takeDamage(int damage){
        this.health -= damage;
    }
    
}
