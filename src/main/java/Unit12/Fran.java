package Unit12;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Fran")
public class Fran extends Unit{
	public Fran() {
		super("Fran");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{4049};
				break;
			case HP:
				ids = new int[]{7378};
				break;
			case S1:
				ids = new int[]{4047, 4048};
				break;
			case S2:
				ids = new int[]{4054, 4055};
				break;
			case EX:
				ids = new int[]{7376, 7377};
				break;
			case AA:
				ids = new int[]{7382};
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
			case 4047:
			case 4048:
			case 4054:
			case 4055:
			case 7376:
			case 7377: //TODO IM LAZY FIX ME PROPERLY
				if(ret.getAilmentById(506).getRank() != -1) {
					ret.getAilmentById(506).setRank(-1);
					ret.getAilmentById(506).getEffects().clear();
					ret.addStaticAilmentEffect(506, "-20% DEF");
					ret.addStaticAilmentEffect(506, "-10% ATK at 2 stacks");
					ret.addStaticAilmentEffect(506, "-20% Max BRV at 3 stacks");
					ret.addStaticAilmentEffect(506, "-20% Magic Damage dealt at 4 stacks");
					ret.addStaticAilmentEffect(506, "Lowers Ranged Resist at 5 stacks");
				}
				break;
		}
		return ret;
	}
}