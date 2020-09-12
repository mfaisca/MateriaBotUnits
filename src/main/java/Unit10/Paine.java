package Unit10;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Paine")
public class Paine extends Unit{
	public Paine() {
		super("Paine");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{8804};
				break;
			case HP:
				ids = new int[]{8805};
				break;
			case S1:
				ids = new int[]{4259};
				break;
			case S2:
				ids = new int[]{4263};
				break;
			case EX:
				ids = new int[]{4264, 4265};
				break;
			case AA:
				ids = new int[]{4132};
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
	//
	//ret.addStaticAilmentEffect(1923, "Each effect is unlocked depending on stacks", 0);
	@Override
	public Ability getSpecificAbility(int id){
		Ability ret = super.getSpecificAbility(id);
		switch(ret.getId()) {
			case 4259:
			case 4263:
				ret.addStaticAilmentEffect(880, "Each effect is unlocked depending on stacks", 0);
				ret.getAilmentById(880).setRank(0);
				ret.getAilmentById(880).setName(ret.getAilmentById(880).getName().replace(" (Fixed)", ""));
				ret.getAilmentById(880).getEffects().get(0).rankData = new String[] {"123"};
				ret.getAilmentById(880).getEffects().get(1).rankData = new String[] {"456"};
				ret.getAilmentById(880).getEffects().get(2).rankData = new String[] {"50"};
				ret.getAilmentById(880).getEffects().get(3).rankData = new String[] {"20"};
				ret.getAilmentById(880).getEffects().get(4).rankData = new String[] {"20"};
				break;
			case 4132:
				ret.getAilmentById(968).setRank(1);
				ret.getAilmentById(968).getEffects().get(1).rankData = new String[] {"", "20"};
				break;
			case 4265:
				ret.addStaticHit("Enabled when 「**" + this.getSpecificAilment(880).getName() + "**」 has 5 stacks", 0);
				ret.getAilmentById(918).getEffects().clear();
				ret.addStaticAilmentEffect(918, "+30% party ATK/DEF");
				ret.addStaticAilmentEffect(918, "+30% party Int BRV/Max BRV");
				ret.addStaticAilmentEffect(918, "+10% party Speed");
				ret.addStaticAilmentEffect(918, "-10% all enemies ATK/DEF/Speed");
				ret.addStaticAilmentEffect(918, "-10% all enemies Int BRV/Max BRV");
				break;
		}
		return ret;
	}
}