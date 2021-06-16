package Unit8;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Selphie")
public class Selphie extends Unit{
	public Selphie() {
		super("Selphie", "booyaka");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{7307};
				break;
			case HP:
				ids = new int[]{7312};
				break;
			case S1:
				ids = new int[]{7304};
				break;
			case S2:
				ids = new int[]{7306};
				break;
			case EX:
				ids = new int[]{7311};
				break;
			case AA:
				ids = new int[]{4348};
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
			case 7306:
				if(ret.getDetails().getAilments().get(0).getEffects().stream().noneMatch(a -> a.effectId == Ailment.EffectType.E58.getId())) {
					Ailment.EffectGrouping eg = new Ailment.EffectGrouping(Ailment.EffectType.E58.getId());
					String argV = ""+ret.getAilmentById(392).getArgs()[0];
					eg.rankData = new String[] {argV, argV, argV, argV, argV, argV, argV, argV, argV, argV};
					ret.getAilmentById(392).getEffects().add(0, eg);
					break;
				}
			case 7311:
				ret.getAilmentById(1444).removeEffect(85);
				break;
		}
		return ret;
	}
}