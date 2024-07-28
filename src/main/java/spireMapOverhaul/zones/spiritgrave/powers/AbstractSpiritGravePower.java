package spireMapOverhaul.zones.spiritgrave.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import spireMapOverhaul.abstracts.AbstractSMOPower;
import spireMapOverhaul.zones.spiritgrave.SpiritGraveZone;

public class AbstractSpiritGravePower  extends AbstractSMOPower {
    public AbstractSpiritGravePower(String ID, String NAME, PowerType powerType, boolean isTurnBased, AbstractCreature owner, int amount) {
        super(ID, NAME, SpiritGraveZone.ID, powerType, isTurnBased, owner, amount);
    }
}
