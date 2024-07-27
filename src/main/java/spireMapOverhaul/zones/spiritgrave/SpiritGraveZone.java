package spireMapOverhaul.zones.spiritgrave;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.rooms.EventRoom;
import spireMapOverhaul.abstracts.AbstractZone;
import spireMapOverhaul.zoneInterfaces.*;
import spireMapOverhaul.zones.gremlinTown.GremlinTown;
import spireMapOverhaul.zones.gremlinTown.cards.*;
import spireMapOverhaul.zones.gremlinTown.monsters.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static spireMapOverhaul.util.Wiz.atb;
import static spireMapOverhaul.util.Wiz.forAllMonstersLiving;

//REFS: GremlinTown, TheFogZone
public class SpiritGraveZone extends AbstractZone
        implements EncounterModifyingZone,
        RewardModifyingZone,
        ShopModifyingZone,
        CombatModifyingZone,
        ModifiedEventRateZone {
    public static final String ID = "SpiritGrave";
    private static ArrayList<AbstractCard> spiritGraveCards;
    private static ArrayList<AbstractCard> commonspiritGraveCards;
    private static ArrayList<AbstractCard> uncommonspiritGraveCards;
    private static ArrayList<AbstractCard> rarespiritGraveCards;

    private final Color zoneColor;

    public SpiritGraveZone() {
        super(ID, Icons.MONSTER, Icons.EVENT, Icons.SHOP);
        width = 1;
        maxWidth = 1;
        height = 5;
//        maxHeight = 5;
        zoneColor = Color.TEAL.cpy();
        zoneColor.a = 1.0F;
    }

    @Override
    public Color getColor() {
        return zoneColor;
    }

    @Override
    public boolean canSpawn() {
        return isAct(2);
    }

    @Override
    public AbstractZone copy() {
        return new SpiritGraveZone();
    }

    @Override
    public void atPreBattle() {
        if (AbstractDungeon.getCurrRoom().monsters == null) {
            return;
        }
        forAllMonstersLiving(m -> {
            atb(new ApplyPowerAction(m, null, new WeakPower(m, 1, false)));
            atb(new ApplyPowerAction(m, null, new VulnerablePower(m, 1, false)));
        });
        atb(new MakeTempCardInDrawPileAction(new Dazed(), 1 , true, true));
    }

    /*@Override
    public void manualRoomPlacement(Random rng) {
        // Replace every node with a ? room
        for (MapRoomNode node : nodes) node.setRoom(new EventRoom());
    }*/
    @Override
    public void manualRoomPlacement(Random rng) {

    }

    /*@Override
    public boolean canIncludeTreasureRow() {
        return false;
    }

    @Override
    protected boolean canIncludeEarlyRows() {
        return false;
    }*/

    @Override
    protected boolean canIncludeFinalCampfireRow() {
        return false;
    }

    @Override
    protected boolean allowAdditionalEntrances() {
        return false;
    }

    @Override
    public float zoneSpecificEventRate() {
        return 0.33f;   //Gremlin Town has 1.0F
    }

    @Override
    public List<ZoneEncounter> getNormalEncounters() {
        return Arrays.asList(
                /*new ZoneEncounter(GREMLIN_RIDERS, 2, () -> new MonsterGroup(
                        new AbstractMonster[] {
                                new GremlinRiderGreen(-320.0F, -12.0F),
                                new GremlinRiderRed(-100.0F, -30.0F),
                                new GremlinRiderBlue(150.0F, 15.0F)
                        })),
                new ZoneEncounter(GREMLIN_GANG_TWO, 2, () -> new MonsterGroup(
                        new AbstractMonster[] {
                                new ArmoredGremlin(-430.0F, -25.0F),
                                new GremlinBarbarian(-170.0F, -21.0F),
                                new GremlinAssassin(115.0F, -30.0F)
                        })),
                new ZoneEncounter(NIB, 2, () -> new MonsterGroup(new GremlinNib(0f, 0f)))*/
        );
    }

    @Override
    public List<ZoneEncounter> getEliteEncounters() {
        // Override that one method so the gremlin leader encounter isn't double registered
        return Arrays.asList(
                /*GREMLIN_LEADER_ENCOUNTER,
                new ZoneEncounter(GREMLIN_ELDER, 2, () -> new MonsterGroup(new GremlinElder(-80f, -50f))),
                new ZoneEncounter(GREMLIN_HORDE, 2, GremlinTown::getHordeStartGroup)*/
        );
    }

    //REF: GremlinTown
    private static void initSpiritGraveCards() {
        commonspiritGraveCards = new ArrayList<>();
        commonspiritGraveCards.add(new SneakAttack());

        uncommonspiritGraveCards = new ArrayList<>();
        uncommonspiritGraveCards.add(new Assassinate());

        rarespiritGraveCards = new ArrayList<>();
        rarespiritGraveCards.add(new Flurry());

        spiritGraveCards = new ArrayList<>();
        spiritGraveCards.addAll(commonspiritGraveCards);
        spiritGraveCards.addAll(uncommonspiritGraveCards);
        spiritGraveCards.addAll(rarespiritGraveCards);
    }
}
