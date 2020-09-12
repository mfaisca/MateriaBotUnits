package UnitOthers;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Agrias")
public class Agrias extends Unit{
	public Agrias() {
		super("Agrias");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{5430};
				break;
			case HP:
				ids = new int[]{5425};
				break;
			case S1:
				ids = new int[]{5424};
				break;
			case S2:
				ids = new int[]{5429};
				break;
			case EX:
				ids = new int[]{5434};
				break;
			case AA:
				ids = new int[]{5438};
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
			case 5434:
				ret.fixMissingAuraAilment(1119, 310, Ailment.EffectType.E1, Ailment.Target.Party);
			case 5424:
				ret.fixAddAuraEffect(662);
				ret.getAilmentById(662).setRank(-1);
				ret.getAilmentById(662).setMaxStacks(5);
				ret.fixMissingAuraAilment(662, 23, Ailment.EffectType.E8, Ailment.Target.Party);
				ret.fixMissingAuraAilment(662, 26, Ailment.EffectType.E7, Ailment.Target.Party);
				ret.addStaticAilmentEffect(662, "+1 stack when taking HP damage", 0);
		}
		return ret;
	}
}