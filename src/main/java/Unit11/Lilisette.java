package Unit11;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Lilisette")
public class Lilisette extends Unit{
	public Lilisette() {
		super("Lilisette", "lili");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{5504};
				break;
			case HP:
				ids = new int[]{5505};
				break;
			case S1:
				ids = new int[]{3263};
				break;
			case S2:
				ids = new int[]{3266};
				break;
			case EX:
				ids = new int[]{5510}; //5396
				break;
			case AA:
				ids = new int[]{5346};
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
			case 5346:
				Ailment.Aura a = ret.fixMissingAuraAilment(1112, 296, Ailment.EffectType.E5, Ailment.Target.Party);
				a.rankData[1] = a.rankData[0];
				ret.fixMissingAuraAilment(1112, 298, Ailment.EffectType.E67, Ailment.Target.Party);
			case 5510:
				ret.fixMissingAuraAilment(1092, 287, Ailment.EffectType.E67, Ailment.Target.Party);
				Ability part2 = this.getSpecificAbility(5396);
				if(ret.getDetails().getHits().size() == 2)
					ret.getDetails().getHits().addAll(part2.getDetails().getHits());
		}
		return ret;
	}
}