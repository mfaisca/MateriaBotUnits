package Unit6;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Setzer")
public class Setzer extends Unit{
	public Setzer() {
		super("Setzer");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{6200};
				break;
			case HP:
				ids = new int[]{6099};
				break;
			case S1:
				ids = new int[]{4718};
				break;
			case S2:
				ids = new int[]{2774};
				break;
			case EX:
				ids = new int[]{6201};
				break;
			case AA:
				ids = new int[]{4515};
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
			case 6200: //BRV
				ret.addStaticHit("If BRV >= 80% Max BRV");
				ret.fixMergeAbility(2562);
				break;
			case 2774: //S2
				ret.fixDelayHitData(949);
				break;
			case 4718:
				ret.fixDelayHitData(3077);
				ret.fixDelayHitData(3266);
				break;
			case 6201:
				ret.getAilmentById(1231).getEffects().stream()
						.filter(eg -> eg.effectId == 5)
						.findFirst().get().rankData = new String[] {"20020020"};
				ret.getAilmentById(1231).getEffects().stream()
						.filter(eg -> eg.effectId == 112)
						.findFirst().get().rankData = new String[] {"50050050"};
				ret.getAilmentById(1231).getEffects().stream()
						.filter(eg -> eg.effectId == 8)
						.findFirst().get().rankData = new String[] {"40040040"};
				ret.fixMissingAuraAilment(1231, 384, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1231, 385, null, Ailment.Target.Party);
				break;
			case 4515: //AA
				ret.fixMissingAuraAilment(989, 206, null, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}