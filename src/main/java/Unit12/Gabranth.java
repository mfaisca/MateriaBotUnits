package Unit12;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Gabranth")
public class Gabranth extends Unit{
	public Gabranth() {
		super("Gabranth", "gab", "gabby", "noah");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{9118};
				break;
			case HP:
				ids = new int[]{9119};
				break;
			case S1:
				switch(region) {
					case "GL":
						ids = new int[]{8513, 10379}; break;
					case "JP":
						ids = new int[]{11663, 10379}; break;
				}
				break;
			case S2:
				ids = new int[]{8538};
				break;
			case EX:
				ids = new int[]{9134, 10379};
				break;
			case AA:
				ids = new int[]{8522};
				break;
			case LD:
				ids = new int[]{11502};
				break;
			case BT:
				ids = new int[]{};
				break;
			case CA:
				ids = new int[]{};
				break;
		}
		if(ids.length == 0)
			return super.getAbility(type, region);
		else
			return IntStream.of(ids).boxed().map(i -> this.getSpecificAbility(i)).collect(Collectors.toList());
	}
	
	@Override
	public Ability getSpecificAbility(int id){
		Ability ret = super.getSpecificAbility(id);
		switch(ret.getId()) {
			case 9134: //EX
				ret.removeHitDataById(5311);
				ret.addStaticHit("100% chance to BREAK target if 「**" + this.getSpecificAilment(1583).getName() + "**」 is on target", 0);
				ret.addStaticHit("Cancels target BREAK status if 「**" + this.getSpecificAilment(1583).getName() + "**」 is on target", 0);
				ret.removeAilmentById(161);
				ret.fixRemoveDispels();
				ret.getHitDataById(5862).setArguments(new Integer[] {20});
				ret.addStaticHit("Has 50% splash instead if 「**" + this.getSpecificAilment(1583).getName() + "**」 is on target");
			case 8513: //S1 GL
			case 11663: //S1 JP
				ret.getAilmentById(1581).removeEffect(69, 184);
				ret.addStaticAilmentEffect(1581, "Counter All Attacks against party with 「**" + this.getSpecificAbility(10379).getName() + "**」");
				Ailment.Aura a1 = ret.fixMissingAuraAilment(1581, 690, Ailment.EffectType.E58, Ailment.Target.Party);
				a1.rankData[0] = a1.rankData[0].replace("-", "");
				a1.rankData[1] = a1.rankData[1].replace("-", "");
				ret.fixMissingAuraAilment(1581, 630, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1581, 735, Ailment.EffectType.E5, Ailment.Target.Party);
				break;
			case 8538: //S2
				ret.removeHitDataById(5311);
				ret.addStaticHit("100% chance to BREAK target if 「**" + this.getSpecificAilment(1583).getName() + "**」 is on target", 0);
				ret.addStaticHit("Cancels target BREAK status if 「**" + this.getSpecificAilment(1583).getName() + "**」 is on target", 0);
				ret.removeAilmentById(161);
				ret.fixRemoveDispels();
				ret.getHitDataById(5862).setArguments(new Integer[] {20});
				ret.addStaticHit("Has 50% splash instead if 「**" + this.getSpecificAilment(1583).getName() + "**」 is on target");
				break;
			case 8522: //AA
				ret.getAilmentById(1730).removeEffect(1);
				ret.addStaticAilmentEffect(1730, "+20% ATK against debuffed targets", 0);
				ret.fixAddAuraEffect(1730);
				Ailment.Aura a = ret.getAilmentById(1730).getAuras().get(734);
				a.target = Ailment.Target.Party.getId();
				a.rankData[0] = a.rankData[0].replace("-", "");
				break;
			case 11502:
				Ailment.Aura a2 = ret.fixMissingAuraAilment(2398, 1012, null, Ailment.Target.Party);
				a2.rankData[0] = a2.rankData[0].replace("-", "");
				break;
			case 10379: //Debuff Counter
				ret.setName("Debuff Counter");
				ret.getDetails().setMovementCost(30);
				ret.addStaticHit("Apply 「**" + this.getSpecificAilment(1582).getName() + "**」 on attacker");
				ret.addStaticHit("Apply 「**" + this.getSpecificAilment(1679).getName() + "**」 on attacker");
				ret.addStaticAilmentEffect(1582, "Turns into 「**" + this.getSpecificAilment(1583).getName() + "**」 with 4 or more debuffs");
				ret.removeAilmentById(1583);
				if(ret.getAilmentById(1583) == null)
					ret.getDetails().getAilments().add(1, this.getSpecificAilment(1583));
				break;
		}
		return ret;
	}
	
	@Override
	public Ailment getSpecificAilment(int id){
		Ailment ret = super.getSpecificAilment(id);
		if(ret == null && id == 1583) {
			Ailment fi = new Ailment(this);
			fi.setId(1583);
			fi.setName("重罪人の刻印");
			fi.setRate(100);
			fi.setTarget(Ailment.Target.ST);
			fi.setRank(0);
			fi.setDuration(-1);
			fi.setArgs(new int[] {});
			fi.getEffects().add(new Ailment.EffectGrouping(1));
			fi.getEffects().add(new Ailment.EffectGrouping(32));
			fi.getEffects().get(0).val_specify = -40;
			fi.getEffects().get(1).val_specify = 80;
			fi.getEffects().add(new Ailment.EffectGrouping("Turns into 「**" + this.getSpecificAilment(1582).getName() + "**」 with 3 or less debuffs"));
			this.getAilments().put(1583, fi);
			ret = fi;
		}
		switch(ret.getId()) {
			case 1581:
				ret.setName("Judge Magister");
				break;
			case 1582:
				ret.setName("Mark of Guilt");
				break;
			case 1583:
				ret.setName("Felon's Imprint");
				break;
			case 1679:
				ret.setName("Judge’s Sentence");
				break;
		}
		return ret;
	}
}