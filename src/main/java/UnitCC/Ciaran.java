package UnitCC;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Ciaran")
public class Ciaran extends Unit{
	public Ciaran() {
		super("Ciaran", "cia");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				switch(region) {
					case "GL": ids = new int[]{6984}; break;
					case "JP": ids = new int[]{10411}; break;
				}
				break;
			case HP:
				switch(region) {
					case "GL": ids = new int[]{7208, 6993, 6994}; break;
					case "JP": ids = new int[]{10412, 10508, 10509}; break;
				}
				break;
			case S1:
				switch(region) {
					case "GL": ids = new int[]{5465}; break;
					case "JP": ids = new int[]{10406}; break;
				}
				break;
			case S2:
				switch(region) {
					case "GL": ids = new int[]{5473, 6988}; break;
					case "JP": ids = new int[]{5473, 10407}; break;
				}
				break;
			case EX:
				ids = new int[]{6999};
				break;
			case AA:
				ids = new int[]{7003};
				break;
			case LD:
				ids = new int[]{10017};
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
		Ailment drops = getSpecificAilment(1996);
		Ability.Details.Hit_Data hd = ret.getDetails().getHits().stream()
				.filter(h -> h.getId() == 10788 || h.getId() == 10789 || 
							 h.getId() == 10790 || h.getId() == 10792)
				.findFirst().orElse(null);
		if(hd != null)
			ret.getDetails().getHits().remove(hd);
		if(!ret.getDetails().getAilments().contains(drops) && ret.getId() != 6993 && ret.getId() != 6994 && ret.getId() != 6988) {
			ret.getDetails().getAilments().add(drops);
			ret.fixMissingAuraAilment(1996, 894, Ailment.EffectType.E1, Ailment.Target.Party);
			ret.fixMissingAuraAilment(1996, 895, Ailment.EffectType.E4, Ailment.Target.Party);
			ret.fixMissingAuraAilment(1996, 898, Ailment.EffectType.E5, Ailment.Target.Party);
			ret.fixMissingAuraAilment(1996, 899, Ailment.EffectType.E2, Ailment.Target.Party);
			ret.getAilmentById(1996).getAuras().remove(900);
			ret.getAilmentById(1996).getAuras().remove(901);
			ret.getAilmentById(1996).getAuras().remove(902);
			ret.getAilmentById(1996).removeEffect(85);
		}
		switch(ret.getId()) {
			case 6993: //HP++
				ret.addStaticHit("Enabled when ?**" + super.getSpecificAilment(1996).getName() + "**」 has 1 stack", 0);
				break;
			case 6994: //HP+++
			case 6988: //S2+
			case 10509: //HP+++ JP
			case 10407: //S2+ JP
				ret.removeHitDataById(7572);
				ret.removeHitDataById(7573);
				ret.removeHitDataById(10782);
				if(ret.getId() == 6994)
					ret.getHitDataById(7757).getArguments()[1] = 1996;
				else if(ret.getId() == 10407)
					;
				else
					;//ret.getHitDataById(7760).getArguments()[1] = 1996;
				ret.addStaticHit("Enabled when ?**" + super.getSpecificAilment(1996).getName() + "**」 has 5 stacks", 0);
				break;
			case 5465: //S1
				ret.fixMissingAuraAilment(1124, 301, Ailment.EffectType.E1, Ailment.Target.Party);
				break;
			case 10017: //LD
				ret.fixMissingAuraAilment(1844, 833, Ailment.EffectType.E4, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1844, 909, Ailment.EffectType.E8, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1844, 910, Ailment.EffectType.E67, Ailment.Target.Party);
				break;
			case 7003: //AA
				ret.fixMissingAuraAilment(1367, 453, Ailment.EffectType.E5, Ailment.Target.Party);
				break;
		}
		return ret;
	}
	
	@Override
	public Ailment getSpecificAilment(int id) {
		Ailment ret = super.getSpecificAilment(id);
		if(id == 1996) {
			ret.setName("Drop of Myrrh");
			ret.setArgs(new int[] {0});
		}
		return ret;
	}
}