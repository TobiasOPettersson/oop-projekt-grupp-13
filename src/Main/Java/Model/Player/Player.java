package Model.Player;

import java.util.List;

import Controller.Interfaces.IMoneyObserver;
import Model.Interfaces.IMoneySubject;

/**
 * A class a player in the game.
 */
public class Player implements IMoneySubject{
    private int money;
    private int health;
    private List<IMoneyObserver> moneyObservers;

    /**
     * Constructor of a player
     * @param money the money that the player uses to buy and upgrade new towers
     * @param health the health points of a player. If this goes to 0 the player dies.
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
     * @param cost       The cost of the tower or upgrade
     * @throws Exception if the player doesn't have enough money
     */
    public void subtractMoney(int cost) throws Exception{
        if (this.money < cost){
            throw new Exception("You don't have enough money");
        } 
        this.money -= cost;
        notifyObservers();
    }

    /**
     * Checks if the player can afford a cost
     * @param cost  The cost of the tower or upgrade 
     * @return      Whether or not the player can afford it
     */
    public boolean canAfford(int cost){
        if (this.money < cost){
            return false;
        } 
        return true;
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
        if (this.health - damage <= 0) {
            this.health = 0;
        }
        else {
            this.health -= damage;
        }
    }
}
