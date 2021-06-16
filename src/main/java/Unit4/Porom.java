package Unit4;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Porom")
public class Porom extends Unit{
	public Porom() {
		super("Porom", "polom");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{7694};
				break;
			case HP:
				ids = new int[]{7695};
				break;
			case S1:
				ids = new int[]{4195, 4196};
				break;
			case S2:
				ids = new int[]{4203, 4204};
				break;
			case EX:
				ids = new int[]{7693};
				break;
			case AA:
				ids = new int[]{4211};
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
			case 4195: //S1
			case 4196:
				ret.fixMissingAuraAilment(909, 126, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.getAilmentById(909).setRank(-1);
				break;
			case 4204:
				ret.getAilmentById(292).setTarget(Ailment.Target.Ally);
				ret.getAilmentById(79).setTarget(Ailment.Target.Ally);
			case 4203:
				ret.fixMissingAuraAilment(854, 114, Ailment.EffectType.E5, Ailment.Target.Party);
				ret.getAilmentById(854).setRank(-1);
				break;
			case 4211: //AA
				ret.fixMissingAuraAilment(962, 156, Ailment.EffectType.E1, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}