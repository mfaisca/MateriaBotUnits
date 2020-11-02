package Unit12;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Basch")
public class Basch extends Unit{
	public Basch() {
		super("Basch");
	}

	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{9315};
				break;
			case HP:
				ids = new int[]{9316};
				break;
			case S1:
				ids = new int[]{9310};
				break;
			case S2:
				ids = new int[]{9312};
				break;
			case EX:
				ids = new int[]{5689, 5690};
				break;
			case AA:
				ids = new int[]{5694};
				break;
			case LD:
				switch(region) {
					case "GL":
						ids = new int[]{9204}; break;
					case "JP":
						ids = new int[]{12039}; break;
				}
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
			case 5690:
				ret.addStaticHit("Enabled when Current HP >= 50% Max HP", 0);
			case 5689:
				ret.fixMissingAuraAilment(1087, 276, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1087, 277, Ailment.EffectType.E5, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}