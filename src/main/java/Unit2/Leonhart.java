package Unit2;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Leonhart")
public class Leonhart extends Unit{
	public Leonhart() {
		super("Leonhart", "leon");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{5013, 5014};
				break;
			case HP:
				ids = new int[]{5015, 5016};
				break;
			case S1:
				ids = new int[]{5005, 5006};
				break;
			case S2:
				ids = new int[]{5009, 5010};
				break;
			case EX:
				ids = new int[]{9281, 9282};
				break;
			case AA:
				ids = new int[]{5020};
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
			case 5005:
			case 5009:
				while(ret.getDetails().getAilments().stream().anyMatch(e -> e.getId() == 618 && e.getRank() != -1)) {
					ret.removeAilmentById(618);
					ret.getAilmentById(618).setRank(-1);
				}
				break;
			case 5006:
			case 5010:
				while(ret.getDetails().getAilments().stream().anyMatch(e -> e.getId() == 618 && e.getRank() != -1)) {
					ret.removeAilmentById(618);
					ret.getAilmentById(618).setRank(-1);
				}
			case 5013:
			case 5015:
				ret.addStaticHit("Enabled when 「**" + getSpecificAilment(618).getName() + "**」 is active and HP >= 50%", 0);
				break;
			case 9282:
				ret.fixMissingAuraAilment(1732, 738, null, Ailment.Target.AoE);
//				ret.fixMissingAuraAilment(1732, 739, null, Ailment.Target.Party);
				ret.getAilmentById(1732).getAuras().remove(739);
				ret.addStaticAilmentEffect(1732, "Grants party Dark Absorb");
			case 5014:
			case 5016:
				ret.addStaticHit("Enabled when 「**" + getSpecificAilment(618).getName() + "**」 is active and HP >= 80%", 0);
				break;
		}
		return ret;
	}
}