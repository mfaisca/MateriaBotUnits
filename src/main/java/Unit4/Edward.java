package Unit4;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Edward")
public class Edward extends Unit{
	public Edward() {
		super("Edward", "ed", "gilbert", "gilbart");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{9697};
				break;
			case HP:
				ids = new int[]{9701};
				break;
			case S1:
				ids = new int[]{9684};
				break;
			case S2:
				ids = new int[]{9392, 9396};
				break;
			case EX:
				ids = new int[]{9688};
				break;
			case AA:
				ids = new int[]{9713};
				break;
			case LD:
				switch(region) {
					case "GL":
						ids = new int[]{9693, 9717}; break;
					case "JP":
						ids = new int[]{12645, 9717}; break;
				}
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
			case 9717:
				ret.setName(ret.getName().replace(".", "")); break;
			case 9684: //S1
				ret.fixMissingAuraAilment(1747, 762, Ailment.EffectType.E1, Ailment.Target.Allies);
				ret.fixMissingAuraAilment(1747, 764, Ailment.EffectType.E67, Ailment.Target.Allies);
				ret.fixMissingAuraAilment(1747, 763, Ailment.EffectType.E8, Ailment.Target.Party);
				ret.getAilmentById(1747).removeEffect(287, 288);
				ret.addStaticAilmentEffect(1747, "-1 duration when hit", 0);
				ret.addStaticAilmentEffect(1747, "All values doubled if 「**" + this.getSpecificAilment(1749).getName() + "**」 is active", 0);
				break;
			case 9392: //S2
				ret.removeHitDataById(9232);
				ret.getAilmentById(1748).removeEffect(194);
				break;
			case 9396: //S2+
				ret.removeHitDataById(10105);
				ret.addStaticHit("Enabled when 「**" + this.getSpecificAilment(1748).getName() + "**」 is active", 0);
				ret.addStaticHit("Free ability use", 0);
				break;
			case 9688: //EX
				ret.addStaticAilmentEffect(1750, "Effects depend on stacks", 0);
				break;
			case 12645:
				ret.getAilmentById(2657).removeEffect(287);
				ret.fixMissingAuraAilment(2657, 1179, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2657, 1180, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2657, 1181, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2657, 1182, null, Ailment.Target.Party);
				ret.addStaticAilmentEffect(2657, "+10% party BRV Damage dealt if 「**" + this.getSpecificAilment(1749).getName() + "**」 is active");
			case 9693: //LD
				ret.getAilmentById(1751).removeEffect(288);
				ret.addStaticAilmentEffect(1751, "-1 duration when hit", 0);
				break;
		}
		return ret;
	}
}