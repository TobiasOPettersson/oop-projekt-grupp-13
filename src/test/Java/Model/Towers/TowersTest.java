package Model.Towers;

import org.junit.jupiter.api.Test;

import Model.Enums.Upgrade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TowersTest {
    
    @Test
    public void test_knife_upgrade_damage() {
        KnifeTower knife = new KnifeTower(0, 0);
        int upgradedDamage = knife.getDamage() + knife.getUpgradeIntMap().get(Upgrade.Damage).intValue();
        knife.upgrade(Upgrade.Damage);
        assertEquals(upgradedDamage, knife.getDamage());
    }    

    @Test
    public void test_knife_upgrade_speed() {
        KnifeTower knife = new KnifeTower(0, 0);
        int upgradedSpeed = knife.getMaxCooldown() - knife.getUpgradeIntMap().get(Upgrade.Speed).intValue();
        knife.upgrade(Upgrade.Speed);
        assertEquals(upgradedSpeed, knife.getMaxCooldown());
    }

    @Test
    public void test_knife_upgrade_targets() {
        KnifeTower knife = new KnifeTower(0, 0);
        int upgradedTargets = knife.getNTargets() + knife.getUpgradeIntMap().get(Upgrade.Targets).intValue();
        knife.upgrade(Upgrade.Targets);
        assertEquals(upgradedTargets, knife.getNTargets());
    }

    @Test
    public void test_knife_upgrade_range() {
        KnifeTower knife = new KnifeTower(0, 0);
        double upgradedRange = knife.getRange() + knife.getUpgradeDoubleMap().get(Upgrade.Range).doubleValue();
        knife.upgrade(Upgrade.Range);
        assertEquals(upgradedRange, knife.getRange());
    }


    @Test
    public void test_mallet_upgrade_damage() {
        MalletTower mallet = new MalletTower(0, 0);
        int upgradedDamage = mallet.getDamage() + mallet.getUpgradeIntMap().get(Upgrade.Damage).intValue();
        mallet.upgrade(Upgrade.Damage);
        assertEquals(upgradedDamage, mallet.getDamage());
    }    

    @Test
    public void test_mallet_upgrade_range() {
        MalletTower mallet = new MalletTower(0, 0);
        double upgradedAoeRange = mallet.getAoeRange() + mallet.getUpgradeDoubleMap().get(Upgrade.AoeRange).doubleValue();
        mallet.upgrade(Upgrade.AoeRange);
        assertEquals(upgradedAoeRange, mallet.getAoeRange());
    }

    @Test
    public void test_mallet_upgrade_aoerange() {
        MalletTower mallet = new MalletTower(0, 0);
        mallet.upgrade(Upgrade.Range);
        assertEquals(1.5, mallet.getRange());
    }


    @Test
    public void test_blowtorch_upgrade_damage() {
        BlowtorchTower blowtorch = new BlowtorchTower(0, 0);
        int upgradedDamage = blowtorch.getDamage() + blowtorch.getUpgradeIntMap().get(Upgrade.Damage).intValue();
        blowtorch.upgrade(Upgrade.Damage);
        assertEquals(upgradedDamage, blowtorch.getDamage());
    }    

    @Test
    public void test_blowtorch_upgrade_aoerange() {
        BlowtorchTower blowtorch = new BlowtorchTower(0, 0);
        double upgradedAoeRange = blowtorch.getAoeRange() + blowtorch.getUpgradeDoubleMap().get(Upgrade.AoeRange).doubleValue();
        blowtorch.upgrade(Upgrade.AoeRange);
        assertEquals(upgradedAoeRange, blowtorch.getAoeRange());
    }

    @Test
    public void test_blowtorch_upgrade_range() {
        BlowtorchTower blowtorch = new BlowtorchTower(0, 0);
        blowtorch.upgrade(Upgrade.Range);
        assertEquals(3.0, blowtorch.getRange());
    }

    @Test
    public void test_blowtorch_upgrade_setonfire() {
        BlowtorchTower blowtorch = new BlowtorchTower(0, 0);
        blowtorch.upgrade(Upgrade.SetOnFire);
        assertTrue(blowtorch.hasUpgrade(Upgrade.SetOnFire));
    }

    @Test
    public void test_slicer_upgrade_damage() {
        SlicerTower slicer = new SlicerTower(0, 0);
        int upgradedDamage = slicer.getDamage() + slicer.getUpgradeIntMap().get(Upgrade.Damage).intValue();
        slicer.upgrade(Upgrade.Damage);
        assertEquals(upgradedDamage, slicer.getDamage());
    }    

    @Test
    public void test_slicer_upgrade_aoerange() {
        SlicerTower slicer = new SlicerTower(0, 0);
        double upgradedAoeRange = slicer.getAoeRange() + slicer.getUpgradeDoubleMap().get(Upgrade.AoeRange).doubleValue();
        slicer.upgrade(Upgrade.AoeRange);
        assertEquals(upgradedAoeRange, slicer.getAoeRange());
    }

    @Test
    public void test_freezer_upgrade_frostbite() {
        FreezerTower freezer = new FreezerTower(0, 0);
        freezer.upgrade(Upgrade.Frostbite);
        assertTrue(freezer.hasUpgrade(Upgrade.Frostbite));
    }

    @Test
    public void test_freezer_upgrade_superchill() {
        FreezerTower freezer = new FreezerTower(0, 0);
        freezer.upgrade(Upgrade.SuperChill);
        assertTrue(freezer.hasUpgrade(Upgrade.SuperChill));
    }

    @Test
    public void test_freezer_upgrade_conditionduration() {
        FreezerTower freezer = new FreezerTower(0, 0);
        freezer.upgrade(Upgrade.ConditionDuration);
        assertTrue(freezer.hasUpgrade(Upgrade.ConditionDuration));
    }

    @Test
    public void test_freezer_upgrade_range() {
        FreezerTower freezer = new FreezerTower(0, 0);
        freezer.upgrade(Upgrade.Range);
        assertEquals(1.5, freezer.getRange());
    }
}
