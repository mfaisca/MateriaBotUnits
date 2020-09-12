package Unit10;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Kimahri")
public class Kimahri extends Unit{
	public Kimahri() {
		super("Kimahri", "hornless");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{9714};
				break;
			case HP:
				ids = new int[]{9715};
				break;
			case S1:
				ids = new int[]{9570};
				break;
			case S2:
				ids = new int[]{9574};
				break;
			case EX:
				ids = new int[]{5898};
				break;
			case AA:
				ids = new int[]{5902};
				break;
			case LD:
				ids = new int[]{9577};
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
			case 9570:
				ret.fixMissingAuraAilment(1170, 331, Ailment.EffectType.E2, Ailment.Target.Party);
				break;
			case 5902:
				ret.fixMissingAuraAilment(1172, 332, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1172, 333, null, Ailment.Target.Party);
				break;
			case 9577:
				ret.getAilmentById(1783).getAuras().clear();
				ret.addStaticAilmentEffect(1783, "Party HP Regen (10% Max HP)");
				ret.addStaticAilmentEffect(1783, "**Double effects below if party member HP >= 80%**");
				ret.addStaticAilmentEffect(1783, "20% Party ATK");
				ret.addStaticAilmentEffect(1783, "20% Party Int BRV");
				ret.addStaticAilmentEffect(1783, "Party BRV Regen (30% IBRV)");
//				ret.fixMissingAuraAilment(1783, 782, Ailment.EffectType.E1, Ailment.Target.Party);
//				ret.fixMissingAuraAilment(1783, 783, Ailment.EffectType.E1, Ailment.Target.Party);
//				ret.fixMissingAuraAilment(1783, 803, Ailment.EffectType.E7, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}