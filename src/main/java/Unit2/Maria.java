package Unit2;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Maria")
public class Maria extends Unit{
	public Maria() {
		super("Maria");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{9530};
				break;
			case HP:
				ids = new int[]{9531};
				break;
			case S1:
				ids = new int[]{9521};
				break;
			case S2:
				ids = new int[]{9523};
				break;
			case EX:
				ids = new int[]{9526}; //Missing triggeredAbility Trap
				break;
			case AA:
				ids = new int[]{6163};
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
			case 6163:
				ret.fixMissingAuraAilment(1224, 377, Ailment.EffectType.E1, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}