package Model.Towers;

import org.junit.jupiter.api.Test;

import Model.Enums.Upgrade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TowersTest {
    
    @Test
    public void test_knife_upgrade() {
        KnifeTower knife = new KnifeTower(0, 0);

        int upgradedDamage = knife.getDamage() + knife.getUpgradeMap().get(Upgrade.Damage).intValue();
        int upgradedSpeed = knife.getMaxCooldown() - knife.getUpgradeMap().get(Upgrade.Speed).intValue();
        int upgradedTargets = knife.getNTargets() + knife.getUpgradeMap().get(Upgrade.Targets).intValue();
        double upgradedRange = knife.getRange() + knife.getUpgradeMap().get(Upgrade.Range).doubleValue();

        knife.upgrade(Upgrade.Damage);
        knife.upgrade(Upgrade.Speed);
        knife.upgrade(Upgrade.Targets);
        knife.upgrade(Upgrade.Range);

        assertEquals(upgradedDamage, knife.getDamage());
        assertEquals(upgradedSpeed, knife.getMaxCooldown());
        assertEquals(upgradedTargets, knife.getNTargets());
        assertEquals(upgradedRange, knife.getRange());
    }    

    @Test
    public void test_mallet_upgrade() {
        MalletTower mallet = new MalletTower(0, 0);

        int upgradedDamage1 = mallet.getDamage() + mallet.getUpgradeMap().get(Upgrade.Damage).intValue();
        double upgradedAoeRange = mallet.getAoeRange() + mallet.getUpgradeMap().get(Upgrade.AoeRange).doubleValue();
        double upgradedRange = mallet.getRange() + mallet.getUpgradeMap().get(Upgrade.Range).doubleValue();

        mallet.upgrade(Upgrade.Damage);
        mallet.upgrade(Upgrade.AoeRange);
        mallet.upgrade(Upgrade.Range);

        assertEquals(upgradedDamage1, mallet.getDamage());
        assertEquals(upgradedAoeRange, mallet.getAoeRange());
        assertEquals(upgradedRange, mallet.getRange());

        int upgradedDamage2 = mallet.getDamage() + mallet.getUpgradeMap().get(Upgrade.Damage2).intValue();        
        mallet.upgrade(Upgrade.Damage2);
        assertEquals(upgradedDamage2, mallet.getDamage());
    }    

    @Test
    public void test_blowtorch_upgrade() {
        BlowtorchTower blowtorch = new BlowtorchTower(0, 0);

        int upgradedDamage = blowtorch.getDamage() + blowtorch.getUpgradeMap().get(Upgrade.Damage).intValue();
        double upgradedAoeRange = blowtorch.getAoeRange() + blowtorch.getUpgradeMap().get(Upgrade.AoeRange).doubleValue();
        double upgradedRange = blowtorch.getRange() + blowtorch.getUpgradeMap().get(Upgrade.Range).doubleValue();

        blowtorch.upgrade(Upgrade.Damage);
        blowtorch.upgrade(Upgrade.AoeRange);
        blowtorch.upgrade(Upgrade.SetOnFire);
        blowtorch.upgrade(Upgrade.Range);

        assertEquals(upgradedDamage, blowtorch.getDamage());
        assertEquals(upgradedAoeRange, blowtorch.getAoeRange());
        assertTrue(blowtorch.hasUpgrade(Upgrade.SetOnFire));
        assertEquals(upgradedRange, blowtorch.getRange());
    }    

    @Test
    public void test_slicer_upgrade() {
        SlicerTower slicer = new SlicerTower(0, 0);
        
        int upgradedDamage1 = slicer.getDamage() + slicer.getUpgradeMap().get(Upgrade.Damage).intValue();
        double upgradedAoeRange = slicer.getAoeRange() + slicer.getUpgradeMap().get(Upgrade.AoeRange).doubleValue();

        slicer.upgrade(Upgrade.Damage);
        slicer.upgrade(Upgrade.AoeRange);

        assertEquals(upgradedDamage1, slicer.getDamage());
        assertEquals(upgradedAoeRange, slicer.getAoeRange());

        int upgradedDamage2 = slicer.getDamage() + slicer.getUpgradeMap().get(Upgrade.Damage2).intValue();        
        slicer.upgrade(Upgrade.Damage2);
        assertEquals(upgradedDamage2, slicer.getDamage());
    }    

    @Test
    public void test_freezer_upgrade() {
        FreezerTower freezer = new FreezerTower(0, 0);

        double upgradedRange = freezer.getRange() + freezer.getUpgradeMap().get(Upgrade.Range).doubleValue();

        freezer.upgrade(Upgrade.Frostbite);
        freezer.upgrade(Upgrade.SuperChill);
        freezer.upgrade(Upgrade.ConditionDuration);
        freezer.upgrade(Upgrade.Range);

        assertTrue(freezer.hasUpgrade(Upgrade.Frostbite));
        assertTrue(freezer.hasUpgrade(Upgrade.SuperChill));
        assertTrue(freezer.hasUpgrade(Upgrade.ConditionDuration));
        assertEquals(upgradedRange, freezer.getRange());
    }
}
