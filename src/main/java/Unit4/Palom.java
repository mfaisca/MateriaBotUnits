package Unit4;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Palom")
public class Palom extends Unit{
	public Palom() {
		super("Palom", "parom");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{9676};
				break;
			case HP:
				ids = new int[]{9678};
				break;
			case S1:
				ids = new int[]{9663, 9664, 9665};
				break;
			case S2:
				ids = new int[]{9669, 9670, 9671, 9672};
				break;
			case EX:
				ids = new int[]{9674};
				break;
			case AA:
				ids = new int[]{5354};
				break;
			case LD:
				ids = new int[]{12695, 12696, 12697};
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
			case 9676:
				ret.addStaticHit("Enabled after using 「**" + super.getSpecificAbility(9663).getName() + "**」 for variable number of turns");
				break;
			case 9678:
				ret.addStaticHit("Enabled after using 「**" + super.getSpecificAbility(9669).getName() + "**」 for variable number of turns");
				break;
			case 9663: //S1
			case 9664:
			case 9665:
				ret.fixMergeAbility(9691);
				if(ret.getId() != 9665)
					ret.addStaticHit("Turns to 「**" + this.getSpecificAbility(ret.getId()+1).getName() + "**」 after use");
				ret.addStaticHit("Enables 「**" + this.getSpecificAbility(9676).getName() + "**」 for " + ret.getAilmentById(623).getDuration() + " turns");
				break;
			case 9669: //S2
			case 9670:
			case 9671:
				ret.addStaticHit("Turns to 「**" + this.getSpecificAbility(ret.getId()+1).getName() + "**」 after use");
				ret.addStaticHit("Enables 「**" + this.getSpecificAbility(9678).getName() + "**」 for " + ret.getAilmentById(1804).getDuration() + " turns");
			case 9672:
				break;
			case 9674: //EX
				ret.removeHitDataById(10118);
				ret.removeHitDataById(3633);
				ret.removeHitDataById(3634);
				ret.addStaticHit("Extends 「**" + this.getSpecificAbility(9676).getName() + "**」 duration by 3 turns");
				ret.addStaticHit("Extends 「**" + this.getSpecificAbility(9678).getName() + "**」 duration by 3 turns");
			case 12697:
			case 12696:
			case 12695:
				if(ret.getId() != 12697)
					ret.addStaticHit("Turns to 「**" + this.getSpecificAbility(ret.getId()+1).getName() + "**」 after use");
				ret.removeAilmentById(2698);
				ret.addStaticAilmentEffect(2666, "+50% HP Damage dealt next turn after breaking a target");
		}
		return ret;
	}
}