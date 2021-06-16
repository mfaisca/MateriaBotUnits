package Unit5;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Krile")
public class Krile extends Unit{
	public Krile() {
		super("Krile", "kururu");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{11102};
				break;
			case HP:
				ids = new int[]{11326};
				break;
			case S1:
				ids = new int[]{5177, 5178};
				break;
			case S2:
				ids = new int[]{5184, 5185};
				break;
			case EX:
				ids = new int[]{5329};
				break;
			case AA:
				ids = new int[]{5194};
				break;
			case LD:
				ids = new int[]{11107, 11108};
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
			case 5177:
			case 5178:
				ret.setMastery(5177, 5178, 1);
				ret.getAilmentById(150).setTarget(Ailment.Target.Ally);
				break;
			case 5184:
			case 5185:
				ret.setMastery(5184, 5185, 1);
				break;
			case 5329:
				ret.getAilmentById(1074).setName(ret.getName().replace(" (Fixed)", ""));
				ret.removeHitDataById(6070);
				break;
			case 5194:
				ret.fixMissingAuraAilment(1077, 269, Ailment.EffectType.E67, Ailment.Target.Party);
				break;
			case 11107:
				ret.fixMissingAuraAilment(2363, 980, Ailment.EffectType.E150, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2363, 983, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2363, 984, null, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}