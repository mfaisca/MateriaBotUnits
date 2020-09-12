package Unit10;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Lulu")
public class Lulu extends Unit{
	public Lulu() {
		super("Lulu", "lu");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{3729};
				break;
			case HP:
				ids = new int[]{3732};
				break;
			case S1:
				ids = new int[]{3717, 3718};
				break;
			case S2:
				ids = new int[]{3725, 3726};
				break;
			case EX:
				ids = new int[]{6614, 6615};
				break;
			case AA:
				ids = new int[]{6609};
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
			case 3717:
			case 3718:
			case 3725:
			case 3726:
				if(ret.getDetails().getAilments().stream().filter(a -> a.getId() == 868).count() > 1)
					ret.removeAilmentById(868);
				ret.getAilmentById(868).setRank(-1);
				ret.getAilmentById(868).setArgs(new int[] {2});
				ret.addStaticAilmentEffect(868, "+1 stack when hitting weakness", 0);
				ret.getAilmentById(868).getEffects().add(new Ailment.EffectGrouping(60));
				ret.fixMissingAuraAilment(868, 125, Ailment.EffectType.E8, Ailment.Target.Party);
				break;
			case 6609:
				ret.fixMissingAuraAilment(1279, 411, Ailment.EffectType.E56, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1279, 412, Ailment.EffectType.E67, Ailment.Target.Party);
				break;
			case 6614:
			case 6615:
				ret.fixMissingAuraAilment(1280, 428, null, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}