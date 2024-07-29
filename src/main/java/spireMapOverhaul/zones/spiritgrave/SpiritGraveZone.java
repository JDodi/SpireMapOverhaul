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
import com.megacrit.cardcrawl.potions.GhostInAJar;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.rooms.EventRoom;
import com.megacrit.cardcrawl.shop.ShopScreen;
import com.megacrit.cardcrawl.shop.StorePotion;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.ui.campfire.RestOption;
import spireMapOverhaul.abstracts.AbstractZone;
import spireMapOverhaul.zoneInterfaces.*;
import spireMapOverhaul.zones.gremlinTown.GremlinTown;
import spireMapOverhaul.zones.gremlinTown.cards.*;
import spireMapOverhaul.zones.gremlinTown.monsters.*;
import spireMapOverhaul.zones.gremlincamp.ScavengeOption;
import spireMapOverhaul.zones.spiritgrave.cards.SpiritArmor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static spireMapOverhaul.util.Wiz.atb;
import static spireMapOverhaul.util.Wiz.forAllMonstersLiving;

//REFS: GremlinTown, GremlinCamp, TheFogZone, CandyLand (rewards)
public class SpiritGraveZone extends AbstractZone
        implements  CampfireModifyingZone,
        CombatModifyingZone,
        EncounterModifyingZone,
        ModifiedEventRateZone,
        RewardModifyingZone,
        ShopModifyingZone {
    public static final String ID = "SpiritGrave";
    private static ArrayList<AbstractCard> spiritGraveCards;
    private static ArrayList<AbstractCard> commonspiritGraveCards;
    private static ArrayList<AbstractCard> uncommonspiritGraveCards;
    private static ArrayList<AbstractCard> rarespiritGraveCards;

    private final Color zoneColor;

    public SpiritGraveZone() {
        super(ID, Icons.MONSTER, Icons.MONSTER, Icons.REST, Icons.SHOP, Icons.EVENT);
        width = 3;      //intended 1
        maxWidth = 3;   //intended 2
        height = 6;     //intended 5
//        maxHeight = 5;
        zoneColor = Color.TEAL.cpy();
        zoneColor.a = 1.0F;
    }

    @Override
    public Color getColor() {
        return zoneColor;
    }

    //intended act 2
    /*@Override
    public boolean canSpawn() {
        return isAct(2);
    }*/

    @Override
    public AbstractZone copy() {
        return new SpiritGraveZone();
    }

    @Override
    public void atPreBattle() {
        if (AbstractDungeon.getCurrRoom().monsters == null) {
            return;
        }
        /*forAllMonstersLiving(m -> {
            atb(new ApplyPowerAction(m, null, new WeakPower(m, 1, false)));
        });
        atb(new MakeTempCardInDrawPileAction(new Dazed(), 1 , true, true));
        */
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

    @Override
    public void postAddButtons(ArrayList<AbstractCampfireOption> buttons) {
        for (AbstractCampfireOption op : buttons) {
            if (op instanceof RestOption && op.usable) {
                op.usable = false;
                ((RestOption) op).updateUsability(false);
                break;
            }
        }
        //To be moved to Elite fight rewards.
        buttons.add(new RemovalOption());
    }

    //REF: PeacePipe.class
    /*
    public void addCampfireOption(ArrayList<AbstractCampfireOption> options) {
        options.add(new TokeOption(!CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()).isEmpty()));
    }*/

    //REF: CandyLand
    @Override
    public void postCreateShopPotions(ShopScreen screen, ArrayList<StorePotion> potions) {
        for(StorePotion potion : potions){
            if(!Objects.equals(potion.potion.ID, GhostInAJar.POTION_ID)){
                //potion.potion = AbstractDungeon.returnRandomPotion(potion.potion.rarity, true);
                potion.potion = new GhostInAJar();
                break;
            }
        }
    }

    //REF: GremlinTown
    private static void initSpiritGraveCards() {
        commonspiritGraveCards = new ArrayList<>();
        commonspiritGraveCards.add(new SneakAttack());

        uncommonspiritGraveCards = new ArrayList<>();
        uncommonspiritGraveCards.add(new Assassinate());

        rarespiritGraveCards = new ArrayList<>();
        rarespiritGraveCards.add(new SpiritArmor());

        spiritGraveCards = new ArrayList<>();
        spiritGraveCards.addAll(commonspiritGraveCards);
        spiritGraveCards.addAll(uncommonspiritGraveCards);
        spiritGraveCards.addAll(rarespiritGraveCards);
    }
}
