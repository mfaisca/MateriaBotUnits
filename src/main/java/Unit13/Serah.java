package Unit13;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Serah")
public class Serah extends Unit{
	public Serah() {
		super("Serah");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{6256};
				break;
			case HP:
				ids = new int[]{6257};
				break;
			case S1:
				ids = new int[]{3913};
				break;
			case S2:
				ids = new int[]{3917};
				break;
			case EX:
				ids = new int[]{6101};
				break;
			case AA:
				ids = new int[]{4701};
				break;
			case LD:
				ids = new int[]{11753};
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
			case 3912:
			case 3913:
			case 6101:
				ret.fixMissingAuraAilment(910, 130, Ailment.EffectType.E2, Ailment.Target.Party);
				ret.fixMissingAuraAilment(910, 131, Ailment.EffectType.E3, Ailment.Target.Party);
				break;
			case 4701:
				ret.fixMissingAuraAilment(526, 216, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(526, 219, Ailment.EffectType.E67, Ailment.Target.Party);
				break;
			case 11753:
				ret.fixMissingAuraAilment(2484, 1040, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2484, 1042, null, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}