package Model.Player;

import java.util.List;

import Controller.Interfaces.IMoneyObserver;
import Model.Interfaces.IMoneySubject;

public class Player implements IMoneySubject{
    private int money;
    private int health;
    private List<IMoneyObserver> moneyObservers;

    public Player(int money, int health){
        this.money = money;
        this.health = health;
    }

    @Override
    public int getMoney(){
        return this.money;
    }


    public void addMoney(int money){
        this.money += money;
        notifyObservers();
    }

    public void subtractMoney(int cost) throws Exception{
        if (this.money < cost){
            throw new Exception("You don't have enough money");
        } 
        this.money -= cost;
        notifyObservers();
    }

    public int getHealth(){
        return this.health;
    }

    public void takeDamage(int damage){
        this.health -= damage;
    }

    
    public void setMoneyObservers(List<IMoneyObserver> observers){
        moneyObservers = observers;
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        for(IMoneyObserver observer : moneyObservers){
            observer.updateMoney(this.money);
        }
    }
}
