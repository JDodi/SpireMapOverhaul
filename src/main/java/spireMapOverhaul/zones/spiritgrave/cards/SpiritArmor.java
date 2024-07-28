package spireMapOverhaul.zones.spiritgrave.cards;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import spireMapOverhaul.SpireAnniversary6Mod;
import spireMapOverhaul.abstracts.AbstractSMOCard;
import spireMapOverhaul.zones.Junkyard.Junkyard;
import spireMapOverhaul.zones.spiritgrave.powers.SpiritArmorPower;

import static spireMapOverhaul.util.Wiz.atb;

public class SpiritArmor extends AbstractSMOCard {
    public static final String ID = SpireAnniversary6Mod.makeID("SpiritArmor");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 0;

    public SpiritArmor() {
        super(ID, SpiritArmor.ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.NONE);
    }


    // Tungsten rod effect
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ApplyPowerAction(p, p, new SpiritArmorPower(p)));
    }

    @Override
    public void upp() {

    }
}
