package Model.Interfaces;

/**
 * Interface that would be implemented by both towers and enemies to make targeting-logic depend on abstraction
 * Not yet implemente fully
 */
public interface ITargetable {
    double getX();
    double getY();
}
