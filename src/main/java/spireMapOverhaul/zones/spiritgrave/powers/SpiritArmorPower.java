package spireMapOverhaul.zones.spiritgrave.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import spireMapOverhaul.SpireAnniversary6Mod;

//TODO: test.
public class SpiritArmorPower extends AbstractSpiritGravePower {

    public static final String POWER_ID = SpireAnniversary6Mod.makeID("SpiritArmor");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SpiritArmorPower(AbstractCreature owner) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, 0);
    }

    //REF: TungstenRod (base game relic)
    //public int onLoseHpLast(int damageAmount) {   //No such Power method, only Relic.
    public int onLoseHp(int damageAmount) {
        if (damageAmount > 0) {
            this.flash();
            return damageAmount - 1;    //In theory can't infinite loop
        } else {
            return damageAmount;
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
