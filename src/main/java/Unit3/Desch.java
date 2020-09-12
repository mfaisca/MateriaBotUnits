package Unit3;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Desch")
public class Desch extends Unit{
	public Desch() {
		super("Desch");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{9004};
				break;
			case HP:
				ids = new int[]{9005};
				break;
			case S1:
				ids = new int[]{8498};
				break;
			case S2:
				ids = new int[]{8502};
				break;
			case EX:
				ids = new int[]{9003};
				break;
			case AA:
				ids = new int[]{8507};
				break;
			case LD:
				ids = new int[]{9006, 8534};
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
			case 8498:
				ret.fixMissingAuraAilment(1579, 623, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1579, 624, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1579, 625, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1579, 684, Ailment.EffectType.E5, Ailment.Target.Party);
				break;
			case 8502:
				ret.fixMissingAuraAilment(1580, 626, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1580, 627, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1580, 628, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1580, 629, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1580, 685, Ailment.EffectType.E5, Ailment.Target.Party);
				break;
			case 9006:
				ret.fixMissingAuraAilment(1585, 635, null, Ailment.Target.Party);
				break;
			case 8534:
				ret.addStaticHit("Activates only at end of enemy turn.");
				ret.addStaticHit("「**" + ret.getAilmentById(7).getName() + "**」 is only applied if 「**" + ret.getAilmentById(1797).getName() + "**」 fails.");
				break;
		}
		return ret;
	}
}