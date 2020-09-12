package UnitOthers;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Ramza")
public class Ramza extends Unit{
	public Ramza() {
		super("Ramza");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				switch(region) {
					case "GL":
						ids = new int[]{8103, 8104, 8105}; break;
					case "JP":
						ids = new int[]{11019, 11020, 10899}; break;
				}
				break;
			case HP:
				ids = new int[]{8100, 8101, 8102};
				break;
			case S1:
				switch(region) {
					case "GL":
						ids = new int[]{4406}; break;
					case "JP":
						ids = new int[]{10893}; break;
				}
				break;
			case S2:
				switch(region) {
					case "GL":
						ids = new int[]{4567}; break;
					case "JP":
						ids = new int[]{10897}; break;
				}
				break;
			case EX:
				ids = new int[]{7919, 7920, 7921};
				break;
			case AA:
				ids = new int[]{4367};
				break;
			case LD:
				ids = new int[]{10812};
				break;
			case BT:
				ids = new int[]{9202};
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
			case 9202:
				ret.fixMissingAuraAilment(1706, 880, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1706, 693, Ailment.EffectType.E4, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1706, 694, Ailment.EffectType.E5, Ailment.Target.Party);
				ret.addStaticAilmentEffect(1706, "Effect increases per buff on party", 0);
				break;
			case 10812:
				ret.removeHitDataById(11393);
				ret.fixDelayHitData(6614);
				ret.fixMissingAuraAilment(981, 177, Ailment.EffectType.E5, Ailment.Target.Party);
				ret.fixMissingAuraAilment(981, 178, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.getAilmentById(981).setArgs(new int[] {1, 3});
				ret.getAilmentById(980).removeEffect(85);
				ret.fixMissingAuraAilment(1949, 877, Ailment.EffectType.E4, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1949, 878, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1949, 879, Ailment.EffectType.E8, Ailment.Target.Party);
				break;
			case 4406:
			case 10893:
				ret.getAilmentById(980).removeEffect(85);
				break;
			case 4567:
			case 10897:
				ret.fixMissingAuraAilment(981, 177, Ailment.EffectType.E5, Ailment.Target.Party);
				ret.fixMissingAuraAilment(981, 178, Ailment.EffectType.E1, Ailment.Target.Party);
				break;
			case 8103:
			case 11019:
				ret.addStaticHit("Enabled when 「**" + this.getSpecificAilment(981).getName() + " I**」 is active", 0);
				break;
			case 8104:
			case 11020:
				ret.addStaticHit("Enabled when 「**" + this.getSpecificAilment(981).getName() + " II**」 is active", 0);
				break;
			case 8105:
			case 10899:
				ret.addStaticHit("Enabled when 「**" + this.getSpecificAilment(981).getName() + " III**」 is active", 0);
				break;
			case 7919:
				ret.removeHitDataById(8723);
				ret.fixDelayHitData(8697);
			case 8100:
				ret.addStaticHit("Enabled when 「**" + this.getSpecificAilment(980).getName() + " I**」 is active", 0);
				break;
			case 7920:
				ret.removeHitDataById(8723);
				ret.fixDelayHitData(8697);
			case 8101:
				ret.addStaticHit("Enabled when 「**" + this.getSpecificAilment(980).getName() + " II**」 is active", 0);
				break;
			case 7921:
				ret.removeHitDataById(8723);
				ret.fixDelayHitData(8697);
			case 8102:
				ret.addStaticHit("Enabled when 「**" + this.getSpecificAilment(980).getName() + " III**」 is active", 0);
				break;
		}
		return ret;
	}
}