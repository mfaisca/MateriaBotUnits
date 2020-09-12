package UnitCC;
import com.materiabot.GameElements.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Sherlotta")
public class Sherlotta extends Unit{
	public Sherlotta() {
		super("Sherlotta", "sher", "lotta", "sherry");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{5023};
				break;
			case HP:
				ids = new int[]{5024};
				break;
			case S1:
				ids = new int[]{4465};
				break;
			case S2:
				ids = new int[]{4476};
				break;
			case EX:
				ids = new int[]{8745, 8746};
				break;
			case AA:
				ids = new int[]{4485};
				break;
			case LD:
				ids = new int[]{};
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
			case 4465: //S1
				ret.fixMissingAuraAilment(1049, 248, Ailment.EffectType.E5, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1049, 249, Ailment.EffectType.E7, Ailment.Target.Party);
				break;
			case 4476: //S2
				ret.removeHitDataById(4641);
				break;
			case 8745: //EX
				ret.fixMissingAuraAilment(1642, 672, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1642, 667, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1642, 668, Ailment.EffectType.E8, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1642, 669, Ailment.EffectType.E7, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1642, 670, null, Ailment.Target.Party);
				break;
			case 8746: //HP++
				ret.removeHitDataById(689);
				ret.addStaticHit("Enabled after using 「**" + this.getSpecificAbility(8745).getName() + "**」 for 1 use", 0);
				break;
		}
		return ret;
	}
}