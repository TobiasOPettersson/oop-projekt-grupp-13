package Model.Enums;

/**
 * The types of targeting towers can do
 * Each ATower has an array of two TargetTypes
 * The first is what type of object it can target with its ability
 * The second which of these it preferes to target
 */
public enum TargetType {
    enemies,
    towers,
    tiles,
    first,
    last,
    all
}
