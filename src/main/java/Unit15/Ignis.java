package Unit15;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Ignis")
public class Ignis extends Unit{
	public Ignis() {
		super("Ignis", "iggy", "recipeh");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{6670, 6672};
				break;
			case HP:
				ids = new int[]{6671, 6673};
				break;
			case S1:
				ids = new int[]{5932};
				break;
			case S2:
				ids = new int[]{5925, 6485, 6491};
				break;
			case EX:
				ids = new int[]{6499};
				break;
			case AA:
				ids = new int[]{6509};
				break;
			case LD:
				ids = new int[]{12283};
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
		Ability ret = super.getSpecificAbility(id);;
		switch(ret.getId()) {
			case 6670:
			case 6671:
				ret.addStaticHit("Enabled when ?**" + super.getSpecificAilment(1270).getName() + "**」 is active", 0);
				break;
			case 6672:
			case 6673:
				ret.addStaticHit("Enabled when ?**" + super.getSpecificAilment(1271).getName() + "**」 is active", 0);
				break;
			case 5932: //S1
				ret.removeHitDataById(7180);
				ret.fixMissingAuraAilment(1272, 391, Ailment.EffectType.E5, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1272, 390, Ailment.EffectType.E1, Ailment.Target.Party);
				break;
			case 6485: //S2 Memory
				ret.removeHitDataById(7048);
				ret.removeHitDataById(688);
				ret.removeHitDataById(689);
				ret.fixMissingAuraAilment(1270, 403, Ailment.EffectType.E7, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1270, 397, null, Ailment.Target.Party);
				break;
			case 6491: //S2 Papa
				ret.removeHitDataById(7048);
				ret.removeHitDataById(688);
				ret.removeHitDataById(689);
				ret.fixMissingAuraAilment(1271, 404, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1271, 398, Ailment.EffectType.E67, Ailment.Target.Party);
				break;
			case 6509: //AA
				ret.fixMissingAuraAilment(1273, 394, Ailment.EffectType.E5, Ailment.Target.Party);
				break;
			case 6499: //EX
				ret.addStaticHit("Adds an extra HP hit for 100% of each party member Current BRV");
				ret.addStaticHit("Both of your allies will act right after you, with chosen ally going first");
				break;
			case 12283:
				ret.fixMissingAuraAilment(2486, 1043, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2486, 1095, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2486, 1143, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2486, 1144, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2486, 1145, Ailment.EffectType.E67, Ailment.Target.Party);
				ret.getAilmentById(2486).setArgs(2485);
				break;
		}
		return ret;
	}
}