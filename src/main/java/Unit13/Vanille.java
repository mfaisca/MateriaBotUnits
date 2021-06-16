package Unit13;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Vanille")
public class Vanille extends Unit{
	public Vanille() {
		super("Vanille");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{6213};
				break;
			case HP:
				ids = new int[]{6214};
				break;
			case S1:
				ids = new int[]{6091};
				break;
			case S2:
				ids = new int[]{6094};
				break;
			case EX:
				ids = new int[]{6096, 3467};
				break;
			case AA:
				ids = new int[]{6106};
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
			case 3467:
				ret.setName(getSpecificAilment(411).getName());
				if(ret.getDetails().getHits().size() < 3)
					ret.addEffectHit(Ability.Details.Hit_Data.EffectType.E8, Ability.Details.Hit_Data.Target.ST, null, 2);
				break;
			case 6096:
				ret.fixRemoveDispels();
				ret.getAilmentById(411).removeEffect(53);
				ret.addStaticAilmentEffect(411, "Trigger ?**" + getSpecificAbility(3467).getName() + "**」 on expiration");
				break;
			case 6106: //AA
				ret.fixMissingAuraAilment(1212, 364, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1212, 365, Ailment.EffectType.E5, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}