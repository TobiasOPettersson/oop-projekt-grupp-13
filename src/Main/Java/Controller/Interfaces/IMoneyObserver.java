package Controller.Interfaces;

public interface IMoneyObserver {
    /**
     * Provides the observers with the updated player bank when it changes
     * @param curMoney The new amount of money in the players bank
     */
    void updateMoney(int curMoney);
}
