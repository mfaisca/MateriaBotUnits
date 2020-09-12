package Unit13;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Fang")
public class Fang extends Unit{
	public Fang() {
		super("Fang");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{5096, 5099};
				break;
			case HP:
				ids = new int[]{5102, 5103};
				break;
			case S1:
				ids = new int[]{5095};
				break;
			case S2:
				ids = new int[]{5098};
				break;
			case EX:
				ids = new int[]{5110, 5111};
				break;
			case AA:
				ids = new int[]{5115};
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

	private boolean s1 = false;
	private boolean s2 = false;
	
	@Override
	public Ability getSpecificAbility(int id){
		Ability ret = super.getSpecificAbility(id);
		switch(ret.getId()) {
			case 5096: //BRV+Sab
			case 5102: //HP+Sab
				ret.addStaticHit("+50% BRV Damage on debuffed targets", 0);
				break;
			case 5115: //AA
				ret.fixMissingAuraAilment(1063, 267, null, Ailment.Target.Party);
				break;
			case 5095: //S1
				ret.getAilmentById(644).removeEffect(22);
				if(!s1) {
					s1 = true;
					ret.removeAilmentById(1062);
					ret.getAilmentById(1062).setRank(-1);
					ret.fixMissingAuraAilment(1062, 265, Ailment.EffectType.E3, Ailment.Target.AoE);
					ret.addStaticAilmentEffect(1062, "+1 stack if target is debuffed", 0);
				}
				break;
			case 5098: //S2
				if(!s2) {
					s2 = true;
					ret.removeAilmentById(1062);
					ret.getAilmentById(1062).setRank(-1);
					ret.fixMissingAuraAilment(1062, 265, Ailment.EffectType.E3, Ailment.Target.AoE);
					ret.addStaticAilmentEffect(1062, "+1 stack if target is broken", 0);
				}
				break;
			case 5111: //EX+
				ret.addStaticHit("Enabled when 「**" + super.getSpecificAilment(1062).getName() + "**」 has 5 stacks", 0);
			case 5110: //EX
				ret.fixMissingAuraAilment(1062, 265, Ailment.EffectType.E3, Ailment.Target.AoE);
				ret.addStaticHit("+50% BRV Damage when attacking a broken target", 0);
		}
		return ret;
	}
}