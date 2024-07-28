package spireMapOverhaul.zones.spiritgrave;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import spireMapOverhaul.util.TexLoader;

import static spireMapOverhaul.SpireAnniversary6Mod.makeID;
import static spireMapOverhaul.SpireAnniversary6Mod.makeUIPath;

//REF: gravewoodGrove TransformOption
public class RemovalOption extends AbstractCampfireOption {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("RemovalOption"));
    public static final String[] TEXT = uiStrings.TEXT;

    public RemovalOption() {
        this.label = TEXT[0];
        this.description = TEXT[1];
        this.img = TexLoader.getTexture(makeUIPath("SpiritGrave/removal.png"));
    }

    @Override
    public void useOption() { AbstractDungeon.effectList.add(new RemovalEffect()); }
}
