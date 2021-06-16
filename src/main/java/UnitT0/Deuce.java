package UnitT0;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Deuce")
public class Deuce extends Unit{
	public Deuce() {
		super("Deuce", "deucey", "doot");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{5226};
				break;
			case HP:
				ids = new int[]{5214};
				break;
			case S1:
				ids = new int[]{5209};
				break;
			case S2:
				ids = new int[]{5222};
				break;
			case EX:
				ids = new int[]{5230};
				break;
			case AA:
				ids = new int[]{5234};
				break;
			case LD:
				ids = new int[]{12026};
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
			case 5214: //HP+
				ret.getHitDataById(6074).setArguments(new Integer[] {10, 15, 20});
				ret.addStaticHit("Battery potency increases based on number of ?**" + this.getSpecificAilment(693).getName() + "**」(1/2/3) on the party");
				break;
			case 5222: //S2
				ret.addStaticAilmentEffect(696, "Buff Potency increased based on number of ?**" + this.getSpecificAilment(693).getName() + "**」(1/2/3) on the party");
				ret.addStaticAilmentEffect(696, "10/20/40% Max BRV");
				ret.addStaticAilmentEffect(696, "BRV Regen (10/20/40% IBRV)");
				break;
			case 5234: //AA
				ret.fixMissingAuraAilment(1083, 270, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1083, 271, Ailment.EffectType.E67, Ailment.Target.Party);
				break;
			case 5230: //EX
				ret.fixMissingAuraAilment(1117, 300, Ailment.EffectType.E67, Ailment.Target.Party);
				break;
			case 12026:
				ret.getAilmentById(2388).setRank(0);
				ret.fixMissingAuraAilment(2388, 1072, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2388, 1073, Ailment.EffectType.E67, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2388, 1074, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2388, 1075, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2388, 1076, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2388, 1096, null, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}