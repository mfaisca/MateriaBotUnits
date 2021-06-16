package Unit4;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Rosa")
public class Rosa extends Unit{
	public Rosa() {
		super("Rosa");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{3413};
				break;
			case HP:
				ids = new int[]{6138};
				break;
			case S1:
				ids = new int[]{5973};
				break;
			case S2:
				ids = new int[]{5975};
				break;
			case EX:
				ids = new int[]{5978};
				break;
			case AA:
				ids = new int[]{5971};
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
			case 5975: //S2
//				ret.getAilmentById(1217).removeEffect(59);
//				ret.getAilmentById(1217).getEffects()
//					.stream().filter(eg -> eg.effectId == 85).findFirst().get().val_specify = 15;
//				ret.getAilmentById(1191).removeEffect(85);
				ret.addStaticAilmentEffect(1191, "If ally HP > 80% Max HP" + System.lineSeparator() + "or ally HP < 50% Max HP", 0);
				ret.fixMissingAuraAilment(1191, 349, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1191, 350, Ailment.EffectType.E4, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1191, 351, Ailment.EffectType.E5, Ailment.Target.Party);
				ret.getAilmentById(1191).getAuras().remove(352);
				ret.getAilmentById(1191).getAuras().remove(353);
				ret.getAilmentById(1191).getAuras().remove(348);
				break;
			case 5978: //EX
				ret.fixMissingAuraAilment(1187, 346, Ailment.EffectType.E8, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1187, 347, Ailment.EffectType.E67, Ailment.Target.Party);
			case 5971: //AA
				ret.fixMissingAuraAilment(1186, 344, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1186, 345, Ailment.EffectType.E67, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}