package Unit6;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Shadow")
public class Shadow extends Unit{
	public Shadow() {
		super("Shadow");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{7555};
				break;
			case HP:
				ids = new int[]{7556};
				break;
			case S1:
				ids = new int[]{7547, 7551};
				break;
			case S2:
				ids = new int[]{7549, 7551};
				break;
			case EX:
				ids = new int[]{7720, 7551};
				break;
			case AA:
				ids = new int[]{7568};
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
			case 7555: //BRV
				ret.addStaticHit("Enabled after using 「**" + this.getSpecificAbility(7549).getName() + "**」 against broken target", 0);
				break;
			case 7720: //EX
				ret.getAilmentById(1457).setName("Intercept");
				ret.getAilmentById(1457).removeEffect(181);
				ret.addStaticAilmentEffect(1457, "Interceptor joins you in battle", 0);
				ret.getAilmentById(1457).getEffects().stream().filter(eg -> eg.effectId == 69).findFirst().get().val_specify = 7551;
				ret.getAilmentById(1457).getEffects().stream().filter(eg -> eg.effectId == 69).findFirst().get().val_type = 2;
				break;
			case 7551: //TriggeredAbility
				ret.addStaticHit("Triggered after using a skill or EX", 0);
				break;
		}
		return ret;
	}
}