package Unit6;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Leo")
public class Leo extends Unit{
	public Leo() {
		super("Leo", "general leo");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{7673};
				break;
			case HP:
				ids = new int[]{7674};
				break;
			case S1:
				ids = new int[]{6901};
				break;
			case S2:
				ids = new int[]{6905};
				break;
			case EX:
				ids = new int[]{7350, 7529};
				break;
			case AA:
				ids = new int[]{7358};
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
			case 6901: //S1
				ret.fixMissingAuraAilment(1424, 496, null, Ailment.Target.Party);
				break;
			case 6905: //S2
				ret.fixDelayHitData(5126);
				ret.fixDelayHitData(5127);
				ret.addStaticHit("Doubles battery and delay if target has 「**" + super.getSpecificAilment(1356).getName() + "**」");
				break;
			case 7529:
				ret.setName(ret.getName() + "+");
				ret.addStaticHit("Enabled if enemy is ST", 0);
			case 7350:
				ret.fixMissingAuraAilment(1358, 497, null, Ailment.Target.Party);
				break;
			case 7358: //AA
				ret.fixMissingAuraAilment(1420, 544, null, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}