package Unit8;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Irvine")
public class Irvine extends Unit{
	public Irvine() {
		super("Irvine");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{6515};
				break;
			case HP:
				ids = new int[]{6696};
				break;
			case S1:
				ids = new int[]{6410};
				break;
			case S2:
				ids = new int[]{6412};
				break;
			case EX:
				ids = new int[]{6418};
				break;
			case AA:
				ids = new int[]{6416};
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
			case 6418:
				ret.fixMissingAuraAilment(1175, 336, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1175, 336, Ailment.EffectType.E169, Ailment.Target.Party);
		}
		return ret;
	}
}