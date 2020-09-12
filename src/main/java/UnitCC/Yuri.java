package UnitCC;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Yuri")
public class Yuri extends Unit{
	public Yuri() {
		super("Yuri");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{2011};
				break;
			case HP:
				ids = new int[]{6645};
				break;
			case S1:
				ids = new int[]{6867};
				break;
			case S2:
				ids = new int[]{6869};
				break;
			case EX:
				ids = new int[]{6644};
				break;
			case AA:
				ids = new int[]{6669};
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
			case 6869: //S2
				ret.getAilmentById(337).removeEffect(53);
				ret.addStaticAilmentEffect(337, "Prevent negative effects from 「**" + this.getSpecificAbility(6867).getName() + "**」 and 「**" + ret.getName() + "**」");
				break;
			case 6644: //EX
				ret.fixMissingAuraAilment(1253, 432, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1253, 433, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1253, 434, Ailment.EffectType.E7, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1253, 431, Ailment.EffectType.E67, Ailment.Target.Party);
				ret.getAilmentById(1253).removeEffect(181);
				ret.addStaticAilmentEffect(1253, "Chelinka joins you in battle", 0);
				break;
		}
		return ret;
	}
}