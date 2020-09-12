package UnitCC;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Keiss")
public class Keiss extends Unit{
	public Keiss() {
		super("Keiss", "kiss");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{8345, 8347};
				break;
			case HP:
				ids = new int[]{8346, 8348};
				break;
			case S1:
				ids = new int[]{7633};
				break;
			case S2:
				ids = new int[]{7637};
				break;
			case EX:
				ids = new int[]{8344};
				break;
			case AA:
				ids = new int[]{7642};
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
			case 8347: //Chase BRV
			case 8348: //Chase HP
				ret.addStaticHit("**Enabled during chase**", 0);
				break;
			case 7633: //S1
				ret.fixMissingAuraAilment(1461, 546, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1461, 620, Ailment.EffectType.E5, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1461, 547, null, Ailment.Target.Party);
				break;
			case 7637:
				Ailment ailS2 = super.getSpecificAilment(1551);
				if(ailS2 != null && !ret.getDetails().getAilments().contains(ailS2)) {
					ailS2.setTarget(Ailment.Target.Ally);
					ret.getDetails().getAilments().add(ailS2);
				}
				break;
			case 8344: //EX
				ret.fixDelayHitData(6614);
				ret.fixMissingAuraAilment(1462, 549, Ailment.EffectType.E3, Ailment.Target.AoE);
				ret.fixMissingAuraAilment(1462, 548, null, Ailment.Target.Party);
				break;
			case 7642: //AA
				ret.fixMissingAuraAilment(1552, 621, Ailment.EffectType.E1, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}