package Unit11;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import com.materiabot.GameElements.Ailment.Target;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Kamlanaut")
public class Kamlanaut extends Unit{
	public Kamlanaut() {
		super("Kam'lanaut", "kamlanaut", "kam", "camelnut", "camel nut", "caramelnut", "caramel nut");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				switch(region) {
					case "GL":
						ids = new int[]{6733, 6734};
						break;
					case "JP":
						ids = new int[]{6733, 6734, 10347};
						break;
				}
				break;
			case HP:
				switch(region) {
					case "GL":
						ids = new int[]{6735, 6736};
						break;
					case "JP":
						ids = new int[]{10242, 10243};
						break;
				}
				break;
			case S1:
				switch(region) {
					case "GL":
						ids = new int[]{6713, 6714, 6968};
						break;
					case "JP":
						ids = new int[]{10227, 10229, 10269};
						break;
				}
				break;
			case S2:
				switch(region) {
					case "GL":
						ids = new int[]{6725, 6726};
						break;
					case "JP":
						ids = new int[]{10234, 10235};
						break;
				}
				break;
			case EX:
				ids = new int[]{6753, 6754};
				break;
			case AA:
				ids = new int[]{6758};
				break;
			case LD:
				ids = new int[]{10015};
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
		Ailment ail = this.getSpecificAilment(1329);
		String r = null;
		switch(ret.getId()) {
			case 10347: //BRV++
				Ailment ail2 = this.getSpecificAilment(1843);
				ret.addStaticHit("Enabled when ?**" + ail.getName() + "**」 and ?**" + ail2.getName() + "**」 are active", 0);
				ret.removeAilmentById(1577);
				break;
			case 6968: //S1++
				r = "BRV++";
			case 1029: //S1++ JP
				r = r + (r == null ? "/BRV+++" : "");
				ret.addStaticHit("Enabled after breaking/broken target with " + r + " for 1 use", 1);
				break;
			case 6734: //BRV+
			case 6736: //HP+
			case 6714: //S1+
			case 10229: //S1+ JP
				ret.addStaticHit("Enabled when ?**" + ail.getName() + "**」 is active", 0);
				break;
			case 6726: //S2+
				if(ret.getId() == 6726) ret.fixMergeAbility(6732);
			case 10235: //S2+ JP
				if(ret.getId() == 10235) ret.fixMergeAbility(10241);
			case 6754: //EX+
				ret.fixDelayHitData(6614);
				ret.addStaticHit("Enabled when ?**" + ail.getName() + "**」 is active", 0);
				Ailment ail3 = ret.getAilmentById(1329);
				if(ail3.getEffects().size() > 2) {
					ail3.getEffects().remove(0);
					ail3.getEffects().remove(0);
					ail3.getEffects().get(0).effectId = Ailment.EffectType.E60.getId();
					ret.fixMissingAuraAilment(1329, 424, Ailment.EffectType.E1, Target.AoE);
					ret.fixMissingAuraAilment(1329, 425, Ailment.EffectType.E5, Target.AoE);
					ail3.getAuras().remove(426);
					ret.addStaticAilmentEffect(1329, "Dispelled when broken");
				}
				break;
			case 6725: //S2
				if(ret.getId() == 6725) ret.fixMergeAbility(6731);
			case 10234: //S2 JP
				if(ret.getId() == 10234) ret.fixMergeAbility(10240);
			case 6753: //EX
				ret.fixRemoveDispels();
				ret.fixDelayHitData(6614);
				if(ret.getDetails().getAilments().stream().noneMatch(a -> a.getId() == 1328)) {
					Ailment eso = this.getSpecificAilment(1328);
					ret.getDetails().getAilments().add(0, eso);
					eso.getEffects().clear();
					eso.getEffects().add(new Ailment.EffectGrouping(60));
					ret.fixMissingAuraAilment(1328, 422, Ailment.EffectType.E1, Ailment.Target.AoE);
					ret.fixMissingAuraAilment(1328, 423, null, Ailment.Target.AoE);
					ret.addStaticAilmentEffect(1328, "Dispelled when broken");
				}
				ret.addStaticHit("Dispels ?**" + this.getSpecificAilment(1328).getName() + "**」 if 5 stacks and grants ?**" + ail.getName() + "**」", 0);
				Ailment ail5 = ret.getAilmentById(1329);
				if(ail5.getEffects().size() > 2) {
					ail5.getEffects().remove(0);
					ail5.getEffects().remove(0);
					ail5.getEffects().get(0).effectId = Ailment.EffectType.E60.getId();
					ret.fixMissingAuraAilment(1329, 424, Ailment.EffectType.E1, Target.AoE);
					ret.fixMissingAuraAilment(1329, 425, Ailment.EffectType.E5, Target.AoE);
					ail5.getAuras().remove(426);
					ret.addStaticAilmentEffect(1329, "Dispelled when broken");
				}
				break;
			case 10015: //LD
				Ailment ail4 = ret.getAilmentById(1329);
				if(ail4.getEffects().size() > 2) {
					ail4.getEffects().remove(0);
					ail4.getEffects().remove(0);
					ail4.getEffects().get(0).effectId = Ailment.EffectType.E60.getId();
					ret.fixMissingAuraAilment(1329, 424, Ailment.EffectType.E1, Target.AoE);
					ret.fixMissingAuraAilment(1329, 425, Ailment.EffectType.E5, Target.AoE);
					ail4.getAuras().remove(426);
					ret.addStaticAilmentEffect(1329, "Dispelled when broken");
				}
				ret.fixMissingAuraAilment(1843, 830, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1843, 831, Ailment.EffectType.E67, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}