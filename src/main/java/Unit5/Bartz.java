package Unit5;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Bartz")
public class Bartz extends Unit{
	public Bartz() {
		super("Bartz", "butz", "buttz");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				switch(region) {
					case "GL":
						ids = new int[]{3485}; break;
					case "JP":
						ids = new int[]{12615}; break;
				}
				break;
			case HP:
				ids = new int[]{6175};
				break;
			case S1:
				switch(region) {
					case "GL":
						ids = new int[]{6377, 6378}; break;
					case "JP":
						ids = new int[]{12606, 12607}; break;
				}
				break;
			case S2:
				switch(region) {
					case "GL":
						ids = new int[]{6381, 6382}; break;
					case "JP":
						ids = new int[]{12610, 12611}; break;
				}
				break;
			case EX:
				ids = new int[]{6174};
				break;
			case AA:
				ids = new int[]{6143};
				break;
			case LD:
				ids = new int[]{12614, 12616};
				break;
			case BT:
				ids = new int[]{12570};
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
			case 6377:
			case 6378:
				ret.setMastery(6377, 6378, 1);
				break;
			case 6381:
			case 6382:
				ret.setMastery(6381, 6382, 1);
				break;
			case 6174:
				ret.fixMissingAuraAilment(1227, 370, Ailment.EffectType.E67, Ailment.Target.Party);
				break;
			case 6143:
				ret.fixMissingAuraAilment(1219, 371, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1219, 372, Ailment.EffectType.E5, Ailment.Target.Party);
				break;
			case 12616: //HP+ from LD
				ret.fixRemoveDispels();
				ret.addStaticHit("Enabled after using ?**" + this.getSpecificAbility(12614).getName() + "**」 for 1 use");
				break;
			case 12614: //LD
				ret.fixMissingAuraAilment(2492, 1185, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2492, 1156, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2492, 1157, Ailment.EffectType.E67, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2492, 1158, null, Ailment.Target.Party);
				ret.addStaticHit("Enables ?**" + super.getSpecificAbility(12616).getName() + "**」 for 1 use");
				break;
			case 12570: //BT
				ret.fixMissingAuraAilment(2502, 1159, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2502, 1160, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2502, 1161, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2502, 1162, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2502, 1163, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2502, 1164, null, Ailment.Target.Party);
				ret.getAilmentById(2502).removeEffect(409);
				ret.addStaticAilmentEffect(2502, "+1 stack to self when Bartz acts", 0);
				ret.addStaticAilmentEffect(2502, "__At 1+ stack:__", 1);
				ret.addStaticAilmentEffect(2502, "__At 2+ stacks:__");
				ret.addStaticAilmentEffect(2502, "+30% party BRV Damage dealt");
				ret.addStaticAilmentEffect(2502, "+20% party Maximum BRV damage limit");
				ret.addStaticAilmentEffect(2502, "__At 3 stacks:__");
				ret.addStaticAilmentEffect(2502, "After HP attack, raises BRV by 50% of HP Damage Dealt");
				ret.addStaticAilmentEffect(2502, "+20% party HP Damage dealt");
				ret.getAilmentById(2502).getAuras().remove(1161);
				ret.getAilmentById(2502).getAuras().remove(1162);
				ret.getAilmentById(2502).getAuras().remove(1163);
				ret.getAilmentById(2502).getAuras().remove(1164);
				break;
		}
		return ret;
	}
}