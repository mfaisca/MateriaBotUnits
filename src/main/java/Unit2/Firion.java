package Unit2;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Firion")
public class Firion extends Unit{
	public Firion() {
		super("Firion");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				switch(region) {
					case "GL":
						ids = new int[]{6254}; break;
					case "JP":
						ids = new int[]{6254}; break;
				}
				break;
			case HP:
				switch(region) {
					case "GL":
						ids = new int[]{6255}; break;
					case "JP":
						ids = new int[]{10751}; break;
				}
				break;
			case S1:
				switch(region) {
					case "GL":
						ids = new int[]{3603, 3604}; break;
					case "JP":
						ids = new int[]{10742, 10743}; break;
				}
				break;
			case S2:
				switch(region) {
					case "GL":
						ids = new int[]{3607, 3666}; break;
					case "JP":
						ids = new int[]{10890, 10749}; break;
				}
				break;
			case EX:
				ids = new int[]{6012, 6013};
				break;
			case AA:
				ids = new int[]{6017};
				break;
			case LD:
				ids = new int[]{10353, 10354};
				break;
			case BT:
				ids = new int[]{10355};
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
			case 3604:
			case 3666:
			case 10743:
			case 10749:
				ret.addStaticHit("Enabled when HP >= 50% Max HP", 0);
				break;
			case 6013:
				ret.addStaticHit("Enabled when HP >= 80% Max HP", 0);
				break;
			case 10354:
				ret.getDetails().setChaseDmg(0);
				ret.getDetails().setMovementCost(30);
				break;
			case 10355:
				ret.addStaticAilmentEffect(1942, "Effects apply to party members when their HP is at max", 0);
				ret.fixMissingAuraAilment(1942, 874, Ailment.EffectType.E5, Ailment.Target.Same);
				ret.fixMissingAuraAilment(1942, 875, Ailment.EffectType.E4, Ailment.Target.Same);
				break;
		}
		return ret;
	}
}