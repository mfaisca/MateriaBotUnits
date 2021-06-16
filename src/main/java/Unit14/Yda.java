package Unit14;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Yda")
public class Yda extends Unit{
	public Yda() {
		super("Yda");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{8269};
				break;
			case HP:
				ids = new int[]{8270};
				break;
			case S1:
				ids = new int[]{4623, 4624};
				break;
			case S2:
				ids = new int[]{4632};
				break;
			case EX:
				ids = new int[]{8267, 8268};
				break;
			case AA:
				ids = new int[]{4031};
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
			case 8269:
			case 8270:
				ret.removeAilmentById(1577);
				ret.addStaticHit("Free turn after breaking a target");
				break;
			case 4624: //S1+
				ret.getHitDataById(3755).getArguments()[1] = 1565;
				ret.removeHitDataById(5333);
				ret.addStaticHit("Enabled after using ?**BRV++**」 or ?**HP++**」 for 1 use", 0);
				Ailment gs = getSpecificAilment(1565);
				if(!ret.getDetails().getAilments().contains(gs)) {
					ret.getDetails().getAilments().add(gs);
					ret.addStaticAilmentEffect(1565, "**Always active even without stacks**", 0);
				}
				break;
			case 8268: //EX
				ret.addStaticHit("Enabled when ?**" + super.getSpecificAilment(1565).getName() + "**」 has 1 stack", 0);
				break;
		}
		return ret;
	}
	
	@Override
	public Ailment getSpecificAilment(int id) {
		Ailment ret = super.getSpecificAilment(id);
		switch(ret.getId()) {
			case 1565:
				ret.setName("Greased Lightning");
		}
		return ret;
	}
}