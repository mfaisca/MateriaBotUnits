package Unit;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Garland")
public class Garland extends Unit{
	public Garland() {
		super("Garland");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				switch(region) {
					case "GL":
						ids = new int[]{5116}; break;
					case "JP":
						ids = new int[]{8792}; break;
				}
				break;
			case HP:
				switch(region) {
					case "GL":
						ids = new int[]{4814}; break;
					case "JP":
						ids = new int[]{8793}; break;
				}
				break;
			case S1:
				ids = new int[]{4812, 4813};
				break;
			case S2:
				ids = new int[]{4821, 4822};
				break;
			case EX:
				ids = new int[]{5120};
				break;
			case AA:
				ids = new int[]{4830};
				break;
			case LD:
				ids = new int[]{8950, 8951};
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
		ret.fixDelayHitData(5568);
		ret.fixDelayHitData(5569);
		switch(ret.getId()) {
			case 8950:
			case 8951:
				ret.getAilmentById(1015).setRank(-1);
				if(ret.getAilmentById(1559).getEffects().size() > 2){
					ret.getAilmentById(1559).getEffects().remove(0);
					ret.fixMissingAuraAilment(1559, 645, Ailment.EffectType.E3, Ailment.Target.AoE);
					ret.fixMissingAuraAilment(1559, 686, Ailment.EffectType.E2, Ailment.Target.AoE);
				}
				break;
		}
		return ret;
	}
}