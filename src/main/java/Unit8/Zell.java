package Unit8;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Zell")
public class Zell extends Unit{
	public Zell() {
		super("Zell", "chickenwuss", "chicken wuss", "chicken", "wuss");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{5161};
				break;
			case HP:
				ids = new int[]{5199};
				break;
			case S1:
				ids = new int[]{5159, 5160};
				break;
			case S2:
				ids = new int[]{5164, 5165};
				break;
			case EX:
				ids = new int[]{5168, 5169, 5170};
				break;
			case AA:
				ids = new int[]{5176};
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
			case 5168: //EX
				ret.removeHitDataById(502);
			case 5159: //S1
			case 5164: //S2
			case 5176: //AA
				ret.getAilmentById(102).removeEffect(68);
				break;
			case 5199: //HP++
			case 5160: //S1+
			case 5165: //S2+
				ret.addStaticHit("Enabled when 「**Duel**」 is active", 0);
				break;
			case 5170: //HP+EX
				if(ret.getDetails().getHits().size() == 5)
					ret.getDetails().getHits().addAll(super.getSpecificAbility(5165).getDetails().getHits());
			case 5169: //BRV+EX
				ret.getDetails().setChaseDmg(3);
				ret.addStaticHit("Enabled after using 「**Different Beat**」 for 1 use __**(shared)**__", 0);
				break;
		}
		return ret;
	}
}