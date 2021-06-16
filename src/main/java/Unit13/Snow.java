package Unit13;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Snow")
public class Snow extends Unit{
	public Snow() {
		super("Snow", "\"hero\"", "hero");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{7808};
				break;
			case HP:
				ids = new int[]{7809};
				break;
			case S1:
				ids = new int[]{7797};
				break;
			case S2:
				ids = new int[]{7804};
				break;
			case EX:
				ids = new int[]{7807};
				break;
			case AA:
				ids = new int[]{7048};
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
			case 7797: //S1
				Ailment aa = ret.getAilmentById(331);
				ret.removeAilmentById(331);
				ret.getDetails().getAilments().add(aa);
				int[] args = ret.getAilmentById(331).getArgs();
				ret.addStaticAilmentEffect(331, "-" + args[0] + "% BRV Damage taken");
				ret.addStaticAilmentEffect(331, "Potency increases by " + args[0] + "% up to " + args[1] + "% after taking BRV damage");
				break;
			case 7048:
				ret.fixMissingAuraAilment(1378, 450, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1378, 451, Ailment.EffectType.E7, Ailment.Target.Party);
				break;
			case 7807:
				Ailment.Aura a = ret.fixMissingAuraAilment(1095, 292, null, Ailment.Target.Same);
				a.rankData[0] = a.rankData[0].replace("-", "");
				break;
		}
		return ret;
	}
}