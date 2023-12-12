package Model.Player;

import java.util.List;

import Controller.Interfaces.IMoneyObserver;
import Model.Interfaces.IMoneySubject;

public class Player implements IMoneySubject{
    private int money;
    private int health;
    private List<IMoneyObserver> moneyObservers;

    /**
     * TODO Javadoc comment
     * @param money
     * @param health
     */
    public Player(int money, int health){
        this.money = money;
        this.health = health;
    }

    //----------------------------Money methods--------------------------//

    /**
     * Adds money and notifies IMoneyObservers with the updated amount in the players bank
     * @param money
     */
    public void addMoney(int money){
        this.money += money;
        notifyObservers();
    }

    /**
     * Called when the player tries to buy a tower or an upgrade
     * @param cost The cost oth the tower or upgrade
     * @throws Exception if the player doesn't have enough money
     */
    public void subtractMoney(int cost) throws Exception{
        if (this.money < cost){
            throw new Exception("You don't have enough money");
        } 
        this.money -= cost;
        notifyObservers();
    }

    //----------------------------IMoneyObserver methods--------------------------//

    /**
     * Adds IMoneyObservers
     * @param observers The money observers
     */
    public void setMoneyObservers(List<IMoneyObserver> observers){
        moneyObservers = observers;
        notifyObservers();
    }

    @Override
    /**
     * Notifies IMoneyObservers with the updated amount in the players bank
     */
    public void notifyObservers() {
        for(IMoneyObserver observer : moneyObservers){
            observer.updateMoney(this.money);
        }
    }

    //----------------------------Getters and setters--------------------------//

    public int getMoney(){
        return this.money;
    }

    public int getHealth(){
        return this.health;
    }

    public void takeDamage(int damage){
        this.health -= damage;
    }
}
