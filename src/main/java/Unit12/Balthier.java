package Unit12;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Balthier")
public class Balthier extends Unit{
	public Balthier() {
		super("Balthier", "balth", "leading man");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{4096};
				break;
			case HP:
				ids = new int[]{3465};
				break;
			case S1:
				ids = new int[]{4094, 4095};
				break;
			case S2:
				ids = new int[]{4099, 4100};
				break;
			case EX:
				ids = new int[]{5720};
				break;
			case AA:
				ids = new int[]{5730};
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
			case 4095:
			case 4100:
				ret.addStaticHit("Enabled when 「**" + super.getSpecificAilment(945).getName() + "**」 has 3 stacks", 0);
			case 4094:
			case 4099:
				if(ret.getDetails().getAilments().size() > 1) {
					ret.removeAilmentById(111);
					Ailment a = ret.removeAilmentById(945);
					if(ret.getAilmentById(945) == null)
						ret.getDetails().getAilments().add(a);
					ret.getAilmentById(945).setRank(-1);
					for(Ailment.EffectGrouping eg : ret.getAilmentById(945).getEffects())
						for(int i = 0; i < eg.rankData.length; i++)
							eg.rankData[i] = "400" + eg.rankData[i];
				}
				break;
			case 5720:
				ret.fixMissingAuraAilment(817, 105, Ailment.EffectType.E1, Ailment.Target.AoE);
				ret.fixMissingAuraAilment(817, 108, Ailment.EffectType.E5, Ailment.Target.AoE);
				break;
		}
		return ret;
	}
}