package UnitCC;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Layle")
public class Layle extends Unit{
	public Layle() {
		super("Layle");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{6982};
				break;
			case HP:
				ids = new int[]{6983};
				break;
			case S1:
				ids = new int[]{6974};
				break;
			case S2:
				ids = new int[]{6977};
				break;
			case EX:
				ids = new int[]{6981};
				break;
			case AA:
				ids = new int[]{6155};
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
			case 6977: //S2
				ret.fixDelayHitData(5126);
				break;
			case 6981: //EX
				ret.fixMissingAuraAilment(929, 144, Ailment.EffectType.E1001, Ailment.Target.Party);
				ret.fixMissingAuraAilment(929, 142, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(929, 143, Ailment.EffectType.E5, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}